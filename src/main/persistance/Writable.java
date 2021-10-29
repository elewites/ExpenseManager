package persistance;

//Code for persistence logic is based on JsonSerializationDemo repo from CPSC210

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
