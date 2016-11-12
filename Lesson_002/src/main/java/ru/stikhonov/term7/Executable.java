package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
interface Executable {
    /**
     * Австрактное действие (запуск игры, выход из программы, отрравка результатов игры в онлайн-базу и т.д.)
     *
     * @return если все хорошо то true
     */
    boolean execute();
}
