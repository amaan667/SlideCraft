package listeners.key;

import presentation.PresentMode;
import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for the right arrow key bind in present mode
 */
public class PresentKeyBindRight extends AbstractAction{


    private Presentation presentation;
    private PresentMode presentMode;

    /**
     * Constructor method for the key bind
     * @param presentmode the instance of present mode
     * @param presentation the presentation being presented
     */
    public PresentKeyBindRight(PresentMode presentmode, Presentation presentation){
        this.presentation = presentation;
        this.presentMode = presentmode;
    }

    /**
     * Calls the reset slide method and moves the presentation onto the next slide.
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Checks there is a next slide
        if(presentMode.getPosition() < presentation.getSlideList().size()-1){
            //Moves the slide on to the next slide.
            Slide tempSlide = presentation.getSlideList().get(presentMode.getPosition()+1);
            presentMode.resetSlide(tempSlide, true);
        }
    }
}
