package listeners.key;

import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for the keybinding when the right arrow is pressed
 * Used to allow navigation of slides
 */
public class SlideKeyBindRight extends AbstractAction {

    private Presentation presentation;

    /**
     * Constructor method
     * @param presentation the presentation being navigated
     */
    public SlideKeyBindRight(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * Method performed when the keybinding is performed
     * Moves the slide onto the next slide
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Gets the current slide and next slide index
        Slide slide = presentation.getUI().getCurrentSlide();
        int nextSlide = presentation.getSlideList().indexOf(slide)+1;

        //Checks the slide exists and sets it
        if (nextSlide < presentation.getSlideList().size())
            presentation.setOnScreenSlide(presentation.getSlideList().get(nextSlide));

    }
}
