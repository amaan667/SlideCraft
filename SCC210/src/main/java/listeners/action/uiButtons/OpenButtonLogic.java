package listeners.action.uiButtons;

import presentation.Presentation;
import serialization.Loading;
import slide.Slide;
import ui.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenButtonLogic implements ActionListener {


    private Presentation presentation;
    public OpenButtonLogic(Presentation presentation){
        this.presentation = presentation;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Loading loading = new Loading(presentation);
        loading.loadPresentation();


    }
}