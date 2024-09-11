package listeners.action.uiButtons;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the slide add button
 */
public class AddButtonLogic implements ActionListener {

    Presentation presentation;

    /**
     * Constructor method
     * @param presentation the presentation where the slide is being added
     */
    public AddButtonLogic(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * Method when the button is pressed.
     * Adds a new slide
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Creates a new slide and adds it
        Slide slide = new Slide(presentation);
        presentation.addSlide(slide);
    }
}
