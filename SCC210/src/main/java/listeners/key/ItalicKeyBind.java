package listeners.key;

import listeners.action.uiButtons.ItalicsButtonLogic;
import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ItalicKeyBind extends AbstractAction {

    private Presentation presentation;

    public ItalicKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ItalicsButtonLogic italicsButtonLogic = new ItalicsButtonLogic(presentation.getUI().getCurrentSlide());
        italicsButtonLogic.actionPerformed(null);
    }
}
