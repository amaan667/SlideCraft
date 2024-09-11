package listeners.action.uiButtons;

import slide.insertables.CodeText;
import slide.insertables.InsertImage;
import slide.Slide;
import slide.insertables.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the insert JComboBox
 */
public class InsertDropDownLogic extends SlideEditButton implements ActionListener {

    private JComboBox thisComboBox;
    private Text text;

    /**
     * Constructor method
     * @param thisComboBox the JComboBox (Used to get selected item)
     * @param currentSlide the slide the user is on
     */
    public InsertDropDownLogic(JComboBox thisComboBox, Slide currentSlide)
    {
        this.thisComboBox = thisComboBox;
        this.currentSlide = currentSlide;
    }

    /**
     * Method when the user selects an option
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Checks what option the user has selected
        if (thisComboBox.getSelectedItem() == "Text")
        {
            //Adds text
            currentSlide.addText(new Text(currentSlide));
        }
        else if (thisComboBox.getSelectedItem() == "Image"){
            //Adds an image
            InsertImage newImage = new InsertImage(currentSlide);
            newImage.setImage();
            currentSlide.addImage(newImage);
        }
        else if (thisComboBox.getSelectedItem() == "CodeText"){
            currentSlide.addText(new CodeText(currentSlide));
        }
        else if (thisComboBox.getSelectedItem() == "List")
        {
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
        else if (thisComboBox.getSelectedItem() == "New Slide"){
            Slide slide = new Slide(currentSlide.getPresentation());
            currentSlide.getPresentation().addSlide(slide);
        }
    }
}
