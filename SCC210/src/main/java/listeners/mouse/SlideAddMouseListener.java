package listeners.mouse;

import presentation.Presentation;
import ui.rightClickMenus.EditorRightClickMenu;
import ui.rightClickMenus.SlideAddRightClickMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SlideAddMouseListener extends MouseAdapter {

    private JButton button;
    private Presentation presentation;

    public SlideAddMouseListener(JButton button, Presentation presentation){
        this.button = button;
        this.presentation = presentation;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            //If so displays the menu
            SlideAddRightClickMenu slideAddRightClickMenu = new SlideAddRightClickMenu(presentation, button);
            slideAddRightClickMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
