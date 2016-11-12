package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessBoard {
    private ChessCells[] chessCellsLine = ChessCells.values();
    private ChessCells[][] chessCellSquare;

    /**
     * Метод инициализирует шахматную доску клетками ChessCells
     * Клетки инифиализируется координатами и цветом
     * Так же метод распологает фигурры ChessMan в клетках
     */
    void initDemension(int boardDimension) {
        this.chessCellSquare = new ChessCells[boardDimension + 1][boardDimension + 1];
        int cellsCounter = 0;
        CellColor cellColor = CellColor.BLACK;
        System.out.println();
        for (int i = 1; i <= boardDimension; i++) {
            System.out.println();
            for (int j = 1; j <= boardDimension; j++) {
                cellsCounter = cellsCounter + 1;
                chessCellsLine[cellsCounter].setY(i);
                chessCellsLine[cellsCounter].setX(j);
                chessCellsLine[cellsCounter].setCellColor(cellColor);
                cellColor = cellColor == CellColor.BLACK ? CellColor.WHITE : CellColor.BLACK;
                chessCellSquare[j][i] = chessCellsLine[cellsCounter];
            }
            cellColor = cellColor == CellColor.BLACK ? CellColor.WHITE : CellColor.BLACK;
        }
        cellsFill();
    }

    /**
     * Мето "заполнения" доски фигурками
     */
    private void cellsFill() {
        ChessCells.a1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.ROOK));
        ChessCells.a2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.b1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.KNIGHT));
        ChessCells.b2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.c1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.JUMBO));
        ChessCells.c2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.d1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.KING));
        ChessCells.d2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.e1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.QUEEN));
        ChessCells.e2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.f1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.JUMBO));
        ChessCells.f2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.g1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.KNIGHT));
        ChessCells.g2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));
        ChessCells.h1.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.ROOK));
        ChessCells.h2.setChessMan(new ChessMan(PieceColor.WHITE, PieceType.PAWN));

        ChessCells.a8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.ROOK));
        ChessCells.a7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.b8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.KNIGHT));
        ChessCells.b7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.c8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.JUMBO));
        ChessCells.c7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.d8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.KING));
        ChessCells.d7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.e8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.QUEEN));
        ChessCells.e7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.f8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.JUMBO));
        ChessCells.f7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.g8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.KNIGHT));
        ChessCells.g7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
        ChessCells.h8.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.ROOK));
        ChessCells.h7.setChessMan(new ChessMan(PieceColor.BLACK, PieceType.PAWN));
    }

    /**
     * @return получить набор клеток ChessCells
     */
    ChessCells[][] getChessCellsSquare() {
        return this.chessCellSquare;
    }
}
