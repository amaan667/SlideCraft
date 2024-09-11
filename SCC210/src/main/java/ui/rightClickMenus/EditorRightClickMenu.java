package ui.rightClickMenus;

import listeners.action.editorRightClickMenu.EditorPasteListener;
import listeners.action.slideRightClickMenu.DeleteThemeLogic;
import listeners.action.uiButtons.ImageButtonLogic;
import listeners.action.uiButtons.TextBoxButtonLogic;
import slide.Slide;

import javax.swing.*;

/**
 * Right click menu for when the users right clicks in the slide editor
 * but not on an insertable object.
 */
public class EditorRightClickMenu extends JPopupMenu {

    private Slide slide;

    /**
     * Constructor method. Creates the pop-up menu
     * @param slide the slide the menu if being made on
     */
    public EditorRightClickMenu(Slide slide){
        this.slide = slide;

        //Defines the menu
        JMenu insert = new JMenu("Insert");
        JMenuItem text = new JMenuItem("Text");
        text.addActionListener(new TextBoxButtonLogic(slide));
        insert.add(text);

        JMenuItem image = new JMenuItem("Image");
        image.addActionListener(new ImageButtonLogic(slide));
        insert.add(image);

        add(insert);

        JMenuItem copy = new JMenuItem("Copy");
        copy.setEnabled(false);
        add(copy);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setEnabled(false);
        add(cut);

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(new EditorPasteListener(slide));
        add(paste);

        JMenuItem deleteTheme = new JMenuItem("Remove theme");
        add(deleteTheme);
        deleteTheme.addActionListener(new DeleteThemeLogic(slide));

    }
}
