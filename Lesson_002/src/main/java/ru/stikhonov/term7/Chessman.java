package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessMan extends Piece {
    final private PieceColor pieceColor;
    final private PieceType pieceType;
    private ChessCells startCell;
    private ChessCells endCell;
    private ChessBoard chessBoard;

    /**
     * В конструкторе инициализируем шахматную фигуру
     *
     * @param pieceColor цвет фигшуры
     * @param pieceType  тип фигуры
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

    @Override
    public boolean move(Enum start, Enum end, Object board) {
        boolean result = false;

        try {
            this.startCell = (ChessCells) start;
            this.endCell = (ChessCells) end;
            this.chessBoard = (ChessBoard) board;

        } catch (Exception e) {
            return result;
        }

        if (this.startCell.getChessMan().getPieceType().equals(PieceType.PAWN) & (this.startCell.getChessMan().getPieceColor().equals(PieceColor.BLACK))) {
            result = BlackPawnShift();
            return result;
        }
        if (this.startCell.getChessMan().getPieceType().equals(PieceType.PAWN) & (this.startCell.getChessMan().getPieceColor().equals(PieceColor.WHITE))) {
            result = WhitePawnShift();
            return result;
        }
        return false;
    }


    /**
     * @return Двигает белую пешку, если удачно - возвращаем true
     */
    private boolean WhitePawnShift() {
        boolean result = false;
        if (endCell.getChessMan() == null) {
            if ((endCell.getX() == (startCell.getX() + 1) & (endCell.getY() == startCell.getY()))) {
                result = true;
            }
            if ((endCell.getX() == (startCell.getX() + 2)) & (startCell.getX() == 2) & (endCell.getY() == startCell.getY())) {
                if (chessBoard.getChessCellsSquare()[startCell.getX() + 1][startCell.getY()].getChessMan() == null) {
                    result = true;
                }
            }
        }

        if (endCell.getChessMan() != null) {
            if ((endCell.getY() == startCell.getY() - 1) || (endCell.getY() == startCell.getY() + 1)) {
                if (endCell.getX() == startCell.getX() + 1) {
                    if (endCell.getChessMan().getPieceColor() != startCell.getChessMan().getPieceColor()) {
                        result = true;
                    }
                }
            }
        }
        if (result) {
            endCell.setChessMan(startCell.getChessMan());
            startCell.setChessMan(null);
        }
        return result;
    }

    /**
     * @return Двигает черную пешку, если удачно - возвращаем true
     */
    private boolean BlackPawnShift() {
        boolean result = false;
        if (endCell.getChessMan() == null) {

            if ((endCell.getX() == (startCell.getX() - 1) & (endCell.getY() == startCell.getY()))) {
                result = true;
            }
            if ((endCell.getX() == (startCell.getX() - 2)) & (startCell.getX() == 7) & (endCell.getY() == startCell.getY())) {
                if (chessBoard.getChessCellsSquare()[startCell.getX() - 1][startCell.getY()].getChessMan() == null) {
                    result = true;
                }
            }
        }

        if (endCell.getChessMan() != null) {
            if ((endCell.getY() == startCell.getY() + 1) || (endCell.getY() == startCell.getY() - 1)) {
                if (endCell.getX() == startCell.getX() - 1) {
                    if (endCell.getChessMan().getPieceColor() != startCell.getChessMan().getPieceColor()) {
                        result = true;
                    }
                }
            }
        }

        if (result) {
            endCell.setChessMan(startCell.getChessMan());
            startCell.setChessMan(null);
        }
        return result;
    }

}
