import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Piece extends JPanel {
    /*
    37-left
    38-up
    39-right
    40-down
     */
    Image image;
    JLabel imagePlaceholder;
    String string;

    public Piece(String string) {
        this.string = string;
    }

    public Piece(Image image) {
        this.image = image;
        this.setSize(200, 200);
        this.imagePlaceholder = new JLabel(new ImageIcon(image));
        this.imagePlaceholder.setAlignmentX(CENTER_ALIGNMENT);
        this.imagePlaceholder.setAlignmentY(CENTER_ALIGNMENT);
    }


}
