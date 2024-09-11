package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

/**
 * Class used to edit a text box
 */
public class TextFormatButton extends SlideEditButton
{
    protected Text text;

    /**
     * Constructor method
     * @param newSlide the slide being edited
     */
    public TextFormatButton(Slide newSlide)
    {
        this.currentSlide = newSlide;
    }

}