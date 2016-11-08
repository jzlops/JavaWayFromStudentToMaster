package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
public class Start {
    /**
     * Создаем шахматную доску
     * Инициализируем ее
     * Создаем правила игры (или саму игру)
     * Двигаем фигурки согласно логике игры
     */
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.init(64);
        Game game = new Game();
        if (game.move(chessBoard.checkerSquare[0], chessBoard.checkerSquare[1], chessBoard)) {
            // ход удался
        } else {
            // ход не удался
        }
    }
}
