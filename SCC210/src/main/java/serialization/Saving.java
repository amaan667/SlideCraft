package serialization;
import com.google.gson.*;
import com.google.gson.GsonBuilder;
import presentation.Presentation;
import slide.Slide;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for saving presentations as a JSON file.
 */
public class Saving {
    private Presentation presentation;
    private Boolean auto;

    /**
     * Constructor method for the saving class
     * @param presentation the presentation being saved.
     */
    public Saving(Presentation presentation){
        this.presentation = presentation;
        auto = false;
    }

    /**
     * Method for saving a new presentation that has not been saved before.
     */
    public void saveNew(){
        //Creates a file chooser menu
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(presentation.getUI().getCurrentSlide());

        if(result == JFileChooser.APPROVE_OPTION) {
            // Saves the presentation at the selected location
            savePresentation(chooser.getSelectedFile());
            presentation.setFilepath(chooser.getSelectedFile());
        }

        // Sets the title of the JFrame to the name of the file.
        try {
            presentation.getUI().getFrame().setTitle(chooser.getSelectedFile().getName());
        }catch (NullPointerException e){}
    }

    /**
     * Method for saving a presentation when given a filepath
     * @param file the file to save the presentation too.
     */
    public void savePresentation(File file){

        for (int i = 0; i < presentation.getSlideList().size(); i++) {
            Slide current = presentation.getSlideList().get(i);

            for (int j = 0; j < current.getTextBoxes().size(); j++) {
                StyledDocument doc = current.getTextBoxes().get(j).getStyledDocument();
                current.getTextBoxes().get(j).setAttributes();

                try {
                    current.getTextBoxes().get(j).setText(doc.getText(0, doc.getLength()));
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }

            for(int j = 0; j < current.getImages().size(); j++){
                current.getImages().get(j).getByteArray();
            }
        }

        presentation.getByteArray();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(presentation, writer);
        } catch (IOException e) {
            System.out.println("Exception found: " + e.getMessage());
        }

    }

    /**
     * Method for saving an existing saved presentation.
     */
    public void saveExisting(){
        File file = presentation.getFilepath();

        savePresentation(file);
    }


}
