package slide;

import listeners.action.uiButtons.SpeakerNotesButtonLogic;
import com.google.gson.annotations.Expose;
import listeners.mouse.SlideMouseListener;
import listeners.mouse.SlideMouseMotionListener;
import presentation.Presentation;
import slide.insertables.*;
import slide.threads.SlideSizeUpdater;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for defining what a slide will look like within the presentation
 */
public class Slide extends JPanel implements Printable {

    private Presentation presentation;
    private Insertable currentInsertable;
    @Expose
    private ArrayList<Text> textBoxes = new ArrayList<>(); //ArrayList of all text
    @Expose
    private ArrayList<InsertImage> images = new ArrayList<>(); // ArrayList of all images
    @Expose
    private int originalWidth;
    @Expose
    private int originalHeight;
    private double slideAspectRatio;
    @Expose
    private String speakerNotes;
    @Expose
    private String colourHex;
    @Expose
    private int slideTemplateNum;
    private Image backgroundImage;
    private int mouseLocationX;
    private int mouseLocationY;


    /**
     * Constructor method for creating a slide
     * @param presentation the Presentation the slide belongs too.
     */
    public Slide(Presentation presentation){
        super();
        setSize(200, 100); //For the slide preview
        this.presentation = presentation;
        setLayout(null); //Null layout so components can be moved to exact positions
        addMouseListener(new SlideMouseListener(this));

        slideAspectRatio = (double) getWidth() / getHeight();

        colourHex = "#FFFFFF";
        speakerNotes = "";

        SlideSizeUpdater slideSizeUpdater = new SlideSizeUpdater(this);
        slideSizeUpdater.start();

        setTemplateOnSlide();
        RepaintManager.currentManager(presentation.getUI().getFrame()).setDoubleBufferingEnabled(true);
        slideTemplateNum = presentation.getTemplateNum();
        addMouseMotionListener(new SlideMouseMotionListener(this));
    }

    public Slide(Presentation presentation, int num){
        this(presentation);
        slideTemplateNum = num;
        int temp = presentation.getTemplateNum();
        presentation.setTemplateNum(slideTemplateNum);
        setTemplateOnSlide();
        presentation.setTemplateNum(temp);

    }

