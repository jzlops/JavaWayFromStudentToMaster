package ru.tikhonov.term1.fserver;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class ClientRun {
    public static void main(String[] args) throws IOException {
        ConsoleInputOutput consoleInputOutput = new ConsoleInputOutput();
        Client client = new Client(consoleInputOutput);
        client.start();
    }
}
