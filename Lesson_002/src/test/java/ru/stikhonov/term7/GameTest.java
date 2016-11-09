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
        Assert.assertTrue(game.move("c2", "c4"));
        Assert.assertTrue(game.move("b7", "b5"));
        Assert.assertTrue(game.move("c4", "b5"));
    }

    /**
     * Тестирование некорректного движения пешек
     */
    @Test
    public void incorrectMovePawns() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Game game = new Game(chessBoard);
        chessBoard.init(8);
        Assert.assertFalse(game.move("c2", "a4"));
        Assert.assertFalse(game.move("b7", "b8"));
        Assert.assertFalse(game.move("f2", "f1"));
    }

    /**
     * * Тестирование корректного ввода данных
     */
    @Test
    public void incorrectEntry() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        Game game = new Game(chessBoard);
        chessBoard.init(8);
        Assert.assertFalse(game.move("cd2", "22a4"));
        Assert.assertFalse(game.move("bf7", "b8"));
        Assert.assertFalse(game.move("f23", "f1d"));
    }


}