package slide.threads;

import presentation.Presentation;
import slide.insertables.CodeText;

import java.awt.datatransfer.Transferable;
import java.util.PropertyResourceBundle;

/**
 * Thread for updating a code text box
 */
public class CodeHighlighter extends Thread{

    private CodeText codeText;
    private Presentation presentation;

    /**
     * Constructor method
     * @param codeText the code text box being updated
     */
    public CodeHighlighter(CodeText codeText, Presentation presentation){
        this.codeText = codeText;
        this.presentation = presentation;
    }

    /**
     * Calls the snytaxHighlighting method to update the text box
     */
    @Override
    public void run(){
        while (presentation.getUI().getCurrentSlide().getCurrentInsertable() == codeText){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            codeText.syntaxHighlighting();
        }
    }

}
