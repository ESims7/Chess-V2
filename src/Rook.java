public class Rook extends Queen {

    Rook(String colour, int row, int column) {
        super(colour, row, column);
        pieceType = "Rook";
    }

    @Override
    public boolean moveAllowed(int destinationRow, int destinationColumn) {
        boolean destinationSquareNotBlocked = !pieceOfSameColourAtDestination(destinationRow, destinationColumn, colour);
        return destinationSquareNotBlocked && canMoveAsRook(destinationRow, destinationColumn);
    }
}
