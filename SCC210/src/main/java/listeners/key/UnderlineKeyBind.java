package listeners.key;

import listeners.action.uiButtons.ItalicsButtonLogic;
import listeners.action.uiButtons.UnderlineButtonLogic;
import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UnderlineKeyBind extends AbstractAction {

    private Presentation presentation;

    public UnderlineKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UnderlineButtonLogic underlineButtonLogic = new UnderlineButtonLogic(presentation.getUI().getCurrentSlide());
        underlineButtonLogic.actionPerformed(null);
    }
}
