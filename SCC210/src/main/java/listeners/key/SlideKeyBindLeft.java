package listeners.key;

import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for the keybinding when the left arrow is pressed
 * Used to allow navigation of slides
 */
public class SlideKeyBindLeft extends AbstractAction {
    private Presentation presentation;

    /**
     * Constructor method
     * @param presentation the presentation being navigated
     */
    public SlideKeyBindLeft(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * Method performed when keybinding is performed
     * Moves the slide to the previous slide
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Gets the current slide and the previous index
        Slide slide = presentation.getUI().getCurrentSlide();
        int nextSlide = presentation.getSlideList().indexOf(slide)-1;

        //Checks it exists and sets it
        if (nextSlide >= 0)
            presentation.setOnScreenSlide(presentation.getSlideList().get(nextSlide));
    }
}
