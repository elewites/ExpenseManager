package ui.gui;

import javax.swing.*;
import java.awt.*;

//represents a title bar panel
public class TitleBarPanel extends JPanel {

    private final JLabel title;           //label for the title bar panel

    //EFFECTS: constructs a panel of size width x height, with a title text and sets its font size
    public TitleBarPanel(int width, int height, String titleText, int fontSize) {
        this.setPreferredSize(new Dimension(width, height));
        title = addLabel(titleText, fontSize);
        this.add(title);
    }

    //MODIFIES: this
    //EFFECTS: returns a label with given text and given fontSize
    private JLabel addLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Sans-serif", Font.BOLD, fontSize));
        return label;
    }

}
