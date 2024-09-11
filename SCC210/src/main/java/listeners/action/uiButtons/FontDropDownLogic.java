package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the font comboBox.
 */
public class FontDropDownLogic extends SlideEditButton implements ActionListener {

    private Font[] fonts;
    private JComboBox dropDown;
    private Text currentText;

    /**
     * Constructor method for the actionListener
     * @param currentSlide The slide the use is on
     * @param fonts //The fonts avaliable
     * @param dropDown //The JComboBox
     */
    public FontDropDownLogic(Slide currentSlide, Font[] fonts, JComboBox dropDown)
    {
        this.currentSlide = currentSlide;
        this.fonts = fonts;
        this.dropDown = dropDown;
    }

    /**
     * Method when an option is selected from the JComboBox
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //If the current selected insertable is a text box downcast
        if (currentSlide.getCurrentInsertable() instanceof Text)
            currentText = (Text)currentSlide.getCurrentInsertable();

        if (currentText != null) {
            // Get the selected text range
            int start = currentText.getSelectionStart();
            int end = currentText.getSelectionEnd();

            // If there is a selection
            if (start != end) {
                StyledDocument styledDoc = currentText.getStyledDocument();
                SimpleAttributeSet style = new SimpleAttributeSet();
                StyleConstants.setFontFamily(style, dropDown.getSelectedItem().toString());
                //Set the font
                styledDoc.setCharacterAttributes(start, end - start, style, false);
            }
            else{
                currentText.setFont(Font.decode(dropDown.getSelectedItem().toString()));
            }
        }
    }
}
