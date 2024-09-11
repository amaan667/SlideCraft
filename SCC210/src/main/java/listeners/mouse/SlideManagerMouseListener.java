package listeners.mouse;

import slide.Slide;
import ui.rightClickMenus.InsertableRightClickMenu;
import ui.rightClickMenus.SlideRightClickMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class for the mouse listener for slide previews
 */
public class SlideManagerMouseListener extends MouseAdapter {

    private Slide slide;


    /**
     * Constructor method
     * @param slide the slide for which the listener is for.
     */
    public SlideManagerMouseListener(Slide slide){
        this.slide = slide;
    }

    /**
     * Method when the user clicks on a slide preview
     * Used to open the right click menu
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //Checks if right-clicked
        if (e.getButton() == MouseEvent.BUTTON3) {
            //If so displays the menu
            SlideRightClickMenu rightClickMenu = new SlideRightClickMenu(slide);
            rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

}
