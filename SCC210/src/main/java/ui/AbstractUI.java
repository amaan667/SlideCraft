package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class for the UIs
 * Allows UI classes to use the createButtonIcon method to easily scale images
 */
public abstract class AbstractUI {

    /**
     * Method for creating image icons for buttons
     * @param imageIcon the image being converted
     * @param wdith the desired width
     * @param height the desired height
     * @return the new icon
     */
    public Icon createButtonIcon(ImageIcon imageIcon, int wdith, int height){
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(wdith, height, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(image);
        return  icon;
    }
}
