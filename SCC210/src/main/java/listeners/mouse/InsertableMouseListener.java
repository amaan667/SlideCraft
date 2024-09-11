package listeners.mouse;

import slide.Slide;
import slide.insertables.*;
import slide.threads.CodeHighlighter;
import ui.rightClickMenus.InsertableRightClickMenu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Class for handling mouse events for insertable
 * Used to detect right clicks and left clicks.
 */
public class InsertableMouseListener extends MouseAdapter {

    private Insertable insertable;
    private Slide currentSlide;
    private int initialX, initialY;
    private double intialMouseX, intialMouseY;
    private InsertableMouseMotionListener insertableMouseMotionListener;

    /**
     * Constructor method
     * @param insertable the insertable being moved
     * @param currentSlide the slide the user is currently on
     */
    public InsertableMouseListener(Insertable insertable, Slide currentSlide, InsertableMouseMotionListener insertableMouseMotionListener){
        this.insertable = insertable;
        this.currentSlide = currentSlide;
        this.insertableMouseMotionListener = insertableMouseMotionListener;
    }

    /**
     * Method when an object is pressed
     * Displays right click menu or passes initial x and y values
     * to mouse motion listener for smoother dragging.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e){

        //Checks if right-clicked
        if (e.getButton() == MouseEvent.BUTTON3) {
            //If so displays the menu
            InsertableRightClickMenu rightClickMenu = new InsertableRightClickMenu(insertable, currentSlide);
            rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
        }
        else{
            //Gets initial location of the press
            insertableMouseMotionListener.setInitialX(e.getX());
            insertableMouseMotionListener.setInitialY(e.getY());
            Point p = MouseInfo.getPointerInfo().getLocation();
            insertableMouseMotionListener.setInitialMouseX(p.getX());
            insertableMouseMotionListener.setInitialMouseY(p.getY());

            //Sets the current insertable to the object clicked
            currentSlide.setCurrentInsertable(insertable);

            //Sets the border of this object if it is a text box.
            if (insertable instanceof Text current){
                ArrayList<Text> textBoxes = currentSlide.getTextBoxes();

                //Removes all borders
                for (Text textBox : textBoxes) textBox.setBorder(null);

                //Sets the border
                current.displayBorder();
                current.setEditable(true);
            }
            else
                currentSlide.getPresentation().getUI().getSlideEditor().requestFocusInWindow(); //Ensures keybinding still works

            if (insertable instanceof CodeText){
                CodeText codeText = (CodeText) insertable;

                CodeHighlighter codeHighlighter = new CodeHighlighter(codeText, currentSlide.getPresentation());
                codeHighlighter.start();
            }
        }
    }

    /**
     * Method when the mouse is released off an insertable
     * Used to stop resizing
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e){
        insertableMouseMotionListener.setResizing(false);
    }
}
