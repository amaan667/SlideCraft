package ui;

import listeners.action.colourUIButtons.ColourCancelLogic;
import listeners.action.colourUIButtons.ColourOkayLogic;
import listeners.action.colourUIButtons.FillAllLogic;
import slide.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class defining what the UI of the colour selector menu looks like
 */
public class ColourSelectorUI {
    JFrame frame = new JFrame();
    boolean fillColour;
    boolean laserColour;
    Slide currentSlide;

    /**
     * Constructor method for the UI
     * @param currentSlide the slide the user is on
     * @param fillColour whether the user is changing the fill colour of the slide
     */
    public ColourSelectorUI(Slide currentSlide, boolean fillColour, boolean laserColour) {

        this.currentSlide = currentSlide;
        this.fillColour = fillColour;
        this.laserColour = laserColour;

        //Outlines layout managers
        BorderLayout colourSelectorLayout = new BorderLayout();
        FlowLayout buttonLayout = new FlowLayout();

        //Creates JPanels
        JPanel window = new JPanel();
        window.setLayout(colourSelectorLayout);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(buttonLayout);

        //Creates title text
        JLabel title = new JLabel("Colour Selector");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        window.add(title, BorderLayout.NORTH);

        //Creates a Colour chooser object
        JColorChooser colorChooser = new JColorChooser();
        window.add(colorChooser, BorderLayout.CENTER);

        //Cancel button
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ColourCancelLogic(frame));

        //Okay button
        JButton okButton = new JButton("Okay");
        buttonPanel.add(okButton);
        okButton.addActionListener(new ColourOkayLogic(colorChooser, okButton, currentSlide, frame, fillColour, laserColour));

        if (fillColour){
            JButton fillAll = new JButton("Fill all");
            buttonPanel.add(fillAll);
            fillAll.addActionListener(new FillAllLogic(currentSlide.getPresentation(), colorChooser, frame));
        }

        //Adds panel to the JFrame
        window.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(window);
        frame.setTitle("Colour Selection");
        frame.setSize(700, 500);  // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);  // Make the frame visible
    }
}
