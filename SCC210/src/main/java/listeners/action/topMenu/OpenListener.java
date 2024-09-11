package listeners.action.topMenu;

import presentation.Presentation;
import serialization.Loading;
import slide.Slide;
import ui.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenListener implements ActionListener {

    private Presentation presentation;

    public OpenListener(Presentation presentation){
        this.presentation = presentation;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Loading loading = new Loading(presentation);
        loading.loadPresentation();
    }
}