package ru.stikhonov.term4;

/**
 * Абстрактный класс реализующий интерфейс MenuAction
 *
 * @author Sergey Tikhonov
 */
abstract class BaseAction implements MenuAction {
    private String info;
    @Override
    public void execute(Tracker tracker, Input input, Output output) {

    }

    @Override
    public String info() {
        return this.info;
    }

    BaseAction(String actionName){
        this.info=actionName;
    }
}
