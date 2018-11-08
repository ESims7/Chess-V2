import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import javax.swing.border.*;

public class ChessBoardWithColumnsAndRows {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";

    ChessBoardWithColumnsAndRows() throws UnsupportedEncodingException {
        initializeGui();
    }

    final void initializeGui() throws UnsupportedEncodingException {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                Board board = new Board();
                Board.setUpBoard();
                String gridReference = board.getGridReference(i, j);
                InputStream testInput = new ByteArrayInputStream( gridReference.getBytes("UTF-8") );
                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        board.handleInput(gridReference);
                        refreshGui();
                    }
                };
                b.addActionListener(actionListener);
                if (Board.chessboard[i][j] != null) {
                    String piece = Board.chessboard[i][j].getColour() + Board.chessboard[i][j].getPieceType();
                    ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/" + piece + ".png"));
                    b.setIcon(icon);
                }
                if ((j % 2 == 1 && i % 2 == 1)
                        //) {
                        || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.PINK);
                }
                chessBoardSquares[j][i] = b;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9 - (ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    final void refreshGui() {
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                if (Board.chessboard[i][j] != null) {
                    String piece = Board.chessboard[i][j].getColour() + Board.chessboard[i][j].getPieceType();
                    ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/" + piece + ".png"));
                    chessBoardSquares[j][i].setIcon(icon);
                }
                else {
                    ImageIcon blank = new ImageIcon(this.getClass().getResource("resources/blank.png"));
                    chessBoardSquares[j][i].setIcon(blank);
                }

            }
        }
    }
    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                ChessBoardWithColumnsAndRows cb =
                        null;
                try {
                    cb = new ChessBoardWithColumnsAndRows();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                JFrame f = new JFrame("ChessChamp");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}