package listeners.action.uiButtons;

import presentation.Presentation;
import serialization.Saving;
import slide.Slide;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButtonLogic implements ActionListener {

    private Presentation presentation;
    private Slide currentSlide;

    public SaveButtonLogic(Presentation presentation){
        this.presentation = presentation;
        currentSlide = presentation.getUI().getCurrentSlide();
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