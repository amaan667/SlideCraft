package listeners.action.editorRightClickMenu;

import slide.Slide;
import slide.insertables.InsertImage;
import slide.insertables.Text;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditorPasteListener implements ActionListener {

    private Slide slide;

    public EditorPasteListener(Slide slide){
        this.slide = slide;
    }

    /** method when button is pressed
     * pastes content to slide
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        slide.pasteOntoSlide();
    }
}
