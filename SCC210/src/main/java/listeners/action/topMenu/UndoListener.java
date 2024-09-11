package listeners.action.topMenu;

import presentation.Presentation;
import slide.insertables.AbstractImageText;
import slide.insertables.Insertable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoListener implements ActionListener {

    private Presentation presentation;

    public UndoListener(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (presentation.getUI().getCurrentSlide().getCurrentInsertable() instanceof AbstractImageText){
            AbstractImageText imageText = (AbstractImageText) presentation.getUI().getCurrentSlide().getCurrentInsertable();
            if (imageText.getUndoManager().canUndo()){
                imageText.getUndoManager().undo();
            }
        }
    }
}
