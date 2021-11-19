package ui.gui;

import javax.swing.*;
import java.awt.*;

//represents a button panel
public class ButtonPanel extends JPanel {

    private static final int BUTTON_FONT_SIZE = 20;    //font size for button text
    private static final int BUTTON_WIDTH = 200;       //button width
    private static final int BUTTON_HEIGHT = 60;       //button height

    private final JButton addExpense;        //add expense button
    private final JButton saveExpenses;      //save expenses button
    private final JButton loadExpenses;      //load expenses button
    private final JButton monthlyExpenses;   //monthly expenses button
    private final JButton totalMoneySpent;        //total costs button

    //EFFECTS: constructs a panel of size width x height
    public ButtonPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));

        addExpense = createButton("Add Expense");
        saveExpenses = createButton("Save Expenses");
        loadExpenses = createButton("Load Expenses");
        monthlyExpenses = createButton("Monthly Expenses");
        totalMoneySpent = createButton("Total Money Spent");

        this.add(addExpense);
        this.add(monthlyExpenses);
        this.add(totalMoneySpent);
        this.add(saveExpenses);
        this.add(loadExpenses);
    }

    //EFFECTS: returns a button with a given name
    public JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFont(new Font("Sans-serif", Font.PLAIN, BUTTON_FONT_SIZE));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        return button;
    }

    //EFFECTS: return addExpense button
    public JButton getAddExpense() {
        return addExpense;
    }

    //EFFECTS: return saveExpenses button
    public JButton getSaveExpenses() {
        return saveExpenses;
    }

    //EFFECT: return loadExpenses button
    public JButton getLoadExpenses() {
        return loadExpenses;
    }

    //EFFECT: return monthlyExpenses button
    public JButton getMonthlyExpenses() {
        return monthlyExpenses;
    }

    //EFFECT: return totalMoneySpent button
    public JButton getTotalMoneySpent() {
        return totalMoneySpent;
    }
}
