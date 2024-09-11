package presentation.threads;

import presentation.Presentation;
import slide.Slide;

import java.util.Objects;

public class TemplateUpdater extends Thread{

    private Presentation presentation;
    private Slide slide;

    public TemplateUpdater(Presentation presentation, Slide slide){
        this.presentation = presentation;
        this.slide = slide;
    }

    @Override
    public void run(){

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (Objects.equals(presentation.getTemplate(), "standard")){
            if (presentation.getSlideList().size() == 1)
                presentation.setFirstStandardTemplate(slide);
            else
                presentation.setStandardTemplate(slide);
        }
    }
}
