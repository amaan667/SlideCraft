package listeners.key;

import listeners.action.uiButtons.PrintButtonLogic;
import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrintKeyBind extends AbstractAction {

    private Presentation presentation;

    public PrintKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrintButtonLogic printButtonLogic = new PrintButtonLogic(presentation.getUI().getCurrentSlide());
        printButtonLogic.actionPerformed(null);
    }
}
