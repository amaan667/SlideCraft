package listeners.action.uiButtons;

import presentation.PresentMode;
import presentation.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PresentButtonLogic implements ActionListener {

    private Presentation presentation;

    public PresentButtonLogic(Presentation presentation){
        this.presentation = presentation;
    }

    public void actionPerformed(ActionEvent e) {
        PresentMode presentmode = new PresentMode(presentation);
        presentmode.start(0);

    }
}
