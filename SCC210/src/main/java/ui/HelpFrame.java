
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HelpFrame extends AbstractUI{
    private JTextField askField;
    private ArrayList<JTextArea> identifyTextAreas;

    private ArrayList<JComponent> components= new ArrayList<>();

    private ArrayList<JComponent> textComponents = new ArrayList<>();

    private UI ui;

    private boolean dark;

    private Color darkColour = Color.decode("#606161");

    private Color lightColour = Color.decode("#d9d7d7");

    private Color lightHighlight = Color.YELLOW;

    private Color darkHighlight = Color.decode("#D5B60A");

    public HelpFrame() {

        Color colour = lightColour;
        identifyTextAreas = new ArrayList<>();
        JFrame frame = new JFrame();
        frame.setTitle("Help Section ");


        JPanel mainPanel = new JPanel(new BorderLayout());
        JTabbedPane firstRowPanel = new JTabbedPane();
        components.add(firstRowPanel);
        textComponents.add(firstRowPanel);

        //insertable features panel
        JPanel p1 = new JPanel();
        components.add(p1);
        p1.setBackground(colour);
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

        JPanel p1topPanel = new JPanel(new FlowLayout());
        components.add(p1topPanel);
        p1topPanel.setBackground(colour);
        Icon addIcon = createButtonIcon(new ImageIcon(getClass().getResource("/plus.png")), 50, 50);
        Icon minusIcon = createButtonIcon(new ImageIcon(getClass().getResource("/minus.png")), 50, 50);
        JLabel addLabel = new JLabel(addIcon);
        JLabel minusLabel = new JLabel(minusIcon);
        p1topPanel.add(addLabel);
        p1topPanel.add(minusLabel);
        JTextArea addText = new JTextArea("Slides can be added and deleted using the plus and minus buttons on the bottom right hand side of the window");
        addText.setFont(addText.getFont().deriveFont(Font.BOLD));
        addText.setBackground(lightColour);
        components.add(addText);
        addText.setEditable(false);
        textComponents.add(addText);
        identifyTextAreas.add(addText);
        p1topPanel.add(addText);
        p1.add(p1topPanel);

        JPanel p1midPanel = new JPanel(new FlowLayout());
        components.add(p1midPanel);
        p1midPanel.setBackground(colour);
        Icon addSlideIcon = createButtonIcon(new ImageIcon(getClass().getResource("/addSlide.png")), 50, 50);
        JLabel addSlideLabel = new JLabel(addSlideIcon);
        p1midPanel.add(addSlideLabel);
        JTextArea addSlideText = new JTextArea("Selection of this icon can also be used to add slides to presentation");
        addSlideText.setFont(addSlideText.getFont().deriveFont(Font.BOLD));
        addSlideText.setBackground(lightColour);
        textComponents.add(addSlideText);
        components.add(addSlideText);
        addSlideText.setEditable(false);
        identifyTextAreas.add(addSlideText);
        p1midPanel.add(addSlideText);
        p1.add(p1midPanel);

        JPanel p1bottomPanel = new JPanel(new FlowLayout());
        components.add(p1bottomPanel);
        p1bottomPanel.setBackground(colour);
        Icon previewIcon = createButtonIcon(new ImageIcon(getClass().getResource("/slidePreviewMenu.png")), 194, 95);
        JLabel previewLabel = new JLabel(previewIcon);
        p1bottomPanel.add(previewLabel);
        JTextArea slidePreviewText = new JTextArea("A slide preview bar can be found at the bottom of the window. \n" +
                "Through right clicking on a slide preview, a menu appears which allows for the addition and deletion of slides. " +
                "\nThis menu also allows user to copy and paste slides to enable reordering of slides. \n" +
                "This can also be done through dragging and dropping slides on the preview bar");
        slidePreviewText.setFont(slidePreviewText.getFont().deriveFont(Font.BOLD));
        slidePreviewText.setBackground(lightColour);
        identifyTextAreas.add(slidePreviewText);
        components.add(slidePreviewText);
        textComponents.add(slidePreviewText);
        p1bottomPanel.add(slidePreviewText);
        p1.add(p1bottomPanel);

        JPanel p1panel4 = new JPanel(new FlowLayout());
        p1panel4.setBackground(colour);
        components.add(p1panel4);
        Icon designIcon = createButtonIcon(new ImageIcon(getClass().getResource("/design.png")), 171, 167);
        JLabel designLabel = new JLabel(designIcon);
        p1panel4.add(designLabel);
        JTextArea designText = new JTextArea("To add a theme to your presentation, navigate this menu to select desired theme.\n" +
                "Selected theme will then appear on all slides unless manually removed.\n" +
                "Custom themes can be added by uploading images to files and setting a custom theme.");
        designText.setFont(designText.getFont().deriveFont(Font.BOLD));
        designText.setEditable(false);
        designText.setBackground(lightColour);
        components.add(designText);
        textComponents.add(designText);
        identifyTextAreas.add(designText);
        p1panel4.add(designText);
        p1.add(p1panel4);


        JPanel p2 = new JPanel();
        components.add(p2);
        p2.setBackground(colour);
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));


        JPanel p2Panel1 = new JPanel(new FlowLayout());
        components.add(p2Panel1);
        p2Panel1.setBackground(colour);
        Icon textBoxIcon = createButtonIcon(new ImageIcon(getClass().getResource("/textBox.png")), 50, 50);
        JLabel textBoxLabel = new JLabel(textBoxIcon);
        p2Panel1.add(textBoxLabel);
        JTextArea textBox = new JTextArea("Pressing this icon will add a new text box to the slide");
        textBox.setFont(textBox.getFont().deriveFont(Font.BOLD));
        textBox.setBackground(lightColour);
        textBox.setEditable(false);
        components.add(textBox);
        textComponents.add(textBox);
        identifyTextAreas.add(textBox);
        p2Panel1.add(textBox);
        p2.add(p2Panel1);


        JPanel textEditingImages = new JPanel(new FlowLayout());
        textEditingImages.setBackground(colour);
        components.add(textEditingImages);
        Icon boldIcon = createButtonIcon(new ImageIcon(getClass().getResource("/b.png")), 30, 30);
        JLabel boldLabel = new JLabel(boldIcon);
        Icon italicIcon = createButtonIcon(new ImageIcon(getClass().getResource("/i.png")), 30, 30);
        JLabel italicLabel = new JLabel(italicIcon);
        Icon underlineIcon = createButtonIcon(new ImageIcon(getClass().getResource("/u.png")), 30, 30);
        JLabel underlineLabel = new JLabel(underlineIcon);
        Icon colourIcon = createButtonIcon(new ImageIcon(getClass().getResource("/color.png")), 30, 30);
        JLabel colourLabel = new JLabel(colourIcon);
        Icon leftIcon = createButtonIcon(new ImageIcon(getClass().getResource("/left.png")), 30, 30);
        JLabel leftLabel = new JLabel(leftIcon);
        Icon centreIcon = createButtonIcon(new ImageIcon(getClass().getResource("/centre.png")), 30, 30);
        JLabel centreLabel = new JLabel(centreIcon);
        Icon rightIcon = createButtonIcon(new ImageIcon(getClass().getResource("/right.png")), 30, 30);
        JLabel rightLabel = new JLabel(rightIcon);
        Icon fontIcon = createButtonIcon(new ImageIcon(getClass().getResource("/fontExample.png")), 125, 73);
        JLabel fontLabel = new JLabel(fontIcon);
        Icon sizeIcon = createButtonIcon(new ImageIcon(getClass().getResource("/sizeExample.png")), 93, 31);
        JLabel sizeLabel = new JLabel(sizeIcon);

        textEditingImages.add(boldLabel);
        textEditingImages.add(italicLabel);
        textEditingImages.add(underlineLabel);
        textEditingImages.add(colourLabel);
        textEditingImages.add(leftLabel);
        textEditingImages.add(centreLabel);
        textEditingImages.add(rightLabel);
        textEditingImages.add(fontLabel);
        textEditingImages.add(sizeLabel);

        JPanel p2Panel2 = new JPanel(new FlowLayout());
        components.add(p2Panel2);
        p2Panel2.setBackground(colour);
        p2Panel2.add(textEditingImages);
        JTextArea textEditText = new JTextArea("Highlight a section of text and select attributes to format it.\n" +
                "Bold, italic, underline, colours and allignment are selected by clicking the associated icon.\n" +
                "Fonts are chosen from the Font drop down menu.\n" +
                "Desired font size is entered into text field.");
        textEditText.setFont(textEditText.getFont().deriveFont(Font.BOLD));
        textEditText.setBackground(lightColour);
        textEditText.setEditable(false);
        components.add(textEditText);
        textComponents.add(textEditText);
        identifyTextAreas.add(textEditText);
        p2Panel2.add(textEditText);
        p2.add(p2Panel2);


        JPanel p2Panel3 = new JPanel(new FlowLayout());
        components.add(p2Panel3);
        p2Panel3.setBackground(colour);
        Icon imageIcon = createButtonIcon(new ImageIcon(getClass().getResource("/image.png")), 50, 50);
        JLabel imageLabel = new JLabel(imageIcon);
        p2Panel3.add(imageLabel);
        JTextArea imageText = new JTextArea("Click on this icon and navigate files to find desired image. Once selected, image will appear on current slide.\n" +
                "Alternatively, images can be copied to clipboard and pasted directly to slides.");
        imageText.setFont(imageText.getFont().deriveFont(Font.BOLD));
        imageText.setBackground(lightColour);
        identifyTextAreas.add(imageText);
        components.add(imageText);
        textComponents.add(imageText);
        imageText.setEditable(false);
        p2Panel3.add(imageText);
        p2.add(p2Panel3);


        JPanel p2Panel4 = new JPanel(new FlowLayout());
        components.add(p2Panel4);
        p2Panel4.setBackground(colour);
        JTextArea resizeText = new JTextArea("Both images and text boxes can be resized by hovering over thier bottom left corner and dragging the item to the desired size\n" +
                "Hovering and clicking the top border allows both to be moved around slide");
        resizeText.setFont(resizeText.getFont().deriveFont(Font.BOLD));
        resizeText.setBackground(lightColour);
        resizeText.setEditable(false);
        components.add(resizeText);
        textComponents.add(resizeText);
        identifyTextAreas.add(slidePreviewText);
        p2.add(p2Panel4);

        JPanel p2Panel5 = new JPanel(new FlowLayout());
        components.add(p2Panel5);
        p2Panel5.setBackground(colour);
        Icon rightMenuIcon = createButtonIcon(new ImageIcon(getClass().getResource("/rightMenu.png")), 122, 57);
        JLabel rightMenuLabel = new JLabel(rightMenuIcon);
        p2Panel5.add(rightMenuLabel);
        JTextArea rightMenuText = new JTextArea("Right clicking an image or text box will display a menu.\n" +
                "The menu gives you the option to delete, cut, copy and paste selected item.\n" +
                "The Resize option takes width and height input measurements to resize the image to a set size.");
        rightMenuText.setFont(rightMenuText.getFont().deriveFont(Font.BOLD));
        rightMenuText.setBackground(lightColour);
        rightMenuText.setEditable(false);
        components.add(rightMenuText);
        textComponents.add(rightMenuText);
        identifyTextAreas.add(rightMenuText);
        p2Panel5.add(rightMenuText);
        p2.add(p2Panel5);

        JPanel p2Panel6 = new JPanel(new FlowLayout());
        components.add(p2Panel6);
        p2Panel6.setBackground(colour);
        Icon insertMenuIcon = createButtonIcon(new ImageIcon(getClass().getResource("/insertDropDown.png")), 99, 124);
        JLabel insertMenuLabel = new JLabel(insertMenuIcon);
        p2Panel6.add(insertMenuLabel);
        JTextArea insertMenuText = new JTextArea("All insertables can be added to slide using the Insert drop down menu located on the top menu bar");
        insertMenuText.setFont(insertMenuText.getFont().deriveFont(Font.BOLD));
        insertMenuText.setBackground(lightColour);
        insertMenuText.setEditable(false);
        components.add(insertMenuText);
        textComponents.add(insertMenuText);
        identifyTextAreas.add(insertMenuText);
        p2Panel6.add(insertMenuText);
        p2.add(p2Panel6);


        // saving/loading
        JPanel p3 = new JPanel();
        components.add(p3);
        p3.setBackground(colour);
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        JPanel p3topPanel = new JPanel(new FlowLayout());
        components.add(p3topPanel);
        p3topPanel.setBackground(colour);
        Icon saveIcon = createButtonIcon(new ImageIcon(getClass().getResource("/save.png")), 50, 50);
        JLabel saveLabel = new JLabel(saveIcon);
        p3topPanel.add(saveLabel);
        JTextArea saveText = new JTextArea("To save presentation, select this icon and navigate files to save to desired file path.");
        saveText.setFont(saveText.getFont().deriveFont(Font.BOLD));
        saveText.setBackground(lightColour);
        components.add(saveText);
        textComponents.add(saveText);
        saveText.setEditable(false);
        identifyTextAreas.add(saveText);
        p3topPanel.add(saveText);

        JPanel p3midPanel = new JPanel(new FlowLayout());
        components.add(p3midPanel);
        p3midPanel.setBackground(colour);
        Icon loadIcon = createButtonIcon(new ImageIcon(getClass().getResource("/open.png")), 50, 50);
        JLabel loadLabel = new JLabel(loadIcon);
        p3midPanel.add(loadLabel);
        JTextArea loadText = new JTextArea("To load an existing presentation, select this icon and navigate files to open a presentation");
        loadText.setFont(loadText.getFont().deriveFont(Font.BOLD));
        loadText.setBackground(lightColour);
        components.add(loadText);
        textComponents.add(loadText);
        identifyTextAreas.add(loadText);
        p3midPanel.add(loadText);

        JPanel p3bottomPanel = new JPanel(new FlowLayout());
        components.add(p3bottomPanel);
        p3bottomPanel.setBackground(colour);
        Icon printIcon = createButtonIcon(new ImageIcon(getClass().getResource("/print.png")), 50, 50);
        JLabel printLabel = new JLabel(printIcon);
        p3bottomPanel.add(printLabel);
        JTextArea printText = new JTextArea("Through selecting this icon, you are given the option to print current presentation or save it to a pdf.");
        printText.setFont(printText.getFont().deriveFont(Font.BOLD));
        printText.setBackground(lightColour);
        components.add(printText);
        textComponents.add(printText);
        identifyTextAreas.add(printText);
        p3bottomPanel.add(printText);

        JPanel autoSavePanel = new JPanel(new FlowLayout());
        components.add(autoSavePanel);
        autoSavePanel.setBackground(colour);
        Icon autoSaveIcon = createButtonIcon(new ImageIcon(getClass().getResource("/autoSaveExample.png")), 172, 106);
        JLabel autoLabel = new JLabel(autoSaveIcon);
        autoSavePanel.add(autoLabel);
        JTextArea autoText = new JTextArea("Navigating the Settings menu and selecting Auto save will periodically save the current presentation to files every 2 seconds.\n");
        autoText.setFont(autoText.getFont().deriveFont(Font.BOLD));
        autoText.setBackground(lightColour);
        autoText.setEditable(false);
        components.add(autoText);
        textComponents.add(autoText);
        identifyTextAreas.add(autoText);
        autoSavePanel.add(autoText);

        p3.add(p3topPanel);
        p3.add(p3midPanel);
        p3.add(p3bottomPanel);
        p3.add(autoSavePanel);


        JPanel p4 = new JPanel();
        p4.setBackground(colour);
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));

        JPanel p4topPanel = new JPanel(new FlowLayout());
        components.add(p4topPanel);
        p4topPanel.setBackground(colour);
        Icon presentIcon = createButtonIcon(new ImageIcon(getClass().getResource("/present.png")), 93, 33);
        JLabel presentLabel = new JLabel(presentIcon);
        p4topPanel.add(presentLabel);
        JTextArea presentText = new JTextArea("Clicking this icon will start present mode, which maximises slides to full screen.\n" +
                "Once in this mode, slides cannot be edited.\n" +
                "Starting from the first slide, the presentation can be navigated through clicking the Left and Right Arrow keys to change slides. \n" +
                "Alternatively, slides can be changed using the Space bar.\n" +
                "Exit this mode by pressing Esc.");
        presentText.setFont(presentText.getFont().deriveFont(Font.BOLD));
        presentText.setBackground(lightColour);
        identifyTextAreas.add(presentText);
        components.add(presentText);
        textComponents.add(presentText);
        presentText.setEditable(false);
        p4topPanel.add(presentText);
        p4.add(p4topPanel);

        JPanel p4midPanel = new JPanel(new FlowLayout());
        components.add(p4midPanel);
        p4midPanel.setBackground(colour);
        Icon presentCurrentIcon = createButtonIcon(new ImageIcon(getClass().getResource("/presentC.png")), 171, 33);
        JLabel presentCLabel = new JLabel(presentCurrentIcon);
        p4midPanel.add(presentCLabel);
        JTextArea presentCText = new JTextArea("This icon starts present mode starting from the Slide currently being edited.\n" +
                "Presentation functionality same as above.");
        presentCText.setFont(presentCText.getFont().deriveFont(Font.BOLD));
        presentCText.setBackground(lightColour);
        presentCText.setEditable(false);
        identifyTextAreas.add(presentCText);
        components.add(presentCText);
        textComponents.add(presentCText);
        p4midPanel.add(presentCText);
        p4.add(p4midPanel);

        JPanel p4bottomPanel = new JPanel(new FlowLayout());
        components.add(p4bottomPanel);
        p4bottomPanel.setBackground(colour);
        Icon laserIcon = createButtonIcon(new ImageIcon(getClass().getResource("/LaserColourExample.png")), 170, 122);
        JLabel laserLabel = new JLabel(laserIcon);
        p4bottomPanel.add(laserLabel);
        JTextArea laserText = new JTextArea("Whilst in the presentation, a laser will appear on screen and follow cursor movement.\n" +
                "The colour of this can be edited by selecting Laser Colour on the Settings drop down menu and choosing a colour");
        laserText.setFont(laserText.getFont().deriveFont(Font.BOLD));
        laserText.setBackground(lightColour);
        laserText.setEditable(false);
        components.add(laserText);
        textComponents.add(laserText);
        identifyTextAreas.add(laserText);
        p4bottomPanel.add(laserText);

        p4.add(p4bottomPanel);


        JPanel speakerNotesPanel = new JPanel(new FlowLayout());
        components.add(speakerNotesPanel);
        speakerNotesPanel.setBackground(lightColour);
        Icon speakerIcon = createButtonIcon(new ImageIcon(getClass().getResource("/speakerNote.png")),  50, 50);
        JLabel speakerLabel = new JLabel(speakerIcon);
        speakerNotesPanel.add(speakerLabel);
        JTextArea speakerText = new JTextArea("Speaker notes can be added to each slide as a presentation aid.\n" +
                "These are viewed on a separate window.\n" +
                "When in present mode, the speaker note window will update to display the speaker note for the corresponding slide");
        speakerText.setEditable(false);
        speakerText.setFont(speakerText.getFont().deriveFont(Font.BOLD));
        speakerText.setBackground(lightColour);
        identifyTextAreas.add(speakerText);
        components.add(speakerText);
        textComponents.add(speakerText);
        speakerNotesPanel.add(speakerText);
        p4.add(speakerNotesPanel);


        JPanel p5 = new JPanel();
        components.add(p5);
        p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
        JPanel p5topPanel = new JPanel(new FlowLayout());
        components.add(p5topPanel);
        p5topPanel.setBackground(colour);
        Icon booleanIcon = createButtonIcon(new ImageIcon(getClass().getResource("/booleanExample.png")), 81, 178);
        JLabel booleanLabel = new JLabel(booleanIcon);
        p5topPanel.add(booleanLabel);
        JTextArea booleanText = new JTextArea("Boolean diagrams can be added to the presentation.\n" +
                "This includes: AND, OR, NOT, NOR, NAND, XOR and connecting lines");
        booleanText.setFont(booleanText.getFont().deriveFont(Font.BOLD));
        booleanText.setBackground(lightColour);
        components.add(booleanText);
        textComponents.add(booleanText);
        booleanText.setEditable(false);
        identifyTextAreas.add(booleanText);
        p5topPanel.add(booleanText);
        p5.add(p5topPanel);

        JPanel codeTextImages = new JPanel(new FlowLayout());
        components.add(codeTextImages);
        codeTextImages.setBackground(colour);
        Icon codeInsertIcon = createButtonIcon(new ImageIcon(getClass().getResource("/codeTextInsert.png")), 147, 117);
        JLabel codeInsertLabel = new JLabel(codeInsertIcon);
        codeTextImages.add(codeInsertLabel);
        Icon codeTextIcon = createButtonIcon(new ImageIcon(getClass().getResource("/code.png")), 50, 50);
        JLabel codeTextLabel = new JLabel(codeTextIcon);
        codeTextImages.add(codeTextLabel);

        JPanel p5midPanel = new JPanel(new FlowLayout());
        components.add(p5midPanel);
        p5midPanel.setBackground(colour);
        p5midPanel.add(codeTextImages);
        JTextArea codeText = new JTextArea("To insert a code text box, select this icon. This text box will detect code key works and highlight them to mimic a code editor.\n" +
                "Code will indent accordingly to detected key words\n" +
                "Code text boxes can be edited whilst in presentation mode to simulate live code demos.");
        codeText.setFont(codeText.getFont().deriveFont(Font.BOLD));
        codeText.setBackground(lightColour);
        codeText.setEditable(false);
        components.add(codeText);
        textComponents.add(codeText);
        identifyTextAreas.add(codeText);
        p5midPanel.add(codeText);
        p5.add(p5midPanel);

        JPanel p6 = new JPanel();
        p6.setBackground(lightColour);
        p6.setLayout(new BoxLayout(p6, BoxLayout.Y_AXIS));
        JTextArea keyBinds = new JTextArea("The following keyboard shortcuts can be used for alternative access to functions:\n\n" +
                "CTRL + C to copy\n" +
                "CTRL + V to paste\n" +
                "CTRL + Z to undo text\n" +
                "CTRL + SHIFT + Z to redo text\n" +
                "CTRL + S to save\n" +
                "CTRL + P to print\n" +
                "CTRL + SHIFT + B to bold selected text\n" +
                "CTRL + SHIFT + I to italic selected text\n" +
                "CTRL + SHIFT + U to underline selected text\n" +
                "CTRL + S (In present mode) to open speaker notes");
        keyBinds.setFont(keyBinds.getFont().deriveFont(Font.BOLD));
        keyBinds.setBackground(lightColour);
        keyBinds.setEditable(false);
        identifyTextAreas.add(keyBinds);
        textComponents.add(keyBinds);
        components.add(keyBinds);
        p6.add(keyBinds);

        firstRowPanel.add("Slides", p1);
        firstRowPanel.add("Insertable features", p2);
        firstRowPanel.add("Saving/Loading", p3);
        firstRowPanel.add("Presentation Mode", p4);
        firstRowPanel.add("Computer Science features", p5);
        firstRowPanel.add("Key Binds", p6);
        mainPanel.add(firstRowPanel, BorderLayout.CENTER);

        JPanel askPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        components.add(askPanel);
        askField = new JTextField(20);
        components.add(askField);
        askField.setToolTipText("Search");
        textComponents.add(askField);
        askPanel.add(new JLabel("Search:"));
        textComponents.add(askPanel);
        askPanel.add(askField);
        mainPanel.add(askPanel, BorderLayout.NORTH);

        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(mainPanel);

        askField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String search = askField.getText().trim().toLowerCase();
                sectionHelp(search);
            }
        });

    }



    public void sectionHelp(String search) {
        for (JTextArea textArea : identifyTextAreas) {
            String text = textArea.getText().toLowerCase();
            if (text.contains(search)) {
                if(dark){
                    textArea.setBackground(darkHighlight);
                }
                else{
                    textArea.setBackground(Color.YELLOW);
                }
            } else {
                if(dark){
                    textArea.setBackground(darkColour);
                }
                else {
                    textArea.setBackground(lightColour);
                }
            }
        }


    }

    public void setColourMode(Boolean darkMode){
        Color colour;
        Color menuColor;
        Color slideEditorColour;
        Color textColour;
        if(darkMode){
            colour = darkColour;

            textColour = Color.WHITE;
            dark = true;

        }
        else{
            colour = lightColour;
            textColour = Color.BLACK;
            dark = false;
        }
        for(int i = 0; i < components.size(); i++){
            components.get(i).setBackground(colour);
        }
        for(int i = 0; i < textComponents.size(); i++){
            textComponents.get(i).setForeground(textColour);
        }

    }

    public void setUI(UI ui){
        this.ui = ui;
    }

    public boolean getDark(){
        return dark;
    }
    public UI getUI(){
        return ui;
    }




}