package listeners.key;

import slide.*;
import slide.insertables.CodeText;
import slide.insertables.Text;
import slide.threads.BulletPointInserter;
import slide.threads.IndentInserter;
import slide.threads.TextOriginalSizeUpdater;

import java.awt.event.*;

/**
 * KeyListener class for the text boxes
 * Mainly used for the bullet point lists detecting when a new line is enterd.
 */
public class TextKeyListener extends KeyAdapter
{
    private Text text;
    private Slide slide;

    /**
     * Constructor method
     * @param text the text box
     * @param slide the slide it is on
     */
    public TextKeyListener(Text text, Slide slide)
    {
        this.text = text;
        this.slide = slide;
    }


    /**
     * Method for detecting the key press (Used for enter key)
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //Checks if enter pressed
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            //Checks if the list boolean is true
            if (text.getList()){
                //Checks if the current text is the text
                if (slide.getCurrentInsertable() == text){
                    //Creates a new thread for adding a bullet point
                    BulletPointInserter bulletPointInserter = new BulletPointInserter(text);
                    bulletPointInserter.start();
                }
            }


            if (text instanceof CodeText){
                CodeText codeText = (CodeText) text;

                IndentInserter indentInserter = new IndentInserter(codeText, codeText.getIndent());
                indentInserter.start();

            }

        }
        else{
            TextOriginalSizeUpdater textOriginalSizeUpdater = new TextOriginalSizeUpdater(text);
            textOriginalSizeUpdater.start();
        }
    }
}
