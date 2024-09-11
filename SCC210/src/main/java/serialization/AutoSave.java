package serialization;


import presentation.Presentation;

/**
 * Thread for auto saving the presentation
 */
public class AutoSave extends Thread{

    private Presentation presentation;
    private Saving saving;

    /**
     * Constructor method for the auto save thread.
     * @param presentation the presentation to be saved.
     */
    public AutoSave(Presentation presentation){
        this.presentation = presentation;
        saving = new Saving(presentation);
    }

    /**
     * Run method for the thread.
     * Calls the save method every 2 seconds
     */
    @Override
    public void run(){
        try {
            while (presentation.getAuto()) {
                saving.saveExisting();
                Thread.sleep(2000);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}