package listeners.mouse;

import slide.Slide;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SlideMouseMotionListener extends MouseMotionAdapter {

    private Slide slide;

    public SlideMouseMotionListener(Slide slide){
        this.slide = slide;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        slide.setMouseLocationX(mouseEvent.getX());
        slide.setMouseLocationY(mouseEvent.getY());
    }
}
