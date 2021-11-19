package ui.gui;

import model.ExpenseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//represents the frame that will pop up when the add expense button is pressed
public class Popup extends JFrame {

    private ListPanel mainPanel;
    private JButton searchButton;
    private ExpenseManager expenseManager;

    //EFFECTS: constructs a frame with given width and height
    public Popup(int width, int height, ExpenseManager manager) {
        this.setSize(width, height);
        this.expenseManager = manager;

        //components
        this.setMainPanel();
        this.setSearchButton();

        //listeners
        this.searchButtonListener();

        this.setVisible(true);
        this.setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: sets the main panel and adds it to the Popup frame
    private void setMainPanel() {
        mainPanel = new ListPanel(10, 1);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: sets search button and adds it to the Popup frame
    private void setSearchButton() {
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Sans-serif", Font.PLAIN, 40));
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        searchButton.setPreferredSize((new Dimension(AppFrame.SECOND_FRAME_HW, 90)));
        this.add(searchButton, BorderLayout.NORTH);
    }

    //MODIFIES: this
    //EFFECTS: listens for event when searchButton is pressed
    private void searchButtonListener() {
        searchButton.addActionListener(e -> {
            String monthFilter = fetchInput("Filter expenses by month:");
            String yearFilter = fetchInput("and year:");
        });
    }

    //EFFECTS: returns user input as a string
    private String fetchInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    //MODIFIES:
    //EFFECTS:



}
