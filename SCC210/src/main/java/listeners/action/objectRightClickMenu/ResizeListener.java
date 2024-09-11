package listeners.action.objectRightClickMenu;

import slide.insertables.Insertable;
import slide.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the resize option in the right click menu
 */
public class ResizeListener implements ActionListener {

    private Insertable object;
    private Slide slide;

    /**
     * Constructor method
     * @param object the object being right-clicked.
     */
    public ResizeListener(Insertable object, Slide slide){
        this.object = object;
        this.slide = slide;
    }

    /**
     * Method when the button is clicked
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Gets user inputs and converts to ints
        int width = object.getWidth();
        int height = object.getHeight();

        // Catches if user enters a non number
        try {
            width = Integer.parseInt(JOptionPane.showInputDialog("Enter width"));
            height = Integer.parseInt(JOptionPane.showInputDialog("Enter height"));
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(slide, "Invalid Input!","Error", JOptionPane.ERROR_MESSAGE);
        }

        // Resizes the object
        object.setObjectSize(width, height, false);
    }
}
