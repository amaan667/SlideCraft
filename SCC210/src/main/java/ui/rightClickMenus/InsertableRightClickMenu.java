package ui.rightClickMenus;

import javax.swing.*;

import listeners.action.objectRightClickMenu.CopyListener;
import listeners.action.objectRightClickMenu.DeleteObjectListener;
import listeners.action.objectRightClickMenu.PasteListener;
import listeners.action.objectRightClickMenu.ResizeListener;
import slide.insertables.Insertable;
import slide.Slide;

/**
 * Class for creating a right click menu for insertable objects.
 */
public class InsertableRightClickMenu extends JPopupMenu {

   private Insertable object;
   private Slide slide;

    /**
     * Constructor method for the right click menu
     * @param object the object which the menu is for
     */
    public InsertableRightClickMenu(Insertable object, Slide slide)
    {
        super();
        this.object = object;
        this.slide = slide;

        //Sets out the menu
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(new DeleteObjectListener(object));
        add(delete);

        JMenuItem resize = new JMenuItem("Resize");
        resize.addActionListener(new ResizeListener(object, slide));
        add(resize);

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(new CopyListener(object, false));
        add(copy);

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(new CopyListener(object, true));
        add(cut);

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(new PasteListener(object));
        add(paste);

    }
}
