package ui;

import listeners.action.topMenu.HelpButtonLogic;
import listeners.action.topMenu.*;
import listeners.action.topMenu.AutoSaveListener;
import listeners.action.uiButtons.SlideEditButton;
import listeners.action.uiButtons.*;
import listeners.component.SlideComponentListener;
import listeners.key.*;
import listeners.mouse.SlideAddMouseListener;
import listeners.window.UIwindowListener;
import presentation.Presentation;
import slide.*;
import presentation.threads.SlidePreviewUpdater;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Thread.sleep;

/**
 * Class for creating the basic UI for the presentation software including the menu
 * the slide editor section and the slide manager
 */
public class UI extends AbstractUI{
    private Slide currentSlide;
    private JFrame frame;
    private final JPanel slideManager;
    private final JPanel slideEditor;
    private JScrollPane scrollPane;
    private SlideComponentListener componentListener;
    private final ArrayList<SlideEditButton> buttonListeners = new ArrayList<>();
    private JCheckBoxMenuItem autoSave;
    private JCheckBoxMenuItem noTemplate;
    private JCheckBoxMenuItem standardTemplate;
    private Action slidePaste;
    private Action slideCopy;
    private Action slideCut;
    private Action saveAction;

    private JLabel buffer;

    private ArrayList<JComponent> components = new ArrayList<>();

    private ArrayList<JComponent> textComponents = new ArrayList<>();

    boolean dark;

    /**
     * Constructor method for the UI.
     * @param presentation this is used so the UI has the presentation for the slide manager.
     */
    public UI(Presentation presentation) {
        dark = false;
        currentSlide = null;
        int defaultImageScale = 33;
        presentation.setUI(this);
        //Creating JPanels
        JPanel topPanel = new JPanel();
        components.add(topPanel);
        JPanel centerPanel = new JPanel();
        components.add(centerPanel);
        slideEditor = new JPanel();
        components.add(slideEditor);
        slideManager = new JPanel();
        components.add(slideManager);
        frame = new JFrame();

        //Layout managers
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);

        topPanel.setLayout(flowLayout);
        topPanel.setBackground(Color.decode("#d9d7d7"));


        //KeyBind
        Action rightAction = new SlideKeyBindRight(presentation);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        slideEditor.getActionMap().put("rightAction", rightAction);

        Action leftAction = new SlideKeyBindLeft(presentation);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        slideEditor.getActionMap().put("leftAction", leftAction);

