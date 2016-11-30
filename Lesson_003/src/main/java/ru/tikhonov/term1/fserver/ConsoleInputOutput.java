package ru.tikhonov.term1.fserver;

import java.io.*;
import java.util.Scanner;


/**
 * @author Sergey Tikhonov
 */
public class ConsoleInputOutput implements InputOutput {
    @Override
    public boolean transferFromTo(DataOutputStream toSocketOutputStream, DataInputStream fromConsoleInputStream) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        if (scanner.hasNextLine()) {
//            toSocketOutputStream.writeBytes(scanner.nextLine());
//            toSocketOutputStream.flush();
//        }

        return true;
    }

    public boolean transferToFrom(DataOutputStream toConsoleOutputStream, DataInputStream fromSocketInputStream) throws IOException {
        StringBuilder buffer = new StringBuilder();
        char c;
        try {
            c = fromSocketInputStream.readChar();
            while (fromSocketInputStream.available() > 0) {
                buffer.append(c);
                c = fromSocketInputStream.readChar();
            }
            System.out.println(buffer.toString());
        } catch (Exception e) {
            System.out.println("Error");
        }
        return true;
    }
}

