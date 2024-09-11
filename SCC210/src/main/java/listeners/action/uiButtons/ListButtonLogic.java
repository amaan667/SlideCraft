package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListButtonLogic extends TextFormatButton implements ActionListener {


    /**
     * Constructor method
     *
     * @param newSlide the slide being edited
     */
    public ListButtonLogic(Slide newSlide) {
        super(newSlide);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //Checks the user is selected on a text box
            if (currentSlide.getCurrentInsertable() instanceof Text)
                text = (Text) currentSlide.getCurrentInsertable(); // Downcast to a Text

            //Checks if lists are in use or not
            if (!text.getList()) {
                //They are so, add bullet point
                text.setList(true);
                text.insertBulletPoint();
            } else {
                //Disable bullet points
                text.setList(false);
            }
        }
        catch (NullPointerException ex){}
    }
}
