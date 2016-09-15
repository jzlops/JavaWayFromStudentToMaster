package ru.stikhonov.term1;

import java.util.Date;

/**
 * Класс отвечает за отрисовку консольгного UI и реакции на собития пользователя
 *
 * @author Sergey Tikhonov
 */
class ConsoleInput {

    /**
     * Метод возвращает значение типа enum (ru.stikhonov.term1.MainMenuChoice) в зависимости т выбора пользователя
     *
     * @return возвращает ссылку на объект enum (ru.stikhonov.term1.MainMenuChoice)
     */
    MainMenuChoice getMainMenuAction() {
//      Ожидаем ввобда пользователя в консоли
        if (true) return MainMenuChoice.ADD;
        if (true) return MainMenuChoice.EDIT;
        if (true) return MainMenuChoice.DELETE;
        if (true) return MainMenuChoice.SHOW;
        if (true) return MainMenuChoice.SHOWF;
        return MainMenuChoice.EXIT;
    }

    /**
     * Класс отрисовывает основное консольное меню
     */
    void showMainMenu() {
        // Рисуем UI
    }

}
