package ui.gui;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

//represents the main frame for the budget manager app
public class MainAppFrame extends JFrame {
    public static final int HEIGHT = 1000;           //height of frame
    public static final int WIDTH = 1500;            //width of frame
    public static final int SECOND_FRAME_HW = 900;   //height and width of Popup
    public static final int PANEL_HEIGHT = 80;       //height of title bar and button panel
    private static final int TITLE_SIZE = 40;        //font size for title bar

    private static final String JSON_STORE = "./data/expensemanager.json";     //json store

    private Popup popup;                            //popup used to search for filtered expenses
    private ButtonPanel buttonPanel;                //button panel
    private ListPanel listPanel;                    //list panel where expenses are displayed
    private JButton addExpenseButton;               //add expense button
    private JButton monthlyExpensesButton;          //button used to show popup
    private JButton saveExpensesButton;             //save expenses button
    private JButton loadExpensesButton;             //load expenses button
    private JButton totalMoneySpentButton;          //total money spent button

    private final Font font = new Font("Sans-serif", Font.PLAIN, 18);  //re-usable font

    private ExpenseManager expenseManager;          //ExpenseManager
    private final JsonWriter writer;                //json writer used to write to JSON_STORE
    private final JsonReader reader;                //json reader used to read from JSON_STORE

    private final EventPrinter eventPrinter = new EventPrinter();  //EventPrinter used to print events to console
    private final Images images = new Images();                   //images used to setIconImages for the main app frame

    //EFFECTS: constructs a frame of size width x height pixels
    public MainAppFrame(int width, int height) {
        //MainAppFrame settings
        this.setMainAppFrameSettings(width, height);

        //initializes ExpenseManager, JsonWriter and JsonReader
        expenseManager = new ExpenseManager();
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);

        //add components to frame
        this.addFrameComponents();

        //add listeners
        this.addExpenseButtonListener();
        this.saveExpensesListener();
        this.loadExpensesButtonListener();
        this.totalMoneySpentButtonListener();
        this.monthlyExpensesButtonListener();
        this.windowListenerForMainAppFrame();

        //set frame visibility to true
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets the main app frame settings
    private void setMainAppFrameSettings(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(MainAppFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("Budget Manager");
        this.setIconImages(images.getImageList());
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
        //title bar panel
        TitleBarPanel titleBarPanel = new TitleBarPanel(WIDTH, PANEL_HEIGHT, "Budget Manager", TITLE_SIZE);
        this.add(titleBarPanel, BorderLayout.NORTH);
    }

    //MODIFIES: this
    //EFFECTS: adds a button panel to the bottom of app frame
    private void addButtonPanel() {
        buttonPanel = new ButtonPanel(WIDTH, PANEL_HEIGHT);
        this.add(buttonPanel, BorderLayout.SOUTH);
        assignButtons();
    }

    //MODIFIES: this
    //EFFECTS: gets buttons in button panel and assigns them to specific fields in the app frame
    private void assignButtons() {
        addExpenseButton = buttonPanel.getAddExpense();
        monthlyExpensesButton = buttonPanel.getMonthlyExpenses();
        saveExpensesButton = buttonPanel.getSaveExpenses();
        loadExpensesButton = buttonPanel.getLoadExpenses();
        totalMoneySpentButton = buttonPanel.getTotalMoneySpent();
    }

    //MODIFIES: this
    //EFFECTS: adds list panel to the app frame
    private void addListPanel() {
        listPanel = new ListPanel(20, 1, 10);
        this.add(listPanel, BorderLayout.CENTER);
    }


    /*BUTTON LISTENERS BELOW AND HELPERS FOR BUTTON LISTENERS*/

    //MODIFIES: this
    //EFFECTS: listens for an event when monthlyExpensesButton is pressed;
    //         then assigns a Popup to popup;
    //         if !popup, pressing monthlyExpenseButton won't do anything;
    private void monthlyExpensesButtonListener() {
        monthlyExpensesButton.addActionListener(e -> {
            if (popup == null) {
                popup = new Popup(SECOND_FRAME_HW, SECOND_FRAME_HW, expenseManager);
                revalidate();
            }
            windowListenerForPopup();
        });
    }

    //MODIFIES: this
    //EFFECTS: listens for an event when addExpenseButton is pressed;
    //         then it adds an Expense to expenseManager and displays it in listPanel
    private void addExpenseButtonListener() {
        addExpenseButton.addActionListener(e -> {
            try {
                Expense expense = createExpenseWithInputs();
                JOptionPane.showMessageDialog(this, expense.toString());

                int confirm = JOptionPane.showOptionDialog(this, "Wish to edit expense?",
                        "Edit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (confirm != 1) {
                    addExpenseButtonListener();
                }
                expenseManager.addExpense(expense);
                eventPrinter.printMostRecentEvent();
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
        //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
        return JOptionPane.showInputDialog(this, message);
    }

    //MODIFIES: this
    //EFFECTS: listens for an event when saveExpensesButton is pressed
    private void saveExpensesListener() {
        saveExpensesButton.addActionListener(e -> this.saveExpenses());
    }

    //EFFECTS: saves expenses in budget manager to file
    private void saveExpenses() {
        try {
            writer.open();
            writer.write(expenseManager);
            writer.close();
            JOptionPane.showMessageDialog(this,"Expenses saved!");
            System.out.println("Expenses saved!");
            System.out.println(" ");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save expenses to file");
            System.out.println("Unable to save expenses to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: listens for an event when loadExpensesButton is pressed;
    //         loads expenseManager from file;
    //         then displays loaded expenses in listPanel
    private void loadExpensesButtonListener() {
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
            System.out.println(" ");
            JOptionPane.showMessageDialog(this,"Expenses loaded!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            JOptionPane.showMessageDialog(this,"Unable to load expenses from file");
        }
    }

    //MODIFIES: this
    //EFFECTS: listens for an event when totalMoneySpentButton is pressed
    private void totalMoneySpentButtonListener() {
        totalMoneySpentButton.addActionListener(e -> {
            try {
                int numOfExpenses = expenseManager.getNumberOfExpenses();
                double totalMoneySpent = expenseManager.total();
                System.out.println(totalMoneySpent);
                ListPanel panel = new ListPanel(2, 1, 5);
                JLabel labelOne = new JLabel("You've made " + numOfExpenses + " purchase/s");
                JLabel labelTwo = new JLabel("You've spent $" + totalMoneySpent);
                panel.add(labelOne);
                panel.add(labelTwo);
                JOptionPane.showMessageDialog(this, panel);
            } catch (HeadlessException headlessException) {
                headlessException.printStackTrace();
            }
        });
    }


    /*WINDOW LISTENERS BELOW*/

    //MODIFIES: this
    //EFFECTS: sets popup to null when popup is closed
    private void windowListenerForPopup() {
        popup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                popup = null;
            }
        });
    }

    //EFFECTS: prints EventLog to the console when MainAppFrame is closed
    private void windowListenerForMainAppFrame() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                eventPrinter.printEvents();
            }
        });
    }
}
