package ui;

import ui.consoleuserinterface.ConsoleBudgetManagerApp;
import ui.gui.MainAppFrame;

public class Main {
    public static void main(String[] args) {

        //graphical user interface
        new MainAppFrame(MainAppFrame.WIDTH, MainAppFrame.HEIGHT);

        //console based interface
        //new ConsoleBudgetManagerApp();
    }
}
