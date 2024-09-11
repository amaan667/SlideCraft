package slide.threads;

import slide.Slide;

import javax.swing.*;

/**
 * Thread for saving the text in a speaker note automatically
 */
public class SpeakerNoteUpdater extends Thread{

    private JTextArea textArea;
    private Slide slide;
    private boolean running;

    /**
     * Constructor method
     * @param textArea the text box from the speaker note
     * @param slide the slide it belongs too.
     */
    public SpeakerNoteUpdater(JTextArea textArea, Slide slide){
        this.textArea = textArea;
        this.slide = slide;
        running = true;
    }

    /**
     * The run method for the thread.
     * This sets the speaker note in the slide to the text in the box
     */
    @Override
    public void run() {
        while (running) {
            slide.setSpeakerNotes(textArea.getText());

            if (slide.getPresentation().getUI().getCurrentSlide() != slide){
                slide = slide.getPresentation().getUI().getCurrentSlide();
                textArea.setText(slide.getSpeakerNotes());
            }

        }
    }

    /**
     * Getter method for the running boolean
     * @param running the new state of running
     */
    public void setRunning(boolean running){
        this.running = running;
    }
}
