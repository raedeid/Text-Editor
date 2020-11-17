package textgen;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    // The list of words with their next words
    private List<ListNode> wordList;

    // The starting "word"
    private String starter;

    // The random number generator
    private Random rnGenerator;

    private boolean flag;

    public MarkovTextGeneratorLoL(Random generator) {
        wordList = new LinkedList<ListNode>();
        starter = "";
        rnGenerator = generator;
        flag = false;
    }


    /**
     * Train the generator by adding the sourceText
     */
    @Override
    public void train(String sourceText) {
        if(!sourceText.equals("")) {
            List<String> listOfwords = getTokens("[a-zA-Z]+", sourceText);
            String[] list = new String[listOfwords.size()];
            list = listOfwords.toArray(list);
            System.out.println(Arrays.toString(list));

            starter = list[0];
            String preWord = starter;


            for (int i = 1; i < list.length; i++) {

                if (this.contains(preWord)) {
                    ListNode test = new ListNode(preWord);
                    int index = this.indexOf(preWord);
                    wordList.get(index).addNextWord(list[i]);

                } else {

                    ListNode word = new ListNode(preWord);
                    word.addNextWord(list[i]);
                    wordList.add(word);
                }
                preWord = list[i];
            }

            ListNode lastMark = new ListNode(preWord);
            lastMark.addNextWord(starter);
            wordList.add(lastMark);
        }
        else {
            wordList= new LinkedList<>();
            flag = true;
        }
    }

    /**
     * Generate the number of words requested.
     */
    @Override
    public String generateText(int numWords) {
        if(!flag){
            String currWord = starter;
            StringBuilder output = new StringBuilder();
            output.append(currWord);
            for (int i = 0; i < numWords; i++) {
                int index = this.indexOf(currWord);
                ListNode randomPackage = wordList.get(index);
                String outputRandom =  randomPackage.getRandomNextWord(rnGenerator);
                output.append(" ");
                output.append(outputRandom);
                currWord = outputRandom;
            }
            return output.toString();
        }else{
            return "";
        }

    }


    // Can be helpful for debugging
    @Override
    public String toString() {
        String toReturn = "";
        for (ListNode n : wordList) {
            toReturn += n.toString();
        }
        return toReturn;
    }

    /**
     * Retrain the generator from scratch on the source text
     */
    @Override
    public void retrain(String sourceText) {
        wordList = new LinkedList<>();
        train(sourceText);

        // TODO: Implement this method.
    }

    protected List<String> getTokens(String pattern, String text) {
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }
    // TODO: Add any private helper methods you need here.

    private boolean contains(String text) {
        for (int i = 0; i < wordList.size(); i++) {
            ListNode test = wordList.get(i);
            if (test.getWord().equals(text)) {
                return true;
            }
        }
        return false;
    }

    private int indexOf(String text) {
        for (int i = 0; i < wordList.size(); i++) {
            ListNode test = wordList.get(i);
            if (test.getWord().equals(text)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is a minimal set of tests.  Note that it can be difficult
     * to test methods/classes with randomized behavior.
     *
     * @param args
     */
    public static void main(String[] args) {
        // feed the generator a fixed random value for repeatable behavior
        MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
//        String textString = "hi there hi leo";
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        gen.train(textString);
		System.out.println(textString);

        System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
    }

}

/**
 * Links a word to the next words in the list
 * You should use this class in your implementation.
 */
class ListNode {
    // The word that is linking to the next words
    private String word;

    // The next words that could follow it
    private List<String> nextWords;

    ListNode(String word) {
        this.word = word;
        nextWords = new LinkedList<String>();
    }

    public String getWord() {
        return word;
    }

    public void addNextWord(String nextWord) {
        nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        // TODO: Implement this method

        // The random number generator should be passed from
        // the MarkovTextGeneratorLoL class
        int randomNumber = generator.nextInt(nextWords.size());
        return nextWords.get(randomNumber);
    }

    public String toString() {
        String toReturn = word + ": ";
        for (String s : nextWords) {
            toReturn += s + "->";
        }
        toReturn += "\n";
        return toReturn;
    }

}


