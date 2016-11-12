package ru.stikhonov.term7;

/**
 * Некий набор операций для интерфейса игры (отрисовка, выбор пунктов меню, и т.д и т.п.,)
 * Реализации пустые, набор параметров тоже
 *
 * @author Sergey Tikhonov
 */
interface MenuOperations {
    /**
     * Выбор пункта меню
     */
    void menuActionExit();

    /**
     * Выбор пункта меню с индексом
     */

    void menuActionExecute(int i);

    /**
     * Отрисовка меню
     *
     * @return true если нет проблем
     */

    boolean menuActionDraw();

    /**
     * Добасление в меню объектов умеющих интерфейс Executable
     *
     * @param executable - объект который будет зарегистрирован в меню
     * @return true если все ок
     */
    boolean addExecutableObject(Executable executable);
}
