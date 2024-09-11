package listeners.action.topMenu;

import presentation.Presentation;
import serialization.Saving;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAsListener implements ActionListener {

    private Presentation presentation;
    public SaveAsListener(Presentation presentation){
        this.presentation = presentation;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Saving save = new Saving(presentation);
        save.saveNew();
    }
}