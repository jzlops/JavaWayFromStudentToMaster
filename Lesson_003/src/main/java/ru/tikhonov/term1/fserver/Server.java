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
class Server implements InputOutput {
    private FileOperations[] fileEngines = new FileOperations[10];
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean isExit = false;
    private String parentDir = ".";
    private String currentDir = ".";
    private String[] cutDir = new String[1000];
    private int cutIndex = 0;

    /**
     * Создаем сервер и сокет к нему на порту 9999
     *
     * @throws IOException
     */
    void start(String parentDir) throws IOException {
        this.parentDir = parentDir;
        this.currentDir = parentDir;
        cutDir[0] = "";
        this.start();
    }

    void start() throws IOException {
        System.out.printf("Server running");
        Scanner scannerFromInputStream;
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket workSocket = serverSocket.accept();


        this.inputStream = workSocket.getInputStream();
        this.outputStream = workSocket.getOutputStream();

        this.fileEngines[0] = this.new DownloadFile();
        this.fileEngines[1] = this.new UploadFile();
        this.fileEngines[2] = this.new ListDir();
        this.fileEngines[3] = this.new ChangeDir();
        this.fileEngines[4] = this.new GoToParent();

        scannerFromInputStream = new Scanner(this.inputStream);

        showGreetings();

        while (!this.isExit) {
            if (scannerFromInputStream.hasNextLine()) {
                commandParserAndRunner(scannerFromInputStream.nextLine());
            }
        }
        scannerFromInputStream.close();
        this.outputStream.close();
        this.inputStream.close();
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
                fileEngines[0].execOperation(command, this.outputStream);
                commandBuffer.setLength(0);
                break;
            }
            if (commandBuffer.toString().equals("upload")) {
                fileEngines[1].execOperation(command, this.outputStream);
                commandBuffer.setLength(0);
                break;

            }
            if (commandBuffer.toString().equals("ls")) {
                fileEngines[2].execOperation(command, this.outputStream);
                commandBuffer.setLength(0);
                break;

            }
            if (commandBuffer.toString().equals("cd")) {
                fileEngines[3].execOperation(command, this.outputStream);
                commandBuffer.setLength(0);
                break;
            }
            if (commandBuffer.toString().equals("..")) {
                fileEngines[4].execOperation(command, this.outputStream);
                commandBuffer.setLength(0);
                break;
            }
        }
        scanner.close();
    }

    private void showHelp() throws IOException {
        this.outputStream.write("\n\r Type <cd> <directory> - to change current directory to new <directory>  \n\r".getBytes());
        this.outputStream.write("\n\r Type <ls> - to show list of files and directories in the current directory \n\r".getBytes());
        this.outputStream.write("\n\r Type <..> - to back the parent directory \n\r".getBytes());
        this.outputStream.write("\n\r Type <download> <srcFile> - to copy the source file form server to  \n\r".getBytes());
        this.outputStream.write("   destination file on the local computer (existed file will be overwrite) \n\r".getBytes());
        this.outputStream.write("\n\r Type <upload> <srcFile> - to copy source file form the client computer to  \n\r".getBytes());
        this.outputStream.write("   destination folder on the server \n\r".getBytes());
        this.outputStream.write("\n\r".getBytes());
    }

    private void showGreetings() throws IOException {
        this.outputStream.write("\n\r <============= Greetings User =============> \n\r".getBytes());
        this.outputStream.write("\n\r Type <help> - to showHelp on supported commands\n\r".getBytes());
        this.outputStream.write("\n\r Type <stop> - to disconnect from server \n\r".getBytes());
    }


    @Override
    public boolean getMessage(final InputStream inputStream, final OutputStream outputStream) {
        return false;
    }

    @Override
    public boolean setMessage(final OutputStream outputStream, final InputStream inputStream) {
        return false;
    }

    class ListDir implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final OutputStream outMessage) throws IOException {
            File dir = new File(currentDir);
            String[] list = dir.list();
            OutputStreamWriter outWriter = new OutputStreamWriter(outMessage);
            for (String s : list) {
                outWriter.write(String.format("/%s%n", s));
            }
            outWriter.write("\n\r");
            outWriter.flush();
            return true;
        }

        @Override
        public boolean getHelp(final String helpMessage) {
            return false;
        }
    }

    class ChangeDir implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final OutputStream outMessage) throws IOException {
            boolean result;
            String tmpCutDir;
            File chkDir;
            StringBuilder tmpBuffer = new StringBuilder();
            Scanner scanner = new Scanner(commandName);
            scanner.next();
            OutputStreamWriter outWriter = new OutputStreamWriter(outMessage);
            if (scanner.hasNext()) {
                tmpCutDir = String.format("%s%s", "/", scanner.next());
                tmpBuffer.append(currentDir).append(tmpCutDir);
                chkDir = new File(tmpBuffer.toString());
                if ((chkDir.isDirectory()) && (chkDir.exists())) {
                    currentDir = tmpBuffer.toString();
                    outWriter.write("\n\rOK\n\r");
                    outWriter.write(currentDir);
                    outWriter.write("\n\r");
                    outWriter.flush();
                    cutDir[cutIndex] = tmpCutDir;
                    cutIndex++;
                    return true;
                }
            }
            outWriter.write("\n\rFAILED\n\r");
            outWriter.flush();
            result = false;

            return result;
        }

        @Override
        public boolean getHelp(final String helpMessage) {
            return false;
        }
    }

    class DownloadFile implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final OutputStream outMessage) throws IOException {
            Scanner scanner = new Scanner(commandName);
            StringBuilder buffer = new StringBuilder();
            BufferedOutputStream dStream = new BufferedOutputStream(outMessage);
            File sFile;
            scanner.next();
            int cElement = 0;
            if (scanner.hasNext()) {
                buffer.append(currentDir).append("/").append(scanner.next());
                sFile = new File(buffer.toString());
                if ((sFile.exists()) && (sFile.isFile())) {
                    BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(sFile));
                    cElement = bIS.read();
                    while (cElement >= 0) {
                        dStream.write(cElement);
                        cElement = bIS.read();
                    }
                    dStream.flush();
                    bIS.close();
                }
            }
            return true;
        }

        @Override
        public boolean getHelp(final String helpMessage) {
            return false;
        }
    }

    class UploadFile implements FileOperations {

        @Override
        public boolean execOperation(final String commandName, final OutputStream outMessage) {
            return false;
        }

        @Override
        public boolean getHelp(final String helpMessage) {
            return false;
        }
    }

    class GoToParent implements FileOperations {
        @Override
        public boolean execOperation(final String commandName, final OutputStream outMessage) throws IOException {
            OutputStreamWriter outWriter = new OutputStreamWriter(outMessage);
            boolean result;
            if (!currentDir.equals(parentDir)) {
                cutIndex--;
                currentDir = currentDir.substring(0, currentDir.length() - cutDir[cutIndex].length());
                outWriter.write("\n\rOK\n\r");
                outWriter.write(currentDir);
                outWriter.write("\n\r");
                outWriter.flush();
                result = true;
            } else {
                outWriter.write("\n\rFAILED\n\r");
                outWriter.flush();
                result = false;
            }
            return result;
        }

        @Override
        public boolean getHelp(final String helpMessage) {
            return false;
        }
    }

}
