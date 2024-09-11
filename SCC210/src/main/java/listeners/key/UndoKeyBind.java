package listeners.key;

import listeners.action.topMenu.UndoListener;
import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoKeyBind extends AbstractAction {

    private Presentation presentation;

    public UndoKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        UndoListener undoListener = new UndoListener(presentation);
        undoListener.actionPerformed(null);
    }
}
