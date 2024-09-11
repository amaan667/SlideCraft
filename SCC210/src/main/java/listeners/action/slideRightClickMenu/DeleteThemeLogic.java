package listeners.action.slideRightClickMenu;

import presentation.Presentation;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for deleting the theme
 */
public class DeleteThemeLogic implements ActionListener {

    private Slide slide;

    /** constructor method
     *
     * @param slide instance of slide
     */
    public DeleteThemeLogic(Slide slide){
        this.slide = slide;
    }

    /** method for when button is clicked
     * check if template is a themed one
     * delete theme
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        slide.setSlideTemplateNum(0);
        int temp = slide.getPresentation().getTemplateNum();
        slide.getPresentation().setTemplateNum(0);
        slide.setTemplateOnSlide();
        slide.getPresentation().setTemplateNum(temp);
        slide.repaint();
    }
}
