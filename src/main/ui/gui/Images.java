package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//represents class used to process png files and convert them to Image
//imageList is then used to setIconImages(imageList) in MainAppFrame
public class Images {
    private ArrayList<Image> imageList;

    //EFFECTS: constructs an Images instant with empty array list
    public Images() {
        imageList = new ArrayList<>();
    }

    //EFFECTS: this
    //MODIFIES: adds several images to imageList
    private void addImages() {
        addImage("./data/MoneyIcon4096.png");
        addImage("./data/MoneyIcon64.png");
        addImage("./data/MoneyIcon128.png");
        addImage("./data/MoneyIcon256.png");
    }

    //EFFECTS: this
    //MODIFIES: makes an Image from png file located at path and then adds it to imageList
    private void addImage(String path) {
        Image image = makeImage(path);
        imageList.add(image);
    }

    //EFFECTS: makes an Image from png file located at path and returns it
    private Image makeImage(String path) {
        return new ImageIcon(path).getImage();
    }

    //EFFECTS: returns imageList
    public ArrayList<Image> getImageList() {
        return imageList;
    }
}
