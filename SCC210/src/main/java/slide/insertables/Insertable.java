package slide.insertables;

import slide.Slide;

/**
 * Interface defining what an insertable object will look like.
 * Is used mainly to allow object to be dragged around the screen.
 */
public interface Insertable {

    /**
     * Method for moving an object
     * @param x the x being moved too
     * @param y the y being moved too
     */
    default void moveObject(int x, int y, boolean scale){}

    /**
     * Method for deleting an object
     */
    default void deleteObject(){}

    /**
     * Getter method for the xPos of an object
     * @return the xPos
     */
    default int getX(){
        return 0;
    }

    /**
     * Getter method for the yPos of an object
     * @return the yPos
     */
    default int getY(){
        return 0;
    }

    /**
     * Getter method for the width of an object
     * @return the width.
     */
    default int getWidth() { return 0; }

    /**
     * Getter method for the height of an object
     * @return the height
     */
    default int getHeight() { return 0; }

    /**
     * Method for resizing the object
     * @param width the new width
     * @param height the new height
     */
    default void setObjectSize(int width, int height, Boolean scale) {}

    /**
     * Scales the insertable when the window is resized
     * @param widthScale the amount the width has changed from 0 to 1
     * @param heightScale the amount the height has changed from 0 to 1
     */
    default void scale(double widthScale, double heightScale){}

}
