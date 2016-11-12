package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 *         Абстрактный класс логики правил
 */
abstract class BoardGameRules {
    /**
     * Метод реализующий движение фигур
     *
     * @param startCell  - началное значение ячейки
     * @param endCell    - конечное движение ячейки
     * @param chessBoard - шахматная доска
     * @return true если операция выполнена успешно
     */
    abstract boolean pieceShift(ChessCells startCell, ChessCells endCell, ChessBoard chessBoard);

    /**
     * Метод трансформирующий (меняющий тип) фигуры в другую фигуру (пешка->ферьз или шашка->дамка)
     *
     * @param piece Абстракция фигуры
     * @return true если операция завершилась успешно
     */
    abstract boolean pieceTransform(Piece piece);
}
