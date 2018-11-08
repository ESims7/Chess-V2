import java.util.Scanner;

public class Board {


    static Piece[][] chessboard = new Piece[8][8];

    public static boolean isGameOver() {
        return gameOver;
    }

    private static boolean gameOver = false;
    private static String playerToMove = "White";



    Board() {
    }

    static void setUpBoard() {

        chessboard[7][0] = new Rook("White", 7, 0);
        chessboard[7][1] = new Knight("White", 7, 1);
        chessboard[7][2] = new Bishop("White", 7, 2);
        chessboard[7][3] = new Queen("White", 7, 3);
        chessboard[7][4] = new King("White", 7, 4);
        chessboard[7][5] = new Bishop("White", 7, 5);
        chessboard[7][6] = new Knight("White", 7, 6);
        chessboard[7][7] = new Rook("White", 7, 7);
        for (int i = 6, j = 0; j < 8; j++) {
            chessboard[i][j] = new Pawn("White", i, j);
        }

        chessboard[0][0] = new Rook("Black", 0, 0);
        chessboard[0][1] = new Knight("Black", 0, 1);
        chessboard[0][2] = new Bishop("Black", 0, 2);
        chessboard[0][3] = new Queen("Black", 0, 3);
        chessboard[0][4] = new King("Black", 0, 4);
        chessboard[0][5] = new Bishop("Black", 0, 5);
        chessboard[0][6] = new Knight("Black", 0, 6);
        chessboard[0][7] = new Rook("Black", 0, 7);
        for (int i = 1, j = 0; j < 8; j++) {
            chessboard[i][j] = new Pawn("Black", i, j);
        }
    }

    String getPlayerToMove() {
        return playerToMove;
    }

    private void setPlayerToMove(String playerToMove) {
        Board.playerToMove = playerToMove;
    }

    void changePlayerToMove() {
        String nextPlayer = playerToMove.equals("White") ? "Black" : "White";
        setPlayerToMove(nextPlayer);
    }

