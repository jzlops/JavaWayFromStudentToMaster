package ru.tikhonov.term1.fserver;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class ServerRun {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        if (args.length > 0){
            server.start(args[0]);
        } else{
            server.start();
        }
    }
}
