package listeners.action.topMenu;

import presentation.Presentation;
import serialization.Saving;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SaveListener implements ActionListener {

    private Presentation presentation;

    public SaveListener(Presentation presentation){
        this.presentation = presentation;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Saving save = new Saving(presentation);
        if(presentation.getFilepath() == null){
            save.saveNew();
        }
        else{
            save.saveExisting();
        }
    }
}
