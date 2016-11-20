package ru.tikhonov.term1.polindromDetector;

/**
 * @author Sergey Tikhonov
 */
public class StubInOut implements InputOutput {
    private String returnString;

    /**
     * Заглушка ввода данных
     *
     * @return
     */
    @Override
    public String in() {
        return this.returnString;
    }

    /**
     * Заглушка вывода данных
     *
     * @param object
     */
    @Override
    public void out(Object object) {
    }

    void setInput(String s) {
        this.returnString = s;
    }
}
