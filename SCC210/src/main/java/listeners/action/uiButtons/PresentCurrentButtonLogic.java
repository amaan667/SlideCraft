package listeners.action.uiButtons;

import presentation.PresentMode;
import presentation.Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentCurrentButtonLogic implements ActionListener {
    private Presentation presentation;

    public PresentCurrentButtonLogic(Presentation presentation){
        this.presentation = presentation;
    }

    public void actionPerformed(ActionEvent e) {
        PresentMode presentmode = new PresentMode(presentation);
        presentmode.start(presentation.getSlideList().indexOf(presentation.getUI().getCurrentSlide()));

    }
}
