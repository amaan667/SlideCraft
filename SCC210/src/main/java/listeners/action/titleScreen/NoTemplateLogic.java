package listeners.action.titleScreen;

import presentation.Presentation;
import ui.TitleScreenUI;
import ui.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the no template button on the title screen
 */
public class NoTemplateLogic implements ActionListener {

    private TitleScreenUI titleScreenUI;

    /**
     * Constructor method for the action listener
     * @param titleScreenUI the instance of the title screen
     */
    public NoTemplateLogic(TitleScreenUI titleScreenUI){
        this.titleScreenUI = titleScreenUI;
    }

    /**
     * Action performed method when the button is clicked
     * Creates a new main UI with no template and closes the title screen
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        UI ui = new UI(new Presentation("noTemplate"));
        titleScreenUI.getFrame().dispose();
    }
}
