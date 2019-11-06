import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GUI_Interface extends JFrame implements ActionListener
{
    //Constants for our GUI Window
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int NUMBER_OF_CHAR = 30;

    static MatrixGraph<wordVertex> mainGraph;




    private JPanel mainPanel;
    private JButton checkButton;
    private JButton clearButton;
    private JTextPane suggestionsTextPane;
    private JEditorPane editorPane1;
    private final String MAIN_SUGGESTION_PROMPT="Suggestions:";
    private String suggestionsText=MAIN_SUGGESTION_PROMPT;

    public GUI_Interface()
    {

        super("Spell Checker");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        mainPanel.add(panel2, BorderLayout.SOUTH);
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        panel2.add(checkButton, BorderLayout.WEST);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        panel2.add(clearButton, BorderLayout.EAST);
        final JSplitPane splitPane1 = new JSplitPane();
        mainPanel.add(splitPane1, BorderLayout.CENTER);
        suggestionsTextPane = new JTextPane();
        suggestionsTextPane.setEditable(false);
        suggestionsTextPane.setEnabled(true);
        suggestionsTextPane.setText(suggestionsText);
        splitPane1.setRightComponent(suggestionsTextPane);
        editorPane1 = new JEditorPane();
        splitPane1.setLeftComponent(editorPane1);

        add(mainPanel);


        bootUp();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand( );

        if (actionCommand.equals("Check"))
        {
            suggestionsText=MAIN_SUGGESTION_PROMPT;
            //name.setText(name.getText());
            System.out.println("Check clicked");
            Scanner sentanceParce= new Scanner(editorPane1.getText()).useDelimiter("\\.");

            while(sentanceParce.hasNext())
            {
                //Check sentence for errors
                String strTemp = sentanceParce.next();
                System.out.println("Current Sentence: "+ strTemp);
                wordReplacementQueue toReplacements = mainGraph.checkSentence(strTemp);
                //if there are errors
                if (toReplacements != null) {

                    suggestionsText += "\n\n\tUnknown word: " + toReplacements.wordToReplace;
                    System.out.println("Unknown word: " + toReplacements.wordToReplace);
                    //String strToReturn = new String(name.getText().replace(toReplacements.wordToReplace,toReplacements.mainQueue.poll().suggestion.value));
                    //name.setText(strToReturn);
                    //System.out.println("suggestion 1:"+toReplacements.mainQueue.poll().suggestion);

                    for (int i = 0; i < 5 && (!toReplacements.mainQueue.isEmpty()); i++) {
                        System.out.println("suggestion :" + toReplacements.mainQueue.peek().suggestion);
                        suggestionsText += "\n\n\t\t Suggestion " + i + " :" + toReplacements.mainQueue.poll().suggestion.value;
                    }
                }
            }

            suggestionsTextPane.setText(suggestionsText);

        }
        else if (actionCommand.equals("Clear"))
        {
            editorPane1.setText("");
        }
        else
        {
            editorPane1.setText("Unexpected error.");
        }
    }


    public void bootUp()
    {




        mainGraph = new MatrixGraph<wordVertex>();
        Scanner fileScanner = null;
        FileReader fileReader=null;
        System.out.println("Enter 1 to create a new graph or 2 to read from file");

        try
        {
            fileReader = new FileReader(MainDriver.TXT_FILE_OF_SENTENCES);
            fileScanner = new Scanner(fileReader).useDelimiter("\\.|!|\\?");
            System.out.println("File " + MainDriver.TXT_FILE_OF_SENTENCES + " found");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File " + MainDriver.TXT_FILE_OF_SENTENCES + " not found");
            System.exit(1);
        }

        int i=0;
        while(fileScanner.hasNext())
        {
            i++;
            String tempStrIn=fileScanner.next();
            System.out.println("Sentence "+ i + ": "+tempStrIn);
            mainGraph.addGoodSentence(tempStrIn.toLowerCase());
        }
        System.out.println("about to write");
        mainGraph.toFile();
        mainGraph.saveMatrix();

        //System.out.println("About to read");
        //mainGraph.readSavedMatrix(mainGraph.fileOfMatrixData);
        System.out.println("done");


    }
}
