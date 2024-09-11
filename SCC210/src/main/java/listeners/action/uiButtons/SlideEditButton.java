package listeners.action.uiButtons;

import slide.Slide;

/**
 * Abstract class for a button used to edit something on the slide
 */
public abstract class SlideEditButton
{
    protected Slide currentSlide;

    /**
     * Setter
     * @param currentSlide the slide being edited
     */
    public void setCurrentSlide(Slide currentSlide){
        this.currentSlide = currentSlide;
    }
}
