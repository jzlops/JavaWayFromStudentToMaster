package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class MainMenu implements MenuOperations, Executable {
    private Executable[] executablesObjects;
    private int menuElementsCount = 0;
    private int menuElementsIndex = 0;
    private Input in;
    private Output out;

    MainMenu(Input input, Output output) {
        this.in = input;
        this.out = output;
    }

    /**
     * Основной цикл меню
     */

    @Override
    public boolean execute() {
        do {
            this.menuActionDraw();
            this.menuActionExecute(0);
        } while (false);
        return false;
    }

    /**
     * Инициализация количества добавляемых элементов
     *
     * @param menuElementsCount количество элементов меню
     */
    void initElements(int menuElementsCount) {
        this.menuElementsCount = menuElementsCount;
        this.executablesObjects = new Executable[this.menuElementsCount];
    }

    @Override
    public void menuActionExit() {

    }

    @Override
    public void menuActionExecute(int index) {
        this.executablesObjects[index].execute();
    }

    @Override
    public boolean menuActionDraw() {
        return false;
    }

    @Override
    public boolean addExecutableObject(Executable object) {
        try {
            this.executablesObjects[this.menuElementsIndex] = object;
            this.menuElementsIndex++;

        } catch (Exception e) {
            System.out.println("WTF?");
            return false;
        }

        return true;
    }


}
