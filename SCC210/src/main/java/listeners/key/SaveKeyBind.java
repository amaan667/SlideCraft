package listeners.key;

import presentation.Presentation;
import serialization.Saving;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveKeyBind extends AbstractAction {

    private Presentation presentation;

    public SaveKeyBind(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Saving saving = new Saving(presentation);
        if(presentation.getFilepath() == null)
            saving.saveNew();
        else
            saving.saveExisting();
    }
}
