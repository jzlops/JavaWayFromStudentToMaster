package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class Chessman {
    private boolean isLive = true;
    final private boolean color;
    final private String type;

    /**
     * В конструкторе инициализируем шахматную фигуру
     * @param isAlive жива ли фигура или "съедена" соперником
     * @param color цвет фигшуры
     * @param type тип "Queen", "King" и т.д.
     */
    Chessman(boolean isAlive, boolean color, String type) {
        this.isLive = isAlive;
        this.color = color;
        this.type = type;
    }

    public boolean getLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
