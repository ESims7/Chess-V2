public class Pawn extends Piece {


    Pawn(String colour, int row, int column) {
        this.colour = colour;
        this.row = row;
        this.column = column;
        pieceType = "Pawn";
    }

    @Override
    public boolean moveAllowed(int destinationRow, int destinationColumn) {

        int changeInColumn = Math.abs(destinationColumn - column);
        int changeInRow = Math.abs(destinationRow - row);

        boolean staysOnSameColumn = (changeInColumn == 0);
        boolean movesOneSquare = (changeInRow == 1);
        boolean movesTwoSquares = (changeInRow == 2);
        boolean isACaptureMove = changeInRow == 1 && changeInColumn == 1;
        boolean destinationSquareNotBlocked = !pieceOfSameColourAtDestination(destinationRow, destinationColumn, colour);
        boolean oppositionPieceAtDestination = chessboard[destinationRow][destinationColumn] != null
                && !chessboard[destinationRow][destinationColumn].getColour().equals(colour);
        boolean squareInFrontBlocked = (colour.equals("White") && row != 0 && chessboard[row - 1][column] != null)
                || (colour.equals("Black") && row != 7 && chessboard[row + 1][column] != null);
        boolean moveBlocked = movesTwoSquares && squareInFrontBlocked;
        boolean movingForward = colour.equals("White") ? destinationRow < row : destinationRow > row;

        return destinationSquareNotBlocked && movingForward && !moveBlocked
                && ((isACaptureMove && oppositionPieceAtDestination) || (staysOnSameColumn  && ((!hasMoved && movesTwoSquares) || movesOneSquare)) && !oppositionPieceAtDestination);
    }
}