    public void setTemplateOnSlide(){
        try {
            if (presentation.getTemplateNum() == 1)
                setBackgroundImage(ImageIO.read(getClass().getResource("/template.jpg")));

            else if (presentation.getTemplateNum() == 2)
                setBackgroundImage(ImageIO.read(getClass().getResource("/template2.png")));
            else if (presentation.getTemplateNum() == 0) {
                setBackgroundImage(null);
            }
            else if (presentation.getTemplateNum() == 3)
                setBackgroundImage(ImageIO.read(getClass().getResource("/template3.jpg")));
            else if (presentation.getTemplateNum() == -1){
                setBackgroundImage(presentation.getTheme().getImage());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Method for scaling the slide and its contents after a window resize
     */
    public void setSlideSize(JFrame frame) {

        int width, height;

        if (frame == presentation.getUI().getFrame())
            height = presentation.getUI().getSlideEditor().getHeight();
        else {
            height = frame.getHeight();
        }

        width = (int) (height * 1.78);
        setSize(width, height);

        if (getWidth() > presentation.getUI().getSlideEditor().getWidth()) {
            //Gets the width of the JFrame
            width = frame.getWidth();
            //Height is scaled of the Width, 1.78 is the aspect ratio
            height = (int) (width / 1.78);
            //Resizes the slide
            setSize(width, height);
        }

        //Gets scales
        double widthScale = (double) width / originalWidth;
        double heightScale = (double) height / originalHeight;

        //Scales all images
        for (InsertImage image : images)
            image.scale(widthScale, heightScale);

        //Scales all the text
        for (Text text : textBoxes) {
            text.scale(widthScale, heightScale);

            MutableAttributeSet attributes = text.getInputAttributes();
            StyleConstants.setFontSize(attributes, text.getFont().getSize());
            StyledDocument doc = text.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), attributes, false);
        }

        //Centres the slide in the slide editor
        int frameWidth = frame.getWidth();
        int diff = frameWidth - getWidth();
        diff = diff / 2;

        int editorHeight = presentation.getUI().getSlideEditor().getHeight();
        int heightDiff = editorHeight - getHeight();
        heightDiff = heightDiff / 2;

        //Sets the location in the slide editor
        setLocation(diff, heightDiff);

    }

    /**
     * Method for adding text to a slide
     */

    public void addText(Text newText){
        add(newText); //Adds it to the slide
        newText.setBounds(newText.getX(), newText.getY(), newText.getWidth(), newText.getHeight()); //Sets its position
        textBoxes.add(newText); //Adds to the ArrayList
        setSlideSize(presentation.getUI().getFrame());
    }

    /**
     * Method for adding an image to a slide
     */
    public void addImage(InsertImage newImage){

        add(newImage); //Adds the image
        newImage.setBounds(500, 500, newImage.getWidth(), newImage.getHeight());
        images.add(newImage);
        setSlideSize(presentation.getUI().getFrame());
    }

    /**
     * Override print method sets a default background to the slide when printing
     * @param graphics the <code>Graphics</code> context in which to paint
     */
    public void startPrint(Graphics graphics){
        setBackground(Color.WHITE);

        print(graphics);
    }

    /**
     * Method for cloning the contents of one slide onto another.
     * Used for slide cutting and paste
     * @param newSlide the slide being copied from
     */
    public void clone(Slide newSlide){
        //Sets the background the same
        setBackground(newSlide.getBackground());

        // Copies Text
        for (int i = 0; i < newSlide.getTextBoxes().size(); i++) {
            Text newText = new Text(this);
            StyledDocument source = newSlide.getTextBoxes().get(i).getStyledDocument();
            StyledDocument newDoc = new DefaultStyledDocument();

            for (int j = 0; j < source.getLength(); j++){
                Element sourceElem = source.getCharacterElement(j);
                AttributeSet sourceAttributes = sourceElem.getAttributes();

                try {
                    newDoc.insertString(j, source.getText(j, 1), sourceAttributes);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
            ArrayList<Integer> temp = new ArrayList<>();

            for (int j = 0; j < newSlide.getTextBoxes().get(i).getOriginalSizes().size(); j++){
                temp.add(j, newSlide.getTextBoxes().get(i).getOriginalSizes().get(j));
            }
            newText.setOriginalSizes(temp);
            addText(newText);

            Text tempText = newSlide.getTextBoxes().get(i);
            newText.setOriginalHeightI(tempText.getOriginalHeightI());
            newText.setOriginalWidthI(tempText.getOriginalWidthI());
            newText.setOriginalX(tempText.getOriginalX());
            newText.setOriginalY(tempText.getOriginalY());
            newText.setStyledDocument(newDoc);

        }

        // Copies Images
        for (int i = 0; i < newSlide.getImages().size(); i++) {
            InsertImage newImage = new InsertImage(this);
            addImage(newImage);
            newImage.setImage(newSlide.getImages().get(i).getIcon());
            newImage.setStyledDocument(newSlide.getImages().get(i).getStyledDocument());
            InsertImage image = newSlide.getImages().get(i);
            newImage.setOriginalHeightI(image.getOriginalHeightI());
            newImage.setOriginalWidthI(image.getOriginalWidthI());
            newImage.setOriginalX(image.getOriginalX());
            newImage.setOriginalY(image.getOriginalY());
        }

        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setSlideSize(presentation.getUI().getFrame());
        });
    }

    public void pasteOntoSlide(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();

        if (contents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            Image clipboardImage;
            try {
                clipboardImage = (Image) contents.getTransferData(DataFlavor.imageFlavor);
            } catch (UnsupportedFlavorException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            ImageIcon imageIcon = new ImageIcon(clipboardImage);
            InsertImage newImage = new InsertImage(this);
            newImage.setImage(imageIcon);
            addImage(newImage);

            int x = (int) point.getX();
            int y = (int) point.getY();

            newImage.moveObject(mouseLocationX, mouseLocationY, false);
        }
        else{
            Text newText = new Text(this);
            addText(newText);
            String clipboardContents;

            try {
                clipboardContents = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            StyledDocument doc = newText.getStyledDocument();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();

            try {
                doc.insertString(0, clipboardContents, simpleAttributeSet);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }


            int x = (int) point.getX();
            int y = (int) point.getY();
            newText.moveObject(mouseLocationX, mouseLocationY, false);

            newText.updateSizes();
        }
    }

    /**
     * Getter method for the current text
     * @return the current text
     */
    public Insertable getCurrentInsertable()
    {
        return currentInsertable;
    }

    /**
     * Getter method for the text box array list
     * @return the ArrayList
     */
    public ArrayList<Text> getTextBoxes() {
        return textBoxes;
    }

    /**
     * Getter method for the images array list
     * @return the ArrayList
     */
    public ArrayList<InsertImage> getImages() {
        return images;
    }

    /**
     * Getter method for the presentation
     * @return the presentation
     */
    public Presentation getPresentation() {
        return presentation;
    }

    /**
     * Setter method for the current text
     * @param currentInsertable the new current text
     */
    public void setCurrentInsertable(Insertable currentInsertable)
    {
        this.currentInsertable = currentInsertable;
    }

    /**
     * Setter method for the original width;
     * @param originalWidth new original width
     */
    public void setOriginalWidth(int originalWidth){
        this.originalWidth = originalWidth;
    }

    /**
     * Setter method for the original height
     * @param originalHeight the new original height
     */
    public void setOriginalHeight(int originalHeight){
        this.originalHeight = originalHeight;
    }

    /**
     * Getter method for the original height
     * @return the original height
     */
    public int getOriginalHeight(){
        return originalHeight;
    }

    /**
     * Getter method for the original width
     * @return the original width
     */
    public int getOriginalWidth(){
        return originalWidth;
    }

    /**
     * Getter method for the slide aspect ratio
     * @return the slide aspect ratio
     */

    public double getSlideAspectRatio(){
        return slideAspectRatio;
    }

    /** method to add the speakernotes
     *
     * @param SpeakerNotesButtonLogic
     */
    public void addSpeakerNotes(SpeakerNotesButtonLogic SpeakerNotesButtonLogic) {
    }

    /** method to set the speaker-notes
     *
     * @param text
     */
    public void setSpeakerNotes(String text){
        speakerNotes = text;
    }

    /** method to get the speaker-notes
     *
     * @return
     */

    public String getSpeakerNotes(){
        return speakerNotes;
    }

    /** method te set the hex value to the colour
     *
     * @param hex
     */

    public void setColourHex(String hex){
        colourHex = hex;
    }


    /** method to set the images
     *
     * @param imageArr
     */
    public void setImages(ArrayList<InsertImage> imageArr){
        images = imageArr;
    }

    /** method to set the background image
     *
     * @param newBack
     */

    public void setBackgroundImage(Image newBack){
        this.backgroundImage = newBack;
    }


    /**
     * Print method from the printable interface.
     * Used to export and print onto paper.
     * @param graphics the context into which the page is drawn
     * @param pageFormat the size and orientation of the page being drawn
     * @param pageIndex the zero based index of the page to be drawn
     * @return Whether the page exists
     * @throws PrinterException
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex > presentation.getSlideList().size()-1)
            return NO_SUCH_PAGE;

        Slide slideToPrint = presentation.getSlideList().get(pageIndex);

        Graphics2D g2d = (Graphics2D) graphics;
        pageFormat.setOrientation(PageFormat.LANDSCAPE);
        int yPos = (int) (pageFormat.getImageableHeight() / 2);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY() + ((double) yPos /4));

        double scale = pageFormat.getImageableWidth() / slideToPrint.getWidth();
        g2d.scale(scale, scale);

        slideToPrint.printAll(g2d);

        return Printable.PAGE_EXISTS;
    }

    /** method to set the slide template number
     *
     * @param slideTemplateNum
     */
    public void setSlideTemplateNum(int slideTemplateNum) {
        this.slideTemplateNum = slideTemplateNum;
    }

    /** method to set the original location of the mouse at x
     *
     * @param mouseLocationX
     */
    public void setMouseLocationX(int mouseLocationX){
        this.mouseLocationX = mouseLocationX;
    }
    /** method to set the original location of the mouse at y
     *
     * @param mouseLocationY
     */
    public void setMouseLocationY(int mouseLocationY){
        this.mouseLocationY = mouseLocationY;
    }
}