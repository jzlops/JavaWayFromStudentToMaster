package ru.stikhonov.term7;


/**
 * @author Sergey Tikhonov
 */
class ChessGame implements Executable {
    private ChessCells startCell, endCell;
    private ChessBoard chessBoard;

    ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * Основной диалог игры
     *
     * @return true если все ок
     */
    @Override
    public boolean execute() {
        //Тут можно вызвать какой-нибудь диалог и т.д. и т.п.
        do {
            UserCellToCellShift("e2", "e4");
            UserCellToCellShift("c7", "c6");
        } while (false);

        return true;
    }

    /**
     * @param startCell от куда мы хотим переместить фигурк
     * @param endCell   куда мы хотим переместить фигуру
     * @return true если удалось сделат ход
     */

    boolean UserCellToCellShift(String startCell, String endCell) {
        try {
            return ChessCells.valueOf(startCell).getChessMan().move(ChessCells.valueOf(startCell), ChessCells.valueOf(endCell), this.chessBoard);
        } catch (IllegalArgumentException ie) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}


