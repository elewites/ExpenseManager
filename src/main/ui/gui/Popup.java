package ui.gui;

import model.Expense;
import model.ExpenseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//represents the frame that will pop up when the add expense button is pressed
public class Popup extends JFrame {

    private ListPanel mainPanel;
    private JButton searchButton;
    private ExpenseManager expenseManager;

    private final Font font = new Font("Sans-serif", Font.PLAIN, 20);  //re-usable font

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
            int yearFilter = Integer.parseInt(fetchInput("and year:"));
            this.addFilteredExpenses(monthFilter, yearFilter);
        });
    }

    //EFFECTS: returns user input as a string
    private String fetchInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    //MODIFIES: this
    //EFFECTS: adds filtered expenses to mainPanel
    private void addFilteredExpenses(String monthFilter, int yearFilter) {
        mainPanel.removeAll();
        revalidate();
        repaint();
        List<Expense> filteredExpenses = expenseManager.getExpensesForDate(monthFilter, yearFilter);
        for (Expense e: filteredExpenses) {
            displayExpense(e);
        }
    }

    //EFFECTS: displays Expense in listPanel
    private void displayExpense(Expense e) {
        JLabel label = new JLabel(e.toString());
        label.setFont(font);
        mainPanel.add(label);
        revalidate();
    }

}
