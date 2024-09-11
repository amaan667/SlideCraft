package slide;

import listeners.action.uiButtons.SpeakerNotesButtonLogic;
import slide.threads.SpeakerNoteUpdater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class for creating a speaker note on the screen.
 * Speaker notes are a separate pop up window
 */
public class SpeakerNote {

    private Slide currentSlide;
    private JTextArea speakerNotesBox;

    /**
     * Constructor method. This is used to create the speaker note
     * @param currentSlide the slide the speaker note is for.
     */
    public SpeakerNote(Slide currentSlide){
        this.currentSlide = currentSlide;

        currentSlide.addSpeakerNotes(new SpeakerNotesButtonLogic(currentSlide));
        //Creates a new Frame and sets properties
        JFrame speakerNotes = new JFrame();
        speakerNotes.setSize(400,250);
        speakerNotes.setVisible(true);
        speakerNotes.setAlwaysOnTop(true);
        speakerNotes.setResizable(false);
        speakerNotes.setTitle("Speaker Note");
        JPanel panel = new JPanel(new GridLayout());

        //Adds the JTextArea to be typed into
        speakerNotesBox = new JTextArea();

        //Sets the text if there is already a saved speaker note for that slide
        speakerNotesBox.setText(currentSlide.getSpeakerNotes());

        panel.add(speakerNotesBox);
        speakerNotes.add(panel);

        //Starts a thread which updates the speaker note attribute in the slide class to what's written in the box.
        SpeakerNoteUpdater speakerNoteUpdater = new SpeakerNoteUpdater(speakerNotesBox, currentSlide);
        speakerNoteUpdater.start();

        //When the speaker note window closes this stops the thread running
        speakerNotes.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                speakerNoteUpdater.setRunning(false);
            }

        });
    }
}

