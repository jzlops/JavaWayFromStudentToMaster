package ru.tikhonov.term1.consoleChat;

/**
 * Общий интерйес для получения ответов от объекта
 *
 * @author Sergey Tikhonov
 */
interface Answerable {
    /**
     * По звпросу позвращает ответ-строку
     *
     * @return объект String
     */
    String getAnswer();
}
