package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.CodeText;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeTextButtonLogic extends SlideEditButton implements ActionListener {

    public CodeTextButtonLogic(Slide slide){
        currentSlide = slide;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentSlide.addText(new CodeText(currentSlide));
    }
}
