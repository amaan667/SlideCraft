package slide.threads;

import slide.insertables.CodeText;

/**
 * Thread for inserting an indent in a code text box
 */
public class IndentInserter extends Thread{

    private CodeText codeText;
    private int num;

    /**
     * Constructor method
     * @param codeText the code text where the indents are being inserted
     * @param num the number of indents to insert
     */
    public IndentInserter(CodeText codeText, int num){
        this.codeText = codeText;
        this.num = num;
    }

    /**
     * Run method for the thread
     */
    @Override
    public void run(){
        // sleeps to let the caret move to the next line
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Inserts the correct number of indents
        for (int i = 0; i < num; i++)
            codeText.insertIndent();
    }
}
