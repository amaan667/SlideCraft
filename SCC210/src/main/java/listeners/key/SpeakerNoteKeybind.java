package listeners.key;

import slide.Slide;
import slide.SpeakerNote;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SpeakerNoteKeybind extends AbstractAction {
   private Slide slide;
   public SpeakerNoteKeybind(Slide slide){
       this.slide = slide;
   }

   @Override
    public void actionPerformed(ActionEvent actionEvent) {
       SpeakerNote speakerNote = new SpeakerNote(slide);
    }

    public void setSlide(Slide slide){
       this.slide = slide;
    }

}
