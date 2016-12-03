package ru.tikhonov.term1.fserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс сервера
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
     * Создаем сервер и сокет к нему на порту 9999
     *
     * @throws IOException
     */
    void start(String parentDir) throws IOException {
        this.parentDir = parentDir;
        this.currentDir = parentDir;
        tailsDir[0] = "";
        this.start();
    }

    void start() throws IOException {
        System.out.printf("Server is running...");
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket workSocket = serverSocket.accept();

        this.socketOutputStream = workSocket.getOutputStream();
        this.socketInputStream = workSocket.getInputStream();
        this.socketBufferedReader = new BufferedReader(new InputStreamReader(this.socketInputStream));
        this.socketPrintWriter = new PrintWriter(this.socketOutputStream, true);

        this.fileEngines[0] = this.new DownloadServerFile();
        this.fileEngines[1] = this.new UploadServerFile();
        this.fileEngines[2] = this.new ListDir();
        this.fileEngines[3] = this.new ChangeDir();
        this.fileEngines[4] = this.new GoToParent();

        showGreetings();
        sayStop();

        String s;
        while (!this.isExit) {
            if ((s = this.socketBufferedReader.readLine()) != null) {
                this.commandParserAndRunner(s);
            }
        }
        this.socketOutputStream.close();
        this.socketInputStream.close();

    }

    private void sayStop() {
        this.socketPrintWriter.println("#STOP");
    }

    private void sayFailed() {
        this.socketPrintWriter.println("#FAILED");
    }

    private void sayDownload() {
        this.socketPrintWriter.println("#DOWNLOAD_START");
    }

    private void commandParserAndRunner(String command) throws IOException {
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
                if (fileEngines[0].execOperation(command, this.socketOutputStream)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("upload")) {
                if (fileEngines[1].execOperation(command, this.socketOutputStream)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("ls")) {
                if (fileEngines[2].execOperation(command, this.socketOutputStream)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("cd")) {
                if (fileEngines[3].execOperation(command, this.socketOutputStream)) {
                    sayStop();
                } else {
                    sayFailed();
                }
                break;
            }
            if (commandBuffer.toString().equals("..")) {
                if (fileEngines[4].execOperation(command, this.socketOutputStream)) {
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

    private void showHelp() throws IOException {
        this.socketPrintWriter.println(" Type <cd> <directory> - to change current directory to new <directory>  ");
        this.socketPrintWriter.println(" Type <ls> - to show list of files and directories in the current directory ");
        this.socketPrintWriter.println(" Type <..> - to back the parent directory ");
        this.socketPrintWriter.println(" Type <download> <srcFile> - to copy the source file form server to  ");
        this.socketPrintWriter.println("   destination file on the local computer (existed file will be overwrite) ");
        this.socketPrintWriter.println(" Type <upload> <srcFile> - to copy source file form the client computer to  ");
        this.socketPrintWriter.println("   destination folder on the server ");
        this.socketPrintWriter.println("");
    }

    private void showGreetings() throws IOException {
        this.socketPrintWriter.println(" <============= Greetings User =============> ");
        this.socketPrintWriter.println(" Type <help> - to showHelp on supported commands");
        this.socketPrintWriter.println(" Type <quit> - to disconnect from server ");
    }

    private class ListDir implements ServerFileOperations {
        @Override
        public boolean execOperation(String commandName, OutputStream outStreamToClient) throws IOException {
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
        public boolean execOperation(String commandName, OutputStream outStreamToClient) throws IOException {
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

    private class DownloadServerFile implements ServerFileOperations {
        @Override
        public boolean execOperation(String commandName, OutputStream outStreamToClient) throws IOException {
            int BUFFER_SIZE = 64 * 1024;
            String downloadedFileName;
            PrintWriter outToClientMessage = new PrintWriter(outStreamToClient, true);
            Scanner scanner = new Scanner(commandName.toLowerCase());
            StringBuilder buffer = new StringBuilder();
            File sourceFile;
            scanner.next();
            if (scanner.hasNext()) {
                downloadedFileName = scanner.next();
                if (downloadedFileName.length() == 0) {
                    return false;
                }
                buffer.append(currentDir).append("/").append(downloadedFileName);
                sourceFile = new File(buffer.toString());

                if ((sourceFile.exists()) && (sourceFile.isFile())) {
                    sayDownload();
                    outToClientMessage.println(downloadedFileName);

                    BufferedInputStream buffFileIn = new BufferedInputStream(new FileInputStream(sourceFile), BUFFER_SIZE);
                    DataOutputStream dataOutStreamToClient = new DataOutputStream(outStreamToClient);

                    long fileLength = sourceFile.length();
                    int enumerator;
                    byte[] chunk = new byte[BUFFER_SIZE];

                    System.out.printf("%nFile Length for download is %d bytes %n", fileLength);
                    dataOutStreamToClient.write(longToByteArray(fileLength));
                    System.out.println(Arrays.toString(longToByteArray(fileLength)));
                    dataOutStreamToClient.flush();

                    while ((enumerator = buffFileIn.read(chunk)) != -1) {
                        dataOutStreamToClient.write(chunk, 0, enumerator);
                        System.out.printf("Bytes read per iteration is %d%n", enumerator);
                        dataOutStreamToClient.flush();
                    }
                    System.out.printf("Download file finished, number of bytes is transmitted to client is %d%n", fileLength);
                    buffFileIn.close();
                } else {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

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
        public boolean execOperation(String commandName, OutputStream outMessage) {
            return false;
        }

    }

    private class GoToParent implements ServerFileOperations {
        @Override
        public boolean execOperation(String commandName, OutputStream outStreamToClient) throws IOException {
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
}




