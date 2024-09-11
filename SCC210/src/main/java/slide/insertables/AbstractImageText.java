package slide.insertables;

import listeners.mouse.InsertableMouseListener;
import listeners.mouse.InsertableMouseMotionListener;
import slide.Slide;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import com.google.gson.annotations.Expose;

/**
 * Abstract class for Image and text.
 * Used as both make use of JTextPanes
 */
public abstract class AbstractImageText extends JTextPane implements Insertable {

    protected Slide slide;
    protected int width;
    protected int height;
    @Expose
    protected int originalWidthI;
    @Expose
    protected  int originalHeightI;
    @Expose
    protected  int originalX;
    @Expose
    protected  int originalY;
    protected InsertableMouseListener insertableMouseListener;
    protected  InsertableMouseMotionListener mouse;
    protected UndoManager undoManager;

    /**
     * Method for setting all the listeners needed for the object
     * @param slide the slide the object will belong too.
     */
    public void setUpListeners(Slide slide){
        undoManager = new UndoManager();
        getStyledDocument().addUndoableEditListener(undoManager);
        mouse = new InsertableMouseMotionListener(this, slide);
        addMouseMotionListener(mouse);

        insertableMouseListener= new InsertableMouseListener(this, slide, mouse);
        addMouseListener(insertableMouseListener);
    }

    /**
     * Method for turning off listens to prevent user interaction with the object
     * Used when entering present mode
     */
    public void disableListeners(){
        if (!(this instanceof CodeText))
            removeMouseListener(insertableMouseListener);
        removeMouseMotionListener(mouse);
    }

    /**
     * Method from the Insertable interface to move the object
     * @param x the x being moved too
     * @param y the y being moved too
     */
    @Override
    public void moveObject(int x, int y, boolean scale)
    {
        //Sets the new location of the object
        setLocation(x, y);

        //Gets the height and width of the frame
        int frameWidth = slide.getWidth();
        int frameHeight = (int) (frameWidth / slide.getSlideAspectRatio());

        //Calculates the scale currently in use so if the frame has been resized
        double widthScale = (double) frameWidth / slide.getOriginalWidth();
        double heightScale = (double) frameHeight / (slide.getOriginalHeight() / 1.137);

        //Sets the originalX and y to the appropriate value plus the scaled value.
        //this allows objects position to always be relative to full screen mode.

        if (!scale) {
            originalX = (int) (x / widthScale);
            originalY = (int) ((y) / heightScale);
        }

    }

    /**
     * Method from the insertable interface to delete an object
     */
    @Override
    public void deleteObject()
    {
        slide.remove(this);
        slide.revalidate();
        slide.repaint();

        if (this instanceof Text){
            slide.getTextBoxes().remove(this);
        }
        else if (this instanceof InsertImage){
            slide.getImages().remove(this);
        }
    }

    /**
     * Method for setting the size of an object
     * @param width new width being set
     * @param height new height being set
     */
    @Override
    public void setObjectSize(int width, int height, Boolean scale)
    {
        //Set the new widths and heights to the attributes
        this.width = width;
        this.height = height;

        //Resize the box
        setSize(width, height);
        repaint();
        revalidate();

        int frameWidth = slide.getWidth();
        int frameHeight = (int) (frameWidth / slide.getSlideAspectRatio());
        double widthScale = (double) slide.getWidth() / slide.getOriginalWidth();
        double heightScale = (double) slide.getHeight() / (slide.getOriginalHeight());


        if (!scale){
            originalHeightI = (int) (height / widthScale);
            originalWidthI = (int) (width / heightScale);
        }
    }

    /**
     * Scale method used to resize and move an object when the window is resized.
     * @param widthScale the amount the width has changed from 0 to 1
     * @param heightScale the amount the height has changed from 0 to 1
     */
    public void scale(double widthScale, double heightScale){
        // Calculates new widths and heights
        double aspectRatio = (double) originalWidthI / originalHeightI;
        int newWidth = (int) (originalWidthI *widthScale);
        int newHeight = (int) (newWidth/aspectRatio);

        // Set the new size
        if (newWidth != getWidth() && newHeight != getHeight())
            setObjectSize(newWidth, newHeight, true);

        //Calculates new x and y position
        int newX = (int) (originalX * widthScale);
        int newY = (int) (originalY * heightScale);

        //Sets new location
        if (newX != getX() && newY != getY())
            moveObject(newX, newY, true);
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public void setOriginalWidthI(int width){
        originalWidthI = width;
    }

    public void setOriginalHeightI(int height){
        originalHeightI = height;
    }

    public void setOriginalX(int x){
        originalX = x;
    }

    public void setOriginalY(int y){
        originalY = y;
    }

    public int getOriginalWidthI(){
        return originalWidthI;
    }

    public int getOriginalHeightI(){
        return originalHeightI;
    }

    public int getOriginalX(){
        return originalX;
    }

    public int getOriginalY(){
        return originalY;
    }







}