    void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(getPieceSymbol(chessboard[i][j]));
                System.out.print(" | ");
            }
            System.out.println();

        }
    }

    int getWhiteKingRow() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getPieceType().equals("King") && chessboard[i][j].getColour().equals("White")) {
                    return i;
                }
            }
        }
        return 0;
    }

    int getWhiteKingColumn() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getPieceType().equals("King") && chessboard[i][j].getColour().equals("White")) {
                    return j;
                }
            }
        }
        return 0;
    }

    int getBlackKingRow() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getPieceType().equals("King") && chessboard[i][j].getColour().equals("Black")) {
                    return i;
                }
            }
        }
        return 0;
    }

    int getBlackKingColumn() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getPieceType().equals("King") && chessboard[i][j].getColour().equals("Black")) {
                    return j;
                }
            }
        }
        return 0;
    }

    private static void setGameOver() {
        Board.gameOver = true;
    }

    void checkForMate() {
        if (whiteIsInCheckmate()) {
            System.out.println("Checkmate. Black wins.");
            setGameOver();

        }
        if (blackIsInCheckmate()) {
            System.out.println("Checkmate. White wins.");
            setGameOver();
        }
    }

    private boolean whiteKingInCheck() {
        int kingRow = getWhiteKingRow();
        int kingColumn = getWhiteKingColumn();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getColour().equals("Black") && chessboard[i][j].moveAllowed(kingRow, kingColumn)) {
                    return true;
                }


            }
        }
        return false;
    }

    private boolean blackKingInCheck() {
        int kingRow = getBlackKingRow();
        int kingColumn = getBlackKingColumn();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getColour().equals("White") && chessboard[i][j].moveAllowed(kingRow, kingColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean whiteIsInCheckmate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (chessboard[i][j] != null && chessboard[i][j].getColour().equals("White") && chessboard[i][j].moveAllowed(k, l) && !chessboard[i][j].moveBlockedByCheck(k, l)) {
                            return false;
                        }
                    }
                }
            }
        }
        return whiteKingInCheck();
    }

    private boolean blackIsInCheckmate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (chessboard[i][j] != null && chessboard[i][j].getColour().equals("Black") && chessboard[i][j].moveAllowed(k, l) && !chessboard[i][j].moveBlockedByCheck(k, l)) {
                            return false;
                        }
                    }
                }
            }
        }
        return blackKingInCheck();
    }


    private String getPieceSymbol(Piece piece) {

        if (piece == null) {
            return "  ";
        }

        if (piece.colour.equals("White")) {
            switch (piece.pieceType) {
                case "King":
                    return "\u2654";
                case "Queen":
                    return "\u2655";
                case "Rook":
                    return "\u2656";
                case "Bishop":
                    return "\u2657";
                case "Knight":
                    return "\u2658";
                case "Pawn":
                    return "\u2659";
                default:
                    return "\u2001";
            }
        }
        else {
            switch (piece.pieceType) {
                case "King":
                    return "\u265A";
                case "Queen":
                    return "\u265B";
                case "Rook":
                    return "\u265C";
                case "Bishop":
                    return "\u265D";
                case "Knight":
                    return "\u265E";
                case "Pawn":
                    return "\u265F";
                default:
                    return "\u2001";
            }
        }
    }


    public void movePiece(String currentPositionAsGridReference, String destinationAsGridReference) {
        int startingRow = getRow(currentPositionAsGridReference);
        int startingColumn = getColumn(currentPositionAsGridReference);
        int destinationRow = getRow(destinationAsGridReference);
        int destinationColumn = getColumn(destinationAsGridReference);

        boolean isCastling = chessboard[startingRow][startingColumn].getPieceType().equals("King") && Math.abs(destinationColumn - startingColumn) == 2;
        boolean castlingQueenside = destinationColumn == 2;
        boolean pawnPromotion = chessboard[startingRow][startingColumn].getPieceType().equals("Pawn") && (destinationRow == 0 || destinationRow == 7);

        chessboard[startingRow][startingColumn].setHasMoved(true);

        chessboard[startingRow][startingColumn].setRow(destinationRow);
        chessboard[startingRow][startingColumn].setColumn(destinationColumn);

        chessboard[destinationRow][destinationColumn] = chessboard[startingRow][startingColumn];
        chessboard[startingRow][startingColumn] = null;

        if (pawnPromotion) {
            String pieceToPromoteTo = selectPromotionPiece();
            if (destinationRow == 0) {
                if (pieceToPromoteTo.equals("queen")) {
                    chessboard[destinationRow][destinationColumn] = new Queen("White", destinationRow, destinationColumn);
                }
                if (pieceToPromoteTo.equals("rook")) {
                    chessboard[destinationRow][destinationColumn] = new Rook("White", destinationRow, destinationColumn);
                }
                if (pieceToPromoteTo.equals("knight")) {
                    chessboard[destinationRow][destinationColumn] = new Knight("White", destinationRow, destinationColumn);
                }
                if (pieceToPromoteTo.equals("bishop")) {
                    chessboard[destinationRow][destinationColumn] = new Bishop("White", destinationRow, destinationColumn);
                }
            }
            else {
                if (pieceToPromoteTo.equals("queen")) {
                    chessboard[destinationRow][destinationColumn] = new Queen("Black", destinationRow, destinationColumn);
                }
                if (pieceToPromoteTo.equals("rook")) {
                    chessboard[destinationRow][destinationColumn] = new Rook("Black", destinationRow, destinationColumn);
                }
                if (pieceToPromoteTo.equals("knight")) {
                    chessboard[destinationRow][destinationColumn] = new Knight("Black", destinationRow, destinationColumn);
                }
                if (pieceToPromoteTo.equals("bishop")) {
                    chessboard[destinationRow][destinationColumn] = new Queen("Black", destinationRow, destinationColumn);
                }
            }
        }

        if (isCastling) {
            if (castlingQueenside) {
                if (getPlayerToMove().equals("White")) {
                    chessboard[7][3] = chessboard[7][0];
                    chessboard[7][0] = null;
                }
                else {
                    chessboard[0][3] = chessboard[0][0];
                    chessboard[0][0] = null;
                }
            }
            else {
                if (getPlayerToMove().equals("White")) {
                    chessboard[7][5] = chessboard[7][7];
                    chessboard[7][7] = null;
                }
                else {
                    chessboard[0][5] = chessboard[0][7];
                    chessboard[0][7] = null;
                }
            }
        }

    }

    private String selectPromotionPiece() {

        System.out.println("Which piece would you like? Queen, Rook, Bishop or Knight?");
        String input = new Scanner(System.in).nextLine();
        input = input.toLowerCase();

        while (!isAppropriatePieceSelection(input)) {
            System.out.println("Invalid Piece");
            System.out.println("Which piece would you like? Queen, Rook, Bishop or Knight?");
            input = new Scanner(System.in).nextLine();
        }

        return input;

    }

    private boolean isAppropriatePieceSelection(String input) {
        return input.equals("queen") || input.equals("knight") || input.equals("bishop") || input.equals("rook");
    }


    private int getColumn(String positionAsGridReference) {
        return positionAsGridReference.toLowerCase().charAt(0) - 97;
    }

    private int getRow(String positionAsGridReference) {
        return 8 - (positionAsGridReference.charAt(1) - 48);
    }


    public boolean moveAllowed(String currentPositionAsGridReference, String destinationAsGridReference) {
        int startingRow = getRow(currentPositionAsGridReference);
        int startingColumn = getColumn(currentPositionAsGridReference);
        int destinationRow = getRow(destinationAsGridReference);
        int destinationColumn = getColumn(destinationAsGridReference);


        return chessboard[startingRow][startingColumn].moveAllowed(destinationRow, destinationColumn) && !chessboard[startingRow][startingColumn].moveBlockedByCheck(destinationRow, destinationColumn);
    }

    boolean pieceOfSameColourAtDestination(int destinationRow, int destinationColumn, String colourOfPiece) {
        return chessboard[destinationRow][destinationColumn] != null && colourOfPiece.equals(chessboard[destinationRow][destinationColumn].getColour());
    }

    String selectPiece() {

        System.out.println("Enter the square of the piece you would like to move");
        String input = new Scanner(System.in).nextLine();

        while (!isValidReference(input)) {
            System.out.println("Invalid reference");
            System.out.println("Enter the square of the piece you would like to move");
            input = new Scanner(System.in).nextLine();
        }
        while (!selectedOwnPiece(input)) {
            System.out.println("Please select one of your own pieces.");
            System.out.println("Enter the square of the piece you would like to move:");
            input = new Scanner(System.in).nextLine();
        }

        return input;
    }

    private boolean selectedOwnPiece(String input) {
        int row = getRow(input);
        int column = getColumn(input);
        if (chessboard[row][column] == null) {
            System.out.println("That square is empty.");

        }
        return (chessboard[row][column] != null && playerToMove.equals(chessboard[row][column].getColour()));
    }

    String selectDestination() {

        System.out.println("Enter the square you would like to move to:");
        String input = new Scanner(System.in).nextLine();

        while (!isValidReference(input)) {
            System.out.println("Invalid reference");
            System.out.println("Enter the square of the piece you would like to move");
            input = new Scanner(System.in).nextLine();
        }

        return input;
    }

    private boolean isValidReference(String reference) {

        return reference.toLowerCase().matches("[a-h][1-8]");
    }

    public String getGridReference(int row, int column) {
        return (getColumnLetter(column) + String.valueOf(9 - (row + 1)));
    }

    private String getColumnLetter(int column) {
        switch (column){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";

        }

        return null;

    }

    public void takeTurn(String selection, String destination) {
        if (moveAllowed(selection, destination)) {
            movePiece(selection, destination);
            printBoard();
            checkForMate();
            changePlayerToMove();
        }
        else {
            System.out.println("Illegal Move");
        }
    }


    static boolean selectingPiece = true;
    static String selection = "";
    static String destination = "";

    public void handleInput(String gridReference) {

        if (selectingPiece) {
            selection = gridReference;
            selectingPiece = false;
        }
        else {
            destination = gridReference;
            selectingPiece = true;
        }

        if (!selection.equals("") && !destination.equals("")) {
            takeTurn(selection, destination);
            selection = "";
            destination = "";
        }

    }
}

