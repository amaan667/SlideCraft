package presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

import listeners.action.uiButtons.SlidePreviewListener;
import listeners.mouse.SlideManagerMouseListener;
import listeners.mouse.SlideManagerMouseMotionListener;
import presentation.threads.TemplateUpdater;
import slide.Slide;
import slide.insertables.Text;
import ui.UI;
import com.google.gson.annotations.Expose;

/**
 * Class for defining a presentation in the program
 */
public class Presentation {
    //Linked lists for the slides and their button previews
    @Expose
    private LinkedList<Slide> slideList;
    private LinkedList<JButton> slidePreviews;
    private Slide slideClipboard;
    private Color laserColour;
    @Expose
    private String template;
    private UI ui;
    int slideCount;
    private File filepath;
    @Expose
    private boolean auto;
    @Expose
    private int templateNum;
    @Expose
    private String serializedTheme;
    private ImageIcon theme;


    /**
     * Constructor method for creating a new presentation
     */
    public Presentation(String template) {
        slideCount = 0;
        slideList = new LinkedList<>();
        slidePreviews = new LinkedList<>();
        //Present laser is red by default
        laserColour = Color.red;
        this.template = template;
        filepath = null;
    }

    /**
     * Method for adding a slide to the presentation
     * @param slide the slide being added
     */
    public void addSlide(Slide slide){
        slideCount++;
        int currentNum = slideList.indexOf(ui.getCurrentSlide());
        slideList.add(currentNum+1, slide); //Adds to the linked list

        //Removes all slide previews
        for (JButton slidePreview : slidePreviews)
            ui.getSlideManager().remove(slidePreview);

        //Creates a JButton for the slide preview
        JButton slideButton = new JButton();
        slideButton.setBorder(null);
        slideButton.setBorderPainted(false);
        slideButton.setContentAreaFilled(false);
        slideButton.setFocusPainted(false);
        SlideManagerMouseListener slideManagerMouseListener = new SlideManagerMouseListener(slide);
        SlideManagerMouseMotionListener slideManagerMouseMotionListener = new SlideManagerMouseMotionListener(slide);
        slideButton.addMouseMotionListener(slideManagerMouseMotionListener);
        slideButton.addMouseListener(slideManagerMouseListener);
        slideButton.addActionListener(new SlidePreviewListener(slide, this, slideManagerMouseListener, slideManagerMouseMotionListener));

        slidePreviews.add(currentNum+1, slideButton);

        //Adds previews back in correct order
        for (JButton slidePreview : slidePreviews)
            ui.getSlideManager().add(slidePreview);

        // Repaint & Revalidate to update the UI
        ui.getSlideManager().repaint();
        ui.getSlideManager().revalidate();

        //Creates the preview for it
        slideButton.setIcon(ui.initSlidePreview(slide));
        setOnScreenSlide(slide);
        slide.setSlideSize(getUI().getFrame());

        TemplateUpdater templateUpdater = new TemplateUpdater(this, slide);
        templateUpdater.start();
    }

