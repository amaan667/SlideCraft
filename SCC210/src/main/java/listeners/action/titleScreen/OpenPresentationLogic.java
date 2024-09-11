package listeners.action.titleScreen;

import com.google.gson.JsonParser;
import presentation.Presentation;
import serialization.Loading;
import serialization.PresentationLoader;
import ui.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Action listener to open file
 */
public class OpenPresentationLogic implements ActionListener {

    private JFrame frame;

    public OpenPresentationLogic(JFrame frame){
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if(result == JFileChooser.APPROVE_OPTION){
            JsonParser jsonParser = new JsonParser();
            FileReader fileReader;
            File file;
            String obj;

            try {
                fileReader = new FileReader(fileChooser.getSelectedFile());
                obj = String.valueOf(jsonParser.parse(fileReader));
                file = fileChooser.getSelectedFile();

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            Presentation presentation = new Presentation("noTemplate");
            UI ui = new UI(presentation);

            PresentationLoader presentationLoader = new PresentationLoader(file, obj, frame, presentation);
            presentationLoader.start();
        }
    }
}
