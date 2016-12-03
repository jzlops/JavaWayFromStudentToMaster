package ru.tikhonov.term1.fclient;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class ClientRun {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }
}
