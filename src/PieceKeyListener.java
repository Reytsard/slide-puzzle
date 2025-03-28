import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PieceKeyListener implements KeyListener {
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
