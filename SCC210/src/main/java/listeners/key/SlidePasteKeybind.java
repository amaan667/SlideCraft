package listeners.key;

import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlidePasteKeybind extends AbstractAction {

    private Slide slide;

    public SlidePasteKeybind(Slide slide){
        this.slide = slide;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            slide.pasteOntoSlide();
        }catch (OutOfMemoryError ex){}
    }

    public void setSlide(Slide slide){
        this.slide = slide;
    }
}
