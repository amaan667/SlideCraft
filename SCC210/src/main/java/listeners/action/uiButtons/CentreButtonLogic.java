package listeners.action.uiButtons;

import slide.Slide;
import slide.insertables.Text;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentreButtonLogic extends TextFormatButton implements ActionListener {

    private int align;

    public CentreButtonLogic(Slide slide, int align){
        super(slide);
        this.align = align;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentSlide.getCurrentInsertable() instanceof Text){
            text = (Text) currentSlide.getCurrentInsertable();
        }

        if (text != null){
            StyledDocument doc = text.getStyledDocument();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            StyleConstants.setAlignment(simpleAttributeSet, align);

            doc.setParagraphAttributes(0, doc.getLength(), simpleAttributeSet, false);

            if (align == StyleConstants.ALIGN_RIGHT){
                text.setAlign("right");
            }
            else if (align == StyleConstants.ALIGN_LEFT){
                text.setAlign("left");
            }
            else{
                text.setAlign("centre");
            }
        }
    }
}
