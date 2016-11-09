package ru.stikhonov.term7;

/**
 * @author Sergey Tikhonov
 */
class ChessBoard {
    private ChessCells[] chessCellsLine = ChessCells.values();
    private ChessCells[][] chessCellSquare;
    private int dimension;

    /**
     * Метод инициализирует шахматную доску клетками ChessCells
     * Клетки инифиализируется координатами и цветом
     * Так же метод распологает фигурры ChessMan в клетках
     */
    void init(int boardDimension) {
        this.dimension = boardDimension;
        this.chessCellSquare = new ChessCells[this.dimension + 1][this.dimension + 1];
        int cellsCounter = 0;
        CellColor cellColor = CellColor.Black;
        System.out.println();
        for (int i = 1; i <= boardDimension; i++) {
            System.out.println();
            for (int j = 1; j <= boardDimension; j++) {
                cellsCounter = cellsCounter + 1;
                chessCellsLine[cellsCounter].setY(i);
                chessCellsLine[cellsCounter].setX(j);
                chessCellsLine[cellsCounter].setCellColor(cellColor);
                cellColor = cellColor == CellColor.Black ? CellColor.White : CellColor.Black;
            }
            cellColor = cellColor == CellColor.Black ? CellColor.White : CellColor.Black;
        }
        cellsFill();
    }

    private void cellsFill() {
        ChessCells.a1.setChessMan(new ChessMan(PieceColor.White, PieceType.Rook));
        ChessCells.a2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.b1.setChessMan(new ChessMan(PieceColor.White, PieceType.Knight));
        ChessCells.b2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.c1.setChessMan(new ChessMan(PieceColor.White, PieceType.Jumbo));
        ChessCells.c2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.d1.setChessMan(new ChessMan(PieceColor.White, PieceType.King));
        ChessCells.d2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.e1.setChessMan(new ChessMan(PieceColor.White, PieceType.Queen));
        ChessCells.e2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.f1.setChessMan(new ChessMan(PieceColor.White, PieceType.Jumbo));
        ChessCells.f2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.g1.setChessMan(new ChessMan(PieceColor.White, PieceType.Knight));
        ChessCells.g2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));
        ChessCells.h1.setChessMan(new ChessMan(PieceColor.White, PieceType.Rook));
        ChessCells.h2.setChessMan(new ChessMan(PieceColor.White, PieceType.Pawn));

        ChessCells.a8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Rook));
        ChessCells.a7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.b8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Knight));
        ChessCells.b7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.c8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Jumbo));
        ChessCells.c7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.d8.setChessMan(new ChessMan(PieceColor.Black, PieceType.King));
        ChessCells.d7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.e8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Queen));
        ChessCells.e7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.f8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Jumbo));
        ChessCells.f7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.g8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Knight));
        ChessCells.g7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
        ChessCells.h8.setChessMan(new ChessMan(PieceColor.Black, PieceType.Rook));
        ChessCells.h7.setChessMan(new ChessMan(PieceColor.Black, PieceType.Pawn));
    }

    /**
     * @return получить набор клеток ChessCells
     */
    ChessCells[][] getChessCellsLine() {
        return this.chessCellSquare;
    }
}
