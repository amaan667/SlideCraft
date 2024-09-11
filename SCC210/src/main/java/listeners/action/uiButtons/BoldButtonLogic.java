package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for when the bold button is pressed
 */
public class BoldButtonLogic extends TextFormatButton implements ActionListener {

    /**
     * Constructor method
     * @param newSlide the slide being edited
     */
    public BoldButtonLogic(Slide newSlide){

        super(newSlide);
    }

    /**
     * Method when the button is pressed.
     * Used to turn selected text bold or undo it.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentSlide.getCurrentInsertable() instanceof Text) {
            Text currentText = (Text) currentSlide.getCurrentInsertable();

            if (currentText != null) {
                // Get the selected text range
                int start = currentText.getSelectionStart();
                int end = currentText.getSelectionEnd();

                // If there is a selection
                if (start != end) {
                    StyledDocument styledDoc = currentText.getStyledDocument();
                    Element element = styledDoc.getCharacterElement(start);
                    AttributeSet attributes = element.getAttributes();

                    // Check if the selected text is not already bold
                    if (!StyleConstants.isBold(attributes)) {
                        SimpleAttributeSet style = new SimpleAttributeSet();
                        StyleConstants.setBold(style, true);
                        styledDoc.setCharacterAttributes(start, end - start, style, false);
                    } else {
                        // If it's already bold, remove the bold
                        SimpleAttributeSet style = new SimpleAttributeSet();
                        StyleConstants.setBold(style, false);
                        styledDoc.setCharacterAttributes(start, end - start, style, false);
                    }
                }
            }
        }
    }
}