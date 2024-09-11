package listeners.mouse;

import slide.*;
import slide.insertables.AbstractImageText;
import slide.insertables.InsertImage;
import slide.insertables.Insertable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

/**
 * Class for allowing insertable objects to be dragged around the screen.
 */
public class InsertableMouseMotionListener extends MouseMotionAdapter {
    private Insertable insertable;
    private Slide currentSlide;
    private int initialX, initialY;
    private double intialMouseX, intialMouseY;
    private boolean resizing;

    /**
     * Constructor method
     * @param insertable the insertable being moved
     * @param currentSlide the slide the user is currently on
     */
    public InsertableMouseMotionListener(Insertable insertable, Slide currentSlide){
        this.insertable = insertable;
        this.currentSlide = currentSlide;
        resizing = false;
    }

    /**
     * Method when the object is dragged
     * Also used for resizing
     * @param e the event to be processed
     */
    public void mouseDragged(MouseEvent e) {
        int buffer = 10; //How far from the edges can be selected to allow dragging

        //Gets the width and height of the object being dragged
        int width = insertable.getWidth();
        int height = insertable.getHeight();

        //Gets the location of the mouse
        Point p = MouseInfo.getPointerInfo().getLocation();

        //Checks if the bottom right has been clicked
        if ((initialX > insertable.getWidth()-buffer && initialY > insertable.getHeight()-buffer) || resizing){
            //Sets to true to allow the mouse to move around
            resizing = true;
            int widthScale = currentSlide.getOriginalWidth() / currentSlide.getWidth();

            //Gets the change in height and width

            int widthDiff = e.getX() - (insertable.getWidth());
            int heightDiff = e.getY() - (insertable.getHeight());
            
            //Sets new height and width. Uses scale to prevent height jumping around
            insertable.setObjectSize(insertable.getWidth()+widthDiff, insertable.getHeight()+heightDiff, false);
        }
        else if ((initialX < buffer || initialX > width - buffer || initialY < buffer || initialY > height - buffer) || insertable instanceof InsertImage) {
            //Edge clicked, so move it
            int newX = insertable.getX() + e.getX() - initialX;
            int newY = insertable.getY() + e.getY() - initialY;

            //Moves the object
            insertable.moveObject(newX, newY, false);
        }
    }

    /**
     * Method when the mouse is in the insertable
     * Used to set the cursor to show the user what actions they can perfrom
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e){
        int buffer = 10;
        int width = insertable.getWidth();
        int height = insertable.getHeight();

        //Sets the cursors depending on where the mouse is.
        if (insertable instanceof AbstractImageText) {

            AbstractImageText abstractImageText = (AbstractImageText) insertable;
            if (((e.getX() > insertable.getWidth()-buffer && e.getY() > insertable.getHeight()-buffer) || resizing)){
                abstractImageText.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            }
            else if ((e.getX() < buffer || e.getX() > width - buffer || e.getY() < buffer || e.getY() > height - buffer) || insertable instanceof InsertImage) {
                abstractImageText.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
            else{

                abstractImageText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }
        }
    }

    /**
     * Setter method for the initialX
     * @param initialX new initial X
     */
    public void setInitialX(int initialX){
        this.initialX = initialX;
    }

    /**
     * Setter method for the initialY
     * @param initialY the new initial Y
     */
    public void setInitialY(int initialY){
        this.initialY = initialY;
    }

    /**
     * Setter method for the initial x of the mouse pointer
     * @param initialMouseX the new initial x
     */
    public void setInitialMouseX(double initialMouseX){
        this.intialMouseX = initialMouseX;
    }

    /**
     * Setter method for the new initial y for the mouse pointer
     * @param initialMouseY the new initial y
     */
    public void setInitialMouseY(double initialMouseY){
        this.intialMouseY = initialMouseY;
    }

    /**
     * Setter method for the resizing boolean
     * Set to true when an object is being resized
     * @param resizing whether resizing is occurring or not.
     */
    public void setResizing(boolean resizing){
        this.resizing = resizing;
    }

}
