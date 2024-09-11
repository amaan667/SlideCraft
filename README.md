# 210 Presentation Software application project

# Introduction 
Our project aimed to create a presentation software application tailored for computer scientists, filling a gap in the market and enhancing the learning experience of students. Inspired by Microsoft PowerPoint, we developed a user-friendly UI in Java 8, leveraging Java Swing libraries. We developed the UI such that the application caters to users of all capabilities, ensuring our project delivers a positive user experience to a wider user base. 

# Functionality
* Addition and removal of slides to form a powerpoint presentation. The slides are can be previewed at the bottom of the UI. The previews can be reordered which in turn edits the order of the slides in the presentation.
* Slide backgrounds can be changed to a range of colours
* Addition of text boxes, images, code text editor boxes and boolean diagrams. Each of these can be dynamically resized and moved on the slides
* Code text boxes allow will update the colour and allignment of text entered if key words are picked up (e.g for and while) to mimic code. These can be used whilst in the presentation to allow for the demonstration of code during presentations.
* Text has the following features: bold, italic, underlined, size, font, allignment and color.
* User can save and load presentation to files
* The autosave option results in the presentation periodically saving every 2 seconds.
* Present mode presents the slides on the full size of the window. Pressing the arrow key allows for navigation through the presentation. User can either present from the start or from the current slide.
* Speaker notes can be created for each slide. These can be viewed on a separate window to the presentation, and are navigated/ linked the slides for effetive navigation through the presentation.
* Light and dark modes. This can be toggled using the button on the top menu bar
* Booelan diagram maker. Diagrams can be added using the dropw down menu.
* Printing and exporting to pdf of slides

# Dependencies and libraries
This project utilsed a Maven build to implement gson and json dependencies. Other than this, the project made use of several standard Java libraries.

To implement these dependencies, ensure the following is in the pom.xml file:

    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20230618</version>
        </dependency>
    </dependencies>


# Usage
### How to run
1. Open the SCC210 folder in the terminal
2. run the following `mvn exec:java -Dexec.mainClass=Main`

3. If you have any issues with this try `mvn clean install exec:java -Dexec.mainClass=Main`
4. Please ensure you have maven installed.

### Help Menu
Once the program opens there is a button to access the help menu. This has detailed instructions on how to use the program.

### Getting started
1. Once the program is running you will be greeted with the title screen.
2. You can select from a blank presentation, a new presentation with the standard template which automaticaly puts text boxes on the screen for you or open a pre existing presentation.
3. Once you are in the main presentation editor screen you can add text boxes by either double clicking or using the menu buttons. Images can be added via copy and paste or the menu button. Objects on the slide can be dragged and can be resided by dragging on the bottom right corner.
4. Text formatting options can be found on the menu bar
5. New slides can be added using the button at the bottom of the screen which has a right click menu for more options or the button on the menu bar.
6. You can enter present mode using the present button and navigate using arrow keys and leave using esc.
7. Presentations can be saved using the save button on the menu.
8. Pre existing presentations can be loaded either from the title screen or using the open button on the menu.
9. Note only presentations made using this program can be opened.
10. Themes can be added under Edit-Themes.


### Key Binds
The following key binds can be used in the program
* CTRL + C to copy
* CTRL + V to paste
* CTRL + Z to undo text
* CTRL + SHIFT + Z to redo text
* CTRL + S to save
* CTRL + P to print
* CTRL + SHIFT + B to bold selected text
* CTRL + SHIFT + I to italic selected text
* CTRL + SHIFT + U to underline selected text
* CTRL + S (In present mode) to open speaker notes

#  Attributions
Icons were retrived from the follwoing links:
* Text box icon - <a href="https://www.flaticon.com/free-icons/text-box" title="text box icons">Text box icons created by Freepik - Flaticon</a>
* Save icon - <a href="https://www.flaticon.com/free-icons/save" title="save icons">Save icons created by Flat Icons - Flaticon</a>
* Open file icon - <a href="https://www.flaticon.com/free-icons/open" title="open icons">Open icons created by Freepik - Flaticon</a>
* Print icon - <a href="https://www.flaticon.com/free-icons/print" title="print icons">Print icons created by Freepik - Flaticon</a>
* Bullet point icon - <a href="https://www.flaticon.com/free-icons/list" title="list icons">List icons created by Chanut - Flaticon</a>
* Centre Text icon - <a href="https://www.flaticon.com/free-icons/center-alignment" title="center alignment icons">Center alignment icons created by ahmedagrma - Flaticon</a>
* Left Align Text icon - <a href="https://www.flaticon.com/free-icons/alignment" title="alignment icons">Alignment icons created by Royyan Wijaya - Flaticon</a>
* Right Align Text icon - <a href="https://www.flaticon.com/free-icons/alignment" title="alignment icons">Alignment icons created by Royyan Wijaya - Flaticon</a>
* Code Text icon - <a href="https://www.flaticon.com/free-icons/coding" title="coding icons">Coding icons created by juicy_fish - Flaticon</a>
* Insert image icon - <a href="https://www.flaticon.com/free-icons/insert" title="insert icons">Insert icons created by Smashicons - Flaticon</a>
* Speaker note icon - <a href="https://www.flaticon.com/free-icons/speech-bubble" title="speech bubble icons">Speech bubble icons created by Smashicons - Flaticon</a>
* Add Slide icon - <a href="https://www.flaticon.com/free-icons/add-button" title="add button icons">Add button icons created by Pixel perfect - Flaticon</a>
* Add slide plus icon - <a href="https://www.flaticon.com/free-icons/add" title="add icons">Add icons created by Pixel perfect - Flaticon</a>
* Remove slide icon - <a href="https://www.flaticon.com/free-icons/subtraction" title="subtraction icons">Subtraction icons created by Fathema Khanom - Flaticon</a>
* Colour picker icon - <a href="https://www.flaticon.com/free-icons/color-picker" title="color picker icons">Color picker icons created by iconixar - Flaticon</a>
* Fill button icon - <a href="https://www.flaticon.com/free-icons/paint-bucket" title="paint bucket icons">Paint bucket icons created by justicon - Flaticon</a>
* Light mode icon - <a href="https://www.flaticon.com/free-icons/cloudy" title="cloudy icons">Cloudy icons created by kosonicon - Flaticon</a>
* Dark mode icon - <a href="https://www.flaticon.com/free-icons/night" title="night icons">Night icons created by Freepik - Flaticon</a>
Other buttons made with - https://www.clickminded.com/button-generator/

JSON and GSON external libraires are used in this program.

# Authors 
* George Booth
* Emma Brown
* Amaan Tanveer
* Nigel Goes


