package listeners.key;

import listeners.action.uiButtons.BoldButtonLogic;
import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoldKeyBind extends AbstractAction {

    private Presentation presentation;

    public BoldKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BoldButtonLogic boldButtonLogic = new BoldButtonLogic(presentation.getUI().getCurrentSlide());
        boldButtonLogic.actionPerformed(null);
    }
}
