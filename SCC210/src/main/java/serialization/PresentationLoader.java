package serialization;

import presentation.Presentation;

import javax.swing.*;
import java.io.File;

/**
 * Thread for loading a presentation.
 * Put in a separate thread to ensure that the slide size is correct before loading.
 */
public class PresentationLoader extends Thread{

    private File file;
    private String obj;
    private JFrame frame;
    private Presentation presentation;

    /**
     * Constructor method for the Presentation Loader thread
     * @param file
     * @param obj
     * @param frame
     * @param presentation
     */
    public PresentationLoader(File file, String obj, JFrame frame, Presentation presentation){
        this.file = file;
        this.obj = obj;
        this.frame = frame;
        this.presentation = presentation;
    }

    @Override
    public void run(){

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Loading loading = new Loading(presentation);
        loading.recreatePresentation(obj, file);
        frame.dispose();
    }
}
