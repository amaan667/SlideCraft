package serialization;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.JsonParser;
import presentation.Presentation;
import slide.Slide;
import slide.insertables.CodeText;
import slide.insertables.InsertImage;
import slide.insertables.Text;
import ui.UI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Objects;

public class Loading {

    private Slide slide;
    private Presentation presentation;

    private UI ui;

    /**
     * Constructor method for the Loading class
     * @param presentation is the current presentation on screen
     */
    public Loading(Presentation presentation){
        this.presentation = presentation;
        this.slide = slide;
        this.ui = ui;
    }

    /**
     * Allows user to select presentation from files
     * Converts file into a json array to be recreated
     */
    public void loadPresentation(){
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(presentation.getUI().getCurrentSlide());

        if(result == JFileChooser.APPROVE_OPTION){
            JsonParser jsonParser = new JsonParser();
            FileReader fileReader;

            try {
                fileReader = new FileReader(chooser.getSelectedFile());
                String obj = String.valueOf(jsonParser.parse(fileReader));
                recreatePresentation(obj, chooser.getSelectedFile());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    /**
     * Function to recreate presentation once loaded
     * @param obj string representation of loaded file
     * @param filepath filepath of loaded file
     */
    public void recreatePresentation(String obj, File filepath) {

        LinkedList<Slide> slideList = presentation.getSlideList();

        for (int i = 0; i < slideList.size()-1; i++){
            presentation.deleteSlide(slideList.get(i));
        }

        ArrayList<Text> textArray = slideList.get(0).getTextBoxes();
        for (int i = 0; i < textArray.size(); i++){
            textArray.get(i).deleteObject();
        }

        ArrayList<InsertImage> imageArray = slideList.get(0).getImages();
        for (int i = 0; i < imageArray.size(); i++){
            imageArray.get(i).deleteObject();
        }

        presentation.getUI().setFrameTitle(filepath.getName());
        try {
            for (int i = 0; i < presentation.getSlideList().size()-1; i++) {
                presentation.deleteSlide(presentation.getSlideList().get(i));
            }

            presentation.setFilepath(filepath);
            JSONObject json = new JSONObject(obj);
            JSONArray slideArr = json.getJSONArray("slideList");

            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(obj);
            boolean auto = jsonObject.getBoolean("auto");
            presentation.setAuto(auto);
            if (auto){
                AutoSave autoSave = new AutoSave(presentation);
                autoSave.start();

                presentation.getUI().getAutoSave().setState(true);
            }

            int templateNum = jsonObject.getInt("templateNum");
            presentation.setTemplateNum(templateNum);

            if (templateNum == -1){
                byte[] byteArr = Base64.getDecoder().decode(jsonObject.getString("serializedTheme"));
                ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArr);
                ObjectInputStream objIS = new ObjectInputStream(inputStream);
                ImageIcon icon = (ImageIcon) objIS.readObject();
                presentation.setTheme(icon);
            }



            for (int i = 0; i < slideArr.length(); i++) {
                int slideTemplateNum = slideArr.getJSONObject(i).getInt("slideTemplateNum");
                Slide slide = new Slide(presentation, slideTemplateNum);

                presentation.addSlide(slide);
            }

            Thread.sleep(1001);
            for (int i = 0; i < presentation.getSlideList().size()-1; i++) {
                slide = presentation.getSlideList().get(i);

                int orgHeight = slideArr.getJSONObject(i).getInt("originalHeight");
                int orgWidth = slideArr.getJSONObject(i).getInt("originalWidth");

                slide.setOriginalWidth(orgWidth);
                slide.setOriginalHeight(orgHeight);

                slide.setSlideSize(presentation.getUI().getFrame());

                String hex = slideArr.getJSONObject(i).getString("colourHex");
                Color color = Color.decode(hex);
                slide.setBackground(color);
                slide.setColourHex(hex);

                String speakerNote = slideArr.getJSONObject(i).getString("speakerNotes");
                slide.setSpeakerNotes(speakerNote);

                JSONArray textboxes = slideArr.getJSONObject(i).getJSONArray("textBoxes");

                for (int j = 0; j < textboxes.length(); j++) {

                    JSONObject textbox = textboxes.getJSONObject(j);
                    int textWidth = textboxes.getJSONObject(j).getInt("originalWidthI");
                    int textHeight = textboxes.getJSONObject(j).getInt("originalHeightI");
                    int textX = textbox.getInt("originalX");
                    int textY = textbox.getInt("originalY");

                    String actualText = textboxes.getJSONObject(j).getString("text");

                    JSONArray sizes = textbox.getJSONArray("originalSizes");
                    ArrayList<Integer> originalSizes = new ArrayList<>();
                    for (int x = 0; x < sizes.length(); x++) {
                        originalSizes.add(sizes.getInt(x));
                    }

                    String align = textboxes.getJSONObject(j).getString("align");
                    int styleAlign;

                    if (Objects.equals(align, "left")){
                        styleAlign = StyleConstants.ALIGN_LEFT;
                    }
                    else if (Objects.equals(align, "right")){
                        styleAlign = StyleConstants.ALIGN_RIGHT;
                    }
                    else{
                        styleAlign = StyleConstants.ALIGN_CENTER;
                    }

                    JSONArray attributes = textbox.getJSONArray("Attributes");
                    JSONArray colourArr = textbox.getJSONArray("colourArr");
                    JSONArray fonts = textbox.getJSONArray("fonts");


                    boolean codeText = textboxes.getJSONObject(j).getBoolean("codeText");

                    if (!codeText) {
                        Text text = new Text(slide);

                        text.setBackground(color);

                        text.setOriginalWidthI(textWidth);
                        text.setOriginalHeightI(textHeight);

                        //set text
                        StyledDocument doc = text.getStyledDocument();
                        try {
                            doc.insertString(0, textbox.getString("text"), text.getInputAttributes());
                        } catch (BadLocationException e) {
                            throw new RuntimeException(e);
                        }

                        // set originalSizes ArrayList of text

                        text.setOriginalSizes(originalSizes);
                        text.scale(1, 1);

                        doc = text.getStyledDocument();


                        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
                        StyleConstants.setAlignment(simpleAttributeSet, styleAlign);
                        doc.setParagraphAttributes(0, doc.getLength(), simpleAttributeSet, false);

                        for (int x = 0; x < doc.getLength(); x++) {

                            Element element = doc.getCharacterElement(x);
                            JSONArray elementAttr = attributes.getJSONArray(x);
                            SimpleAttributeSet set = new SimpleAttributeSet();

                            StyleConstants.setBold(set, elementAttr.getBoolean(0));
                            StyleConstants.setItalic(set, elementAttr.getBoolean(1));
                            StyleConstants.setUnderline(set, elementAttr.getBoolean(2));

                            Color elementColor = Color.decode(colourArr.getString(x));
                            StyleConstants.setForeground(set, elementColor);

                            StyleConstants.setFontFamily(set, fonts.getString(x));
                            doc.setCharacterAttributes(x, x + 1, set, false);
                        }

                        // update position
                        text.setOriginalY(textY);
                        text.setOriginalX(textX);

                        slide.addText(text);
                    }
                    else{
                        CodeText text = new CodeText(slide);
                        int indent = textbox.getInt("indent");
                        int numOpen = textbox.getInt("numOpen");
                        int numClose = textbox.getInt("numClose");

                        text.setOriginalWidthI(textWidth);
                        text.setOriginalHeightI(textHeight);

                        //set text
                        StyledDocument doc = text.getStyledDocument();
                        try {
                            doc.insertString(0, textbox.getString("text"), text.getInputAttributes());
                        } catch (BadLocationException e) {
                            throw new RuntimeException(e);
                        }

                        // set originalSizes ArrayList of text

                        text.setOriginalSizes(originalSizes);
                        text.scale(1, 1);

                        doc = text.getStyledDocument();


                        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
                        StyleConstants.setAlignment(simpleAttributeSet, styleAlign);
                        doc.setParagraphAttributes(0, doc.getLength(), simpleAttributeSet, false);

                        for (int x = 0; x < doc.getLength(); x++) {

                            Element element = doc.getCharacterElement(x);
                            JSONArray elementAttr = attributes.getJSONArray(x);
                            SimpleAttributeSet set = new SimpleAttributeSet();

                            StyleConstants.setBold(set, elementAttr.getBoolean(0));
                            StyleConstants.setItalic(set, elementAttr.getBoolean(1));
                            StyleConstants.setUnderline(set, elementAttr.getBoolean(2));

                            Color elementColor = Color.decode(colourArr.getString(x));
                            StyleConstants.setForeground(set, elementColor);

                            StyleConstants.setFontFamily(set, fonts.getString(x));
                            doc.setCharacterAttributes(x, x + 1, set, false);
                        }

                        // update position
                        text.setOriginalY(textY);
                        text.setOriginalX(textX);

                        text.setIndent(indent);
                        text.setNumClose(numClose);
                        text.setNumOpen(numOpen);


                        slide.addText(text);
                    }


                }

                JSONArray images = slideArr.getJSONObject(i).getJSONArray("images");
                for (int j = 0; j < images.length(); j++) {
                    JSONObject imageObject = images.getJSONObject(j);
                    byte[] byteArr = Base64.getDecoder().decode(images.getJSONObject(j).getString("serializedIcon"));
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArr);
                    try {
                        ObjectInputStream objIS = new ObjectInputStream(inputStream);
                        ImageIcon icon = (ImageIcon) objIS.readObject();

                        InsertImage newImage = new InsertImage(slide);
                        newImage.setImage(icon);


                        int originalY = imageObject.getInt("originalY");
                        int originalX = imageObject.getInt("originalX");
                        int width = imageObject.getInt("originalWidthI");
                        int height = imageObject.getInt("originalHeightI");

                        newImage.setOriginalY(originalY);
                        newImage.setOriginalX(originalX);
                        newImage.setOriginalWidthI(width);
                        newImage.setOriginalHeightI(height);
                        slide.addImage(newImage);


                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } catch (ClassNotFoundException e1) {
                        System.out.println(e1.getMessage());
                    }
                }

                slide.setSlideSize(presentation.getUI().getFrame());

            }

            slide.repaint();
            presentation.setOnScreenSlide(slide);
            presentation.updatePreviews(slide);

            presentation.deleteSlide(presentation.getSlideList().get(0));


            for (int i = 0; i < presentation.getSlideList().size(); i++){
                presentation.setOnScreenSlide(presentation.getSlideList().get(i));
                presentation.updatePreviews(presentation.getSlideList().get(i));
            }

            String template = jsonObject.getString("template");
            presentation.setTemplate(template);


        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }




}

