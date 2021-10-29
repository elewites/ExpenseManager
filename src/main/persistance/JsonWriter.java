package persistance;

import model.ExpenseManager;
import org.json.JSONObject;
import java.io.*;

//Code for persistence logic is based on JsonSerializationDemo repo from CPSC210

//Represents a writer that writes data to JSON file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //         be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of ExpenseManager to file
    public void write(ExpenseManager manager) {
        JSONObject json = manager.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
