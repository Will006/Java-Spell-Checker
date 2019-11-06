/**
 * MatrixGraph.java is the main matrix graph for the spell checking GUI. It consists of a 2 dimensional array
 * of wordVertex 's.
 * William Pickering, Xavier Finney, and Leo Beltran
 * Last Edit: 5/20/18
 */
import java.io.*;
import java.util.*;

public class MatrixGraph<E extends Comparable<E>> extends AbstractGraph implements Serializable
{
    //[currentWord_axix][nextWord_axix]
    public wordVertex[][] mainMatrix;
    final int MATRIX_START_SIZE=9731; //WILL BE 10000
    final double ACCURATE_WEIGHT_LIMIT=0.5;
    int currentNumberOfNodes=0;
    String fileOfMatrixData="src/matrixData.dat";

    /**
     * Constructor that instantiates the mainMatrix two-dimensional array and
     * creates a MatrixGraph object
     */
    public MatrixGraph()
    {
        mainMatrix= new wordVertex[MATRIX_START_SIZE][MATRIX_START_SIZE];
    }

    public boolean add(wordVertex vertextIn, int currentWord_axixIn, int nextWord_axixIn)
    {
        return false;
    }

    /**
     * Reads in data from given file and stores it into mainMatrix
     * @param fileNameIn name of file to read in
     * @return true if successfully read in file name, false if otherwise
     */
    public boolean readSavedMatrix(String fileNameIn)
    {
        try {
            FileInputStream fis = new FileInputStream(fileNameIn);
            ObjectInputStream ois = new ObjectInputStream(fis);
            mainMatrix= (wordVertex[][]) ois.readObject();

            fis.close();
            ois.close();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Outputs mainMatrix to fileOfMatrixData
     * @return true if successfully outputted to file, false if otherwise
     */
    public boolean saveMatrix()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileOfMatrixData);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mainMatrix);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * toFile updates the graphData.txt file that then can be imported into excel for data analysis.
     */
    public void toFile()
    {
        Writer compressFileWriter=null;
        String strOfUncompressFile=("src/graphData.txt");
        System.out.println(strOfUncompressFile);

        try {
            //TO WRITE THE UNCOMPRESS FILE
            compressFileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(strOfUncompressFile), "utf-8"));

            String rowStr="\t";
            for(int j=0; j<mainMatrix.length;j++)
            {
                if(mainMatrix[j][j]!=null)
                {
                    rowStr+=mainMatrix[j][j].value+"\t";
                }
            }
            System.out.println(rowStr);
            compressFileWriter.write(rowStr);
            rowStr="\n";
            for(int i=0; i<mainMatrix.length; i++)
            {
                if(mainMatrix[i][i]!=null)
                {
                    rowStr+="\n"+mainMatrix[i][i].value+"\t";
                    for(int j=0; j<mainMatrix.length;j++)
                    {
                        if(mainMatrix[j][j]!=null)
                        {
                            if(mainMatrix[i][j]==null)
                            {
                                rowStr+="0\t";
                            }
                            else
                            {
                                rowStr+=mainMatrix[i][j].weight+"\t";
                            }

                        }
                    }
                }

                System.out.println(i);
                compressFileWriter.write(rowStr);
                rowStr="";
            }

