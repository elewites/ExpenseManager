package model.enums;

//Represents a month
public enum Month {
    JANUARY("JANUARY"),
    FEBRUARY("FEBRUARY"),
    MARCH("MARCH"),
    APRIL("APRIL"),
    MAY("MAY"),
    JUNE("JUNE"),
    JULY("JULY"),
    AUGUST("AUGUST"),
    SEPTEMBER("SEPTEMBER"),
    OCTOBER("OCTOBER"),
    NOVEMBER("NOVEMBER"),
    DECEMBER("DECEMBER");

    private String month;    //represents enum as a string

    //EFFECTS: constructs an enum and associates it with a string cat
    Month(String m) {
        month = m;
    }

    //EFFECTS: returns month of enum
    public String getMonth() {
        return month;
    }
}
