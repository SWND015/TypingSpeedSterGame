package Game;

import java.util.Random;

public class WordGenerator extends TypingComponent {

	private static final String[] easyWords = {
		    "cat", "dog", "ball", "run", "fun", "hat", "sun", "book", "distribution", "pen", "fish",
		    "tree", "milk", "car", "sky", "home", "determination", "cloud", "driver",
		    "supply", "we", "pillow", "travel", "food", "summer", "winter", "charge", "finish", "extraordinary",
		    "system", "button", "castle", "market", "camera", "honest", "fridge", "rocket", "walk", "play", "jump", 
		    "cold", "is", "be", "and",
		    "apple", "banana", "grape", "orange", "bread", "water", "egg", "tea", "rice", "soup",
		    "bed", "chair", "table", "door", "window", "phone", "cup", "bag", "shoe", "shirt",
		    "man", "woman", "boy", "girl", "baby", "friend", "family", "mom", "dad", "brother", "sister",
		    "red", "blue", "green", "black", "white", "big", "small", "happy", "sad", "fast", "slow"
		};

		private static final String[] mediumWords = {
		    "window", "garden", "animal", "doctor", "bottle", "school", "yellow", "basket", "cookie", "purple",
		    "laptop", "pencil", "river",  "mathematical", "family",  "authenticity", "announcement", "person", "happy", "summer", "winter",  "charge",  "finish", "extraordinary", "system", "button", "castle", "market", "camera",
		    "honest", "fridge", "rocket", "flight", "coffee", "letter", "forest", "silver", "banana", "puzzle"
		};

		private static final String[] hardWords = {
		    "encyclopedia", "architecture", "walk", "play", "jump", "cold", "is",  "configuration", "revolutionary", "psychologist", "circumference", "transparency",
		    "infrastructure", "communication", "responsibility", "jurisdiction", "hypothetical", "transformation",
		    "relationship", "fridge", "rocket", "flight", "coffee",
		    "characteristics","representation", "manufacturing", "sophistication",
		    "interpretation", "restructuring", "discrimination", "transportation", "inconsistency", "procrastination"
		};



    public String generateSentence(int difficultyLevel, int words) {

         int wordCount;
         String[] selectedArray;

         switch (difficultyLevel) {
             case 1:
                 wordCount = words;
                 selectedArray = easyWords;
                 break;
             case 2:
                 wordCount = words;
                 selectedArray = mediumWords;
                 break;
             case 3:
                 wordCount = words;
                 selectedArray = hardWords;
                 break;
             default:
                 wordCount = 20;
                 selectedArray = easyWords;
                 break;
         }

         
        StringBuilder sentenceBuilder = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < wordCount; i++) {
// to generate random words from the array => 0 - (length of array -1)
        	 	String word = selectedArray[rand.nextInt(selectedArray.length)];

            //using append to add words to the sentence
            sentenceBuilder.append(word);

            if (i != wordCount - 1) {
                //if it is not the last word, add a space
                sentenceBuilder.append(" ");
            } else {
                //if it is the last word, add a fullstop
                sentenceBuilder.append(".");
            }
        }
        //convert the sentenceBuilder to a string
        String sentence = sentenceBuilder.toString();
        // set sentence as target text for user to type
        setTargetText(sentence); 
        return sentence;   
    }
}

