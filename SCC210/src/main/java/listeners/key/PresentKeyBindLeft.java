package listeners.key;

import presentation.PresentMode;
import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for the left arrow key bind in present mode
 */
public class PresentKeyBindLeft extends AbstractAction {
    private Presentation presentation;
    private PresentMode presentMode;

    /**
     * Constructor method for the key bind
     * @param presentmode the instance of present mode
     * @param presentation the presentation being presented
     */
    public PresentKeyBindLeft(PresentMode presentmode, Presentation presentation){
        this.presentation = presentation;
        this.presentMode = presentmode;
    }

    /**
     * Moves the presentation back a slide
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Checks if you can go back a slide
        if(presentMode.getPosition() > 0){
            //Moves the presentation back one.
            Slide tempSlide = presentation.getSlideList().get(presentMode.getPosition()-1);
            presentMode.resetSlide(tempSlide, false);
        }
    }
}
