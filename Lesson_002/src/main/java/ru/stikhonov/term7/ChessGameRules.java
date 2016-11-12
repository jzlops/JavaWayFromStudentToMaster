package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessGameRules extends BoardGameRules {
    private ChessCells startCell, endCell;
    private ChessBoard chessBoard;

    @Override
    boolean pieceShift(ChessCells startCell, ChessCells endCell, ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.startCell = startCell;
        this.endCell = endCell;
        if (startCell.getChessMan().getPieceType().equals(PieceType.PAWN) & (startCell.getChessMan().getPieceColor().equals(PieceColor.BLACK))) {
            return BlackPawnShift();
        }
        if (startCell.getChessMan().getPieceType().equals(PieceType.PAWN) & (startCell.getChessMan().getPieceColor().equals(PieceColor.WHITE))) {
            return WhitePawnShift();
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


    @Override
    boolean pieceTransform(Piece piece) {
        return false;
    }
}
