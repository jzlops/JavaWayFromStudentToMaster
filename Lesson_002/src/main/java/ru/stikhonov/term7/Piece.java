package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 *         Абстрактный класс игровой фигурки содержит 4 основных метода, справедливых для всех фигур
 */
abstract class Piece {
    /**
     * @return возвращает текущий цвет фигурры
     */
    abstract public PieceColor getPieceColor();

    /**
     * @return возвращает текущий тип фигуры
     */
    abstract public PieceType getType();

}
