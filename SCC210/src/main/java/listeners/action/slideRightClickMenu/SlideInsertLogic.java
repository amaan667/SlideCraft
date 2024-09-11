package listeners.action.slideRightClickMenu;

import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the insert button on the slide manager right click menu
 * Used to insert a new slide
 */
public class SlideInsertLogic implements ActionListener {

    private Slide slide;

    /**
     * Constructor method
     * @param slide the slide where the new slide is being inserted
     */
    public SlideInsertLogic(Slide slide){
        this.slide = slide;
    }

    /**
     * Method when the button is pressed.
     * Creates a new slide and adds it.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Slide newSlide = new Slide(slide.getPresentation());
        slide.getPresentation().setOnScreenSlide(slide);
        slide.getPresentation().addSlide(newSlide);

    }
}
