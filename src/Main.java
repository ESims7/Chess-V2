public class Main {

    public static void main(String[] args) {


        Board board = new Board();
        Board.setUpBoard();
        board.printBoard();
        Gui gui = new Gui();

        while(!Board.isGameOver()) {

            String selection = board.selectPiece();
            String destination = board.selectDestination();

            board.takeTurn(selection, destination);

        }
    }
}
