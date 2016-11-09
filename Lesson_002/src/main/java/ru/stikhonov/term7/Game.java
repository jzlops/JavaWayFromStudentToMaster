package ru.stikhonov.term7;


/**
 * @author Sergey Tikhonov
 */
class Game {
    private ChessCells begin, end;
    private ChessBoard chessBoard;

    Game(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * @param chessCellBegin от куда мы хотим переместить фигурк
     * @param chessCellEnd   куда мы хотим переместить фигуру
     * @return true если удалось сделат ход
     */
    boolean move(String chessCellBegin, String chessCellEnd) {
        try {
            this.begin = ChessCells.valueOf(chessCellBegin);
            this.end = ChessCells.valueOf(chessCellEnd);
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (begin.getChessMan() == null) {
            return false;
        }

        if (begin.getChessMan().getType().equals(PieceType.Pawn) & (begin.getChessMan().getPieceColor().equals(PieceColor.Black))) {
            return goBlackPawn();
        }
        if (begin.getChessMan().getType().equals(PieceType.Pawn) & (begin.getChessMan().getPieceColor().equals(PieceColor.White))) {
            return goWhitePawn();
        }

        // И так далее с остальными фигурами....

        return false;
    }

    /**
     * @return Двигает белую пешку, если удачно - возарвщает true
     */
    private boolean goWhitePawn() {
        boolean result = false;
        if (end.getChessMan() == null) {
            if ((end.getX() == (begin.getX() + 1) & (end.getY() == begin.getY()))) {
                result = true;
            }
            if ((end.getX() == (begin.getX() + 2)) & (begin.getX() == 2) & (end.getY() == begin.getY())) {
                if (chessBoard.getChessCellsSquare()[begin.getX() + 1][begin.getY()].getChessMan() == null) {
                    result = true;
                }
            }
        }

        if (end.getChessMan() != null) {
            if ((end.getY() == begin.getY() - 1) || (end.getY() == begin.getY() + 1)) {
                if (end.getX() == begin.getX() + 1) {
                    if (end.getChessMan().getPieceColor() != begin.getChessMan().getPieceColor()) {
                        result = true;
                    }
                }
            }
        }
        if (result) finalShift();
        return result;

    }

    /**
     * @return Двигает белую пешку, если удачно - возарвщает true
     */
    private boolean goBlackPawn() {
        boolean result = false;
        if (end.getChessMan() == null) {

            if ((end.getX() == (begin.getX() - 1) & (end.getY() == begin.getY()))) {
                result = true;
            }
            if ((end.getX() == (begin.getX() - 2)) & (begin.getX() == 7) & (end.getY() == begin.getY())) {
                if (chessBoard.getChessCellsSquare()[begin.getX() - 1][begin.getY()].getChessMan() == null) {
                    result = true;
                }
            }
        }

        if (end.getChessMan() != null) {
            if ((end.getY() == begin.getY() + 1) || (end.getY() == begin.getY() - 1)) {
                if (end.getX() == begin.getX() - 1) {
                    if (end.getChessMan().getPieceColor() != begin.getChessMan().getPieceColor()) {
                        result = true;
                    }
                }
            }
        }

        if (result) finalShift();
        return result;
    }

    /**
     * Перемещение фигуры с начального положения в конечное
     */
    private void finalShift() {
        end.setChessMan(begin.getChessMan());
        begin.setChessMan(null);
    }
}
