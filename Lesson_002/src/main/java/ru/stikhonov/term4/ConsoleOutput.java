package ru.stikhonov.term4;

/**
 * @author Sergey Tikhonov
 */
class ConsoleOutput implements Output {
    @Override
    public void out(Object obj) {
        System.out.print(obj.toString());
    }
}
