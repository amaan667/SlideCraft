package listeners.window;

import presentation.Presentation;
import serialization.Saving;
import ui.UI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UIwindowListener extends WindowAdapter {

    private Presentation presentation;

    public UIwindowListener(Presentation presentation){
        this.presentation = presentation;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (presentation.getFilepath() == null){
            int choice = JOptionPane.showConfirmDialog(presentation.getUI().getFrame(), "You have unsaved changes. Do you want to save before closing?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);

            if (choice == JOptionPane.YES_OPTION){
                Saving saving = new Saving(presentation);
                saving.saveNew();
            }
            else if (choice == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }
        else{
            System.exit(0);
        }
    }
}
