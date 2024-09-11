package listeners.action.topMenu;

import presentation.Presentation;
import serialization.AutoSave;
import serialization.Saving;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoSaveListener implements ActionListener {

    private JToggleButton autosave;

    private Presentation presentation;

    private AutoSave auto;

    private Saving save;
    public AutoSaveListener(Presentation presentation){
        this.presentation = presentation;
        save = new Saving(presentation);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!presentation.getAuto()){
            if(presentation.getFilepath() == null){
                save.saveNew();
            }
            if(presentation.getFilepath() != null){
                auto = new AutoSave(presentation);
                auto.start();
                presentation.setAuto(true);
            }
        }else{
            presentation.setAuto(false);
        }
    }
}