        slidePaste = new SlidePasteKeybind(currentSlide);
        KeyStroke pasteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pasteKeyStroke, "slidePaste");
        slideEditor.getActionMap().put("slidePaste", slidePaste);

        slideCopy = new SlideCopyKeybind(currentSlide);
        KeyStroke copyKeystroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(copyKeystroke, "slideCopy");
        slideEditor.getActionMap().put("slideCopy", slideCopy);

        slideCut = new SlideCutKeyBind(currentSlide);
        KeyStroke cutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(cutKeyStroke, "slideCut");
        slideEditor.getActionMap().put("slideCut", slideCut);

        saveAction = new SaveKeyBind(presentation);
        KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(saveKeyStroke, "saveAction");
        slideEditor.getActionMap().put("saveAction", saveAction);

        BoldKeyBind boldKeyBind = new BoldKeyBind(presentation);
        KeyStroke boldKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(boldKeyStroke, "boldKeyBind");
        slideEditor.getActionMap().put("boldKeyBind", boldKeyBind);

        ItalicKeyBind italicKeyBind = new ItalicKeyBind(presentation);
        KeyStroke italicKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(italicKeyStroke, "italicKeyBind");
        slideEditor.getActionMap().put("italicKeyBind", italicKeyBind);

        UnderlineKeyBind underlineKeyBind = new UnderlineKeyBind(presentation);
        KeyStroke underlineKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(underlineKeyStroke, "underlineKeyBind");
        slideEditor.getActionMap().put("underlineKeyBind", underlineKeyBind);

        PrintKeyBind printKeyBind = new PrintKeyBind(presentation);
        KeyStroke printKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(printKeyStroke, "printKeyBind");
        slideEditor.getActionMap().put("printKeyBind", printKeyBind);

        UndoKeyBind undoKeyBind = new UndoKeyBind(presentation);
        KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(undoKeyStroke, "undoKeyBind");
        slideEditor.getActionMap().put("undoKeyBind", undoKeyBind);

        RedoKeyBind redoKeyBind = new RedoKeyBind(presentation);
        KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        slideEditor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(redoKeyStroke, "redoKeyBind");
        slideEditor.getActionMap().put("redoKeyBind", redoKeyBind);

        slideManager.setBackground(Color.decode("#d9d7d7"));
        slideEditor.setLayout(null);
        slideManager.setLayout(new FlowLayout(FlowLayout.LEFT));


        //Creates menu bar
        JMenuBar menuBar = new JMenuBar();
        components.add(menuBar);
        menuBar.setBackground(Color.decode("#d9d7d7"));
        JToggleButton autoSaveSwitch = new JToggleButton();
        JMenu fileMenu = new JMenu("File");
        textComponents.add(fileMenu);
        JMenuItem newPres = new JMenu("New");
        components.add(newPres);
        fileMenu.add(newPres);
        JMenuItem newPresNoTemplate = new JMenuItem("No Template");
        components.add(newPresNoTemplate);
        JMenuItem newPresStandardTemplate = new JMenuItem("Standard Template");
        components.add(newPresStandardTemplate);
        newPresNoTemplate.addActionListener(new NewPresentationListener(presentation, "noTemplate"));
        newPresStandardTemplate.addActionListener(new NewPresentationListener(presentation, "standard"));
        newPres.add(newPresNoTemplate);
        newPres.add(newPresStandardTemplate);
        JMenuItem open = new JMenuItem("Open");
        components.add(open);

        open.addActionListener(new OpenListener(presentation));
        JMenuItem save = new JMenuItem("Save");
        components.add(save);
        save.addActionListener(new SaveListener(presentation));
        JMenuItem saveAs = new JMenuItem("Save as..");
        components.add(saveAs);
        saveAs.addActionListener(new SaveAsListener(presentation));
        JMenuItem print = new JMenuItem("Print");
        components.add(print);
        print.addActionListener(new PrintButtonLogic(currentSlide));
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(print);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        textComponents.add(editMenu);

        JMenuItem insertMenu = new JMenu("Insert");
        components.add(insertMenu);
        JMenuItem text = new JMenuItem("Text");
        components.add(text);
        TextBoxButtonLogic insertText = new TextBoxButtonLogic(currentSlide);
        buttonListeners.add(insertText);
        text.addActionListener(insertText);
        JMenuItem image = new JMenuItem("Image");
        components.add(image);
        ImageButtonLogic insertImage = new ImageButtonLogic(currentSlide);
        buttonListeners.add(insertImage);
        image.addActionListener(insertImage);

        insertMenu.add(text);
        insertMenu.add(image);
        menuBar.add(insertMenu);

        JMenuItem undo = new JMenuItem("Undo");
        components.add(undo);
        undo.addActionListener(new UndoListener(presentation));
        JMenuItem redo = new JMenuItem("Redo");
        components.add(redo);
        redo.addActionListener(new RedoListener(presentation));
        editMenu.add(insertMenu);
        editMenu.add(undo);
        editMenu.add(redo);

        JMenuItem theme = new JMenu("Themes");
        JMenuItem remove = new JMenuItem("Remove Theme");
        JMenuItem template1 = new JMenuItem(createButtonIcon(new ImageIcon(getClass().getResource("/template.jpg")), 126, 70));
        theme.add(template1);
        JMenuItem template2 = new JMenuItem(createButtonIcon(new ImageIcon(getClass().getResource("/template2.png")), 126, 70));
        template2.addActionListener(new TemplateListener(presentation, 2));
        theme.add(template2);
        JMenuItem template3 = new JMenuItem(createButtonIcon(new ImageIcon(getClass().getResource("/template3.jpg")), 126, 70));
        template3.addActionListener(new TemplateListener(presentation, 3));
        theme.add(template3);
        JMenuItem addCustom = new JMenuItem("Add Custom Theme");
        theme.add(addCustom);
        addCustom.addActionListener(new TemplateListener(presentation, -1));
        theme.add(remove);
        remove.addActionListener(new TemplateListener(presentation, 0));
        template1.addActionListener(new TemplateListener(presentation, 1));
        editMenu.add(theme);
        menuBar.add(editMenu);


        frame.setJMenuBar(menuBar);

        JMenu settingsMenu = new JMenu("Settings");
        textComponents.add(settingsMenu);
        JMenuItem laserColour = new JMenuItem("Laser Colour");
        components.add(laserColour);
        laserColour.addActionListener(new LaserColourListener(presentation));
        settingsMenu.add(laserColour);
        menuBar.add(settingsMenu);
        autoSave = new JCheckBoxMenuItem("Auto save");
        autoSave.addActionListener(new AutoSaveListener(presentation));
        settingsMenu.add(autoSave);
        components.add(autoSave);
        JMenuItem changeTemplate = new JMenu("Change Template");
        components.add(changeTemplate);
        settingsMenu.add(changeTemplate);
        JCheckBoxMenuItem noTemplateChange = new JCheckBoxMenuItem("No Template");
        JCheckBoxMenuItem standardTemplateChange = new JCheckBoxMenuItem("Standard Template");
        changeTemplate.add(noTemplateChange);
        components.add(noTemplateChange);
        components.add(standardTemplateChange);
        changeTemplate.add(standardTemplateChange);
        noTemplateChange.addActionListener(new TemplateCheckBoxListener(noTemplateChange, standardTemplateChange, presentation));
        standardTemplateChange.addActionListener(new TemplateCheckBoxListener(standardTemplateChange, noTemplateChange, presentation));

        if (Objects.equals(presentation.getTemplate(), "noTemplate")){
            noTemplateChange.setState(true);
        }else {
            standardTemplateChange.setState(true);
        }

        JMenu help = new JMenu("Help");
        textComponents.add(help);
        JMenuItem helpButton = new JMenuItem("Help");
        components.add(helpButton);
        help.add(helpButton);
        helpButton.addActionListener(new HelpButtonLogic(dark, this));
        menuBar.add(help);

        //dark mode button
        Icon lightMode = createButtonIcon(new ImageIcon(getClass().getResource("/cloudy-day.png")), 20, 20);
        JToggleButton darkMode = new JToggleButton(lightMode);
        darkMode.setBorder(null);
        darkMode.setBorderPainted(false);
        darkMode.setContentAreaFilled(false);
        darkMode.setFocusPainted(false);
        darkMode.setToolTipText("Toggle between light and dark mode");
        darkMode.addItemListener(new DarkModeListener(this, darkMode));
        menuBar.add(darkMode);

        ArrayList<JButton> buttons = new ArrayList<>();

        //Save button
        JButton saveButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/save.png")), defaultImageScale, defaultImageScale));
        saveButton.setToolTipText("Save file");
        buttons.add(saveButton);
        topPanel.add(saveButton);
        saveButton.addActionListener(new SaveButtonLogic(presentation));
        saveButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/saveHover.png")), defaultImageScale, defaultImageScale));

        //Open Button
        JButton openButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/open.png")), defaultImageScale, defaultImageScale));
        openButton.setToolTipText("Open file");
        buttons.add(openButton);
        openButton.addActionListener(new OpenButtonLogic(presentation));
        topPanel.add(openButton);
        openButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/openHover.png")), defaultImageScale, defaultImageScale));

        //Print
        JButton printButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/print.png")), defaultImageScale, defaultImageScale));
        buttons.add(printButton);
        topPanel.add(printButton);
        PrintButtonLogic printButtonLogic = new PrintButtonLogic(currentSlide);
        buttonListeners.add(printButtonLogic);
        printButton.addActionListener(printButtonLogic);
        printButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/printHover.png")), defaultImageScale ,defaultImageScale));
        printButton.setToolTipText("Print presentation or export");

        //Font Drop down
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames(); //Gets all the available fonts
        Font[] fonts = ge.getAllFonts();
        JComboBox<String> fontDropDown = new JComboBox<>(fontNames);
        textComponents.add(fontDropDown);
        components.add(fontDropDown);
        FontDropDownLogic fontDropDownLogic = new FontDropDownLogic(currentSlide, fonts, fontDropDown);
        fontDropDown.addActionListener(fontDropDownLogic);
        buttonListeners.add(fontDropDownLogic);
        topPanel.add(fontDropDown);


        // Fill Button
        JButton fillButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/fill.png")), defaultImageScale, defaultImageScale));
        fillButton.setToolTipText("Fill background colour");
        buttons.add(fillButton);
        ColourButtonLogic colourButtonLogic = new ColourButtonLogic(currentSlide, true);
        buttonListeners.add(colourButtonLogic);
        fillButton.addActionListener(colourButtonLogic);
        topPanel.add(fillButton);
        fillButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/fillHover.png")), defaultImageScale, defaultImageScale));

        //Size text
        JLabel sizeLabel = new JLabel("Size");
        textComponents.add(sizeLabel);
        topPanel.add(sizeLabel);
        JTextField sizeInput = new JTextField(3);
        textComponents.add(sizeInput);
        components.add(sizeInput);
        sizeInput.setText("13");
        sizeInput.setBorder(null);
        sizeInput.setFont(new Font("Arial", Font.PLAIN, 16));
        SizeInputLogic sizeInputLogic = new SizeInputLogic(sizeInput, currentSlide);
        sizeInput.addActionListener(sizeInputLogic);
        buttonListeners.add(sizeInputLogic);
        topPanel.add(sizeInput);


        //Size insert box
        JLabel insertLabel = new JLabel("Insert");
        textComponents.add(insertLabel);
        topPanel.add(insertLabel);
        JComboBox insertDropDown = new JComboBox<>(new String[]{"Text", "Image", "List", "CodeText", "New Slide"});
        textComponents.add(insertDropDown);
        components.add(insertDropDown);
        InsertDropDownLogic insertDropDownLogic = new InsertDropDownLogic(insertDropDown, currentSlide);
        insertDropDown.addActionListener(insertDropDownLogic);
        buttonListeners.add(insertDropDownLogic);
        topPanel.add(insertDropDown);

        //Boolean diagram insert
        JComboBox insertBooleanDiagram = new JComboBox<>(new String[]{"AND", "OR", "NOT", "NOR","NAND","XOR","Line","Vertical Line"});
        BooleanDiagramLogic insertBooleanDiagramLogic = new BooleanDiagramLogic(insertBooleanDiagram,currentSlide);
        components.add(insertBooleanDiagram);
        textComponents.add(insertBooleanDiagram);
        insertBooleanDiagram.addActionListener(insertBooleanDiagramLogic);
        buttonListeners.add(insertBooleanDiagramLogic);
        topPanel.add(insertBooleanDiagram);



        //Present Current button
        ImageIcon icon = new ImageIcon(getClass().getResource("/presentC.png"));
        JButton presentCurrentButton = new JButton(icon);
        buttons.add(presentCurrentButton);
        presentCurrentButton.setToolTipText("Present from this slide");
        presentCurrentButton.addActionListener(new PresentCurrentButtonLogic(presentation));
        topPanel.add(presentCurrentButton);
        presentCurrentButton.setRolloverIcon(new ImageIcon(getClass().getResource("/presentCHover.png")));

        //Present button
        icon = new ImageIcon(getClass().getResource("/present.png"));
        JButton presentButton = new JButton(icon);
        buttons.add(presentButton);
        presentButton.setToolTipText("Present from the beginning");
        presentButton.addActionListener(new PresentButtonLogic(presentation));
        topPanel.add(presentButton);
        presentButton.setRolloverIcon(new ImageIcon(getClass().getResource("/presentHover.png")));

        // Create a panel for Save and Load buttons
        JPanel saveLoadPanel = new JPanel(flowLayout);
        saveLoadPanel.setBackground(Color.decode("#d9d7d7"));
        components.add(saveLoadPanel);

        // Text box button
        JButton textBoxButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/textBox.png")), defaultImageScale, defaultImageScale));
        buttons.add(textBoxButton);
        saveLoadPanel.add(textBoxButton);
        TextBoxButtonLogic textBoxButtonLogic = new TextBoxButtonLogic(currentSlide);
        buttonListeners.add(textBoxButtonLogic);
        textBoxButton.addActionListener(textBoxButtonLogic);
        textBoxButton.setToolTipText("Add a text box");
        textBoxButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/textBoxHover.png")), defaultImageScale, defaultImageScale));


        //Bold Button
        icon = new ImageIcon(getClass().getResource("/b.png"));
        JButton boldButton = new JButton(icon);
        buttons.add(boldButton);
        boldButton.setToolTipText("Bold");
        BoldButtonLogic boldButtonLogic = new BoldButtonLogic(currentSlide);
        buttonListeners.add(boldButtonLogic);
        boldButton.addActionListener(boldButtonLogic);
        boldButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/bHover.png")), defaultImageScale, defaultImageScale));

        //Italics Button
        icon = new ImageIcon(getClass().getResource("/i.png"));
        JButton italicButton = new JButton(icon);
        italicButton.setToolTipText("Italic");
        buttons.add(italicButton);
        ItalicsButtonLogic italicsButtonLogic = new ItalicsButtonLogic(currentSlide);
        buttonListeners.add(italicsButtonLogic);
        italicButton.addActionListener(italicsButtonLogic);
        italicButton.setRolloverIcon(new ImageIcon(getClass().getResource("/iHover.png")));

        //Underline Button
        icon = new ImageIcon(getClass().getResource("/u.png"));
        JButton underlineButton = new JButton(icon);
        underlineButton.setToolTipText("Underline");
        buttons.add(underlineButton);
        UnderlineButtonLogic underlineButtonLogic = new UnderlineButtonLogic(currentSlide);
        underlineButton.addActionListener(underlineButtonLogic);
        buttonListeners.add(underlineButtonLogic);
        underlineButton.setRolloverIcon(new ImageIcon(getClass().getResource("/uHover.png")));

        // Add buttons to the top panel
        saveLoadPanel.add(boldButton);
        saveLoadPanel.add(italicButton);
        saveLoadPanel.add(underlineButton);

        //Colour Selector button
        JButton colourButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/color.png")), defaultImageScale, defaultImageScale));
        colourButton.setToolTipText("Select text colour");
        buttons.add(colourButton);
        colourButtonLogic = new ColourButtonLogic(currentSlide, false);
        colourButton.addActionListener(colourButtonLogic);
        buttonListeners.add(colourButtonLogic);
        saveLoadPanel.add(colourButton);
        colourButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/colorHover.png")), defaultImageScale, defaultImageScale));

        // List button
        JButton listButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/list.png")), defaultImageScale, defaultImageScale));
        buttons.add(listButton);
        saveLoadPanel.add(listButton);
        ListButtonLogic listButtonLogic = new ListButtonLogic(currentSlide);
        buttonListeners.add(listButtonLogic);
        listButton.addActionListener(listButtonLogic);
        listButton.setToolTipText("Add a list to a text box");

        //Centre text
        JButton centerButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/centre.png")), defaultImageScale, defaultImageScale));
        buttons.add(centerButton);
        saveLoadPanel.add(centerButton);
        centerButton.setToolTipText("Centre text");
        CentreButtonLogic centreButtonLogic = new CentreButtonLogic(currentSlide, StyleConstants.ALIGN_CENTER);
        buttonListeners.add(centreButtonLogic);
        centerButton.addActionListener(centreButtonLogic);

        //Left Text
        JButton leftButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/left.png")), defaultImageScale, defaultImageScale));
        buttons.add(leftButton);
        saveLoadPanel.add(leftButton);
        leftButton.setToolTipText("Align Text left");
        CentreButtonLogic centreButtonLogicLeft = new CentreButtonLogic(currentSlide, StyleConstants.ALIGN_LEFT);
        buttonListeners.add(centreButtonLogicLeft);
        leftButton.addActionListener(centreButtonLogicLeft);

        //Right Text
        JButton rightButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/right.png")), defaultImageScale, defaultImageScale));
        buttons.add(rightButton);
        saveLoadPanel.add(rightButton);
        rightButton.setToolTipText("Align Text right");
        CentreButtonLogic centreButtonLogicRight = new CentreButtonLogic(currentSlide, StyleConstants.ALIGN_RIGHT);
        buttonListeners.add(centreButtonLogicRight);
        rightButton.addActionListener(centreButtonLogicRight);

        //Code Text
        JButton codeTextButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/code.png")), defaultImageScale, defaultImageScale));
        buttons.add(codeTextButton);
        saveLoadPanel.add(codeTextButton);
        codeTextButton.setToolTipText("Add a code text box");
        CodeTextButtonLogic codeTextButtonLogic = new CodeTextButtonLogic(currentSlide);
        buttonListeners.add(codeTextButtonLogic);
        codeTextButton.addActionListener(codeTextButtonLogic);
        codeTextButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/codeHover.png")), defaultImageScale, defaultImageScale));

        //Insert image
        JButton imageButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/image.png")), defaultImageScale, defaultImageScale));
        buttons.add(imageButton);
        saveLoadPanel.add(imageButton);
        ImageButtonLogic imageButtonLogic = new ImageButtonLogic(currentSlide);
        buttonListeners.add(imageButtonLogic);
        imageButton.addActionListener(imageButtonLogic);
        imageButton.setToolTipText("Insert an image");
        imageButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/imageHover.png")), defaultImageScale, defaultImageScale));

        // Speaker notes
        JButton speakerNotesButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/speakerNote.png")), defaultImageScale, defaultImageScale));
        buttons.add(speakerNotesButton);
        saveLoadPanel.add(speakerNotesButton);
        SpeakerNotesButtonLogic speakerNotesButtonLogic = new SpeakerNotesButtonLogic(currentSlide);
        buttonListeners.add(speakerNotesButtonLogic);
        speakerNotesButton.addActionListener(speakerNotesButtonLogic);
        speakerNotesButton.setToolTipText("Add speaker notes");
        speakerNotesButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/speakerNoteHover.png")), defaultImageScale, defaultImageScale));

        JButton addSlide = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/addSlide.png")), defaultImageScale, defaultImageScale));
        buttons.add(addSlide);
        saveLoadPanel.add(addSlide);
        AddButtonLogic addButtonLogic = new AddButtonLogic(presentation);
        addSlide.addActionListener(addButtonLogic);
        addSlide.setToolTipText("Add a a new slide");
        addSlide.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/addSlideHover.png")), defaultImageScale, defaultImageScale));

        // JPanels for the slide manager
        JPanel bottomContainer = new JPanel(new BorderLayout());
        components.add(bottomContainer);
        JPanel addCreatePanel = new JPanel(new BorderLayout());
        addCreatePanel.setLayout(new BoxLayout(addCreatePanel, BoxLayout.Y_AXIS));
        components.add(addCreatePanel);

        //Slide add button
        JButton addButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/plus.png")), 50, 50));
        addButton.setToolTipText("Create a new slide");
        addButton.addMouseListener(new SlideAddMouseListener(addButton, presentation));
        buttons.add(addButton);

        addButton.addActionListener(addButtonLogic);
        addButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/plusHover.png")), 50, 50));

        //Slide delete button
        JButton deleteButton = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/minus.png")), 50, 50));
        deleteButton.setToolTipText("Delete current slide");
        buttons.add(deleteButton);
        DeleteButtonLogic deleteButtonLogic = new DeleteButtonLogic(presentation, currentSlide);
        buttonListeners.add(deleteButtonLogic);
        deleteButton.addActionListener(deleteButtonLogic);
        deleteButton.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/minusHover.png")), 50, 50));

        //Creates a buffer meaning full slide preview always visible
        buffer = new JLabel(".");
        components.add(buffer);
        buffer.setFont(new Font(buffer.getFont().getName(), buffer.getFont().getStyle(), 30));
        buffer.setForeground(Color.decode("#d9d7d7"));

        addCreatePanel.add(buffer, BorderLayout.SOUTH);
        addCreatePanel.add(addButton, BorderLayout.NORTH);
        addCreatePanel.add(deleteButton, BorderLayout.CENTER);
        addCreatePanel.setBackground(Color.decode("#d9d7d7"));
        bottomContainer.add(addCreatePanel, BorderLayout.EAST);

        //Creates scroll pane to allow for scrolling when there are a lot of slides
        scrollPane = new JScrollPane(slideManager);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, scrollPane.getPreferredSize().height));

        scrollPane.setBorder(null);
        bottomContainer.add(scrollPane);

        // Use a container to hold the topPanel and saveLoadPanel
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.add(topPanel);
        topContainer.add(saveLoadPanel);

        frame.add(topContainer, BorderLayout.NORTH);
        topContainer.setBackground(Color.decode("#b3b5ba"));
        frame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout()); // Set BorderLayout for centerPanel
        centerPanel.add(slideEditor, BorderLayout.CENTER); // Add slideEditor to the center of centerPanel
        centerPanel.add(bottomContainer, BorderLayout.SOUTH); // Add slideManager to the east of centerPanel

        slideEditor.setBackground(Color.decode("#e1e3e8"));

        frame.add(topContainer, BorderLayout.NORTH);
        frame.setTitle("Untitled Presentation");
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);

        //Set formatting for buttons
        for (JButton button : buttons) {
            button.setBorder(null);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
        }

        //Create new thread for updating slide previews
        SlidePreviewUpdater slidePreviewUpdater = new SlidePreviewUpdater(presentation);
        slidePreviewUpdater.start();


        componentListener = new SlideComponentListener(presentation);
        frame.addComponentListener(componentListener);
        frame.addWindowListener(new UIwindowListener(presentation));

        currentSlide = new Slide(presentation);
        presentation.addSlide(currentSlide);

    }

    public void setFrameTitle(String title){
        frame.setTitle(title);
    }

    /**
     * Method for changing the action listeners when a new slide is clicked
     */
    public void resetActionListeners(){
        for (SlideEditButton buttonListener : buttonListeners) buttonListener.setCurrentSlide(currentSlide);

        SlidePasteKeybind slidePasteKeybind = (SlidePasteKeybind) slidePaste;
        slidePasteKeybind.setSlide(currentSlide);

        SlideCopyKeybind slideCopyKeybind = (SlideCopyKeybind) slideCopy;
        slideCopyKeybind.setSlide(currentSlide);

        SlideCutKeyBind slideCutKeyBind = (SlideCutKeyBind) slideCut;
        slideCutKeyBind.setSlide(currentSlide);

    }

    public JFrame getFrame(){
        return frame;
    }

    /**
     * Setter method for slide variable
     * @param slide new slide
     */
    public void setSlide(Slide slide){
        currentSlide = slide;
    }

    /**
     * Getter method for the slide Manager JPanel
     * @return the SlideManager
     */
    public JPanel getSlideManager(){
        return slideManager;
    }

    /**
     * Getter method for the slide editor JPanel
     * @return the slide editor
     */
    public JPanel getSlideEditor()
    {
        return slideEditor;
    }

    /**
     * Getter method for the current slide
     * @return the current slide
     */
    public Slide getCurrentSlide()
    {
        return currentSlide;
    }

    /**
     * Getter method for the Scroll pane for slide manager
     * @return the scroll pane
     */
    public JScrollPane getScrollPane() {return scrollPane;}

    public JCheckBoxMenuItem getAutoSave(){
        return autoSave;
    }
    /**
     * Method for converting a slide into an ImageIcon
     * @param slide the slide being converted
     * @return the ImageIcon
     */

    public ImageIcon panelToImageIcon(Slide slide, int width, int height) {
        //Creates a new BufferedImage
        BufferedImage image;
        try {
            image = new BufferedImage(slide.getWidth(), slide.getHeight(), BufferedImage.TYPE_INT_ARGB);
            //Create Graphics2D
            Graphics2D graphics2D = image.createGraphics();
            //Sets what the put into the ImageIcon
            graphics2D.setClip(0, 0, slide.getWidth(), slide.getHeight());
            //Paints the icon
            slide.paintAll(graphics2D);

            return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (IllegalArgumentException e) {
            return new ImageIcon();
        }
    }

    /**
     * Method for setting the initial slide preview (Uses overridden print method)
     * @param panel the slide being converted
     * @return the ImageIcon
     */
    public ImageIcon initSlidePreview(Slide panel){
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.startPrint(image.createGraphics());

        return new ImageIcon(image.getScaledInstance(200, 100, Image.SCALE_SMOOTH));
    }

    public JCheckBoxMenuItem getNoTemplate() {
        return noTemplate;
    }

    public void setNoTemplate(JCheckBoxMenuItem noTemplate) {
        this.noTemplate = noTemplate;
    }

    public JCheckBoxMenuItem getStandardTemplate() {
        return standardTemplate;
    }

    public void setStandardTemplate(JCheckBoxMenuItem standardTemplate) {
        this.standardTemplate = standardTemplate;
    }

    public void setColourMode(Boolean dark){
        Color colour;
        Color menuColor;
        Color slideEditorColour;
        Color textColour;
        if(dark){
            colour = Color.decode("#606161");
            menuColor = Color.decode("#B2B4B3");
            slideEditorColour = Color.decode("#999999");
            textColour = Color.WHITE;
        }
        else{
            colour = Color.decode("#d9d7d7");
            menuColor = Color.decode("#FFFFFF");
            slideEditorColour = Color.decode("#e1e3e8");
            textColour = Color.BLACK;
        }
        for(int i = 0; i < components.size(); i++){
            if(components.get(i) instanceof JComboBox || components.get(i) instanceof JTextField || components.get(i) instanceof JMenuItem || components.get(i) instanceof JCheckBoxMenuItem || components.get(i) instanceof JMenu){
                components.get(i).setBackground(slideEditorColour);
            }
            else if(components.get(i) == slideEditor){
                components.get(i).setBackground(slideEditorColour);

            }
            else if(components.get(i) == buffer){
                components.get(i).setForeground(colour);
            }
            else{
                components.get(i).setBackground(colour);
            }
        }
        for(int i = 0; i < textComponents.size(); i++){
            textComponents.get(i).setForeground(textColour);
        }
    }

    public boolean getDark(){
        return dark;
    }

    public void setDark(boolean setDark){
        this.dark = setDark;
    }
}