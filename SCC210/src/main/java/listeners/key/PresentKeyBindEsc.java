package listeners.key;

import listeners.action.uiButtons.SlidePreviewListener;
import presentation.PresentMode;
import presentation.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for the esc key bind in present mode
 */
public class PresentKeyBindEsc extends AbstractAction{

    private JFrame frame;
    private Presentation presentation;

    /**
     * Constructor method for the key bind
     * @param frame the frame the keybind is for (This is presentFrame)
     * @param presentation the presentation being presented
     */
    public PresentKeyBindEsc(JFrame frame, Presentation presentation){
        this.frame = frame;
        this.presentation = presentation;
    }

    /**
     * Action performed method of the key bind
     * Disposes of the present frame then set-ups the action listeners again
     * This is to allow editing again.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Removes th present window
        frame.dispose();

        //Loops through each slide
        for (int i = 0; i < presentation.getSlideList().size(); i++){
            presentation.getSlideList().get(i).setLocation(0, 0);

            //Sets up the listeners again.
            for (int j = 0; j < presentation.getSlideList().get(i).getImages().size(); j++)
                presentation.getSlideList().get(i).getImages().get(j).setUpListeners(presentation.getSlideList().get(i));

            for (int j = 0; j < presentation.getSlideList().get(i).getTextBoxes().size(); j++) {
                presentation.getSlideList().get(i).getTextBoxes().get(j).setUpListeners(presentation.getSlideList().get(i));
                presentation.getSlideList().get(i).getTextBoxes().get(j).setEditable(true);
            }
            presentation.getSlideList().get(i).setSlideSize(presentation.getUI().getFrame());
        }

        //Ensure the correct slide is set once presentation mode is exited
        SlidePreviewListener slidePreviewListener = new SlidePreviewListener(presentation.getUI().getCurrentSlide(), presentation, null, null);
        slidePreviewListener.actionPerformed(null);

    }
}
