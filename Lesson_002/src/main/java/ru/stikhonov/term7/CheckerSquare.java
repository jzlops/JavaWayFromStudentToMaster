package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class CheckerSquare {
    private int x, y;
    private Chessman chessman;
    private boolean color;

    /**
     * Создаем шахматную клетку и в конструкторе инициализируем ее характеристики
     *
     * @param x координаты кдетки по X
     * @param y координаты кдетки по Y
     * @param chessman ссылка на шахматную фигурку (если есть) иначе клетка пустая (null)
     * @param color цвет клетки (true - белый, false - черный)
     */
    CheckerSquare(int x, int y, Chessman chessman, boolean color) {
        this.x = x;
        this.y = y;
        this.chessman = chessman;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Chessman getChessman() {
        return chessman;
    }

    public void setChessman(Chessman chessman) {
        this.chessman = chessman;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
