package model.enums;

public enum ExpenseCategory {
    FOOD("FOOD"),
    RENT("RENT"),
    MEDICAL("MEDICAL"),
    CLOTHING("CLOTHING"),
    ENTERTAINMENT("ENTERTAINMENT");

    private String category;

    //EFFECTS: constructs an enum and associates it with a string cat
    ExpenseCategory(String cat) {
        category = cat;
    }

    //EFFECTS: returns category of enum
    public String getCategory() {
        return category;
    }

}

