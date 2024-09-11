package listeners.action.uiButtons;

import ui.ColourSelectorUI;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for buttons bringing up the colour selector menu
 */
public class ColourButtonLogic extends SlideEditButton implements ActionListener {

    boolean fillColour; //Used to track if fill colour should be affected or not

    /**
     * Constructor method
     * @param currentSlide The current slide being edited
     * @param fillColour If the fill colour is being changed
     */
    public ColourButtonLogic(Slide currentSlide, boolean fillColour)
    {
        this.currentSlide = currentSlide;
        this.fillColour = fillColour;
    }

    /**
     * Creates a new colour selector UI on the screen
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ColourSelectorUI UI = new ColourSelectorUI(currentSlide, fillColour, false);
    }
}
