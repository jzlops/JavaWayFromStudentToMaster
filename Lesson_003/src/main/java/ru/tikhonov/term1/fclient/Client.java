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
    private boolean isExit = false;

    void start() throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        this.socketInputStream = socket.getInputStream();
        this.socketOutputStream = socket.getOutputStream();

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
    }

    private class InOut implements ru.tikhonov.term1.fclient.InputOutput {
        @Override
        public void transferFrom(InputStream inputStream) throws IOException {
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
                System.out.println(buffer.toString());
                buffer.setLength(0);
            }
            inputStreamReadPause = true;
        }

        @Override
        public void transferTo(OutputStream outputStream) throws IOException {
            PrintWriter out = new PrintWriter(outputStream, true);
            Scanner scanner = new Scanner(System.in);
            StringBuilder buffer = new StringBuilder();
            if (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
                out.println(buffer.toString());
                if (buffer.toString().toLowerCase().equals("quit")) {
                    isExit = true;
                }
            }
            inputStreamReadPause = false;
        }
    }

    private class fileDownloadUpload implements ru.tikhonov.term1.fclient.InputOutput {
        @Override
        public void transferFrom(InputStream inputStream) throws IOException {
            File dstFile = new File(currentDir + "/" + downloadFileName);
            int BUFFER_SIZE = 64 * 1024;
            if ((dstFile.exists()) && (dstFile.isFile())) {
                dstFile.delete();
            }
            dstFile.createNewFile();

            BufferedOutputStream buffFileOut = new BufferedOutputStream(new FileOutputStream(dstFile));
            DataInputStream dataInputStreamFromServer = new DataInputStream(inputStream);

            byte[] b = new byte[8];
            byte[] chunk = new byte[BUFFER_SIZE];
            long supposeFileLength;
            long iterCount;
            int enumerator;
            long lastBuffer;
            dataInputStreamFromServer.read(b, 0, 8);
            supposeFileLength = byteArrayToLong(b, 0);
            System.out.printf("File length is %d bytes %n", supposeFileLength);
            iterCount = supposeFileLength / BUFFER_SIZE;
            lastBuffer = supposeFileLength % BUFFER_SIZE;
            while (iterCount >= 0) {
                if ((iterCount > 0) && (dataInputStreamFromServer.available() >= BUFFER_SIZE)) {
                    enumerator = dataInputStreamFromServer.read(chunk);
                    buffFileOut.write(chunk, 0, enumerator);
                    System.out.printf("Bytes read per iteration from input socket %d%n", enumerator);
                    iterCount--;
                    continue;
                }
                if ((iterCount == 0) && (dataInputStreamFromServer.available() == lastBuffer)) {
                    enumerator = dataInputStreamFromServer.read(chunk);
                    buffFileOut.write(chunk, 0, enumerator);
                    System.out.printf("Bytes read per iteration from input socket %d%n", enumerator);
                    iterCount--;
                    continue;
                }
            }
            buffFileOut.flush();
            buffFileOut.close();
            System.out.printf("Bytes remain in input socket buffer is %d%n", dataInputStreamFromServer.available());
            System.out.printf("Download file is finished %n");
        }

        @Override
        public void transferTo(OutputStream outputStream) throws IOException {
        }

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