            compressFileWriter.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a current word and the word that follows it in the sentence, the add method will first check if either
     * of the words have been read in, if not, then a new vertex for the word and the combination will be added, else
     * the current combination node's weight will decrement
     * @param vertexValueIn The first string/word to add to the matrix
     * @param vertexNextValueIn The following string/word to add to the matrix
     * @return true if the words were successfully checked and added to the matrix
     */
    public boolean add(String vertexValueIn, String vertexNextValueIn)
    {
        wordVertex tempVertex;
        int currentWord_axis=0, nextWord_axis=0;

        //create a new vertex object of current word
        tempVertex=new wordVertex(vertexValueIn);
        //get the current word's hash code-same as the word vertex
        currentWord_axis=tempVertex.hashCode()%getLength();

        //if negative location, make positive
        if(currentWord_axis<0)
        {
            currentWord_axis+=getLength();
        }

        System.out.println("currentWord_axis: "+ currentWord_axis + " currentWord_axis hash:"+vertexValueIn.hashCode());
        System.out.println("start length:"+ MATRIX_START_SIZE + " other:"+mainMatrix.length);

        //Check if word has not been read in before
        if(mainMatrix[currentWord_axis][currentWord_axis]==null)
        {
            //increase the node count
            currentNumberOfNodes++;

            System.out.println("New word: " +vertexValueIn+" being added to graph");
            mainMatrix[currentWord_axis][currentWord_axis]=new wordVertex(vertexValueIn,1);
        }

        //Else there is a hash clash, loop rehashing until no hash clash exists
        /*while*/if(mainMatrix[currentWord_axis][currentWord_axis].value!=vertexValueIn)
        {
            System.out.println("Graph needs to be rehashed"+mainMatrix[currentWord_axis][currentWord_axis].value+" vs "+vertexValueIn);
            //resize array
            //mainGraph.rehash()
        }

        //get the next word's hash code    -same as the wordVertex's hash
        nextWord_axis=vertexNextValueIn.hashCode()%getLength();

        //if negative location, make positive
        if(nextWord_axis<0)
        {
            nextWord_axis+=getLength();
        }

        //Check if word has not been read in before
        if(mainMatrix[nextWord_axis][nextWord_axis]==null)
        {
            //increase the node count
            currentNumberOfNodes++;

            System.out.println("New word: " +vertexNextValueIn+" being added to graph in next word");
            mainMatrix[nextWord_axis][nextWord_axis]=new wordVertex(vertexNextValueIn,1);
        }

        //Else there is a hash clash, loop rehashing until no hash clash exists
        /*while*/if(mainMatrix[nextWord_axis][nextWord_axis].value!=vertexNextValueIn)
        {
            System.out.println("Graph needs to be rehashed"+mainMatrix[nextWord_axis][nextWord_axis].value+" vs "+nextWord_axis);
            //rehash array
            //mainGraph.rehash()
        }

        //Check to see if it is a new word combo
        if(mainMatrix[currentWord_axis][nextWord_axis]==null)
        {
            //increase the node count
            currentNumberOfNodes++;

            mainMatrix[currentWord_axis][nextWord_axis]=new wordVertex(vertexNextValueIn,.5);
        }
        else
        {
            mainMatrix[currentWord_axis][nextWord_axis].decrementWeight();
        }
        System.out.println("Updated weight"+mainMatrix[currentWord_axis][nextWord_axis]);
        System.out.println("Location["+currentWord_axis+"]["+nextWord_axis+"]");

        return true;
    }

    /**
     * Checks to see if the given wordVertex shares the same spot on the matrix with another word
     * @param vertexIn The wordVertex to check
     * @return true if a different word shares the same spot; otherwise, return false
     */
    private boolean checkMissMatchValue(wordVertex vertexIn)
    {
        //return true if a different word has the same spot
        return false;
    }

    /**
     * Retrieves the length of the mainMatrix
     * @return the length of the mainMatrix
     */
    public int getLength()
    {
        return mainMatrix.length;
    }

    /**
     * Returns an Iterator of the graph that starts a [0][0], goes through the top row, then decrements a row and
     * goes through every vertex in that row, and so on until the bottom row has gone through
     * @return Iterator of type wordVertex containing all vertices
     */
    public Iterator<wordVertex> getIterator()
    {
        LinkedList<wordVertex> linkedListOfMatrix = new LinkedList<wordVertex>();
        for(int i=0; i<getLength();i++)
        {
            for(int j=0; j<getLength();j++)
            {
                if(mainMatrix[i][j]==null)
                {
                    //May not want this
                    linkedListOfMatrix.add(new wordVertex(null));
                }
                else
                {
                    linkedListOfMatrix.add(mainMatrix[i][j]);
                }
            }
        }

        return linkedListOfMatrix.iterator();
    }

    /**
     * Roughly increases the size to 2*(current length)-1
     */
    public void rehash()
    {
        //int newGrpahSize=MATRIX_START_SIZE*2-1;
        //wordVertex[][] tempGraph = new wordVertex[newGrpahSize][newGrpahSize];
    }

