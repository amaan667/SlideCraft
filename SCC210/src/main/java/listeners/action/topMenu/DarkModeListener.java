package listeners.action.topMenu;

import ui.UI;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DarkModeListener implements ItemListener {

    private JToggleButton switchButton;
    private UI ui;

    private Icon darkMode;

    private Icon lightMode;

    public DarkModeListener(UI ui, JToggleButton switchButton){
        this.switchButton = switchButton;
        this.ui = ui;
        darkMode = ui.createButtonIcon(new ImageIcon(getClass().getResource("/night.png")), 20, 20);
        lightMode = ui.createButtonIcon(new ImageIcon(getClass().getResource("/cloudy-day.png")), 20, 20);


    }


    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(itemEvent.getStateChange() == ItemEvent.SELECTED){
            switchButton.setIcon(darkMode);
            ui.setColourMode(true);
            ui.setDark(true);
        }
        else{
            switchButton.setIcon(lightMode);
            ui.setColourMode(false);
            ui.setDark(false);
        }
    }
}
