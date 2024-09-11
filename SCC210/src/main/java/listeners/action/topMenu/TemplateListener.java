package listeners.action.topMenu;

import presentation.Presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TemplateListener implements ActionListener {

    private Presentation presentation;
    private int templateNumber;

    public TemplateListener(Presentation presentation, int templateNumber){
        this.presentation = presentation;
        this.templateNumber = templateNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < presentation.getSlideList().size(); i++){

            if (templateNumber == 1) {
                try {
                    presentation.getSlideList().get(i).setBackgroundImage(ImageIO.read(getClass().getResource("/template.jpg")));
                    presentation.getSlideList().get(i).setSlideTemplateNum(1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                presentation.getSlideList().get(i).repaint();
                presentation.setTemplateNum(1);
            }
            else if (templateNumber == 0){
                presentation.getSlideList().get(i).setBackgroundImage(null);
                presentation.getSlideList().get(i).repaint();
                presentation.setTemplateNum(0);
                presentation.getSlideList().get(i).setSlideTemplateNum(0);
            }
            else if (templateNumber == 2){
                try {
                    presentation.getSlideList().get(i).setBackgroundImage(ImageIO.read(getClass().getResource("/template2.png")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                presentation.getSlideList().get(i).repaint();
                presentation.setTemplateNum(2);
                presentation.getSlideList().get(i).setSlideTemplateNum(2);
            }
            else if (templateNumber == 3){
                try {
                    presentation.getSlideList().get(i).setBackgroundImage(ImageIO.read(getClass().getResource("/template3.jpg")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                presentation.getSlideList().get(i).repaint();
                presentation.setTemplateNum(3);
                presentation.getSlideList().get(i).setSlideTemplateNum(3);
            }

        }
        if (templateNumber == -1){

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            Image newTheme;
            if (result == JFileChooser.APPROVE_OPTION) {
                //Sets the image the selected file
                String selectedFilePath = fileChooser.getSelectedFile().getPath();
                newTheme = new ImageIcon(selectedFilePath).getImage();

                for (int i = 0; i < presentation.getSlideList().size(); i++) {
                    presentation.setTheme(new ImageIcon(newTheme));
                    presentation.getSlideList().get(i).setBackgroundImage(presentation.getTheme().getImage());
                    presentation.getSlideList().get(i).repaint();
                    presentation.setTemplateNum(-1);
                    presentation.getSlideList().get(i).setSlideTemplateNum(-1);
                }
            }
        }

        for (int i = 0; i < presentation.getSlideList().size(); i++){
            int index = presentation.getSlideList().indexOf(presentation.getUI().getCurrentSlide());
            for (int j = 0; j < presentation.getSlideList().size(); j++){
                presentation.setOnScreenSlide(presentation.getSlideList().get(j));
                presentation.updatePreviews(presentation.getSlideList().get(j));
            }
            presentation.setOnScreenSlide(presentation.getSlideList().get(index));
        }
    }
}
