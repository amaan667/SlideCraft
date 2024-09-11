package listeners.action.uiButtons;

import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for the print button
 */
public class PrintButtonLogic extends SlideEditButton implements ActionListener {

    /**
     * Constructor method for the print button action listener
     * @param slide the slide the user is currently on
     */
    public PrintButtonLogic(Slide slide){
        this.currentSlide = slide;
    }

    /**
     * Method when the button is pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Calls the print presentation method on the current presentation.
        currentSlide.getPresentation().printPresentation();
    }
}
