package ru.stikhonov.term7;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */
public class GameTest {
    /**
     * Тестирование корректного движения пешек
     */
    @Test
    public void correctMovePawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Game game = new Game(chessBoard);
        chessBoard.init(8);
        Assert.assertTrue(game.cellToCellUserAction("e2", "e4"));
        Assert.assertTrue(game.cellToCellUserAction("c7", "c6"));
        Assert.assertTrue(game.cellToCellUserAction("b2", "b4"));
        Assert.assertTrue(game.cellToCellUserAction("c6", "c5"));
        Assert.assertTrue(game.cellToCellUserAction("b4", "c5"));
        Assert.assertTrue(game.cellToCellUserAction("e7", "e5"));
    }

    /**
     * Тестирование некорректного движения пешек
     */
    @Test
    public void incorrectMovePawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Game game = new Game(chessBoard);
        chessBoard.init(8);
        Assert.assertFalse(game.cellToCellUserAction("c2", "a4"));
        Assert.assertFalse(game.cellToCellUserAction("b7", "b8"));
        Assert.assertFalse(game.cellToCellUserAction("f2", "f1"));

    }

    /**
     * * Тестирование корректного ввода данных
     */
    @Test
    public void incorrectEntry() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Game game = new Game(chessBoard);
        chessBoard.init(8);
        Assert.assertFalse(game.cellToCellUserAction("cd2", "22a4"));
        Assert.assertFalse(game.cellToCellUserAction("bf7", "b8"));
        Assert.assertFalse(game.cellToCellUserAction("f23", "f1d"));
    }
    @Test
    public void incorrectLeapPawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Game game = new Game(chessBoard);
        chessBoard.init(8);
        Assert.assertTrue(game.cellToCellUserAction("h2", "h4"));
        Assert.assertTrue(game.cellToCellUserAction("h4", "h5"));
        Assert.assertFalse(game.cellToCellUserAction("h7", "h5"));
        Assert.assertTrue(game.cellToCellUserAction("h5", "h6"));
        Assert.assertFalse(game.cellToCellUserAction("h7", "h6"));
        Assert.assertFalse(game.cellToCellUserAction("h7", "h5"));
    }


}