package listeners.action.slideRightClickMenu;

import slide.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the paste button in the slide right click menu
 */
public class SlidePasteLogic implements ActionListener {

    private Slide slide;

    /**
     * Constructor method
     * @param slide the slide the user right-clicked on
     */
    public SlidePasteLogic(Slide slide){
        this.slide = slide;
    }

    /**
     * Method when the button is pressed
     * Adds the slide from the clipboard to the slide deck
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Creates new slide
        Slide newSlide = new Slide(slide.getPresentation());

        // Copies contents of slide from the clipboard
        try {
            //Checks the slide is not null
            slide.getPresentation().setOnScreenSlide(slide);
            slide.getPresentation().addSlide(newSlide);

            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                newSlide.clone(newSlide.getPresentation().getSlideClipboard());
            });

        }
        catch (NullPointerException ex) {
            // Displays error message
            JOptionPane.showMessageDialog(slide, "Slide clipboard is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
