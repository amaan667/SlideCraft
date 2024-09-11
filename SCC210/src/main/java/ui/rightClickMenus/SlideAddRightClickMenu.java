package ui.rightClickMenus;

import listeners.action.slideAddRightClickMenu.AddNoThemeLogic;
import listeners.action.slideAddRightClickMenu.AddNormalLogic;
import listeners.action.slideAddRightClickMenu.AddStandardLogic;
import presentation.Presentation;
import slide.insertables.Text;

import javax.swing.*;

/**
 * Right click menu for the slide add button.
 * Used add a new side with different templates
 */
public class SlideAddRightClickMenu extends JPopupMenu {

    private Presentation presentation;
    private JButton button;

    /**
     * Constructor method for the right click menu.
     * Displays the pop-up menu
     * @param presentation the presentation being made
     * @param button the actual button
     */
    public SlideAddRightClickMenu(Presentation presentation, JButton button){
        super();
        this.presentation = presentation;
        this.button = button;

        //Defines the menu
        JMenuItem addNormal = new JMenuItem("Add no template slide");
        JMenuItem addStandard = new JMenuItem("Add standard slide");
        JMenuItem addNoTheme = new JMenuItem("Add without theme");
        addNoTheme.addActionListener(new AddNoThemeLogic(presentation));
        addNormal.addActionListener(new AddNormalLogic(presentation));
        addStandard.addActionListener(new AddStandardLogic(presentation));
        add(addNormal);
        add(addStandard);
        add(addNoTheme);
    }
}
