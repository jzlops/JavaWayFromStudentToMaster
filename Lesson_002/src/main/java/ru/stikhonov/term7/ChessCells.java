package ru.stikhonov.term7;

/**
 * Перечисление создает 64 клетки для шахматной доски со своими характеристиками (цвет, координаты, имя, ссылка на фигуру chessMan)
 *
 * @author Sergey Tikhonov
 */
enum ChessCells {
    fake,
    a1, a2, a3, a4, a5, a6, a7, a8,
    b1, b2, b3, b4, b5, b6, b7, b8,
    c1, c2, c3, c4, c5, c6, c7, c8,
    d1, d2, d3, d4, d5, d6, d7, d8,
    e1, e2, e3, e4, e5, e6, e7, e8,
    f1, f2, f3, f4, f5, f6, f7, f8,
    g1, g2, g3, g4, g5, g6, g7, g8,
    h1, h2, h3, h4, h5, h6, h7, h8;

    private int x, y;
    private Piece piece;
    private CellColor cellColor;


    /**
     * @return возвращает координаты Х текущей клетки
     */
    public int getX() {
        return x;
    }

    /**
     * @param x задает координаты Х текущей клетки
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return возвращает координаты У текущей клетки
     */
    public int getY() {
        return y;
    }

    /**
     * @param y задает координаты Х текущей клетки
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return возвращает объект шахматной фигуры ChessMan расположенной в клетке
     */
    public Piece getChessMan() {
        return this.piece;
    }

    /**
     * @param piece присваивание клетке абстрактной фигуры Piece
     */
    public void setChessMan(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return возвращает цвет клетки
     */
    public CellColor getCellColor() {
        return cellColor;
    }

    /**
     * @param cellColor задает цыет клетки
     */
    public void setCellColor(CellColor cellColor) {
        this.cellColor = cellColor;
    }
}
