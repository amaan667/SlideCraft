package listeners.action.uiButtons;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the delete slide button
 */
public class DeleteButtonLogic extends SlideEditButton implements ActionListener {

    private Presentation presentation;

    /**
     * Constructor method
     * @param presentation the presentation where the slide is being deleted
     * @param slide the current slide the user is on
     */
    public  DeleteButtonLogic(Presentation presentation, Slide slide){
        this.presentation = presentation;
        this.currentSlide = slide;
    }

    /**
     * Method when the button is pressed.
     * Deltes the slide the user is currently on.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Deletes the slide
        presentation.deleteSlide(currentSlide);
    }
}