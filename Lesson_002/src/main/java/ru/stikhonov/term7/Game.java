package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class Game {
    /**
     * @param checkerSquareBegin объект - клетка, от куда мы хотим переместить фигурк
     * @param checkerSquareEnd   объект - клетка, куда мы хотим переместить фигуру
     * @param chessBoard         объект шахматная доска
     * @return true если удалось сделат ход
     */
    public boolean move(CheckerSquare checkerSquareBegin, CheckerSquare checkerSquareEnd, ChessBoard chessBoard) {
        // тут согласно типу фигуры определяем логику ее перемещения по клеткам шахматной доски
        // Возможно использовать какие-либо приватные методы класса Game для реализации хода в зависимоти от типа фигуры
        return true;
    }
}
