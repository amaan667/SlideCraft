package listeners.action.colourUIButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the cancel button on the colour selector menu
 */
public class ColourCancelLogic implements ActionListener
{
    private final JFrame frame;

    /**
     * Constructor method
     * @param frame the JFrame for the colour selector menu
     */
    public ColourCancelLogic(JFrame frame)
    {
        this.frame = frame;
    }

    /**
     * Methods when the button is pressed
     * Deleted the menu
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

}
