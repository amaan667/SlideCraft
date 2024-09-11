package listeners.action.topMenu;
import ui.HelpFrame;
import ui.TitleScreenUI;
import ui.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the no template button on the title screen
 */
public class
HelpButtonLogic implements ActionListener{

    private boolean dark;
    private UI ui;


    /**
     * Action performed method when the button is clicked
     * Creates a new main UI with no template and closes the title screen
     * @param actionEvent the event to be processed
     */

    public HelpButtonLogic(Boolean dark, UI ui){
        this.dark = dark;
        this.ui = ui;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //the UI
        HelpFrame helpFrame = new HelpFrame();
        helpFrame.setUI(ui);
        helpFrame.setColourMode(ui.getDark());

    }
}