    /**
     * Takes in a string of what is known as a "good sentence" and will update the edges in the graph
     * @param strIn The string to take in
     */
    public void addGoodSentence(String strIn)
    {
        //if there are any commas, make them their own word

        strIn=strIn.replace("  "," ");
        strIn=strIn.replace(","," ,");
        strIn=strIn.trim();

        System.out.println("Updated sentence: "+ strIn);
        String currentWord="", nextWord="";
        Scanner sentenceParse = new Scanner(strIn).useDelimiter("\\s");
        Stack<String> tempstackOfWords = new Stack<String>();
        Stack<String> stackOfWords = new Stack<String>();

    //CONVERT SCANNER TO A PRIORITY QUEUE
        while(sentenceParse.hasNext())
        {
            String tempstr = sentenceParse.next();
            System.out.println(tempstr+" Being added to queue");
            tempstackOfWords.push(tempstr);
        }
        while(!tempstackOfWords.empty())
        {
            stackOfWords.push(tempstackOfWords.pop());
        }
    //LOOP UNTIL THERE ARE NO MORE WORD LEFT
        while(stackOfWords.size()!=0)
        {
            currentWord=stackOfWords.pop();
            System.out.println("current word: "+ currentWord);

            //CHECK IF CURRENT WORD IS THE LAST WORD
            if(stackOfWords.size()==0)
            {
                nextWord=".";
            }
            else
            {
                nextWord=stackOfWords.peek();
            }

        //ADD THE VERTEX
            System.out.println("next word: " +nextWord);
            add(currentWord,nextWord);
        }
    }
    public void addGoodSentences(String strIN)
    {

    }

    public wordReplacementQueue findReplacementWord(wordVertex previousWordIn, wordVertex wordToReplaceIn)
    {
        return null;
    }

    /**
     * Checks if each word links to the next word, if not, finds a word that could replace the unknown word
     * @param strIn The string to check
     */
    public wordReplacementQueue checkSentence(String strIn)
    {
        //if there are any commas, make them their own word
        strIn=strIn.replace(","," ,");
        strIn=strIn.replace("  "," ");

        strIn=strIn.replace(".","");

        String currentWord="", nextWord="";
        int currentWord_axix, nextWord_axis;
        Scanner sentenceParse = new Scanner(strIn).useDelimiter("\\s");
        Stack<String> tempstackOfWords = new Stack<String>();
        Stack<String> stackOfWords = new Stack<String>();
        wordReplacementQueue topSuggestions=null;

        //CONVERT SCANNER TO A PRIORITY QUEUE
        while(sentenceParse.hasNext())
        {
            tempstackOfWords.push(sentenceParse.next());
        }
        while (!tempstackOfWords.isEmpty())
        {
            stackOfWords.push(tempstackOfWords.pop());
        }
        while(stackOfWords.size()>0)
        {
        //GET CURRENT WORD
            currentWord=stackOfWords.pop();
        //GET WORD AFTER CURRENT WORD
            if(stackOfWords.size()>0)
            {
                nextWord=stackOfWords.peek();
            }
            else
            {
                nextWord=".";
            }
        //get the current word's hash code    -same as the wordVertex's hash
            currentWord_axix=currentWord.hashCode()%getLength();
            //if negative location, make positive
            if(currentWord_axix<0)
            {
                currentWord_axix+=getLength();
            }

        //get the next word's hash code    -same as the wordVertex's hash
            nextWord_axis=nextWord.hashCode()%getLength();
            //if negative location, make positive
            if(nextWord_axis<0)
            {
                nextWord_axis+=getLength();
            }

        //CHECK IF NEXT WORD IS A WORD IN GRAPH
            if(mainMatrix[nextWord_axis][nextWord_axis]==null)
            {
                topSuggestions=new wordReplacementQueue(nextWord);
                Stack<wordVertex> stackOfEdges = new Stack<wordVertex>();
                for(int i=0; i<mainMatrix.length;i++)
                {
                    if(i!=nextWord_axis && mainMatrix[currentWord_axix][i]!=null)
                    {
                        stackOfEdges.push(mainMatrix[currentWord_axix][i]);
                    }
                }
                while(!stackOfEdges.empty())
                {
                    topSuggestions.addSuggestion(stackOfEdges.pop());
                }
                System.out.println("All Done Here");
                return topSuggestions;
            }

            ///*while*/if(!(mainMatrix[currentWord_axix][currentWord_axix].value.equals(currentWord)||mainMatrix[nextWord_axis][nextWord_axis].value.equals(nextWord)))
            {
            //    System.out.println("Graph needs to be rehashed"+mainMatrix[nextWord_axis][nextWord_axis].value+" vs "+nextWord_axis);
                //rehash array
                //mainGraph.rehash()
            }
            if(mainMatrix[currentWord_axix][nextWord_axis]==null || mainMatrix[currentWord_axix][nextWord_axis].weight<=ACCURATE_WEIGHT_LIMIT)
            {
                //preform the search for the shorest route
            }

        }
        return topSuggestions;
    }
}
