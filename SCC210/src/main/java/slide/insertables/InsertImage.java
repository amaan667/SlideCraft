package slide.insertables;

import com.google.gson.annotations.Expose;
import slide.Slide;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
 * Class for defining what an image in the presentation
 */
public class InsertImage extends AbstractImageText {
    private ImageIcon icon;
    private ImageIcon originalIcon;
    @Expose
    private String serializedIcon;

    private BufferedImage backgroundImage;

    /**
     * Constructor method for creating an image
     * @param slide the slide the image will belong too.
     */
    public InsertImage(Slide slide) {

        this.slide = slide;
        setEditable(false); // Prevents editing of the JTextPane

        setOpaque(false);
    }

    /**
     * Sets the image of the InsertImage
     * No image passed in so will ask the user to select a file
     */
    public void setImage(){
        //Creates a File Chooser to pick a file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            //Sets the image the selected file
            String selectedFilePath = fileChooser.getSelectedFile().getPath();
            icon = new ImageIcon(selectedFilePath);
            setImage(icon);
        }
    }

    /**
     * Sets the image of the InsertImage
     * @param newIcon the image being set
     */
    public void setImage(ImageIcon newIcon){
        icon = newIcon;
        originalIcon = icon;

        // Sets the width and height of the JTextPane to the size of the image
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        originalHeightI = height;
        originalWidthI = width;
        insertIcon(icon);
        setUpListeners(slide);
        setCaretColor(slide.getBackground()); // Hides caret
    }

    /**
     * Method for setting the size of the image
     * @param width the new width
     * @param height the new height
     */
    public void setObjectSize(int width, int height, Boolean scale)
    {
        //Scale the image
        Image image = originalIcon.getImage();
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        this.width = width;
        this.height = height;
        setText("");
        super.setObjectSize(width, height, scale);
        insertIcon(icon);
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public void getByteArray() {
        try {
            ByteArrayOutputStream newStream = new ByteArrayOutputStream();
            ObjectOutputStream objOS = new ObjectOutputStream(newStream);

            objOS.writeObject(icon);

            serializedIcon = Base64.getEncoder().encodeToString(newStream.toByteArray());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
