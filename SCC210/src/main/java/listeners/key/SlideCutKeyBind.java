package listeners.key;

import listeners.action.objectRightClickMenu.CopyListener;
import slide.Slide;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlideCutKeyBind extends AbstractAction {

    private Slide slide;

    public SlideCutKeyBind(Slide slide){
        this.slide = slide;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CopyListener copyListener = new CopyListener(slide.getCurrentInsertable(), true);
        copyListener.actionPerformed(null);
        slide.getCurrentInsertable().deleteObject();
    }

    public void setSlide(Slide slide){
        this.slide = slide;
    }
}
