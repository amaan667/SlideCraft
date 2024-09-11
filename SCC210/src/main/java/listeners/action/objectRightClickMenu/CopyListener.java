package listeners.action.objectRightClickMenu;

import serialization.ImageSelection;
import slide.insertables.InsertImage;
import slide.insertables.Insertable;
import slide.insertables.Text;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

/**
 * action listener for when copy is clicked
 */
public class  CopyListener implements ActionListener {

    private Insertable current;
    private Boolean cut;

    /** constructor method
     *
     * @param current the current object being copied
     * @param cut the current object that is removed
     */
    public CopyListener(Insertable current, Boolean cut){
        this.current = current;
        this.cut = cut;
    }

    /** method for when button is pressed
     * checks if any text has been added already
     * adds copied text or image
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (current instanceof Text) {
            Text currentText = (Text) current;
            StyledDocument styledDoc = currentText.getStyledDocument();
            int start = currentText.getSelectionStart();
            int end = currentText.getSelectionEnd();

            String text = "";
            try {
                text = styledDoc.getText(start, end - start);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }

            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            if (cut){
                try {
                    styledDoc.remove(start, end-start);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if (current instanceof InsertImage){
            InsertImage image = (InsertImage) current;
            Image copyImage = image.getIcon().getImage();

            Transferable transferable = new ImageSelection(copyImage);

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            clipboard.setContents(transferable, null);

        }
    }
}
