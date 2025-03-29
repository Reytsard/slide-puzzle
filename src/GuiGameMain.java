import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class GuiGameMain {
    public static void main(String[] args) {

        GuiGameMain program;
        try {
            program = new GuiGameMain();
            program.run();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        JFrame actualFrame = new JFrame("slide puzzle");
        actualFrame.setSize(600,600);
        JPanel panel = new JPanel();
        actualFrame.add(panel);
        panel.setLayout(new GridLayout(3, 3));
        panel.setSize(600, 600);
        actualFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Piece[][] gameTiles = new Piece[3][3];
        initTiles(gameTiles);
        ArrayList<Integer> randoNumbers = getRandoArrayList();
        disarrangeTiles(gameTiles, randoNumbers);
        addFrameFunctionality(actualFrame, gameTiles, panel);
        addTilesToPanel(panel, gameTiles);
        actualFrame.setVisible(true);



    }

    private void disarrangeTiles(Piece[][] gameTiles, ArrayList<Integer> randoNums) {
        for (int rando : randoNums) {
            System.out.println("rando: " + rando);
            switch (rando) {
                case 1 -> up(gameTiles);
                case 2 -> right(gameTiles);
                case 3 -> left(gameTiles);
                case 4 -> down(gameTiles);
            }
        }
    }

    private static void up(Piece[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile - 1][columnOfBlankTile];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile - 1][columnOfBlankTile] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static void down(Piece[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile + 1][columnOfBlankTile];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile + 1][columnOfBlankTile] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static void left(Piece[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile][columnOfBlankTile - 1];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile][columnOfBlankTile - 1] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static void right(Piece[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile][columnOfBlankTile + 1];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile][columnOfBlankTile + 1] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    public static int locateBlankColumn(Piece[][] gameTiles) {
        for (Piece[] gameTileRow : gameTiles) {
            for (int j = 0; j < gameTileRow.length; j++) {
                if (gameTileRow[j].string.equals(" ")) {
                    System.out.println("blank at: " + j);
                    return j;
                }
            }
        }
        return -1;
    }

    public static int locateBlankRow(Piece[][] gameTiles) {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].string.equals(" ")) {
                    System.out.println("row at: " + i);
                    return i;
                }
            }
        }
        return -1;
    }

    private ArrayList<Integer> getRandoArrayList() {
        ArrayList<Integer> mixer = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            double random = Math.random() * 4 + 1;
            mixer.add((int) random);
        }

        return mixer;
    }

    private void addFrameFunctionality(JFrame actualFrame, Piece[][] gameTiles, JPanel panel) {
        actualFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case 37 -> right(gameTiles);
                    case 38 -> down(gameTiles);
                    case 39 -> left(gameTiles);
                    case 40 -> up(gameTiles);
                }
                addTilesToPanel(panel, gameTiles);
                if(checkIfTilesAreCorrect(gameTiles)){
                    actualFrame.setVisible(false);
                    JOptionPane.showConfirmDialog(null,"you won","congrats",JOptionPane.DEFAULT_OPTION);
                    System.exit(0);
                }

            }

            private boolean checkIfTilesAreCorrect(Piece[][] gameTiles) {
                return Objects.equals(gameTiles[0][0].string, "1") &&
                        Objects.equals(gameTiles[0][1].string, "2") &&
                        Objects.equals(gameTiles[0][2].string, "3") &&
                        Objects.equals(gameTiles[1][0].string, "4") &&
                        Objects.equals(gameTiles[1][1].string, "5") &&
                        Objects.equals(gameTiles[1][2].string, "6") &&
                        Objects.equals(gameTiles[2][0].string, "7") &&
                        Objects.equals(gameTiles[2][1].string, "8") &&
                        Objects.equals(gameTiles[2][2].string, " ");
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void initTiles(Piece[][] gameTiles) {
        gameTiles[0][0] = new Piece("1");
        gameTiles[0][1] = new Piece("2");
        gameTiles[0][2] = new Piece("3");
        gameTiles[1][0] = new Piece("4");
        gameTiles[1][1] = new Piece("5");
        gameTiles[1][2] = new Piece("6");
        gameTiles[2][0] = new Piece("7");
        gameTiles[2][1] = new Piece("8");
        gameTiles[2][2] = new Piece(" ");
    }

    private void addTilesToPanel(JPanel panel, Piece[][] gameTiles) {
        panel.removeAll();
        for (Piece[] row : gameTiles) {
            for (Piece tile : row) {
                JLabel label = new JLabel(tile.string);
                label.setFont(new Font("verdana", Font.PLAIN, 26));
                label.setBorder(new LineBorder(Color.BLACK, 1));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                panel.add(label);
            }
        }
        if (panel.isVisible()) {
            panel.revalidate();
            panel.repaint();
        } else {
        }

    }
}

class PieceKeyListenerGUI implements KeyListener {
    Piece[][] gameTiles;
    JFrame frame;

    public PieceKeyListenerGUI(Piece[][] gameTiles, JFrame frame) {
        this.gameTiles = gameTiles;
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37 -> moveLeft();
            case 38 -> moveUp();
            case 39 -> moveRight();
            case 40 -> moveDown();
        }
        frame.removeAll();
        frame.revalidate();
        frame.repaint();
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3,3));
//        for (Piece[] row : gameTiles) {
//            for (Piece tile : row) {
//                JLabel label = new JLabel(tile.string);
//                label.setFont(new Font("verdana", Font.PLAIN, 26));
//                label.setBorder(new LineBorder(Color.BLACK, 1));
//                label.setHorizontalAlignment(SwingConstants.CENTER);
//                label.setVerticalAlignment(SwingConstants.CENTER);
//                panel.add(label);
//            }
//        }
//        panel.revalidate();
//        panel.repaint();
//        frame.add(panel);
//        frame.validate();
//        frame.repaint();
    }

    private void moveDown() {
        int columnOfBlankTile = GuiGameMain.locateBlankColumn(gameTiles);
        int rowOfBlankTile = GuiGameMain.locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile + 1][columnOfBlankTile];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile + 1][columnOfBlankTile] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private void moveRight() {
        int columnOfBlankTile = GuiGameMain.locateBlankColumn(gameTiles);
        int rowOfBlankTile = GuiGameMain.locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile][columnOfBlankTile + 1];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile][columnOfBlankTile + 1] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private void moveUp() {
        int columnOfBlankTile = GuiGameMain.locateBlankColumn(gameTiles);
        int rowOfBlankTile = GuiGameMain.locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile - 1][columnOfBlankTile];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile - 1][columnOfBlankTile] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private void moveLeft() {
        int columnOfBlankTile = GuiGameMain.locateBlankColumn(gameTiles);
        int rowOfBlankTile = GuiGameMain.locateBlankRow(gameTiles);
        try {
            Piece toSwap = gameTiles[rowOfBlankTile][columnOfBlankTile - 1];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile][columnOfBlankTile - 1] = new Piece(" ");
        } catch (IndexOutOfBoundsException _) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
