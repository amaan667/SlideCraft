package listeners.action.colourUIButtons;

import jdk.management.jfr.RemoteRecordingStream;
import presentation.Presentation;
import slide.Slide;
import slide.insertables.CodeText;
import slide.insertables.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Action listener for the fill all button on the colour menu.
 */
public class FillAllLogic implements ActionListener {

    private Presentation presentation;
    private JColorChooser colorChooser;
    private JFrame frame;

    /**
     * Constructor method for the action listener
     * @param presentation the current presentation
     * @param colorChooser the color chooser object being used to pick the colour
     * @param frame the colour menu JFrame
     */
    public FillAllLogic(Presentation presentation, JColorChooser colorChooser, JFrame frame){
        this.presentation = presentation;
        this.colorChooser = colorChooser;
        this.frame = frame;
    }

    /**
     * Action performed method for the button
     * Sets all the slides to the selected colour
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Sets all slides to the selected colour
        Slide orginalSlide = presentation.getUI().getCurrentSlide();
        for (int i = 0; i < presentation.getSlideList().size(); i++){
            presentation.getSlideList().get(i).setBackground(colorChooser.getColor());


            //Updates previews
            presentation.setOnScreenSlide(presentation.getSlideList().get(i));
            presentation.updatePreviews(presentation.getSlideList().get(i));
        }
        presentation.setOnScreenSlide(orginalSlide);

        //Deletes the colour menu
        frame.dispose();
    }
}
