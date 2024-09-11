package listeners.key;

import listeners.action.objectRightClickMenu.CopyListener;
import listeners.action.slideRightClickMenu.SlideCopyLogic;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlideCopyKeybind extends AbstractAction {

    private Slide slide;

    public SlideCopyKeybind(Slide slide){
        this.slide = slide;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CopyListener copyListener = new CopyListener(slide.getCurrentInsertable(), false);
        copyListener.actionPerformed(null);
    }

    public void setSlide(Slide slide){
        this.slide = slide;
    }
}
