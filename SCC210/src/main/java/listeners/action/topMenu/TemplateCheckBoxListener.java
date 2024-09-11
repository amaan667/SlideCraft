package listeners.action.topMenu;

import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Action Listener for the checkboxes to change the template in the menu bar.
 */
public class TemplateCheckBoxListener implements ActionListener {

    private JCheckBoxMenuItem checkBoxMenuItem;
    private JCheckBoxMenuItem other;
    private Presentation presentation;

    /**
     * Constructor method
     * @param checkBoxMenuItem the selected checkbox
     * @param other the other checkbox
     * @param presentation the current presentation
     */
    public TemplateCheckBoxListener(JCheckBoxMenuItem checkBoxMenuItem, JCheckBoxMenuItem other, Presentation presentation){
        this.checkBoxMenuItem = checkBoxMenuItem;
        this.other = other;
        this.presentation = presentation;
    }

    /**
     * Action performed method when the checkbox is selected
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Sets the one clicked to true and other to false
        checkBoxMenuItem.setState(true);
        other.setState(false);

        //Sets the template in the presentation
        if (Objects.equals(presentation.getTemplate(), "noTemplate")){
            presentation.setTemplate("standard");
        }
        else{
            presentation.setTemplate("noTemplate");
        }
    }
}
