package ru.stikhonov.term1;

/**
 * @author Sergey Tikhonov
 */
class ConsoleOutputHelper implements Output {
    @Override
    public void out(Object obj) {
        System.out.print(obj.toString());
    }
}
