public class Piece extends Board {

    String colour;
    String pieceType;
    boolean hasMoved = false;


    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }


    int row;
    int column;

    public String getColour() {
        return colour;
    }

    public String getPieceType() {
        return pieceType;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean moveAllowed(int destinationRow, int destinationColumn) {
        return true;
    }

    boolean moveBlockedByCheck(int destinationRow, int destinationColumn) {

            Piece tempStoreForPieceToBeMoved = chessboard[row][column];
            Piece tempStoreForPieceAtDestinationSquare = chessboard[destinationRow][destinationColumn];

            chessboard[destinationRow][destinationColumn] = chessboard[row][column];
            chessboard[row][column] = null;

            boolean moveNotAllowed = getColour().equals("White") ? squareHasBlackAttackers(getWhiteKingRow(), getWhiteKingColumn()) : squareHasWhiteAttackers(getBlackKingRow(), getBlackKingColumn());

            chessboard[row][column] = tempStoreForPieceToBeMoved;
            chessboard[destinationRow][destinationColumn] = tempStoreForPieceAtDestinationSquare;

            if (moveNotAllowed) {
                System.out.println("That move puts you in check");
            }

            return moveNotAllowed;
        }

    boolean squareHasWhiteAttackers(int row, int column) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getColour().equals("White") && chessboard[i][j].moveAllowed(row, column)) {
                    return true;
                }

            }
        }

        return false;
    }

    boolean squareHasBlackAttackers(int row, int column) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getColour().equals("Black") && chessboard[i][j].moveAllowed(row, column)) {
                    return true;
                }

            }
        }
        return false;
    }
}
