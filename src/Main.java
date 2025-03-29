import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[][] gameTiles =  new String[3][3];
    static JFrame frame = new JFrame("Slide Puzzle");
    public static void main(String[] args) {
        initPieces(gameTiles);
        displayTiles(gameTiles);
        ArrayList<Integer> randomMixer = generateMixerValues();
        disarrangeTiles(gameTiles, randomMixer);
        displayTiles(gameTiles);
        do {
            System.out.print("Input(up,down,left,right): ");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "up" -> up(gameTiles);
                case "right" -> right(gameTiles);
                case "left" -> left(gameTiles);
                case "down" -> down(gameTiles);
                default -> System.out.println("Input only 'up','down','left','right'");
            }
            displayTiles(gameTiles);
        } while (!isCorrect(gameTiles));
        System.out.println("congratulations!");
    }

    private static boolean isCorrect(String[][] gameTiles) {
        return Objects.equals(gameTiles[0][0], "1") &&
                Objects.equals(gameTiles[0][1], "2") &&
                Objects.equals(gameTiles[0][2], "3") &&
                Objects.equals(gameTiles[1][0], "4") &&
                Objects.equals(gameTiles[1][1], "5") &&
                Objects.equals(gameTiles[1][2], "6") &&
                Objects.equals(gameTiles[2][0], "7") &&
                Objects.equals(gameTiles[2][1], "8") &&
                Objects.equals(gameTiles[2][2], " ");
    }

    private static void disarrangeTiles(String[][] gameTiles, ArrayList<Integer> randomMixer) {
        for (int rando : randomMixer) {
            System.out.println("rando: " + rando);
            switch (rando) {
                case 1 -> up(gameTiles);
                case 2 -> right(gameTiles);
                case 3 -> left(gameTiles);
                case 4 -> down(gameTiles);
            }
            displayTiles(gameTiles);
        }
    }

    private static void down(String[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            String toSwap = gameTiles[rowOfBlankTile + 1][columnOfBlankTile];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile + 1][columnOfBlankTile] = " ";
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static void left(String[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            String toSwap = gameTiles[rowOfBlankTile][columnOfBlankTile - 1];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile][columnOfBlankTile - 1] = " ";
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static void right(String[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            String toSwap = gameTiles[rowOfBlankTile][columnOfBlankTile + 1];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile][columnOfBlankTile + 1] = " ";
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static void up(String[][] gameTiles) {
        int columnOfBlankTile = locateBlankColumn(gameTiles);
        int rowOfBlankTile = locateBlankRow(gameTiles);
        try {
            String toSwap = gameTiles[rowOfBlankTile - 1][columnOfBlankTile];
            gameTiles[rowOfBlankTile][columnOfBlankTile] = toSwap;
            gameTiles[rowOfBlankTile - 1][columnOfBlankTile] = " ";
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private static int locateBlankColumn(String[][] gameTiles) {
        for (String[] gameTileRow : gameTiles) {
            for (int j = 0; j < gameTileRow.length; j++) {
                if (gameTileRow[j].equals(" ")) {
                    System.out.println("blank at: " + j);
                    return j;
                }
            }
        }
        return -1;
    }

    private static int locateBlankRow(String[][] gameTiles) {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].equals(" ")) {
                    System.out.println("row at: " + i);
                    return i;
                }
            }
        }
        return -1;
    }

    private static ArrayList<Integer> generateMixerValues() {
        ArrayList<Integer> mixer = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            double random = Math.random() * 4 + 1;
            mixer.add((int) random);
        }

        return mixer;
    }

    private static void displayTiles(String[][] gameTiles) {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,3));


        System.out.println("=============================");
        for (String[] row : gameTiles) {
            for (String tile : row) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel(tile);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setAlignmentY(Component.CENTER_ALIGNMENT);
                panel.add(label);
                System.out.print(tile + "\t");
            }
            System.out.println();
        }
        System.out.println("=============================");

        frame.pack();
        if(frame.isVisible()){
            frame.revalidate();
            frame.repaint();
        }else{
            frame.setVisible(true);
        }
    }

    private static void initPieces(String[][] gameTiles) {


        gameTiles[0][0] = "1";
        gameTiles[0][1] = "2";
        gameTiles[0][2] = "3";
        gameTiles[1][0] = "4";
        gameTiles[1][1] = "5";
        gameTiles[1][2] = "6";
        gameTiles[2][0] = "7";
        gameTiles[2][1] = "8";
        gameTiles[2][2] = " ";

    }
}
class PieceKeyListener implements KeyListener {
    public PieceKeyListener(){

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
    }

    private void moveDown() {
    }

    private void moveRight() {
    }

    private void moveUp() {
    }

    private void moveLeft() {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}



