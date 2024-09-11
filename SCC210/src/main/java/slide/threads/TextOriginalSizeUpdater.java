package slide.threads;

import slide.insertables.Text;

/**
 * Thread class used to update the original size of letters in a text box
 * in real time.
 */
public class TextOriginalSizeUpdater extends Thread{

    private Text text;

    /**
     * Constructor method
     * @param text the text box being updated
     */
    public TextOriginalSizeUpdater(Text text){
        this.text = text;
    }

    /**
     * Run method when the thread starts
     * Calls the update sizes function
     */
    @Override
    public void run(){
        //Short time delay to allow the new letter to be typed
        /*try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        text.updateSizes();
    }
}
