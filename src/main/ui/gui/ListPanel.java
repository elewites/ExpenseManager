package ui.gui;

import javax.swing.*;
import java.awt.*;

//represents a panel where all expenses will be displayed
public class ListPanel extends JPanel {

    //EFFECTS: constructs a grid panel with given #rows and #columns
    public ListPanel(int rows, int columns) {
        GridLayout layout = new GridLayout(rows, columns);
        layout.setVgap(5);
        this.setLayout(layout);
        this.setVisible(true);
    }
}
