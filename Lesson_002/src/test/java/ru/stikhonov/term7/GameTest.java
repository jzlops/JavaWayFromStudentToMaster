package ru.stikhonov.term7;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sergey Tikhonov
 */
public class GameTest {
    ChessBoard chessBoard = new ChessBoard();
    Game game = new Game(chessBoard);

    @Test
    public void move() throws Exception {
        chessBoard.init(8);
        Assert.assertTrue(game.move("a2","a3"));

    }

}