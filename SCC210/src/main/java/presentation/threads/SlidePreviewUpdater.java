package presentation.threads;

import presentation.Presentation;

import javax.swing.*;

/**
 * Class for a new thread which upddates the slide previews
 */
public class SlidePreviewUpdater extends Thread{
    Presentation presentation;

    /**
     * Constructor method for the new thread
     * @param presentation the presentation previews being updated
     */
    public SlidePreviewUpdater(Presentation presentation){
        this.presentation = presentation;
    }

    /**
     * Run method for the thread.
     * Used to update the preview of the slide the user is on every 500ms
     */
    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> {
                // Updates the preview
                presentation.updatePreviews(presentation.getUI().getCurrentSlide());
            });

            try {
                //500 ms time delay
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
