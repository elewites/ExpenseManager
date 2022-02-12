package ui;

import ui.consoleuserinterface.ConsoleBudgetManagerApp;
import ui.gui.MainAppFrame;

public class Main {
    public static void main(String[] args) {

        //graphical user interface
        //if you wish to use the console based interface, comment out the line below
        //and see the comments below
        new MainAppFrame(MainAppFrame.WIDTH, MainAppFrame.HEIGHT);

        //console based interface
        //uncomment line below if you wish to use the console based interface instead of the GUI
        //new ConsoleBudgetManagerApp();
    }
}
