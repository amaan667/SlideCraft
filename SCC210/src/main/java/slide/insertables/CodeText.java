package slide.insertables;

import com.google.gson.annotations.Expose;
import slide.Slide;
import slide.threads.CodeHighlighter;

import javax.swing.text.*;
import java.awt.*;
import java.lang.constant.DynamicCallSiteDesc;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class for code text box.
 * These are the same as a normal text box except for syntax highlighting
 */
public class CodeText extends Text {

    @Expose
    private int indent;
    @Expose
    private int numOpen;
    @Expose
    private int numClose;
    private boolean clicked;

    /**
     * Constructor method for creating a text box
     *
     * @param slide the slide the text box will belong too.
     */
    public CodeText(Slide slide) {
        super(slide);
        //By default no indents
        indent = 0;
        clicked = true;

        //No open or closed {, }
        numClose = 0;
        numOpen = 0;

        //Starts the thread checking for keywords
        CodeHighlighter codeHighlighter = new CodeHighlighter(this, slide.getPresentation());
        codeHighlighter.start();

        //Sets a black background and white caret.
        setBackground(Color.black);
        setOpaque(true);
        setCaretColor(Color.white);

        super.codeText = true;
    }

    /**
     * Method for searching for a given word and returning an arrayList of indexes and lengths
     * @param search the word being searched for
     * @param size the length of this word
     * @return the array list of integer arrays. [0] is the index, [1] is the length
     */
    private ArrayList<Integer[]> searchForWord(String search, int size) {
        //Creates new array list and gets style doc
        ArrayList<Integer[]> wordIndex = new ArrayList<>();
        StyledDocument doc = getStyledDocument();

        int find = 0;
        int i = 0;

        //Loops until no more can be found
        while (find != -1) {
            int next;
            if (i > 0)
                next = find + 1;
            else
                next = 0;

            int index = 0;
            try {
                index = doc.getText(0, doc.getLength()).indexOf(search, next);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            if (index != -1) {
                Integer[] indexArray = new Integer[2];
                indexArray[0] = index;
                indexArray[1] = size;
                wordIndex.add(indexArray);
            }
            find = index;
            i++;
        }

        return wordIndex;
    }

    /**
     * Method for finding the highest index within all the array lists for code highlighting
     * @param codeArray the array list with all other code array lists in
     * @return the highest index array
     */
    private int[] findHighestIndex(ArrayList<ArrayList<ArrayList<Integer[]>>> codeArray){
        int highest = -1;
        int len = -1;

        //Loops through every element in the array list
        for (int i = 0; i < codeArray.size(); i++){
            for (int j = 0; j < codeArray.get(i).size(); j++){
                for (int k = 0; k < codeArray.get(i).get(j).size(); k++){
                    //Checks if it is higher than the current highest
                    if (codeArray.get(i).get(j).get(k)[0] > highest) {
                        highest = codeArray.get(i).get(j).get(k)[0];
                        len = codeArray.get(i).get(j).get(k)[1];
                    }
                }
            }
        }

        int[] array = new int[2];
        array[0] = highest;
        array[1] = len;

        return array;
    }

    /**
     * Takes the overarching array list and turns it into a single array list of integer arrays.
     * @param codeArray the array list of array lists of array lists.
     * @return the new single array list
     */
    private ArrayList<Integer[]> combineLists(ArrayList<ArrayList<ArrayList<Integer[]>>> codeArray){
        ArrayList<Integer[]> combinedList = new ArrayList<>();

        for (int i = 0; i < codeArray.size(); i++){
            for (int j = 0; j < codeArray.get(i).size(); j++){
                for (int k = 0; k < codeArray.get(i).get(j).size(); k++){
                    Integer[] array = new Integer[2];
                    array[0] = codeArray.get(i).get(j).get(k)[0];
                    array[1] = codeArray.get(i).get(j).get(k)[1];
                    combinedList.add(array);

                }
            }
        }

        return combinedList;
    }

    /**
     * Turns none keywords into white text
     * @param combinedArray the single array list with all indexes
     */
    private void noneKeyword(ArrayList<Integer[]> combinedArray){
        int start = 0;
        int len = 0;
        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(simpleAttributeSet, Color.white);

        for (int i = 0; i < combinedArray.size(); i++){
            len = (combinedArray.get(i)[0]-1) - start;
            if (len < 0){
                len = 0;
            }

            doc.setCharacterAttributes(start,len, simpleAttributeSet, false);

            start = combinedArray.get(i)[0] + combinedArray.get(i)[1];
        }
    }

    /**
     * Method for taking a colour array list of array lists and highlighting the text
     * @param arrayList the colour array list
     * @param colour the colour to change the text
     */
    private void highlightText(ArrayList<ArrayList<Integer[]>> arrayList, Color colour){
        StyledDocument doc = getStyledDocument();
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(simpleAttributeSet, colour);
        for (int i = 0; i < arrayList.size(); i++){
            for (int j = 0; j < arrayList.get(i).size(); j++){
                doc.setCharacterAttributes(arrayList.get(i).get(j)[0], arrayList.get(i).get(j)[1], simpleAttributeSet, false);
            }
        }
    }


    /**
     * Method for searching and highlighting keywords in the text box
     */
    public void syntaxHighlighting(){
        StyledDocument doc = getStyledDocument();

        ArrayList<ArrayList<ArrayList<Integer[]>>> codeArray = new ArrayList<>();

        ArrayList<ArrayList<Integer[]>> magentaArray = new ArrayList<>();
        ArrayList<ArrayList<Integer[]>> orangeArray = new ArrayList<>();
        ArrayList<ArrayList<Integer[]>> cyanArray = new ArrayList<>();
        codeArray.add(cyanArray);
        codeArray.add(magentaArray);
        codeArray.add(orangeArray);

        magentaArray.add(searchForWord("for ", 3));
        magentaArray.add(searchForWord("while ", 5));
        magentaArray.add(searchForWord("break ", 5));
        orangeArray.add(searchForWord("if ", 2));
        orangeArray.add(searchForWord("else", 4));
        orangeArray.add(searchForWord("True ", 4));
        orangeArray.add(searchForWord("False ", 5));
        cyanArray.add(searchForWord("int ", 3));
        cyanArray.add(searchForWord("string ", 6));
        cyanArray.add(searchForWord("float ", 5));
        cyanArray.add(searchForWord("double ", 6));
        cyanArray.add(searchForWord("boolean ", 7));
        cyanArray.add(searchForWord("char ", 4));




        ArrayList<Integer[]> combinedList = combineLists(codeArray);


        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();

        highlightText(magentaArray, Color.MAGENTA);
        highlightText(orangeArray, Color.ORANGE);
        highlightText(cyanArray, Color.CYAN);

        StyleConstants.setForeground(simpleAttributeSet, Color.white);
        int[] highestIndex = findHighestIndex(codeArray);
        int diff = doc.getLength() - highestIndex[0];
        doc.setCharacterAttributes(highestIndex[0]+highestIndex[1], diff+1, simpleAttributeSet, false);

        combinedList.sort(Comparator.comparingInt(arr -> arr[0]));
        noneKeyword(combinedList);


        try {

            if (doc.getLength() > 0) {

                if (Objects.equals(doc.getText(doc.getLength() - 1, 1), "{")) {
                    if (numOpen < countChar('{'))
                        indent++;
                }

                if (Objects.equals(doc.getText(doc.getLength() - 1, 1), "}")) {
                    if (numClose < countChar('}'))
                        indent--;
                }
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        numOpen = countChar('{');
        numClose = countChar('}');

    }

    /**
     * Method for inserting an indent in the text box
     */
    public void insertIndent(){
        StyledDocument doc = getStyledDocument();
        int caretPosition = getCaretPosition(); // Get the current caret position
        try {
            //Inserts the bullet point.
            doc.insertString(caretPosition, "  ", null);
            setCaretPosition(caretPosition+2); //Moves the caret on 2.
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method for counting the number of times a character appears in a string
     * @param target the character being searched for
     * @return
     */
    private int countChar(char target){
        int count = 0;
        String string;
        try {
            string = getStyledDocument().getText(0, getDocument().getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        for (char c : string.toCharArray()) {
            if (c == target) {
                count++;
            }
        }

        return count;
    }

    /**
     * Getter method for the indent value
     * @return the indent number
     */
    public int getIndent() {
        return indent;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setIndent(int indent){
        this.indent = indent;
    }

    public void setNumOpen(int numOpen){
        this.numOpen = numOpen;
    }

    public void setNumClose(int numClose){
        this.numClose = numClose;
    }
}
