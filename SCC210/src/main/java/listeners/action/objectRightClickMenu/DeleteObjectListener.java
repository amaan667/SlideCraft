package listeners.action.objectRightClickMenu;

import slide.insertables.Insertable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the delete object option on right click menu
 */
public class DeleteObjectListener implements ActionListener {

    Insertable object;

    /**
     * Constructor method
     * @param object the object being right-clicked
     */
    public DeleteObjectListener(Insertable object)
    {
        this.object = object;
    }

    /**
     * Method for the delete object option
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Deletes the object
        object.deleteObject();
    }
}
