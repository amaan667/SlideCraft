package presentation;

import listeners.key.PresentKeyBindEsc;
import listeners.key.PresentKeyBindLeft;
import listeners.key.PresentKeyBindRight;
import listeners.key.SpeakerNoteKeybind;
import slide.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Class for present mode.
 * Used when presenting
 */
public class PresentMode {
    private Presentation presentation;
    private Slide presentSlide;
    private int position;
    private JFrame presentFrame;
    private JPanel presentPanel;
    private Action sAction;

    /**
     * Constructor method uses to set up a new present mode window
     * @param presentation the presentation being displayed
     */
    public PresentMode(Presentation presentation){
        this.presentation = presentation;
        position = 0; //Starts at first slide by default

        //Creates new JFrame for the window
        presentFrame = new JFrame();
        presentFrame.setVisible(true);
        presentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        presentPanel = new JPanel(null);
        presentFrame.add(presentPanel);
        presentPanel.setBackground(new Color(0, 0, 0, 0));
        presentFrame.repaint();



        try {
            Thread.sleep(100);
        } catch (Exception e){
            e.printStackTrace();
        }

        //Sets the window to full screen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(presentFrame);

        //Adds the right key bind to allow navigation in present mode
        Action rightAction = new PresentKeyBindRight(this, presentation);
        presentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        presentPanel.getActionMap().put("rightAction", rightAction);

        Action rightAction2 = new PresentKeyBindRight(this, presentation);
        presentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "rightAction2");
        presentPanel.getActionMap().put("rightAction2", rightAction2);

        //Adds the left key bind
        Action leftAction = new PresentKeyBindLeft(this, presentation);
        presentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        presentPanel.getActionMap().put("leftAction", leftAction);


        //Adds the esc key bind to allow exiting
        Action escAction = new PresentKeyBindEsc(presentFrame, presentation);
        presentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escAction");
        presentPanel.getActionMap().put("escAction", escAction);

        //Sets the focus so the key binds work
        presentPanel.requestFocusInWindow();
        presentPanel.setBackground(Color.black);
    }

    /**
     * Method when initially starting a presentation
     * @param startNumber the slide number to start on
     */
    public void start(int startNumber){
        //Gets the slide to be displayed
        presentSlide = presentation.getSlideList().get(startNumber);
        position = startNumber;

        //Sets the slide on the screen
        resetSlide(presentSlide, true);
        //resetSlide would increment position to decrements to bring back to correct number
        position--;
        presentPanel.setCursor(laserPointer(presentation.getLaserColour()));

        for (int i = 0; i < presentation.getSlideList().size(); i++){
            Slide slide = presentation.getSlideList().get(i);
            for (int j = 0; j < slide.getTextBoxes().size(); j++)
                slide.getTextBoxes().get(j).setCursor(laserPointer(presentation.getLaserColour()));

            for (int j = 0; j < slide.getImages().size(); j++)
                slide.getImages().get(j).setCursor(laserPointer(presentation.getLaserColour()));
        }
    }

    /**
     * Method for setting a slide onto the present window
     * @param slide the slide being presented
     * @param next whether the slide is a previous or a next slide
     */
    public void resetSlide(Slide slide, Boolean next){

        //Takes the current slide off the window
        presentPanel.remove(presentSlide);

        //Adds the new slide onto the window
        presentSlide = slide;
        presentation.setOnScreenSlide(presentSlide);

        sAction = sAction = new SpeakerNoteKeybind(slide);
        KeyStroke speakerNoteKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        presentPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(speakerNoteKey, "sAction");
        presentPanel.getActionMap().put("sAction", sAction);



        //Ensures listeners are not active to prevent slide modification in present mode
        for (int i = 0; i < presentSlide.getImages().size(); i++)
            presentSlide.getImages().get(i).disableListeners();

        for (int i = 0; i  < presentSlide.getTextBoxes().size(); i++) {
            presentSlide.getTextBoxes().get(i).disableListeners();
            presentSlide.getTextBoxes().get(i).setEditable(false);
            presentSlide.getTextBoxes().get(i).setBorder(null);
        }

        presentPanel.add(presentSlide);
        presentSlide.setSlideSize(presentFrame);
        presentFrame.repaint();;

        //Centres it in the middle
        int height = presentFrame.getHeight();
        int diff = height - slide.getHeight();
        diff = diff /2;

        int width = presentFrame.getWidth();
        int widthDiff = width - slide.getWidth();
        widthDiff = widthDiff / 2;

        presentSlide.setLocation(widthDiff, diff);

        //Changes the position accordingly to track what slide is being displayed
        if (next)
            position++;
        else
            position--;

    }

    /**
     * Method for creating a cursor for present mode
     * Cursor is a solid circle
     * @param colour the colour of the cursor
     * @return the new cursor
     */
    public Cursor laserPointer(Color colour){
        BufferedImage image = new BufferedImage(15, 15, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(colour);
        g2d.fillOval(0, 0, 15, 15);

        return Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(5, 5), "laser");
    }

    /**
     * Getter method for the position in the presentation
     * @return the position
     */
    public int getPosition(){
        return position;
    }

    public Slide getPresentSlide(){
        return presentSlide;
    }

}
