package ui.gui;

import javax.swing.*;
import java.awt.*;

//represents a panel where all expenses will be displayed
public class ListPanel extends JPanel {

    //EFFECTS: constructs a grid panel with given #rows and #columns, and verticalGap
    public ListPanel(int rows, int columns, int verticalGap) {
        GridLayout layout = new GridLayout(rows, columns);
        layout.setVgap(verticalGap);
        this.setLayout(layout);
        this.setVisible(true);
    }
}
