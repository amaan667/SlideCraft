package listeners.action.topMenu;

import presentation.Presentation;
import ui.ColourSelectorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the laser colour selector option in the menu bar
 */
public class LaserColourListener implements ActionListener {

    private Presentation presentation;

    /**
     * Constructor method for the action listener
     * @param presentation the presentation being edited
     */
    public LaserColourListener(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * Action performed method when the button is clicked
     * Creates a new colour picker menu and allows the user to pick a colour.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ColourSelectorUI colourSelectorUI = new ColourSelectorUI(presentation.getUI().getCurrentSlide(),false, true);
    }
}
