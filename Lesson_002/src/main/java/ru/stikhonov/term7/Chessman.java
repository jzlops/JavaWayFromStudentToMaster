package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessMan extends Piece {
    final private PieceColor pieceColor;
    final private PieceType pieceType;

    /**
     * В конструкторе инициализируем шахматную фигуру
     *
     * @param pieceColor цвет фигшуры
     * @param pieceType       тип фигуры
     */
    ChessMan(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    @Override
    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }

}
