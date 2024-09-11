package ui.rightClickMenus;

import javax.swing.*;

import listeners.action.slideRightClickMenu.*;
import listeners.action.uiButtons.DeleteButtonLogic;
import slide.Slide;

/**
 * Class for creating a right click menu for slide previews
 */
public class SlideRightClickMenu extends JPopupMenu {


    private Slide slide;

    /**
     * Constructor method for the right click menu
     */
    public SlideRightClickMenu(Slide slide)
    {
        super();

        this.slide = slide;
        //Sets out the menu
        JMenuItem insert = new JMenuItem("Insert");
        insert.addActionListener(new SlideInsertLogic(slide));
        add(insert);

        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(new DeleteButtonLogic(slide.getPresentation(), slide));
        add(delete);

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(new SlideCutLogic(slide));
        add(cut);

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(new SlideCopyLogic(slide));
        add(copy);

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(new SlidePasteLogic(slide));
        add(paste);
    }
}
