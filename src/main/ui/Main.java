package ui;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import org.json.JSONObject;
import persistance.JsonWriter;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new BudgetManagerApp();
    }
}
