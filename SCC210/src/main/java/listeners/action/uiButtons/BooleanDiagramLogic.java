package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.InsertImage;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for the boolean diagram drop down menu
 */
public class BooleanDiagramLogic extends SlideEditButton implements ActionListener {

    private JComboBox thisComboBox;

    /**
     * Constructor method for the action listener
     * @param insertBooleanDiagram the drop-down menu.
     * @param currentSlide the slide the user is currently on.
     */
    public BooleanDiagramLogic(JComboBox insertBooleanDiagram, Slide currentSlide) {
        this.currentSlide = currentSlide;
        thisComboBox = insertBooleanDiagram;

    }

    /**
     * Action performed method for when the user selects a drop-down option.
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // If else for which option is picked then inserting that as an image onto the slide.
        if (thisComboBox.getSelectedItem() == "AND") {
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/AND.png")));
            currentSlide.addImage(andImage);

        }
        else if (thisComboBox.getSelectedItem() == "OR"){
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/OR.png")));
            currentSlide.addImage(andImage);

        } else if (thisComboBox.getSelectedItem() == "NOT") {
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/NOT.png")));
            currentSlide.addImage(andImage);

        }
        else if (thisComboBox.getSelectedItem() == "NOR"){
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/NOR.png")));
            currentSlide.addImage(andImage);

        }
        else if (thisComboBox.getSelectedItem() == "NAND") {
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/NAND.png")));
            currentSlide.addImage(andImage);
        }

        else if (thisComboBox.getSelectedItem() == "XOR") {
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/XOR.png")));
            currentSlide.addImage(andImage);
        }
       else if (thisComboBox.getSelectedItem() == "Line") {
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/LINE.png")));
            currentSlide.addImage(andImage);
        }
        else if (thisComboBox.getSelectedItem() == "Vertical Line") {
            InsertImage andImage = new InsertImage(currentSlide);
            andImage.setImage(new ImageIcon(getClass().getResource("/verticalLine.png")));
            currentSlide.addImage(andImage);

        }
    }
}