    /**
     * Sets the standard template onto the first slide.
     * Only used for the first slide.
     * @param slide the slide the template is being applied too.
     */
    public void setFirstStandardTemplate(Slide slide){

        Text titleText = new Text(slide);
        slide.addText(titleText);

        titleText.setObjectSize(700, 100, false);
        int x = slide.getWidth() /2;
        x -= (700/2);
        titleText.moveObject(x, 160, false);

        StyledDocument doc = titleText.getStyledDocument();
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        MutableAttributeSet mutableAttributeSet = titleText.getInputAttributes();
        try {
            doc.insertString(0, "Title Text", mutableAttributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        StyleConstants.setFontSize(simpleAttributeSet, 80);
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_CENTER);
        doc.setCharacterAttributes(0, 25, simpleAttributeSet, false);
        doc.setParagraphAttributes(0, doc.getLength(), simpleAttributeSet, false);
        titleText.updateSizes();
        titleText.setAlign("centre");

       Text nameText = new Text(slide);
        slide.addText(nameText);
        x = slide.getWidth() /2;
        x -= (500/2);
        nameText.moveObject(x, 350, false);
        nameText.setObjectSize(500, 100, false);
        doc = nameText.getStyledDocument();
        simpleAttributeSet = new SimpleAttributeSet();
        mutableAttributeSet = nameText.getInputAttributes();
        try {
            doc.insertString(0, "Name Here", mutableAttributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        StyleConstants.setFontSize(simpleAttributeSet, 40);
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_CENTER);
        doc.setCharacterAttributes(0, 25, simpleAttributeSet, false);
        doc.setParagraphAttributes(0, doc.getLength(), simpleAttributeSet, false);
        nameText.updateSizes();
        nameText.setAlign("centre");


    }

    /**
     * Sets the standard template on the slide.
     * This is used for all slides after the first slide.
     * @param slide the slide which the template will be applied too.
     */
    public void setStandardTemplate(Slide slide){

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Text titleBox = new Text(slide);
        slide.addText(titleBox);
        titleBox.setObjectSize(700, 100, false);
        titleBox.moveObject(40, 30, false);
        StyledDocument doc = titleBox.getStyledDocument();
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setFontSize(style, 45);
        MutableAttributeSet mutableAttributeSet = titleBox.getInputAttributes();

        try {
            doc.insertString(0, "Insert Slide Title Here", mutableAttributeSet);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        doc.setCharacterAttributes(0, 25, style, false);
        titleBox.updateSizes();

        Text mainText = new Text(slide);
        slide.addText(mainText);

        mainText.setObjectSize(700, 350, false);

        mainText.moveObject(43, 170, false);
        StyledDocument mainDoc = mainText.getStyledDocument();
        MutableAttributeSet mutableAttributeSet1 = mainText.getInputAttributes();
        StyleConstants.setFontSize(style, 13);

        try {
            mainDoc.insertString(0, "Enter text here.....", mutableAttributeSet1);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        StyleConstants.setFontSize(style, 25);
        mainDoc.setCharacterAttributes(0, 25, style, false);
        mainText.updateSizes();

    }

    /**
     * Method for refreshing the slide previews order in the slide manager
     * Used after a slide re arrangement action has occurred.
     */
    public void updateSlideOrder(){
        //Removes all slide previews
        for (JButton slidePreview : slidePreviews)
            ui.getSlideManager().remove(slidePreview);

        for (JButton slidePreview : slidePreviews)
            ui.getSlideManager().add(slidePreview);

    }

    /**
     * Method for updating the current slide preview the user is on
     */
    public void updatePreviews(Slide slide){
        //Sets the preview to an updated version of the slide
        slidePreviews.get(slideList.indexOf(slide)).setIcon(ui.panelToImageIcon(slide, 178, 100));
        ui.getSlideManager().repaint();
    }

    /**
     * Method for deleting a slide from the presentation
     * @param slide the slide to be deleted
     */
    public void deleteSlide(Slide slide) {

        //Checks there is more than 1 slide before deleting
        if (slideList.size() != 1) {
            int oldIndex = slideList.indexOf(slide); //Index of the slide to be deleted
            slideCount--;

            //Remove the slide and preview from the linked lists
            ui.getSlideManager().remove(slidePreviews.get(slideList.indexOf(slide)));
            slidePreviews.remove(slideList.indexOf(slide));
            slideList.remove(slide);

            //Repaint and revalidate to update the UI
            ui.getSlideManager().repaint();
            ui.getSlideManager().revalidate();

            //Displays a new slide after deletion
            if (oldIndex < slideList.size())
                setOnScreenSlide(slideList.get(oldIndex));
            else
                setOnScreenSlide(slideList.get(oldIndex - 1));
        }
    }

    /**
     * Method for setting the slide on the screen
     * @param slide the slide that will be put on screen
     */
    public void setOnScreenSlide(Slide slide){
        //Removes the current slide off the screen.
        getUI().getSlideEditor().remove(getUI().getCurrentSlide());

        //Sets the new slide
        getUI().setSlide(getSlideList().get(getSlideList().indexOf(slide)));
        getUI().getSlideEditor().add(getUI().getCurrentSlide());
        getUI().resetActionListeners();
        getUI().getCurrentSlide().setVisible(true);

        //Removes border from old current slide
        for (JButton slidePreview : slidePreviews) {
            slidePreview.setBorderPainted(false);
            slidePreview.setBorder(null);
        }

        //Sets border on the new slide
        slidePreviews.get(slideList.indexOf(slide)).setBorderPainted(true);
        slidePreviews.get(slideList.indexOf(slide)).setBorder(new LineBorder(Color.decode("#747574"), 3));

        //Repaint and revalidate
        getUI().getCurrentSlide().repaint();
        getUI().getSlideEditor().repaint();
        getUI().getSlideEditor().revalidate();
    }

    /**
     * Prints the presentation and opens the print menu.
     */
    public void printPresentation() {
        //Creates a printer job which creates the printer menu.
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(slideList.get(0));

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void getByteArray() {
        try {
            ByteArrayOutputStream newStream = new ByteArrayOutputStream();
            ObjectOutputStream objOS = new ObjectOutputStream(newStream);

            objOS.writeObject(theme);

            serializedTheme = Base64.getEncoder().encodeToString(newStream.toByteArray());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println(serializedTheme);
    }


    /**
     * Setter method for the ui
     * @param ui the ne ui
     */
    public void setUI(UI ui){
        this.ui = ui;
    }

    /**
     * Setter method for the ui
     * @return the ui
     */
    public UI getUI()
    {
        return ui;
    }

    /**
     * Getter method for the slide list
     * @return the slide list.
     */
    public LinkedList<Slide> getSlideList()
    {
        return slideList;
    }

    /**
     * Getter method for the slide preview list
     * @return the slide preview list.
     */
    public LinkedList<JButton> getSlidePreviews()
    {
        return slidePreviews;
    }

    /**
     * Getter method for the slideClipboard
     * Used for copy and pasting of slides
     * @return the slide in the clipboard
     */
    public Slide getSlideClipboard(){
        return slideClipboard;
    }

    /**
     * Setter method for the slideClipboard
     * Used for copy and pasting of slides.
     * @param slide the new slide for the clipboard
     */
    public void setSlideClipboard(Slide slide){
        slideClipboard = slide;
    }

    /**
     * Setter method for the laser colour in present mode
     * @param laserColour the new colour
     */
    public void setLaserColour(Color laserColour) {
        this.laserColour = laserColour;
    }

    /**
     * Getter method for the laser colour
     * @return the colour of the laser
     */
    public Color getLaserColour() {
        return laserColour;
    }

    /**
     * Getter method for the template string
     * @return the template in use
     */
    public String getTemplate(){
        return template;
    };

    /**
     * Setter method for the template variable.
     * @param template the new template
     */
    public void setTemplate(String template){
        this.template = template;
    }

    /**
     * Setter method for the filepath variable.
     * @param filepath the new filepath being set for the presentation
     */
    public void setFilepath(File filepath){
        this.filepath = filepath;
    }

    /**
     * Getter method for the filepath variable
     * @return the current filepath
     */
    public File getFilepath(){
        return filepath;
    }

    /**
     * Getter for the auto save boolean
     * @return the state of the auto save boolean
     */
    public boolean getAuto(){
        return auto;
    }

    /**
     * Setter method for the auto save boolean
     * @param auto the new state of the auto save boolean.
     */
    public void setAuto(boolean auto){
        this.auto = auto;
    }

    public void setTemplateNum(int templateNum) {
        this.templateNum = templateNum;
    }

    public int getTemplateNum() {
        return templateNum;
    }

    public void setTheme(ImageIcon theme) {
        this.theme = theme;
    }
    public ImageIcon getTheme(){
        return theme;
    }
}
