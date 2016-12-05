package ru.tikhonov.term1.fclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Sergey Tikhonov
 */
class Client {
    private ru.tikhonov.term1.fclient.InputOutput[] inOut = new ru.tikhonov.term1.fclient.InputOutput[10];
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private boolean inputStreamReadPause = false;
    private String currentDir = ".";
    private String downloadFileName;
    private String uploadFileName;
    private boolean isExit = false;
    private Scanner consoleScanner;

    /**
     * Запускаем клиента
     *
     * @throws IOException
     */
    void start() {

        try (Socket socket = new Socket("127.0.0.1", 9999);
             InputStream socketInputStream = socket.getInputStream();
             OutputStream socketOutputStream = socket.getOutputStream();
             Scanner consoleScanner = new Scanner(System.in)) {

            this.consoleScanner = consoleScanner;
            this.socketInputStream = socketInputStream;
            this.socketOutputStream = socketOutputStream;


            this.inOut[0] = new fileDownloadUpload();
            this.inOut[1] = new InOut();

            while (!isExit) {
                if (!this.inputStreamReadPause) {
                    this.inOut[1].transferFrom(this.socketInputStream);
                }
                if (this.inputStreamReadPause) {
                    this.inOut[1].transferTo(this.socketOutputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class InOut implements ru.tikhonov.term1.fclient.InputOutput {
        /**
         * Метод для считывания ответов от сервера
         *
         * @param inputStream входящий поток на клиенте
         * @throws IOException
         */
        @Override
        public void transferFrom(InputStream inputStream) {
            Scanner scanner = new Scanner(inputStream);
            StringBuilder buffer = new StringBuilder();

            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
                if (buffer.toString().equals("#STOP")) {
                    inputStreamReadPause = true;
                    return;
                }
                if (buffer.toString().equals("#FAILED")) {
                    inputStreamReadPause = true;
                    System.out.println(buffer.toString());
                    return;
                }
                if ((buffer.toString().equals("#DOWNLOAD_START")) && (scanner.hasNextLine())) {
                    downloadFileName = scanner.nextLine();
                    inOut[0].transferFrom(socketInputStream);
                    return;
                }
                if ((buffer.toString().equals("#UPLOAD_START")) && (scanner.hasNextLine())) {
                    uploadFileName = scanner.nextLine();
                    inOut[0].transferTo(socketOutputStream);
                    return;
                }
                /* вывод на консоль данных с сервера */
                System.out.println(buffer.toString());
                buffer.setLength(0);
            }
            inputStreamReadPause = true;
        }

        /**
         * Метод для считывания комманд с консоли от пользователя и передача их серверу
         * Осуществляет частичный арсинг комманд, часть комманд парстится на сервере (что плохо очень, передалю потом)
         *
         * @param outputStream исходящий поток клиента
         * @throws IOException
         */
        @Override
        public void transferTo(OutputStream outputStream) {
            PrintWriter out = new PrintWriter(outputStream, true);
            StringBuilder buffer = new StringBuilder();

            if (consoleScanner.hasNextLine()) {
                buffer.append(consoleScanner.nextLine());

                if ((buffer.toString().toLowerCase().startsWith("upload")) && (buffer.toString().length()) > "upload".length()) {
                    if (!fileExistCheck(buffer.toString())) {
                        System.out.println("#FAILED!");
                        return;
                    }
                }
                if ((buffer.toString().toLowerCase().startsWith("download")) && (buffer.toString().length()) > "download".length()) {
                    if (!fileIsNotDirCheck(buffer.toString())) {
                        System.out.println("#FAILED!");
                        return;
                    }
                }
                if (buffer.toString().toLowerCase().equals("download")) {
                    System.out.println("#FAILED!");
                    return;
                }
                if (buffer.toString().toLowerCase().equals("upload")) {
                    System.out.println("#FAILED!");
                    return;
                }
                if (buffer.toString().toLowerCase().equals("quit")) {
                    isExit = true;
                    out.println(buffer.toString());
                    return;
                }
                if (!buffer.toString().equals("")) {
                    out.println(buffer.toString());
                    inputStreamReadPause = false;
                }
            }
        }

        /**
         * Парсим комманды работы с файлами и проверяем что это - не дирректория
         *
         * @param command комманада для проверки
         * @return
         */
        private boolean fileIsNotDirCheck(String command) {
            Scanner scanner = new Scanner(command.toLowerCase());
            File chckdFile;
            String fileName;
            scanner.next();
            if (scanner.hasNext()) {
                fileName = scanner.next();
                if (fileName.length() == 0) {
                    return false;
                }
                chckdFile = new File(String.format("%s%s%s", currentDir, "/", fileName));
                if (chckdFile.isDirectory()) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

        private boolean fileExistCheck(String command) {
            Scanner scanner = new Scanner(command.toLowerCase());
            File checkedFile;
            String fileName;
            scanner.next();
            if (scanner.hasNext()) {
                fileName = scanner.next();
                if (fileName.length() == 0) {
                    return false;
                }
                checkedFile = new File(String.format("%s%s%s", currentDir, "/", fileName));
                if (checkedFile.isDirectory()) {
                    return false;
                }
                if (!checkedFile.exists()) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }
    }

    private class fileDownloadUpload implements InputOutput {
        /**
         * Метод для скачивания файла с сервера
         *
         * @param inputStream входящий сетевой поток на клиенте
         * @throws IOException
         */
        @Override
        public void transferFrom(InputStream inputStream) {
            try {
                File fileFromServer = new File(currentDir + "/" + downloadFileName);
                if ((fileFromServer.exists()) && (fileFromServer.isFile())) {
                    fileFromServer.delete();
                }
                fileFromServer.createNewFile();

                try (BufferedOutputStream bufferedFileStream = new BufferedOutputStream(new FileOutputStream(fileFromServer))) {

                    DataInputStream dataInputStreamFromServer = new DataInputStream(inputStream);
                    int BUFFER_SIZE = 64 * 1024;
                    byte[] b = new byte[8];
                    byte[] chunk = new byte[BUFFER_SIZE];
                    long supposeFileLength;
                    long iterationCount;
                    int enumerator;
                    long lastBuffer;

                    /*читаем массив из 8 байтов представляющий длинну скачиваемого файла
                     * конвертируем массив в число long */
                    dataInputStreamFromServer.read(b, 0, 8);
                    supposeFileLength = byteArrayToLong(b, 0);
                    System.out.printf("File length is %d bytes %n", supposeFileLength);
                    iterationCount = supposeFileLength / BUFFER_SIZE;
                    lastBuffer = supposeFileLength % BUFFER_SIZE;

                     /*Читаем входной поток на клиенте кусками BUFFER_SIZE и остатком от буфера на последне итерции
                       * если в потоке нет данных, ждем его заполнения*/
                    while (iterationCount >= 0) {
                        if ((iterationCount > 0) && (dataInputStreamFromServer.available() >= BUFFER_SIZE)) {
                            enumerator = dataInputStreamFromServer.read(chunk);
                            bufferedFileStream.write(chunk, 0, BUFFER_SIZE);
                            bufferedFileStream.flush();
                            System.out.printf("Bytes read per iteration from input socket %d%n", enumerator);
                            iterationCount--;
                            continue;
                        }
                        if ((iterationCount == 0) && (dataInputStreamFromServer.available() == lastBuffer)) {
                            enumerator = dataInputStreamFromServer.read(chunk);
                            bufferedFileStream.write(chunk, 0, (int) lastBuffer);
                            bufferedFileStream.flush();
                            System.out.printf("Bytes read per iteration from input socket %d%n", enumerator);
                            iterationCount--;
                        }
                    }
                    System.out.printf("Bytes remain in input socket buffer is %d%n", dataInputStreamFromServer.available());
                    System.out.printf("Download file is finished %n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Загрузка файла на сервер
         *
         * @param outputStreamToServer выходной сетевой поток клиента
         * @throws IOException
         */
        @Override
        public void transferTo(OutputStream outputStreamToServer) {
            try {
                File sourceFile = new File(currentDir + "/" + uploadFileName);
                int BUFFER_SIZE = 64 * 1024;

                try (BufferedInputStream bufferedFileStream = new BufferedInputStream(new FileInputStream(sourceFile), BUFFER_SIZE)) {

                    DataOutputStream dataOutputStreamToServer = new DataOutputStream(outputStreamToServer);
                    long supposeFileLength = sourceFile.length();
                    int enumerator;
                    byte[] chunk = new byte[BUFFER_SIZE];
                    long iterationCount = supposeFileLength / BUFFER_SIZE;
                    long lastBuffer = supposeFileLength % BUFFER_SIZE;

                    System.out.printf("%nFile Length for upload is %d bytes %n", supposeFileLength);

                   /*сообщаем серверу длинну выгружаемого файла в виде массива из 8 байтов*/
                    dataOutputStreamToServer.write(longToByteArray(supposeFileLength));
                    dataOutputStreamToServer.flush();

                   /* Выгрузка файла кусками BUFFER_SIZE в выхдной поток на клиенте и остаток от буфера на последней итерации*/
                  /* если в потоке нет данных, ждем его заполнения*/
                    while (iterationCount >= 0) {
                        if ((iterationCount > 0) && (bufferedFileStream.available() >= BUFFER_SIZE)) {
                            enumerator = bufferedFileStream.read(chunk);
                            dataOutputStreamToServer.write(chunk, 0, BUFFER_SIZE);
                            System.out.printf("Bytes read per iteration is %d%n", enumerator);
                            dataOutputStreamToServer.flush();
                            iterationCount--;
                            continue;
                        }
                        if ((iterationCount == 0) && (bufferedFileStream.available() == lastBuffer)) {
                            enumerator = bufferedFileStream.read(chunk);
                            dataOutputStreamToServer.write(chunk, 0, (int) lastBuffer);
                            System.out.printf("Bytes read per iteration is %d%n", enumerator);
                            dataOutputStreamToServer.flush();
                            iterationCount--;
                        }
                    }
                    System.out.printf("Upload file finished, number of bytes is transmitted to Server is %d%n", supposeFileLength);
                    bufferedFileStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Конвертер long -> byte[8]
         *
         * @param value значние long
         * @return
         */
        byte[] longToByteArray(long value) {
            return new byte[]
                    {
                            (byte) (value >>> 56),
                            (byte) (value >>> 48),
                            (byte) (value >>> 40),
                            (byte) (value >>> 32),
                            (byte) (value >>> 24),
                            (byte) (value >>> 16),
                            (byte) (value >>> 8),
                            (byte) value
                    };
        }

        /**
         * Конвертор byte[]->long
         *
         * @param buffer массив byte
         * @param offset смещеение
         * @return
         */
        long byteArrayToLong(byte[] buffer, int offset) {
            return (buffer[offset] << 56)
                    + ((buffer[offset + 1] & 0xFF) << 48)
                    + ((buffer[offset + 2] & 0xFF) << 40)
                    + ((buffer[offset + 3] & 0xFF) << 32)
                    + ((buffer[offset + 4] & 0xFF) << 24)
                    + ((buffer[offset + 5] & 0xFF) << 16)
                    + ((buffer[offset + 6] & 0xFF) << 8)
                    + (buffer[offset + 7] & 0xFF);
        }
    }
}

