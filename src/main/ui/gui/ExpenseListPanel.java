package ui.gui;

import javax.swing.*;
import java.awt.*;

//represents a panel where all expenses will be displayed
public class ExpenseListPanel extends JPanel {

    //EFFECTS: constructs a grid panel
    public ExpenseListPanel() {
        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5);
        this.setLayout(layout);
        this.setVisible(true);
    }

    public void displayExpense() {
        JLabel label = new JLabel("EXPENSE");
        this.add(label);
    }

}
