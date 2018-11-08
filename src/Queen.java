public class Queen extends Piece {

    Queen(String colour, int row, int column) {
        this.colour = colour;
        this.row = row;
        this.column = column;
        pieceType = "Queen";
    }

    @Override
    public boolean moveAllowed(int destinationRow, int destinationColumn) {
        boolean destinationSquareNotBlocked = !pieceOfSameColourAtDestination(destinationRow, destinationColumn, colour);
        return destinationSquareNotBlocked && (canMoveAsRook(destinationRow, destinationColumn) || canMoveAsBishop(destinationRow, destinationColumn));
    }

    boolean canMoveAsBishop (int destinationRow, int destinationColumn) {
        int changeInRow = Math.abs(destinationRow - row);
        int changeInColumn = Math.abs(destinationColumn - column);
        boolean movesDiagonally = (changeInRow == changeInColumn);

        return movesDiagonally && !bishopMoveBlocked(destinationRow,destinationColumn);
    }

    private boolean bishopMoveBlocked(int destinationRow, int destinationColumn) {

        boolean movingOnBottomLeftToTopRightDiagonal = destinationRow - row == 0 - (destinationColumn - column);
        boolean movingOnTopLeftToBottomRightDiagonal = destinationRow - row == destinationColumn - column;
        boolean movingLeft = destinationColumn < column;
        boolean movingRight = destinationColumn > column;

        if (movingOnBottomLeftToTopRightDiagonal) {
            if (movingLeft) {
                for (int rowToCheck = destinationRow - 1, columnToCheck = destinationColumn + 1; rowToCheck > this.row && columnToCheck < this.column; rowToCheck--, columnToCheck++) {
                    if (chessboard[rowToCheck][columnToCheck] != null) {
                        return true;
                    }
                }
            }
            else if (movingRight) {
                for (int rowToCheck = destinationRow + 1, columnToCheck = destinationColumn - 1; rowToCheck < this.row && columnToCheck > this.column; rowToCheck++, columnToCheck--) {
                    if (chessboard[rowToCheck][columnToCheck] != null) {
                        return true;
                    }
                }
            }
        }
        else if (movingOnTopLeftToBottomRightDiagonal) {
            if (movingLeft) {
                for (int rowToCheck = destinationRow + 1, columnToCheck = destinationColumn + 1; rowToCheck < this.row && columnToCheck < this.column; rowToCheck++, columnToCheck++) {
                    if (chessboard[rowToCheck][columnToCheck] != null) {
                        return true;
                    }
                }
            }
            else if (movingRight) {
                for (int rowToCheck = destinationRow - 1, columnToCheck = destinationColumn - 1; rowToCheck > this.row && columnToCheck > this.column; rowToCheck--, columnToCheck--) {
                    if (chessboard[rowToCheck][columnToCheck] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean canMoveAsRook (int destinationRow, int destinationColumn) {
        boolean staysOnSameRow = (row == destinationRow);
        boolean staysOnSameColumn = (column == destinationColumn);

        return (staysOnSameRow || staysOnSameColumn) && !rookMoveBlocked(destinationRow, destinationColumn);
    }

    @SuppressWarnings("Duplicates")
    private boolean rookMoveBlocked(int destinationRow, int destinationColumn) {
        boolean staysOnSameRow = (row == destinationRow);
        boolean staysOnSameColumn = (column == destinationColumn);

        if (staysOnSameColumn) {
            boolean movingDownTheBoard = destinationRow > row;
            boolean movingUpTheBoard = destinationRow < row;

            if (movingDownTheBoard) {
                for (int i = destinationRow - 1; i > row; i--) {
                    if (chessboard[i][column] != null) {
                        return true;
                    }
                }
            }
            else if (movingUpTheBoard){
                for (int i = destinationRow + 1; i < row; i++) {
                    if (chessboard[i][column] != null) {
                        return true;
                    }
                }
            }
        }
        else if (staysOnSameRow) {
            boolean movingLeft = destinationColumn < column;
            boolean movingRight = destinationColumn > column;

            if (movingRight) {
                for (int i = destinationColumn - 1; i > column; i--) {
                    if (chessboard[row][i] != null) {
                        return true;
                    }
                }
            }
            else if (movingLeft){
                for (int i = destinationColumn + 1; i < column; i++) {
                    if (chessboard[row][i] != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
