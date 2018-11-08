public class King extends Piece {

    King(String colour, int row, int column) {
        this.colour = colour;
        this.row = row;
        this.column = column;
        pieceType = "King";
    }

    @Override
    public boolean moveAllowed(int destinationRow, int destinationColumn) {

        boolean destinationSquareNotBlocked = !pieceOfSameColourAtDestination(destinationRow, destinationColumn, colour);
        int rowChange = (Math.abs(destinationRow - row));
        int columnChange = (Math.abs(destinationColumn - column));
        boolean movesOneRowOrLess = rowChange < 2;
        boolean movesOneColumnOrLess = columnChange < 2;
        boolean doesMove = (destinationRow != row || destinationColumn != column);
        boolean isCastling = !getHasMoved() && rowChange == 0 && columnChange == 2;

        return destinationSquareNotBlocked && doesMove && ((movesOneRowOrLess && movesOneColumnOrLess) || isCastling && canCastle(destinationColumn));
    }

    private boolean canCastle(int destinationColumn) {
        if (getColour().equals("White")) {
            if (destinationColumn == 6) {
                return whiteCanCastleKingside();
            }
            if (destinationColumn == 2) {
                return whiteCanCastleQueenside();
            }
        }
        else {
            if (destinationColumn == 6) {
                return blackCanCastleKingside();
            }
            if (destinationColumn == 2) {
                return blackCanCastleQueenside();
            }
        }
        return false;
    }

    private boolean whiteCanCastleKingside(){
        boolean kingHasNotMoved = !getHasMoved();
        boolean rookHasNotMoved = !chessboard[7][7].getHasMoved();
        boolean pathBetweenKingAndRookIsClear = chessboard[7][5] == null && chessboard[7][6] == null;
        boolean doesntMoveThroughCheck = !squareHasBlackAttackers(7, 4) && !squareHasBlackAttackers(7,5) && !squareHasBlackAttackers(7, 6);
        return kingHasNotMoved && rookHasNotMoved && pathBetweenKingAndRookIsClear && doesntMoveThroughCheck;
    }

    private boolean whiteCanCastleQueenside(){
        boolean kingHasNotMoved = !getHasMoved();
        boolean rookHasNotMoved = !chessboard[7][0].getHasMoved();
        boolean pathBetweenKingAndRookIsClear = chessboard[7][1] == null && chessboard[7][2] == null && chessboard[7][3] == null;
        boolean doesntMoveThroughCheck = !squareHasBlackAttackers(7, 4) && !squareHasBlackAttackers(7,3) && !squareHasBlackAttackers(7, 2);
        return kingHasNotMoved && rookHasNotMoved && pathBetweenKingAndRookIsClear && doesntMoveThroughCheck;
    }

    private boolean blackCanCastleKingside(){
        boolean kingHasNotMoved = !getHasMoved();
        boolean rookHasNotMoved = !chessboard[0][7].getHasMoved();
        boolean pathBetweenKingAndRookIsClear = chessboard[0][5] == null && chessboard[0][6] == null;
        boolean doesntMoveThroughCheck = !squareHasWhiteAttackers(0, 4) && !squareHasWhiteAttackers(0,5) && !squareHasWhiteAttackers(7, 6);
        return kingHasNotMoved && rookHasNotMoved && pathBetweenKingAndRookIsClear && doesntMoveThroughCheck;
    }

    private boolean blackCanCastleQueenside(){
        boolean kingHasNotMoved = !getHasMoved();
        boolean rookHasNotMoved = !chessboard[0][0].getHasMoved();
        boolean pathBetweenKingAndRookIsClear = chessboard[0][1] == null && chessboard[0][2] == null && chessboard[0][3] == null;
        boolean doesntMoveThroughCheck = !squareHasWhiteAttackers(0, 4) && !squareHasWhiteAttackers(0,3) && !squareHasWhiteAttackers(0, 2);
        return kingHasNotMoved && rookHasNotMoved && pathBetweenKingAndRookIsClear && doesntMoveThroughCheck;
    }

}
