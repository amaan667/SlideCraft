package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.InsertImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageButtonLogic extends TextFormatButton implements ActionListener {
    /**
     * Constructor method
     *
     * @param newSlide the slide being edited
     */
    public ImageButtonLogic(Slide newSlide) {
        super(newSlide);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InsertImage newImage = new InsertImage(currentSlide);
        newImage.setImage();
        currentSlide.addImage(newImage);
    }
}
