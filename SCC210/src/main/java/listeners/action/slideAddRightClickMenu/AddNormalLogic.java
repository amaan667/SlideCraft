package listeners.action.slideAddRightClickMenu;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * action listener to add template
 */
public class AddNormalLogic implements ActionListener {

    private Presentation presentation;

    /** constructor method
     *
     * @param presentation instance of the presentation
     */

    public AddNormalLogic(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * method when button is clicked
     * checks and sets up template or no template presentation
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(presentation.getTemplate(), "standard")){
            presentation.setTemplate("noTemplate");
            presentation.addSlide(new Slide(presentation));
            try {
                Thread.sleep(1100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            presentation.setTemplate("standard");
        }
        else{
            presentation.addSlide(new Slide(presentation));
        }
    }
}
