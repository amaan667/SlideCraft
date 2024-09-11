package listeners.action.topMenu;

import presentation.Presentation;
import slide.insertables.AbstractImageText;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoListener implements ActionListener {

    private Presentation presentation;

    public RedoListener(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (presentation.getUI().getCurrentSlide().getCurrentInsertable() instanceof AbstractImageText){
            AbstractImageText imageText = (AbstractImageText) presentation.getUI().getCurrentSlide().getCurrentInsertable();
            if (imageText.getUndoManager().canRedo()){
                imageText.getUndoManager().redo();
            }
        }
    }
}
