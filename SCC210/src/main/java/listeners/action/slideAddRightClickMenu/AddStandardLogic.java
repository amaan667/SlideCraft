package listeners.action.slideAddRightClickMenu;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
/**
 * action listener to add
 */
public class AddStandardLogic implements ActionListener {

    private Presentation presentation;

    /** constructor method
     *
     *
     * @param presentation instance of presentation
     */
    public AddStandardLogic(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(presentation.getTemplate(), "noTemplate")){
            presentation.setTemplate("standard");
            presentation.addSlide(new Slide(presentation));
            try {
                Thread.sleep(1100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            presentation.setTemplate("noTemplate");
        }
        else{
            presentation.addSlide(new Slide(presentation));
        }

    }
}
