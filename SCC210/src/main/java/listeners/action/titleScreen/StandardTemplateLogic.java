package listeners.action.titleScreen;

import presentation.Presentation;
import ui.TitleScreenUI;
import ui.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the standard template button in the title screen.
 */
public class StandardTemplateLogic implements ActionListener {

    private TitleScreenUI titleScreenUI;

    /**
     * Constructor method for the action listener
     * @param titleScreenUI
     */
    public StandardTemplateLogic(TitleScreenUI titleScreenUI){
        this.titleScreenUI = titleScreenUI;
    }

    /**
     * Action performed method.
     * Creates a new main UI and sets the template and closes the title screen.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        UI ui = new UI(new Presentation("standard"));
        titleScreenUI.getFrame().dispose();
    }
}
