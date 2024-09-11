package listeners.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import slide.*;
import slide.insertables.Text;
import ui.rightClickMenus.EditorRightClickMenu;
import ui.rightClickMenus.InsertableRightClickMenu;

/**
 * MouseListener for the slide.
 * Used to allow text boxes to be deselected
 */
public class SlideMouseListener extends MouseAdapter {

    private Slide slide;

    /**
     * Constructor method
     * @param slide the slide the user is currently on
     */
    public SlideMouseListener(Slide slide){
        this.slide = slide;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //Checks if right-clicked
        if (e.getButton() == MouseEvent.BUTTON3) {
            //If so displays the menu
            EditorRightClickMenu editorRightClickMenu = new EditorRightClickMenu(slide);
            editorRightClickMenu.show(e.getComponent(), e.getX(), e.getY());
        }

        if (e.getClickCount() == 2){
            Text text = new Text(slide);
            slide.addText(text);
            text.moveObject(e.getX(), e.getY(), false);
        }

    }

    /**
     * Method when anywhere except an object on the slide is clicked
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //Sets all the borders to null
        ArrayList<Text> textBoxes = slide.getTextBoxes();
        for (Text textBox : textBoxes) {
            textBox.setBorder(null);
        }

        //Prevents editing of the text box after deselected
        if (slide.getCurrentInsertable() instanceof Text text)
            text.setEditable(false);

        slide.getPresentation().getUI().getSlideEditor().requestFocusInWindow();
    }
}
