package listeners.action.uiButtons;

import listeners.key.SpeakerNoteKeybind;
import slide.Slide;
import slide.SpeakerNote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SpeakerNotesButtonLogic extends SlideEditButton implements ActionListener {


    public SpeakerNotesButtonLogic(Slide slide){
        currentSlide = slide;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SpeakerNote speakerNote = new SpeakerNote(currentSlide);
    }
}


