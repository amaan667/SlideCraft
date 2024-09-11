package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the size input box
 */
public class SizeInputLogic extends SlideEditButton implements ActionListener {

    private JTextField currentSize;
    private Text currentText;

    /**
     * Constructor method
     * @param currentSize the input box (Used to get the input)
     * @param currentSlide the current slide the user is on
     */
    public SizeInputLogic(JTextField currentSize, Slide currentSlide){
        this.currentSize = currentSize;
        this.currentSlide = currentSlide;
    }

    /**
     * Method when the user inputs a new size
     * Used to update selected text to that size.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){

        for (int i = 0; i < 2; i++) {
            //Checks if selected insertable is text
            if (currentSlide.getCurrentInsertable() instanceof Text)
                currentText = (Text) currentSlide.getCurrentInsertable(); // It is so downcast it.

            if (currentText != null) {
                // Get the selected text range
                int start = currentText.getSelectionStart();
                int end = currentText.getSelectionEnd();

                // If there is a selection
                if (start != end) {
                    StyledDocument styledDoc = currentText.getStyledDocument();
                    SimpleAttributeSet style = new SimpleAttributeSet();

                    // Handle when user enters non numbers
                    try {
                        int newSize = Integer.parseInt(currentSize.getText());
                        double widthScale = (double) currentSlide.getWidth() / currentSlide.getOriginalWidth();
                        System.out.println(currentSlide.getOriginalWidth());

                        newSize = (int) (newSize * widthScale);

                        StyleConstants.setFontSize(style, newSize);
                    } catch (NumberFormatException ex) {
                        currentSize.setText(String.valueOf(currentText.getFont().getSize()));
                    }

                    styledDoc.setCharacterAttributes(start, end - start, style, false);
                    currentText.updateSizes();
                    currentSlide.setSlideSize(currentSlide.getPresentation().getUI().getFrame());
                }
            }
        }

    }
}