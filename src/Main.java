import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {


        Board board = new Board();
        Board.setUpBoard();
        board.printBoard();
        Gui gui = new Gui();

        while(!Board.isGameOver()) {

            String selection = board.selectPiece();
            String destination = board.selectDestination();

            board.takeTurn(selection, destination);
//            if (board.moveAllowed(selection, destination)) {
//                board.movePiece(selection, destination);
//                board.printBoard();
//                board.checkForMate();
//                board.changePlayerToMove();
//            }
//            else {
//                System.out.println("Illegal Move");
//            }
        }
    }
}
