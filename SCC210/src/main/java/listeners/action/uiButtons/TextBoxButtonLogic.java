package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxButtonLogic extends SlideEditButton implements ActionListener {

    public TextBoxButtonLogic(Slide currentSlide){
        this.currentSlide = currentSlide;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentSlide.addText(new Text(currentSlide));
    }
}
