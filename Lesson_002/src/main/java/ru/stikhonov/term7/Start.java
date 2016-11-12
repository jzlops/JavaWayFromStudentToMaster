package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
public class Start {
    private final static int BOARD_DIMENSION = 8;
    private final static int MENU_ELEMENTS_COUNT = 8;

    public static void main(String[] args) {
        Input input = new InOut();
        Output output = new InOut();
        MainMenu mainMenu = new MainMenu(input, output);
        mainMenu.initElements(MENU_ELEMENTS_COUNT);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initDemension(BOARD_DIMENSION);
        mainMenu.addExecutableObject(new ChessGame(chessBoard));
        mainMenu.execute();

    }
}