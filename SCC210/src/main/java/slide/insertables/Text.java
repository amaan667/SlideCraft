package slide.insertables;

import listeners.key.TextKeyListener;
import com.google.gson.annotations.Expose;
import slide.Slide;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.text.*;

/**
 * Class for creating a text box
 */
public class Text extends AbstractImageText {
    private Boolean list;
    @Expose
    private ArrayList<Integer> originalSizes;

    @Expose
    private String text;

    private ArrayList<Font> currentFont;

    @Expose
    private ArrayList<Boolean[]> Attributes;

    @Expose
    private ArrayList<String> colourArr;

    @Expose
    private ArrayList<String> fonts;

    @Expose
    protected boolean codeText;
    @Expose
    private String align;


    /**
     * Constructor method for creating a text box
     * @param slide the slide the text box will belong too.
     */
    public Text(Slide slide){
        this.slide = slide;
        list = false;
        originalSizes = new ArrayList<>();
        align = "left";

        //Outlines default location and size
        width = 200;
        height = 100;

        setUpListeners(slide); //Adds listeners
        slide.setCurrentInsertable(this);
        addKeyListener(new TextKeyListener(this, slide));

        //Removes all selected borders from other text boxes
        ArrayList<Text> textBoxes = slide.getTextBoxes();
        for (Text textBox : textBoxes) {
            textBox.setBorder(null);
        }

        displayBorder();
        originalWidthI = width;
        originalHeightI = height;

        codeText = false;
        setOpaque(false);
    }

    public void displayBorder(){
        //Sets the border for this text box
        setBorder(new LineBorder(Color.BLACK));
    }

    /**
     * Method for inserting a bullet point character.
     * Used by BulletPointInserter thread
     */
    public void insertBulletPoint(){
        StyledDocument doc = getStyledDocument();
        int caretPosition = getCaretPosition(); // Get the current caret position
        try {
            //Inserts the bullet point.
            doc.insertString(caretPosition, "• ", null);
            setCaretPosition(caretPosition+2); //Moves the caret on 2.
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method for updating the original size of letters within a text box.
     */
    public void updateSizes(){
        StyledDocument doc = getStyledDocument();

        //Loop for every letter.
        for (int i = 0; i < doc.getLength(); i++) {
            //Sets to 0 to ensure no out of bound errors
            originalSizes.add(i, 0);
            AttributeSet oldAttributes = getStyledDocument().getCharacterElement(i).getAttributes();

            //Gets scale
            int size = StyleConstants.getFontSize(oldAttributes);
            double widthScale = (double) slide.getWidth() / slide.getOriginalWidth();

            //Sets the size and adds to the array list
            originalSizes.set(i, (int) Math.round(size/widthScale));
        }
       // slide.setSlideSize(slide.getPresentation().getUI().getFrame());
    }

    /**
     * Scale method uses method from abstract class but adds functionality for scaling text size.
     * @param widthScale the amount the width has changed compared to original
     * @param heightScale the amount the height has changed compared to original
     */
    @Override
    public void scale(double widthScale, double heightScale){
        super.scale(widthScale, heightScale);

        //Scales the font size -- NOT WORKING
        StyledDocument doc = getStyledDocument();
        //Adjusts each individual character
        for (int i = 0; i < doc.getLength(); i++) {
            AttributeSet oldAttributes = getStyledDocument().getCharacterElement(i).getAttributes();
            MutableAttributeSet newAttributes = new SimpleAttributeSet(oldAttributes.copyAttributes());

            if (StyleConstants.getFontFamily(oldAttributes) != null) {
                String fontFamily = StyleConstants.getFontFamily(oldAttributes);

                int originalSize = originalSizes.get(i);

                StyleConstants.setFontFamily(newAttributes, fontFamily);
                //Scales the font size
                StyleConstants.setFontSize(newAttributes, (int) Math.round(originalSize * widthScale));
                doc.setCharacterAttributes(i, 1, newAttributes, false);
            }
        };
    }

    /**
     * Getter method for the list boolean
     * @return the list
     */
    public Boolean getList() {
        return list;
    }

    /**
     * Setter method for the list
     * @param list the new state of list.
     */
    public void setList(Boolean list){
        this.list = list;
    }

    /**
     * Getter method for the original size array list
     * @return the array list
     */
    public ArrayList<Integer> getOriginalSizes(){
        return originalSizes;
    }

    /**
     * Setter method for the original sizes array list
     * @param originalSizes the new original sizes array list
     */
    public void setOriginalSizes(ArrayList<Integer> originalSizes){
        this.originalSizes = originalSizes;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setCurrentFont(ArrayList<Font> font){
        currentFont = font;
    }

    public ArrayList<Font> getCurrentFont(){
        return currentFont;
    }


    public void setAttributes(){
        StyledDocument document  =this.getStyledDocument();
        ArrayList<Boolean[]> attributes = new ArrayList<>();
        ArrayList<String> colours = new ArrayList<>();
        ArrayList<String> fontArr = new ArrayList<>();

        for(int i = 0; i < document.getLength(); i++) {

            Element element = document.getCharacterElement(i);
            AttributeSet attr = element.getAttributes();
            Color color = StyleConstants.getForeground(attr);
            String font = StyleConstants.getFontFamily(attr);

            Boolean[] format = new Boolean[3];
            format[0] = StyleConstants.isBold(attr);
            format[1] = StyleConstants.isItalic(attr);
            format[2] = StyleConstants.isUnderline(attr);

            colours.add(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
            attributes.add(format);
            fontArr.add(font);
        }
        System.out.println("attr" + attributes);
        Attributes = attributes;
        colourArr = colours;
        fonts = fontArr;


    }

    public void setAlign(String align) {
        this.align = align;
    }

}