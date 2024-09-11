package slide.threads;

import slide.insertables.Text;

/**
 * Class for a thread which will insert bullet points
 */
public class BulletPointInserter extends Thread{
    Text text;

    /**
     * Constructor method
     * @param text the text box which will have the bullet points
     */
    public BulletPointInserter(Text text){
        this.text = text;
    }

    /**
     * Run method for the thread.
     * Used for inserting the bullet points
     */
    @Override
    public void run(){
        try {
            // 100ms time delay in order to allow the caret to move to the next line before inserting the bullet point
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Inserts the bullet point
        text.insertBulletPoint();
    }
}
