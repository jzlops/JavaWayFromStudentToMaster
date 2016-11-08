package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessBoard {
    CheckerSquare[] checkerSquare;

    /**
     * Создаем и заполняем массив клеток
     */
    void init(int checkerSquareCount) {
        this.checkerSquare = new CheckerSquare[checkerSquareCount];
        this.checkerSquare[0] = new CheckerSquare(1, 1, new Chessman(true, true, "Queen"), true);
        this.checkerSquare[1] = new CheckerSquare(1, 1, new Chessman(true, true, "King"), false);
        // и т.д.
    }
}
