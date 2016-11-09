package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
public class Start {
    final static private int BOARD_DIMENSION = 8;

    /**
     * Создаем шахматную доску
     * Инициализируем ее клетками и фигурами
     * Создаем правила игры (или саму игру)
     * Пробуем двигаем фигурки согласно логике игры
     */
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.init(Start.BOARD_DIMENSION);
        Game game = new Game(chessBoard);
    }
}
