package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessMan extends Piece {
    final private PieceColor pieceColor;
    final private PieceType type;

    /**
     * В конструкторе инициализируем шахматную фигуру
     *
     * @param pieceColor цвет фигшуры
     * @param type       тип фигуры
     */
    ChessMan(PieceColor pieceColor, PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    @Override
    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public PieceType getType() {
        return this.type;
    }

}
