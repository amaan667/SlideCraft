package listeners.component;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Class for detecting when the window is resized
 */
public class SlideComponentListener extends ComponentAdapter {

    private final Presentation presentation;

    /**
     * Constructor method for the listener
     * @param presentation the presentation the user is making
     */
    public SlideComponentListener(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * Method for when the window is resized
     * Scales all the slides in the presentation
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {

        for (int i = 0; i < presentation.getSlideList().size(); i++){
            presentation.getSlideList().get(i).setSlideSize(presentation.getUI().getFrame());
        }
    }
}
