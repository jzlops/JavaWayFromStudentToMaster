package ru.stikhonov.term7;


/**
 * @author Sergey Tikhonov
 */
class Game {
    private ChessCells startCell, endCell;
    private ChessBoard chessBoard;

    Game(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * @param startCell от куда мы хотим переместить фигурк
     * @param endCell   куда мы хотим переместить фигуру
     * @return true если удалось сделат ход
     */
    boolean cellToCellUserAction(String startCell, String endCell) {
        try {
            this.startCell = ChessCells.valueOf(startCell);
            this.endCell = ChessCells.valueOf(endCell);
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (this.startCell.getChessMan() == null) {
            return false;
        }

        if (this.startCell.getChessMan().getPieceType().equals(PieceType.PAWN) & (this.startCell.getChessMan().getPieceColor().equals(PieceColor.BLACK))) {
            return this.new BlackPawnAction().shift();
        }
        if (this.startCell.getChessMan().getPieceType().equals(PieceType.PAWN) & (this.startCell.getChessMan().getPieceColor().equals(PieceColor.WHITE))) {
            return this.new WhitePawnAction().shift();
        }

        // И так далее с остальными фигурами....

        return false;
    }


    class WhitePawnAction extends PieceShift {
        /**
         * @return Двигает белую пешку, если удачно - возвращаем true
         */
        @Override
        boolean shift() {
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
    }

    class BlackPawnAction extends PieceShift {
        /**
         * @return Двигает черную пешку, если удачно - возвращаем true
         */
        @Override
        boolean shift() {
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


}
