package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 *         Абстрактный класс игровой фигурки содержит 4 основных метода, справедливых для всех фигур
 */
abstract class Piece implements Moveble {
    /**
     * @return возвращает текущий цвет фигурры
     */
    abstract public PieceColor getPieceColor();

    /**
     * @return возвращает текущий тип фигуры
     */
    abstract public PieceType getPieceType();

    /**
     * Метод движения фигуры
     *
     * @param startCell  начальная клетка
     * @param endCell    конечная клетка
     * @param chessBoard игральная доска
     */
    @Override
    abstract public boolean move(Enum startCell, Enum endCell, Object chessBoard);
}
