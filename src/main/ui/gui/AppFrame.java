package ui.gui;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

//represents the main frame for the budget manager app
public class AppFrame extends JFrame {
    public static final int HEIGHT = 1000;      //height of frame
    public static final int WIDTH = 1000;        //width of frame
    public static final int SECOND_FRAME_HW = 500; //height and width of AddExpenseFrame
    public static final int PANEL_HEIGHT = 80;     //height of title bar and button panel
    private static final int TITLE_SIZE = 40;      //font size for title bar

    private static final String JSON_STORE = "./data/expensemanager.json";

    private AddExpenseFrame popup;
    private TitleBarPanel titleBarPanel;
    private ButtonPanel buttonPanel;
    private ExpenseListPanel listPanel;
    private JButton addExpenseButton;
    private JButton monthlyExpensesButton;
    private JButton saveExpensesButton;
    private JButton loadExpensesButton;
    private final Font font = new Font("Sans-serif", Font.PLAIN, 20);  //re-usable font

    private ExpenseManager expenseManager;
    private JsonWriter writer;
    private JsonReader reader;


    //EFFECTS: constructs a frame of size width x height pixels
    public AppFrame(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(AppFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        expenseManager = new ExpenseManager();
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);

        //components
        this.addFrameComponents();
        this.assignButtons();

        //listeners
        this.addExpenseListener();
        this.saveExpensesListener();
        this.loadExpensesListener();

        this.setVisible(true);
    }

    //EFFECTS: returns expenseManager
    public ExpenseManager getExpenseManager() {
        return expenseManager;
    }

    //MODIFIES: this
    //EFFECTS: adds title bar, button panel, and list panel to the app frame
    private void addFrameComponents() {
        this.addTitleBar();
        this.addButtonPanel();
        this.addListPanel();
    }

    //MODIFIES: this
    //EFFECTS: adds a title bar to the top of the app frame
    private void addTitleBar() {
        titleBarPanel = new TitleBarPanel(WIDTH, PANEL_HEIGHT, "Budget Manager", TITLE_SIZE);
        this.add(titleBarPanel, BorderLayout.NORTH);
    }

    //MODIFIES: this
    //EFFECTS: adds a button panel to the bottom of app frame
    private void addButtonPanel() {
        buttonPanel = new ButtonPanel(WIDTH, PANEL_HEIGHT);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: gets buttons in button panel and assigns them to specific fields in the app frame
    private void assignButtons() {
        addExpenseButton = buttonPanel.getAddExpense();
        monthlyExpensesButton = buttonPanel.getMonthlyExpenses();
        saveExpensesButton = buttonPanel.getSaveExpenses();
        loadExpensesButton = buttonPanel.getLoadExpenses();
    }

    //MODIFIES: this
    //EFFECTS: adds list panel to the app frame
    private void addListPanel() {
        listPanel = new ExpenseListPanel();
        this.add(listPanel, BorderLayout.CENTER);
    }

//    //MODIFIES: this, buttonPanel
//    //EFFECTS: adds event listener to add expense button;
//    //         assigns an AddExpenseFrame to popup when add expense button is pressed;
//    //         if !popup, pressing add expense button won't do anything;
//    private void addExpenseListener() {
//        addExpenseButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                if (popup == null) {
//                    popup = new AddExpenseFrame(SECOND_FRAME_HW, SECOND_FRAME_HW, expenseManager, list);
//                    revalidate();
//                }
//                addWindowListenerToPopup();
//            }
//        });
//    }
//
//    //MODIFIES: this
//    //EFFECTS: sets popup to null when popup is closed
//    private void addWindowListenerToPopup() {
//        popup.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                popup = null;
//            }
//        });
//    }

    //MODIFIES: this
    //EFFECTS: listens for an event when addExpenseButton is pressed;
    //         then it adds an Expense to expenseManager and displays it in listPanel
    private void addExpenseListener() {
        addExpenseButton.addActionListener(e -> {
            try {
                Expense expense = createExpenseWithInputs();
                JOptionPane.showMessageDialog(this, expense.toString());

                int confirm = JOptionPane.showOptionDialog(this, "Wish to edit expense?",
                        "Edit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (confirm != 1) {
                    addExpenseListener();
                }
                expenseManager.addExpense(expense);
                displayExpense(expense);

            } catch (HeadlessException headlessException) {
                headlessException.printStackTrace();
            }
        });
    }

    //MODIFIES:
    //EFFECTS: displays Expense in listPanel
    private void displayExpense(Expense e) {
        JLabel label = new JLabel(e.toString());
        label.setFont(font);
        listPanel.add(label);
        revalidate();
    }

    //EFFECTS: creates a new Expense with given user inputs
    private Expense createExpenseWithInputs() {
        String description = fetchInput("Description: ");
        //
        double amount = Double.parseDouble(fetchInput("Enter amount:"));
        //
        String category = fetchInput("Enter category: Food, Rent, Medical, Clothing, Entertainment");
        ExpenseCategory expenseCategory = ExpenseCategory.valueOf(category.toUpperCase(Locale.ROOT));
        //
        String month = fetchInput("Enter month of purchase:");
        Month monthEnum = Month.valueOf(month.toUpperCase(Locale.ROOT));
        //
        int year = Integer.parseInt(fetchInput("Enter year of purchase:"));
        //
        return new Expense(description, amount, expenseCategory, monthEnum, year);
    }

    //EFFECTS: returns user input as a string
    private String fetchInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }


    //MODIFIES: this
    //EFFECTS: listens for an event when saveExpensesButton is pressed
    private void saveExpensesListener() {
        saveExpensesButton.addActionListener(e -> {
            this.saveExpenses();
        });
    }

    //EFFECTS: saves expenses in budget manager to file
    private void saveExpenses() {
        try {
            writer.open();
            writer.write(expenseManager);
            writer.close();
            JOptionPane.showMessageDialog(this,"Expenses saved!");
            System.out.println("Expenses saved!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save expenses to file");
            System.out.println("Unable to save expenses to file: " + JSON_STORE);
        }
    }


    //MODIFIES: this
    //EFFECTS: listens for an event when loadExpensesButton is pressed;
    //         loads expenseManager from file;
    //         then displays loaded expenses in listPanel
    private void loadExpensesListener() {
        loadExpensesButton.addActionListener(e -> {
            int numOfExpenses = expenseManager.getNumberOfExpenses();
            this.loadWorkRoom();
            if (expenseManager.getNumberOfExpenses() > numOfExpenses) {
                for (Expense expense : expenseManager.getExpenses()) {
                    displayExpense(expense);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads expense manager from file
    private void loadWorkRoom() {
        try {
            expenseManager = reader.read();
            System.out.println("Loaded expenses from " + JSON_STORE);
            JOptionPane.showMessageDialog(this,"Expenses loaded!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            JOptionPane.showMessageDialog(this,"Unable to load expenses from file");
        }
    }


}