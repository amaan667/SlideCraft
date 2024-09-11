package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the underline button
 */
public class UnderlineButtonLogic extends SlideEditButton implements ActionListener {

    /**
     * Constructor method
     * @param slide the slide being edited
     */
    public UnderlineButtonLogic(Slide slide) {
        this.currentSlide = slide;
    }

    /**
     * Method when the button is pressed.
     * Used to underline selected text or undo it.
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

                    // Check if the selected text is not already underlined
                    if (!StyleConstants.isUnderline(attributes)) {
                        SimpleAttributeSet style = new SimpleAttributeSet();
                        StyleConstants.setUnderline(style, true);
                        styledDoc.setCharacterAttributes(start, end - start, style, false);
                    } else {
                        // If it's already underlined, remove the underline
                        SimpleAttributeSet style = new SimpleAttributeSet();
                        StyleConstants.setUnderline(style, false);
                        styledDoc.setCharacterAttributes(start, end - start, style, false);
                    }
                }
            }
        }
    }
}
