package slide.threads;

import slide.Slide;

public class SlideSizeUpdater extends Thread {

    private Slide slide;

    public SlideSizeUpdater(Slide slide){
        this.slide = slide;
    }

    @Override
    public void run(){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        slide.setOriginalHeight(slide.getHeight());
        slide.setOriginalWidth(slide.getWidth());
    }


}
