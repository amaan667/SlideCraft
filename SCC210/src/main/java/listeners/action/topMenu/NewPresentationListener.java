package listeners.action.topMenu;

import presentation.Presentation;
import presentation.threads.TemplateUpdater;
import serialization.PresentationLoader;
import slide.Slide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPresentationListener implements ActionListener {

    private Presentation presentation;
    private String template;

    public NewPresentationListener(Presentation presentation, String template){
        this.presentation = presentation;
        this.template = template;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        presentation.setFilepath(null);

        for (int i = 0; i < presentation.getSlideList().size()-1; i++){
            presentation.deleteSlide(presentation.getSlideList().get(i));
        }

        Slide slide = new Slide(presentation);
        presentation.addSlide(slide);

        presentation.deleteSlide(presentation.getSlideList().get(0));
        presentation.getUI().setFrameTitle("Untitled Presentation");

        TemplateUpdater templateUpdater = new TemplateUpdater(presentation, slide);
        templateUpdater.start();

        presentation.setTemplate(template);
    }
}
