package ui;

import listeners.action.titleScreen.TitleHelpButtonLogic;
import listeners.action.topMenu.HelpButtonLogic;
import listeners.action.titleScreen.NoTemplateLogic;
import listeners.action.titleScreen.OpenPresentationLogic;
import listeners.action.titleScreen.StandardTemplateLogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class for creating a title screen UI
 * This UI is the first window that appears when the program is run
 */
public class TitleScreenUI extends AbstractUI{

    JFrame frame;

    /**
     * Constructor method for the title screen UI
     */
    public TitleScreenUI(){
        //Creates the frame and panels
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel mainTitlePanel = new JPanel(new FlowLayout());
        JPanel templateTitle = new JPanel(new FlowLayout());
        JPanel templatePanel = new JPanel(new FlowLayout());
        JPanel noTemplatePanel = new JPanel(new BorderLayout());
        JPanel standardTemplatePanel = new JPanel(new BorderLayout());
        JPanel templateSectionPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new FlowLayout());

        //Array List for the buttons so formatting can applied at the end
        ArrayList<JButton> buttons = new ArrayList<>();

        frame.add(mainPanel);

        //Title text
        JLabel title = new JLabel("210 Slides");
        title.setFont(new Font(title.getName(), Font.PLAIN, 50));
        mainTitlePanel.add(title);
        mainPanel.add(mainTitlePanel, BorderLayout.NORTH);

        //Template title text
        JLabel templateTitleLabel = new JLabel("Start a new Presentation");
        templateTitleLabel.setFont(new Font(templateTitleLabel.getName(), Font.PLAIN, 25));
        templateTitle.add(templateTitleLabel);

        //No template button
        JButton noTemplate = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/noTemplate.png")), 200, 112));
        noTemplatePanel.add(noTemplate, BorderLayout.NORTH);
        noTemplate.addActionListener(new NoTemplateLogic(this));
        JPanel noTemplateCenter = new JPanel(new FlowLayout());
        noTemplate.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/noTemplateHover.png")), 200, 112));

        //No Template text
        JLabel noTemplateLabel = new JLabel("No Template");
        noTemplateCenter.add(noTemplateLabel);
        noTemplatePanel.add(noTemplateCenter, BorderLayout.CENTER);
        templatePanel.add(noTemplatePanel);
        buttons.add(noTemplate);

        //Standard template button
        JButton standardTemplate = new JButton(createButtonIcon(new ImageIcon(getClass().getResource("/standardTemplate.png")), 200, 112));
        JPanel standardTemplateCenter = new JPanel(new FlowLayout());

        //Standard template text
        JLabel standardTemplateLabel = new JLabel("Standard Template");
        standardTemplateCenter.add(standardTemplateLabel);
        standardTemplatePanel.add(standardTemplateCenter, BorderLayout.CENTER);
        standardTemplatePanel.add(standardTemplate, BorderLayout.NORTH);
        standardTemplate.addActionListener(new StandardTemplateLogic(this));
        templatePanel.add(standardTemplatePanel);
        standardTemplate.setRolloverIcon(createButtonIcon(new ImageIcon(getClass().getResource("/standardTemplateHover.png")), 200, 112));
        buttons.add(standardTemplate);

        //Adding the two panels to the main template panel
        templateSectionPanel.add(templateTitle, BorderLayout.NORTH);
        templateSectionPanel.add(templatePanel, BorderLayout.CENTER);

        //Open button
        ImageIcon icon = new ImageIcon(getClass().getResource("/openButton.png"));
        JButton openButton = new JButton(icon);
        openButton.addActionListener(new OpenPresentationLogic(frame));
        rightPanel.add(openButton, BorderLayout.NORTH);
        buttons.add(openButton);
        openButton.setRolloverIcon(new ImageIcon(getClass().getResource("/openButtonHover.png")));
        openButton.setToolTipText("Open an existing presentation");

        //Help button
        icon = new ImageIcon(getClass().getResource("/helpButton.png"));
        JButton helpButton = new JButton(icon);
        rightPanel.add(helpButton, BorderLayout.CENTER);
        buttons.add(helpButton);
        helpButton.addActionListener(new TitleHelpButtonLogic());
        helpButton.setRolloverIcon(new ImageIcon(getClass().getResource("/helpButtonHover.png")));
        helpButton.setToolTipText("How to use the program");

        mainPanel.add(panel, BorderLayout.CENTER);
        panel.add(templateSectionPanel, BorderLayout.NORTH);
        panel.add(rightPanel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setTitle("SCC210");
        frame.setVisible(true);
        frame.setSize(700, 400);

        //Set formatting for buttons
        for (JButton button : buttons) {
            button.setBorder(null);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
        }

    }


    /**
     * Getter method for the JFrame
     * @return the frame
     */
    public JFrame getFrame(){
        return frame;
    }
}
