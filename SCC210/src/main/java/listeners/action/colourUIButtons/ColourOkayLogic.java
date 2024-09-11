package listeners.action.colourUIButtons;

import slide.Slide;
import slide.insertables.CodeText;
import slide.insertables.Text;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ActionListener for the okay button is the colour selector menu
 */
public class ColourOkayLogic implements ActionListener
{
    private JColorChooser colorChooser;
    private Slide currentSlide;
    private JFrame frame;
    private boolean fillColour;
    private  boolean laserColour;
    private Text currentText;

    /**
     * Constructor method
     * @param colorChooser the colorChooser from the menu (Used to get the colour chosen)
     * @param currentSlide the slide the user is on
     * @param frame the actual JFrame for the colour menu
     * @param fillColour if the fill colour is being affected.
     */
    public ColourOkayLogic(JColorChooser colorChooser, JButton button, Slide currentSlide, JFrame frame, boolean fillColour, boolean laserColour)
    {
        this.colorChooser = colorChooser;
        this.currentSlide = currentSlide;
        this.laserColour = laserColour;
        this.frame = frame;
        this.fillColour = fillColour;
    }

    /**
     * Method when the okay button is pressed.
     * Updates the colour as appropriate.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        //If fill colour being affected change the background
        if (fillColour)
        {
            //Updates colour
            currentSlide.setBackground(colorChooser.getColor());
            Color color = colorChooser.getColor();

            currentSlide.setColourHex(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        }
        else if (laserColour){
            currentSlide.getPresentation().setLaserColour(colorChooser.getColor());
        }
        else {
            //Updates the selected text colour.
            if (currentSlide.getCurrentInsertable() instanceof Text)
                currentText = (Text) currentSlide.getCurrentInsertable();

            if (currentText != null) {
                // Get the selected text range
                int start = currentText.getSelectionStart();
                int end = currentText.getSelectionEnd();

                // If there is a selection
                if (start != end) {
                    StyledDocument styledDoc = currentText.getStyledDocument();
                    SimpleAttributeSet style = new SimpleAttributeSet();
                    StyleConstants.setForeground(style, colorChooser.getColor());
                    styledDoc.setCharacterAttributes(start, end - start, style, false);
                }
            }
        }
        frame.dispose(); //Removes the colour selector menu

    }
}