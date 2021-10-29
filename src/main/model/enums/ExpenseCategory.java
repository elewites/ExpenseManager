package model.enums;

//represents an ExpenseCategory
public enum ExpenseCategory {
    FOOD("FOOD"),
    RENT("RENT"),
    MEDICAL("MEDICAL"),
    CLOTHING("CLOTHING"),
    ENTERTAINMENT("ENTERTAINMENT");

    private String category;   //represents enum as a string

    //EFFECTS: constructs an enum and associates it with a string category
    ExpenseCategory(String category) {
        this.category = category;
    }

    //EFFECTS: returns category of enum
    public String getCategory() {
        return category;
    }

}

