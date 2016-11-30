package ru.tikhonov.term1.fserver;

import java.io.*;
import java.net.Socket;

/**
 * @author Sergey Tikhonov
 */
public class Client implements InputOutput {
    private DataInputStream socketInputStream;
    private DataOutputStream socketOutputStream;
    private DataOutputStream consoleOutputStream;
    private InputOutput intOut;
    private boolean isExit = false;
   // private String currentDir = ".";

    Client(InputOutput inOut) {
        this.intOut = inOut;
    }

    void start() throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        this.socketInputStream = new DataInputStream(socket.getInputStream());
        this.socketOutputStream = new DataOutputStream(socket.getOutputStream());

        while (!this.isExit) {
            this.intOut.transferToFrom(this.consoleOutputStream, this.socketInputStream);
       //     this.intOut.transferFromTo(this.socketOutputStream, new DataInputStream(System.in));

        }
    }

    @Override
    public boolean transferFromTo(DataOutputStream byteOutputStreamToConsole, DataInputStream fromServerDataInputStream) throws IOException {
        return true;
    }

    @Override
    public boolean transferToFrom(DataOutputStream outputStream, DataInputStream inputStream) throws IOException {
        return false;
    }
}
