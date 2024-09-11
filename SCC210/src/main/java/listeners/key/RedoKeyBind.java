package listeners.key;

import listeners.action.topMenu.RedoListener;
import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RedoKeyBind extends AbstractAction {

    private Presentation presentation;

    public RedoKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        RedoListener redoListener = new RedoListener(presentation);
        redoListener.actionPerformed(null);
    }
}
