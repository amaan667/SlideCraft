package listeners.action.slideAddRightClickMenu;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * action listener to add template with no theme
 */
public class AddNoThemeLogic implements ActionListener {

    private Presentation presentation;

    /** constructor method
     *
     * @param presentation instance of the presentation
     */
    public AddNoThemeLogic(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Slide newSlide = new Slide(presentation, 0);
        presentation.addSlide(newSlide);
    }
}
