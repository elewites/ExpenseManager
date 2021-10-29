package persistance;

import model.Expense;
import model.ExpenseManager;
import model.enums.ExpenseCategory;
import model.enums.Month;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads expense manager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpenseManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseManager(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

    // EFFECTS: parses expense manager from JSON object and returns it
    private ExpenseManager parseExpenseManager(JSONObject jsonObject) {
        ExpenseManager manager = new ExpenseManager();
        addExpenses(manager, jsonObject);
        return manager;
    }

    // MODIFIES: manager
    // EFFECTS: parses expenses from JSON object and adds them to expense manager
    private void addExpenses(ExpenseManager manager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(manager, nextExpense);
        }
    }

    // MODIFIES: manager
    // EFFECTS: parses expense from JSON object and adds it to expense manager
    private void addExpense(ExpenseManager manager, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        Double amount = jsonObject.getDouble("amount");

        ExpenseCategory category =  ExpenseCategory.valueOf(jsonObject.getString("category"));
        Month month = Month.valueOf(jsonObject.getString("month"));

        int year = jsonObject.getInt("year");

        Expense expense = new Expense(description, amount, category, month, year);
        manager.addExpense(expense);
    }
}
