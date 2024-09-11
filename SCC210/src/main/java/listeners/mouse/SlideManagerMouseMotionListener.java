package listeners.mouse;

import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

public class SlideManagerMouseMotionListener extends MouseMotionAdapter {

    private Slide slide;
    private boolean move;


    public SlideManagerMouseMotionListener(Slide slide){
        this.slide = slide;
        move = true;
    }

    /**
     * Method when the mouse is released
     * Used to allow rearrangement of slides by drag and drop.
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getX());

        //Gets location of mouse
        Point p = MouseInfo.getPointerInfo().getLocation();
        LinkedList<JButton> slidePreviews = slide.getPresentation().getSlidePreviews(); //Gets the previews

        //Checks It's not just a regular click
        if (move) {
            int indexOfSlide = -1;
            for (int i = 0; i < slidePreviews.size(); i++) {
                //Used to get location of the mouse when released
                p = MouseInfo.getPointerInfo().getLocation();
                //Used to get location of each slide preview
                Component component = slide.getPresentation().getUI().getSlideManager().getComponent(i);

                //Checks if the Mouse was released over a slide preview
                if (p.x >= component.getLocationOnScreen().x && p.x <= component.getLocationOnScreen().x + 200) {
                    if (e.getY() >= slidePreviews.get(i).getLocation().y && e.getY() <= slidePreviews.get(i).getLocation().y + 100) {
                        indexOfSlide = i; //Index of the slide to swap
                    }
                }
            }

            // Checks if a swap was found
            if (indexOfSlide > -1) {
                //Removes the slide then re adds in new location
                int index = slide.getPresentation().getSlideList().indexOf(slide);
                slide.getPresentation().getSlideList().remove(slide);
                slide.getPresentation().getSlideList().add(indexOfSlide, slide);

                //Removes the preview then re adds it to the Linked list
                JButton button = slide.getPresentation().getSlidePreviews().get(index);
                slide.getPresentation().getSlidePreviews().remove(index);
                slide.getPresentation().getSlidePreviews().add(indexOfSlide, button);

                //Updates the slide manager
                slide.getPresentation().updateSlideOrder();
                slide.getPresentation().setOnScreenSlide(slide.getPresentation().getUI().getCurrentSlide());
            }
        }
        // Resets the move
        move = true;
    }

    /**
     * Method to determine if the user is moving slide order
     * @param move ture or false
     */
    public void setMove(boolean move){
        this.move = move;
    }
}
