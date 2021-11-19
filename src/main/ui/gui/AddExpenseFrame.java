package ui.gui;

import model.ExpenseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//represents the frame that will pop up when the add expense button is pressed
public class AddExpenseFrame extends JFrame {

    private JTextField description;
    private JTextField amount;
    private JTextField category;
    private JTextField month;
    private JTextField year;

    private JLabel descriptionLabel;
    private JLabel amountLabel;
    private JLabel categoryLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;

    private JPanel mainPanel;

    private JButton addExpense;

    private ExpenseManager expenseManager;
    private ExpenseListPanel listPanel;

    //EFFECTS: constructs a frame with given width and height
    public AddExpenseFrame(int width, int height, ExpenseManager manager, ExpenseListPanel listPanel) {
        this.setSize(width, height);
        this.expenseManager = manager;
        this.listPanel = listPanel;

        this.addMainPanel(11, 1, 10);
        this.structureMainPanel();
        this.addExpenseEventListener();

        this.setVisible(true);
        this.setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: adds list panel to frame, with given # of rows and columns
    public void addMainPanel(int rows, int columns, int verticalGap) {
        mainPanel = new JPanel();
        GridLayout layout = new GridLayout(rows, columns);
        layout.setVgap(verticalGap);
        mainPanel.setLayout(layout);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: structures main panel with description fields, text fields, and add task button
    public void structureMainPanel() {
        this.assignTextFields();
        this.assignLabels();
        mainPanel.add(descriptionLabel);
        mainPanel.add(description);
        mainPanel.add(amountLabel);
        mainPanel.add(amount);
        mainPanel.add(categoryLabel);
        mainPanel.add(category);
        mainPanel.add(monthLabel);
        mainPanel.add(month);
        mainPanel.add(yearLabel);
        mainPanel.add(year);
        this.setAddExpense();
    }

    //MODIFIES: this
    //EFFECTS: assigns text fields for this
    public void assignTextFields() {
        description = new JTextField(30);
        amount = new JTextField(30);
        category = new JTextField(30);
        month = new JTextField(30);
        year = new JTextField(30);
    }

    //MODIFIES: this
    //EFFECTS: assigns label fields for this
    public void assignLabels() {
        descriptionLabel = new JLabel("  Description:");
        amountLabel = new JLabel("  Amount:");
        categoryLabel = new JLabel("  Category: Food, Rent, Medical, Clothing, Entertainment");
        monthLabel = new JLabel("  Month:");
        yearLabel = new JLabel("  Year:");
    }

    //MODIFIES: this
    //EFFECTS: sets the add task button and adds it to the main panel
    public void setAddExpense() {
        addExpense = new JButton("Add Expense");
        addExpense.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        addExpense.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.add(addExpense);
    }

    //MODIFIES: this, listPanel
    //EFFECTS: adds event listener for addTask button;
    //         when button is pressed, new expense is displayed in ExpenseListPanel
    public void addExpenseEventListener() {
        addExpense.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                listPanel.add(new JLabel("eros"));
                revalidate();
            }
        });
    }

}
