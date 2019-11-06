import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;

public class wordReplacementQueue
{

    private final int MAX_NUMBER_OF_SUGGESTIONS=5;
    PriorityQueue<wordReplacement> mainQueue= new PriorityQueue<wordReplacement>();
    String wordToReplace;

    /**
     * Constructor that takes in a string and changes the wordToReplace
     * instance variable to said string
     * @param wordToReplaceIn The string to take in
     */
    public wordReplacementQueue(String wordToReplaceIn)
    {
        wordToReplace=wordToReplaceIn;
    }

    /**
     * Takes in a wordVertex as a suggestion to replace with the misspelled word,
     * calculates the number of errors (differences) between the suggested word
     * and the misspelled word, then adds the suggestion to the mainQueue
     * @param suggestion The wordVertex to compare to the misspelled word
     * @return false after adding the suggested word to the mainQueue
     */
    public boolean addSuggestion(wordVertex suggestion)
    {
        int errors = 0;

        System.out.println("misspelled word: "+wordToReplace);
        System.out.println("Suggestion word: "+suggestion);
        int start=0;

        //Calculating the amount of errors for each letter in our Suggested word and
        //the misspelled word.
        for(int end=0; end<=wordToReplace.length()&& end<=suggestion.value.length(); end++)
        {

            System.out.println("testing section (start=" + start+" end="+end+"): "+wordToReplace.substring(start,end));
            if(!suggestion.value.substring(start,suggestion.value.length()).contains(wordToReplace.substring(start,end)) && start<suggestion.value.length())
            {
                System.out.println("Increment errors");
                errors++;
                start=end;
            }
        }

        //Calculates the amount of errors according to the length of our Suggested word
        //and our Misspelled word
        if(suggestion.value.length() > wordToReplace.length())
        {
            errors += suggestion.value.length() - wordToReplace.length();
        }
        else
        {
            errors += wordToReplace.length() - suggestion.value.length();
        }

        System.out.println("Total error: "+errors + suggestion);
        wordVertex tempWord=null;
        int tempError=0;

        mainQueue.offer(new wordReplacement(suggestion, errors));

        return false;
    }

    /**
     * Retrieves the five best suggestions with which to replace the misspelled word
     * @return a wordVertex array of size five consisting of the five best word
     * suggestions with which to replace the misspelled word
     */
    public wordVertex[] getTopFive()
    {
        wordVertex[] arrayOfTopFive = new wordVertex[MAX_NUMBER_OF_SUGGESTIONS];
        int index=0;
        while(mainQueue.size()!=0)
        {
            arrayOfTopFive[index]=mainQueue.poll().suggestion;
        }
        for(int i=0; i<5;i++)
        {
            System.out.println(mainQueue.poll().error);
        }
        return arrayOfTopFive;
    }

    /**
     * wordReplacement inner class used to store the error count between a wordVertex and the given misspelled word
     * William Pickering, Xavier Finney, and Leo Beltran
     * Last Edit: 5/20/18
     */
    class wordReplacement implements Comparable
    {
        wordVertex suggestion;
        int error;

        /**
         * Constructor that takes in a wordVertex as a suggestion for the
         * user and the number of errors in comparison to the misspelled word
         * @param suggestionIn the suggested word to replace with the
         *                     misspelled word
         * @param errorIn the number of errors/differences between the
         *                misspelled word and the suggested word
         */
        public wordReplacement(wordVertex suggestionIn, int errorIn)
        {
            suggestion=suggestionIn;
            if(errorIn >= 0) {
                error = errorIn;
            }
        }

        /**
         * Compares the number of errors between two wordReplacement objects
         * and returns the result if they are not equal to each other; otherwise, it
         * compares both objects' wordVertex objects and returns that result
         * @param replacementIn The object to compare to the wordReplacement object
         * @return -1 if the wordReplacement object's number of errors is less than
         *         the errors in the given object and 1 if it is greater; otherwise,
         *         return the result of comparing the two objects' wordVertex objects
         */
        public int compareTo(Object replacementIn)
        {
            if(error<((wordReplacement)replacementIn).error)
            {
                return -1;
            }
            else if(error==((wordReplacement)replacementIn).error)
            {
                return suggestion.compareTo(((wordReplacement)replacementIn).suggestion);
            }
            else
            {
                return 1;
            }
        }
    }
}
