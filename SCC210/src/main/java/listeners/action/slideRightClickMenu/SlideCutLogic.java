package listeners.action.slideRightClickMenu;


import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the cut button on the slide right click menu
 */
public class SlideCutLogic implements ActionListener {

    private Slide slide;

    /**
     * Constructor method
     * @param slide the slide being cut
     */
    public SlideCutLogic(Slide slide){
        this.slide = slide;
    }

    /**
     * Method when the button is pressed
     * Sets the slide to the slide clipboard to be used later.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        slide.getPresentation().setSlideClipboard(slide);
        slide.getPresentation().deleteSlide(slide);
    }
}
