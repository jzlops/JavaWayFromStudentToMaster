package ru.stikhonov.term7;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */
public class ChessGameShiftTest {
    /**
     * Тестирование корректного движения пешек
     */
    @Test
    public void correctMovePawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessBoard.initDemension(8);
        Assert.assertTrue(chessGame.UserCellToCellShift("e2", "e4"));
        Assert.assertTrue(chessGame.UserCellToCellShift("c7", "c6"));
        Assert.assertTrue(chessGame.UserCellToCellShift("b2", "b4"));
        Assert.assertTrue(chessGame.UserCellToCellShift("c6", "c5"));
        Assert.assertTrue(chessGame.UserCellToCellShift("b4", "c5"));
        Assert.assertTrue(chessGame.UserCellToCellShift("e7", "e5"));
    }

    /**
     * Тестирование некорректного движения пешек
     */
    @Test
    public void incorrectMovePawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessBoard.initDemension(8);
        Assert.assertFalse(chessGame.UserCellToCellShift("c2", "a4"));
        Assert.assertFalse(chessGame.UserCellToCellShift("b7", "b8"));
        Assert.assertFalse(chessGame.UserCellToCellShift("f2", "f1"));

    }

    /**
     * * Тестирование корректного ввода данных
     */
    @Test
    public void incorrectEntry() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessBoard.initDemension(8);
        Assert.assertFalse(chessGame.UserCellToCellShift("cd2", "22a4"));
        Assert.assertFalse(chessGame.UserCellToCellShift("bf7", "b8"));
        Assert.assertFalse(chessGame.UserCellToCellShift("f23", "f1d"));
    }
    @Test
    public void incorrectLeapPawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessBoard.initDemension(8);
        Assert.assertTrue(chessGame.UserCellToCellShift("h2", "h4"));
        Assert.assertTrue(chessGame.UserCellToCellShift("h4", "h5"));
        Assert.assertFalse(chessGame.UserCellToCellShift("h7", "h5"));
        Assert.assertTrue(chessGame.UserCellToCellShift("h5", "h6"));
        Assert.assertFalse(chessGame.UserCellToCellShift("h7", "h6"));
        Assert.assertFalse(chessGame.UserCellToCellShift("h7", "h5"));
    }


}