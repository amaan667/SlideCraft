package listeners.action.uiButtons;

import listeners.mouse.SlideManagerMouseListener;
import listeners.mouse.SlideManagerMouseMotionListener;
import presentation.Presentation;
import slide.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the slide preview buttons
 * These are the button the user clicks on to change slide
 */
public class SlidePreviewListener implements ActionListener {
    private Slide slide;
    private Presentation presentation;
    private SlideManagerMouseListener slideManagerMouseListener;
    private SlideManagerMouseMotionListener slideManagerMouseMotionListener;

    /**
     * Constructor method
     * @param slide the slide the button links too.
     * @param presentation the presentation it belongs too.
     */
    public SlidePreviewListener(Slide slide, Presentation presentation, SlideManagerMouseListener slideManagerMouseListener, SlideManagerMouseMotionListener slideManagerMouseMotionListener){
        this.presentation = presentation;
        this.slide = slide;
        this.slideManagerMouseListener = slideManagerMouseListener;
        this.slideManagerMouseMotionListener = slideManagerMouseMotionListener;
    }

    /**
     * Method when the slide preview is clicked
     * Changes the slide to the one clicked.
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        presentation.setOnScreenSlide(slide);
        presentation.getUI().getSlideEditor().requestFocusInWindow(); //Ensures keybindings still work

        if (slideManagerMouseListener != null)
            slideManagerMouseMotionListener.setMove(false);
    }
}
