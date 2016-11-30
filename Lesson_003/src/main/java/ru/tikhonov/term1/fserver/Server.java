package ru.tikhonov.term1.fserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс сервера
 *
 * @author Sergey Tikhonov
 */
class Server implements InputOutput {
    private FileOperations[] fileEngines = new FileOperations[10];
    private DataInputStream socketInputStream;
    private DataOutputStream socketOutputStream;
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
        Scanner scannerFromInputStream;
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket workSocket = serverSocket.accept();


        this.socketInputStream = new DataInputStream(workSocket.getInputStream());
        this.socketOutputStream = new DataOutputStream(workSocket.getOutputStream());

        this.fileEngines[0] = this.new DownloadFile();
        this.fileEngines[1] = this.new UploadFile();
        this.fileEngines[2] = this.new ListDir();
        this.fileEngines[3] = this.new ChangeDir();
        this.fileEngines[4] = this.new GoToParent();

        scannerFromInputStream = new Scanner(this.socketInputStream);

        showGreetings();

//        while (!this.isExit) {
//            if (scannerFromInputStream.hasNextLine()) {
//           //     commandParserAndRunner(scannerFromInputStream.nextLine());
//            }
//        }
        scannerFromInputStream.close();
        this.socketOutputStream.close();
        this.socketInputStream.close();
    }


    private void commandParserAndRunner(final String command) throws IOException {
        StringBuilder commandBuffer = new StringBuilder();
        Scanner scanner = new Scanner(command.toLowerCase());
        while (scanner.hasNext()) {
            commandBuffer.append(scanner.next());
            if (commandBuffer.toString().equals("help")) {
                showHelp();
                scanner.close();
                break;
            }
            if (commandBuffer.toString().equals("stop")) {
                isExit = true;
                break;
            }
            if (commandBuffer.toString().equals("download")) {
                fileEngines[0].execOperation(command, this.socketOutputStream);
                commandBuffer.setLength(0);
                break;
            }
            if (commandBuffer.toString().equals("upload")) {
                fileEngines[1].execOperation(command, this.socketOutputStream);
                commandBuffer.setLength(0);
                break;

            }
            if (commandBuffer.toString().equals("ls")) {
                fileEngines[2].execOperation(command, this.socketOutputStream);
                commandBuffer.setLength(0);
                break;

            }
            if (commandBuffer.toString().equals("cd")) {
                fileEngines[3].execOperation(command, this.socketOutputStream);
                commandBuffer.setLength(0);
                break;
            }
            if (commandBuffer.toString().equals("..")) {
                fileEngines[4].execOperation(command, this.socketOutputStream);
                commandBuffer.setLength(0);
                break;
            }
        }
        scanner.close();
    }

    private void showHelp() throws IOException {
        this.socketOutputStream.writeChars("\n\r Type <cd> <directory> - to change current directory to new <directory>  \n\r");
        this.socketOutputStream.writeChars("\n\r Type <ls> - to show list of files and directories in the current directory \n\r");
        this.socketOutputStream.writeChars("\n\r Type <..> - to back the parent directory \n\r");
        this.socketOutputStream.writeChars("\n\r Type <download> <srcFile> - to copy the source file form server to  \n\r");
        this.socketOutputStream.writeChars("   destination file on the local computer (existed file will be overwrite) \n\r");
        this.socketOutputStream.writeChars("\n\r Type <upload> <srcFile> - to copy source file form the client computer to  \n\r");
        this.socketOutputStream.writeChars("   destination folder on the server \n\r");
        this.socketOutputStream.writeChars("\n\r");
        this.socketOutputStream.flush();
    }

    private void showGreetings() throws IOException {
        this.socketOutputStream.writeChars("\n\r <============= Greetings User =============> \n\r");
        this.socketOutputStream.writeChars("\n\r Type <help> - to showHelp on supported commands\n\r");
        this.socketOutputStream.writeChars("\n\r Type <stop> - to disconnect from server \n\r");
        this.socketOutputStream.flush();
    }


    @Override
    public boolean transferFromTo(final DataOutputStream outputStream, final DataInputStream inputStream) {
        return false;
    }

    @Override
    public boolean transferToFrom(DataOutputStream outputStream, DataInputStream inputStream) throws IOException {
        return false;
    }

    class ListDir implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final DataOutputStream outStreamToClient) throws IOException {
            File listedDir = new File(currentDir);
            String[] list = listedDir.list();

            for (String s : list) {
                outStreamToClient.writeChars(String.format("/%s%n", s));
            }
            outStreamToClient.writeChars("\n\r");
            outStreamToClient.flush();
            return true;
        }
    }

    class ChangeDir implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final DataOutputStream outStreamToClient) throws IOException {
            boolean result;
            String tmpTailDir;
            File chekedDir;
            StringBuilder tmpBuffer = new StringBuilder();
            Scanner scanner = new Scanner(commandName.toLowerCase());
            scanner.next();
            if (scanner.hasNext()) {
                tmpTailDir = String.format("%s%s", "/", scanner.next());
                tmpBuffer.append(currentDir).append(tmpTailDir);
                chekedDir = new File(tmpBuffer.toString());
                if ((chekedDir.isDirectory()) && (chekedDir.exists())) {
                    currentDir = tmpBuffer.toString();
                    outStreamToClient.writeChars("\n\rOK\n\r");
                    outStreamToClient.writeChars(currentDir);
                    outStreamToClient.writeChars("\n\r");
                    outStreamToClient.flush();
                    tailsDir[tailsIndex] = tmpTailDir;
                    tailsIndex++;
                    return true;
                }
            }
            outStreamToClient.writeChars("\n\rFAILED\n\r");
            outStreamToClient.flush();
            result = false;

            return result;
        }
    }

    class DownloadFile implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final DataOutputStream outStreamToClient) throws IOException {
            Scanner scanner = new Scanner(commandName.toLowerCase());
            StringBuilder buffer = new StringBuilder();
            File sourceFile;
            scanner.next();
            int readElement = 0;
            if (scanner.hasNext()) {
                buffer.append(currentDir).append("/").append(scanner.next());
                sourceFile = new File(buffer.toString());
                if ((sourceFile.exists()) && (sourceFile.isFile())) {
                    BufferedInputStream fromFileStream = new BufferedInputStream(new FileInputStream(sourceFile));
                    readElement = fromFileStream.read();
                    while (readElement >= 0) {
                        outStreamToClient.write(readElement);
                        readElement = fromFileStream.read();
                    }
                    outStreamToClient.flush();
                    fromFileStream.close();
                } else {
                    outStreamToClient.writeChars("\n\rFAILED\n\r");
                    outStreamToClient.flush();
                }
            }
            return true;
        }
    }

    class UploadFile implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final DataOutputStream outMessage) {
            return false;
        }

    }

    class GoToParent implements FileOperations {
        @Override
        public boolean execOperation(final String commandName, final DataOutputStream outStreamToClient) throws IOException {
            boolean result;
            if (!currentDir.equals(parentDir)) {
                tailsIndex--;
                currentDir = currentDir.substring(0, currentDir.length() - tailsDir[tailsIndex].length());
                outStreamToClient.writeChars("\n\rOK\n\r");
                outStreamToClient.writeChars(currentDir);
                outStreamToClient.writeChars("\n\r");
                outStreamToClient.flush();
                result = true;
            } else {
                outStreamToClient.writeChars("\n\rFAILED\n\r");
                outStreamToClient.flush();
                result = false;
            }
            outStreamToClient.flush();
            return result;
        }
    }

}
