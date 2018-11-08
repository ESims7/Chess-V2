public class Bishop extends Queen {

    Bishop(String colour, int row, int column) {
        super(colour, row, column);
        pieceType = "Bishop";
    }

    @Override
    public boolean moveAllowed(int destinationRow, int destinationColumn) {
        boolean destinationSquareNotBlocked = !pieceOfSameColourAtDestination(destinationRow, destinationColumn, colour);
        return destinationSquareNotBlocked && canMoveAsBishop(destinationRow, destinationColumn);
    }
}
