package listeners.action.objectRightClickMenu;

import slide.insertables.Insertable;
import slide.insertables.Text;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.IOException;

/**
 * action listener for when paste is clicked
 */
public class PasteListener implements ActionListener {

    private Insertable current;

    /** constructor method
     *
     * @param current current object to be pasted
     */
    public PasteListener(Insertable current){
        this.current = current;
    }

    /** method for when button is clicked
     * checks if there's anything that has been copied
     * pastes content
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (current instanceof Text){
            Text currentText = (Text) current;
            StyledDocument styledDoc = currentText.getStyledDocument();

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            String clipboardText = null;

            try {
                clipboardText = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            int caretPosition = currentText.getCaretPosition();
            try {
                styledDoc.insertString(caretPosition, clipboardText, null);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }

        }
    }
}
