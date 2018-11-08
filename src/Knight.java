public class Knight extends Piece {

    Knight(String colour, int row, int column) {
        this.colour = colour;
        this.row = row;
        this.column = column;
        pieceType = "Knight";
    }

    @Override
    public boolean moveAllowed(int destinationRow, int destinationColumn) {
        boolean destinationSquareNotBlocked = !pieceOfSameColourAtDestination(destinationRow, destinationColumn, colour);
        boolean movesForwardOrBackOneSquare = (Math.abs(destinationRow - row) == 1);
        boolean movesForwardOrBackTwoSquares = (Math.abs(destinationRow - row) == 2);
        boolean movesLeftOrRightOneSquare = (Math.abs(destinationColumn - column) == 1);
        boolean movesLeftOrRightTwoSquares = (Math.abs(destinationColumn - column) == 2);
        boolean movesInLShape = (movesForwardOrBackOneSquare && movesLeftOrRightTwoSquares) || (movesForwardOrBackTwoSquares && movesLeftOrRightOneSquare);

        return destinationSquareNotBlocked && movesInLShape;
    }
}
