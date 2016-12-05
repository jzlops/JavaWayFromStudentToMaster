package ru.tikhonov.term1.fserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс консольного сервера
 *
 * @author Sergey Tikhonov
 */
class Server {
    private ServerFileOperations[] fileEngines = new ServerFileOperations[10];
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private BufferedReader socketBufferedReader;
    private PrintWriter socketPrintWriter;
    private boolean isExit = false;
    private String parentDir = ".";
    private String currentDir = ".";
    private String[] tailsDir = new String[1000];
    private int tailsIndex = 0;

    /**
     * Создаем сервер
     *
     * @throws IOException
     */
    void start(String parentDir) throws IOException {
        this.start();
        this.parentDir = parentDir;
        this.currentDir = parentDir;
        tailsDir[0] = "";
    }

    void start() {
        System.out.printf("Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(9999);
             Socket workSocket = serverSocket.accept();
             OutputStream socketOutputStream = workSocket.getOutputStream();
             InputStream socketInputStream = workSocket.getInputStream();
             BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socketInputStream));
             PrintWriter socketPrintWriter = new PrintWriter(socketOutputStream, true)) {

            this.socketOutputStream = socketOutputStream;
            this.socketInputStream = socketInputStream;
            this.socketBufferedReader = socketBufferedReader;
            this.socketPrintWriter = socketPrintWriter;

            this.fileEngines[0] = this.new DownloadServerFile();
            this.fileEngines[1] = this.new UploadServerFile();
            this.fileEngines[2] = this.new ListDir();
            this.fileEngines[3] = this.new ChangeDir();
            this.fileEngines[4] = this.new GoToParent();

            /*выволдим приветсвие*/
            showGreetings();
            /*посылаем комманду СТОП на клиент*/
            sayStop();

            String s;
            while (!this.isExit) {
                if ((s = this.socketBufferedReader.readLine()) != null) {
                    this.commandParserAndRunner(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Сказать клиенту СТОП
     */
    private void sayStop() {
        this.socketPrintWriter.println("#STOP");
    }

    /**
     * Сказать клиенту ОШИБКА
     */
    private void sayFailed() {
        this.socketPrintWriter.println("#FAILED");
    }

    /**
     * Сказать клиенту ЗАГРУЗКА НАЧАТА
     */
    private void sayDownload() {
        this.socketPrintWriter.println("#DOWNLOAD_START");
    }

    /**
     * Сказать клиенту ВЫГРУЗКА НАЧАТА
     */
    private void sayUpload() {
        this.socketPrintWriter.println("#UPLOAD_START");
    }

    /**
     * Основной парсер комманд на стороне серера (необходимо перенести большую часть фунционаа на клиент
     *
     * @param command комманада для парсинга данных
     * @throws IOException
     */
    private void commandParserAndRunner(String command) {
        StringBuilder commandBuffer = new StringBuilder();
        Scanner scanner = new Scanner(command.toLowerCase());
        while (scanner.hasNext()) {
            commandBuffer.append(scanner.next());

            if (commandBuffer.toString().equals("help")) {
                showHelp();
                sayStop();
                break;
            }
            if (commandBuffer.toString().equals("quit")) {
                isExit = true;
                sayStop();
                break;
            }
            if (commandBuffer.toString().equals("download")) {
                if (fileEngines[0].execOperation(command, this.socketOutputStream, null)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("upload")) {
                if (fileEngines[1].execOperation(command, this.socketOutputStream, this.socketInputStream)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("ls")) {
                if (fileEngines[2].execOperation(command, this.socketOutputStream, null)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("cd")) {
                if (fileEngines[3].execOperation(command, this.socketOutputStream, null)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("..")) {
                if (fileEngines[4].execOperation(command, this.socketOutputStream, null)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            sayFailed();
        }
        commandBuffer.setLength(0);
        scanner.close();
    }

    /**
     * Выкнуть в поток клиенут информацию по коммандам
     *
     * @throws IOException
     */
    private void showHelp() {
        this.socketPrintWriter.println(" Type <cd> <directory> - to change current directory to new <directory>  ");
        this.socketPrintWriter.println(" Type <ls> - to show list of files and directories in the current directory ");
        this.socketPrintWriter.println(" Type <..> - to back the parent directory ");
        this.socketPrintWriter.println(" Type <download> <srcFile> - to copy the source file form server to  ");
        this.socketPrintWriter.println("   destination file on the local computer (existed file will be overwrite) ");
        this.socketPrintWriter.println(" Type <upload> <srcFile> - to copy source file form the client computer to  ");
        this.socketPrintWriter.println("   destination folder on the server ");
        this.socketPrintWriter.println("");
    }

    /**
     * Выкнуть в поток клиенут приветсвие
     *
     * @throws IOException
     */

    private void showGreetings() {
        this.socketPrintWriter.println(" <============= Greetings User =============> ");
        this.socketPrintWriter.println(" Type <help> - to showHelp on supported commands");
        this.socketPrintWriter.println(" Type <quit> - to disconnect from server ");
    }

    private class ListDir implements ServerFileOperations {
        /**
         * Выводит клиенту список файлов и директорий
         *
         * @param commandName           комманда
         * @param outStreamToClient     выходной сетевой поток
         * @param inputStreamFromClient входной сетевой поток
         * @return результат выполнения
         * @throws IOException
         */
        @Override
        public boolean execOperation(String commandName, OutputStream outStreamToClient, InputStream inputStreamFromClient) {
            PrintWriter outToClient = new PrintWriter(outStreamToClient, true);
            File listedDir = new File(currentDir);
            String[] list = listedDir.list();
            if (list != null) {
                outToClient.println("#OK");
                for (String s : list) {
                    outToClient.println(String.format("/%s", s));
                }
                outToClient.println("");
                return true;
            } else {
                return false;
            }
        }
    }

    private class ChangeDir implements ServerFileOperations {
        @Override
        /**
         * Спускаемся вниз по деррикториям
         *
         * @param commandName           комманда
         * @param outStreamToClient     выходной сетевой поток
         * @param inputStreamFromClient входной сетевой поток
         * @return результат выполнения
         * @throws IOException
         */
        public boolean execOperation(String commandName, OutputStream outStreamToClient, InputStream inputStreamFromClient) {
            PrintWriter outToClient = new PrintWriter(outStreamToClient, true);
            String tmpTailDir;
            File checkedDir;
            StringBuilder tmpBuffer = new StringBuilder();
            Scanner scanner = new Scanner(commandName.toLowerCase());
            scanner.next();
            if (scanner.hasNext()) {
                tmpTailDir = String.format("%s%s", "/", scanner.next());
                if ((tmpTailDir.equals("/.")) || (tmpTailDir.contains("//")) || (tmpTailDir.contains("..") || (tmpTailDir.contains("\\")))) {
                    return false;
                }
                tmpBuffer.append(currentDir).append(tmpTailDir);
                checkedDir = new File(tmpBuffer.toString());
                if ((checkedDir.isDirectory()) && (checkedDir.exists())) {
                    currentDir = tmpBuffer.toString();
                    outToClient.println("#OK");
                    outToClient.println(currentDir);
                    outToClient.flush();
                    tailsDir[tailsIndex] = tmpTailDir;
                    tailsIndex++;
                    return true;
                }
            }
            return false;
        }
    }

    private class GoToParent implements ServerFileOperations {
        @Override
        /**
         * Поднимаемся вверх по директориям
         *
         * @param commandName           комманда
         * @param outStreamToClient     выходной сетевой поток
         * @param inputStreamFromClient входной сетевой поток
         * @return результат выполнения
         * @throws IOException
         */
        public boolean execOperation(String commandName, OutputStream outStreamToClient, InputStream inputStreamFromClient) {
            PrintWriter outToClient = new PrintWriter(outStreamToClient, true);
            if (!currentDir.equals(parentDir)) {
                tailsIndex--;
                currentDir = currentDir.substring(0, currentDir.length() - tailsDir[tailsIndex].length());
                outToClient.println("#OK");
                outToClient.println(currentDir);
                outToClient.println("");
                return true;
            } else {
                return false;
            }
        }
    }

    private class DownloadServerFile implements ServerFileOperations {
        @Override
        /**
         * Закгржаем файл на клиента
         *
         * @param commandName           комманда
         * @param outStreamToClient     выходной сетевой поток
         * @param inputStreamFromClient входной сетевой поток
         * @return результат выполнения
         * @throws IOException
         */
        public boolean execOperation(String commandName, OutputStream outStreamToClient, InputStream inputStreamFromClient) {
            int BUFFER_SIZE = 64 * 1024;
            String fileNameToClient;
            Scanner scanner = new Scanner(commandName.toLowerCase());
            StringBuilder buffer = new StringBuilder();
            File fileToClient;
            scanner.next();
            if (scanner.hasNext()) {
                fileNameToClient = scanner.next();
                if (fileNameToClient.length() == 0) {
                    return false;
                }
                buffer.append(currentDir).append("/").append(fileNameToClient);
                fileToClient = new File(buffer.toString());

                if ((fileToClient.exists()) && (fileToClient.isFile())) {

                    /*После всевозможного парсинга - говорим клиенту что начанаем загрузку*/
                    try (BufferedInputStream bufferedFileStream = new BufferedInputStream(new FileInputStream(fileToClient), BUFFER_SIZE)) {
                        PrintWriter outToClientMessage = new PrintWriter(outStreamToClient, true);
                        DataOutputStream dataOutStreamToClient = new DataOutputStream(outStreamToClient);

                        /*Говорим клиенту чтоб готовился к загрузке файла*/
                        sayDownload();
                        outToClientMessage.println(fileNameToClient);

                        long supposeFileLength = fileToClient.length();
                        int enumerator;
                        byte[] chunk = new byte[BUFFER_SIZE];
                        long iterationCount;
                        long lastBuffer;

                        System.out.printf("%nFile Length for download is %d bytes %n", supposeFileLength);
                        dataOutStreamToClient.write(longToByteArray(supposeFileLength));
                        dataOutStreamToClient.flush();

                        /* Вычисляем количество итераций и размер остаточного буфера*/
                        iterationCount = supposeFileLength / BUFFER_SIZE;
                        lastBuffer = supposeFileLength % BUFFER_SIZE;

                        /*Читаем входной поток от файла кусками BUFFER_SIZE и остатком от буфера на последне итерции и передаем в исходящий сетевой поток
                        * если в файловом потоке нет данных, ждем его заполнения*/
                        while (iterationCount >= 0) {
                            if ((iterationCount > 0) && (bufferedFileStream.available() >= BUFFER_SIZE)) {
                                enumerator = bufferedFileStream.read(chunk);
                                dataOutStreamToClient.write(chunk, 0, BUFFER_SIZE);
                                dataOutStreamToClient.flush();
                                System.out.printf("Bytes read  per iteration from input socket %d%n", enumerator);
                                iterationCount--;
                                continue;
                            }
                            if ((iterationCount == 0) && (bufferedFileStream.available() == lastBuffer)) {
                                enumerator = bufferedFileStream.read(chunk);
                                dataOutStreamToClient.write(chunk, 0, (int) lastBuffer);
                                dataOutStreamToClient.flush();
                                System.out.printf("Bytes read per iteration from input  socket %d%n", enumerator);
                                iterationCount--;
                            }
                        }
                        System.out.printf("Download file finished, number of bytes is transmitted to client is %d%n", supposeFileLength);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
            return true;
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
    }

    private class UploadServerFile implements ServerFileOperations {
        @Override
        /**
         * Скачиваем файл с клиента
         *
         * @param commandName           комманда
         * @param outStreamToClient     выходной сетевой поток
         * @param inputStreamFromClient входной сетевой поток
         * @return результат выполнения
         * @throws IOException
         */
        public boolean execOperation(String commandName, OutputStream outStreamToClient, InputStream inputStreamFromClient) {
            int BUFFER_SIZE = 64 * 1024;
            String fileNameFromClient;
            PrintWriter outToClientMessage = new PrintWriter(outStreamToClient, true);
            Scanner scanner = new Scanner(commandName.toLowerCase());
            StringBuilder buffer = new StringBuilder();
            File fileFromClient;
            scanner.next();
            if (scanner.hasNext()) {
                fileNameFromClient = scanner.next();
                if (fileNameFromClient.length() == 0) {
                    return false;
                }
                buffer.append(currentDir).append("/").append(fileNameFromClient);
                fileFromClient = new File(buffer.toString());

                if ((fileFromClient.exists()) && (fileFromClient.isDirectory())) {
                    return false;
                }
                if ((fileFromClient.exists()) && (fileFromClient.isFile())) {
                    fileFromClient.delete();
                }

                try {
                    fileFromClient.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*После всевозможного парсинга - говорим клиенту что начанаем выгрузку*/
                try (BufferedOutputStream bufferedFileStream = new BufferedOutputStream(new FileOutputStream(fileFromClient), BUFFER_SIZE)) {

                    /*Говорим клиенту чтоб готовился к выгрузке файла*/
                    sayUpload();
                    outToClientMessage.println(fileNameFromClient);

                    DataInputStream dataInStreamFromClient = new DataInputStream(inputStreamFromClient);

                    byte[] b = new byte[8];
                    byte[] chunk = new byte[BUFFER_SIZE];
                    long supposeFileLength;
                    long iterationCount;
                    int enumerator;
                    long lastBuffer;

                    dataInStreamFromClient.read(b, 0, 8);
                    supposeFileLength = byteArrayToLong(b, 0);
                    System.out.printf("File length is %d bytes %n", supposeFileLength);

                    /* Вычисляем количество итераций и размер остаточного буфера*/
                    iterationCount = supposeFileLength / BUFFER_SIZE;
                    lastBuffer = supposeFileLength % BUFFER_SIZE;

                    /*Читаем входной сетевой поток кусками BUFFER_SIZE и остатком от буфера на последне итерции и пишем все в фаловый поток
                     * если в сетевом потоке нет данных, ждем его заполнения*/
                    while (iterationCount >= 0) {
                        if ((iterationCount > 0) && (dataInStreamFromClient.available() >= BUFFER_SIZE)) {
                            enumerator = dataInStreamFromClient.read(chunk);
                            bufferedFileStream.write(chunk, 0, BUFFER_SIZE);
                            bufferedFileStream.flush();
                            System.out.printf("Bytes read per  iteration from input socket %d%n", enumerator);
                            iterationCount--;
                            continue;
                        }
                        if ((iterationCount == 0) && (dataInStreamFromClient.available() == lastBuffer)) {
                            enumerator = dataInStreamFromClient.read(chunk);
                            bufferedFileStream.write(chunk, 0, (int) lastBuffer);
                            bufferedFileStream.flush();
                            System.out.printf("Bytes read  per iteration from input socket %d%n", enumerator);
                            iterationCount--;
                        }
                    }
                    bufferedFileStream.flush();
                    bufferedFileStream.close();
                    System.out.printf("Bytes remain in input socket buffer is %d%n", dataInStreamFromClient.available());
                    System.out.printf("Upload file is finished %n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return false;
            }
            return true;
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
