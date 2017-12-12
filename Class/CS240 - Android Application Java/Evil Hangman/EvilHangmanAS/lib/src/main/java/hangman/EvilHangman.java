package hangman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Seong-EunCho on 1/26/17.
 */

public class EvilHangman implements IEvilHangmanGame {
    private Set<String> dictionarySet = new HashSet<String>();
    private Set<String> currentSet = new HashSet<String>();
    private boolean end = false;
    private boolean correctGuess;
    private Pattern currentWord = new Pattern("");
    private ArrayList<String> guessedLetters = new ArrayList<>();
    int numPattern;

    @Override
    public void startGame(File dictionary, int wordLength) {
        try {
            InputStream is = new FileInputStream(dictionary);
            BufferedInputStream bis = new BufferedInputStream(is);
            Scanner s = new Scanner(bis);
            for (int i = 0; i < wordLength; i++){
                currentWord.setPattern(currentWord.getPattern().append("-"));
            }

            while(s.hasNext()){
                dictionarySet.add(s.next());
            }
            for (String str : dictionarySet){
                if (str.length() == wordLength){
                    currentSet.add(str);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        Character.toLowerCase(guess);
        boolean guessMade = false;
        for (int i = 0; i < guessedLetters.size(); i++){
            if (guess == guessedLetters.get(i).charAt(0)){
                guessMade = true;
            }
        }
        if (guessMade == true){
            throw new GuessAlreadyMadeException();
        } else {
            Map<String, HashSet<String>> partition = new HashMap<>();
            guessedLetters.add(Character.toString(guess));
            //Partition the current set of words according to each pattern
            for (String str : currentSet) {
                Pattern currentPattern = new Pattern(currentWord.getPattern().toString());
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == guess) {
                        currentPattern.setPattern(currentPattern.getPattern().deleteCharAt(i));
                        currentPattern.setPattern(currentPattern.getPattern().insert(i, guess));
                        currentPattern.increment();
                        currentPattern.addIndex(i);
                    }
                }
                if (partition.containsKey(currentPattern.getPattern().toString())) {
                    partition.get(currentPattern.getPattern().toString()).add(str);
                } else {
                    HashSet<String> temp = new HashSet<>();
                    temp.add(str);
                    partition.put(currentPattern.getPattern().toString(), temp);
                }

            }

            //Determine the largest size among the partitioned sets
            int biggest = 0;
            ArrayList<Pattern> biggestPatterns = new ArrayList<>();
            for (String t : partition.keySet()) {
                if (partition.get(t).size() > biggest) {
                    biggest = partition.get(t).size();
                }
            }

            //Determine how many sets have equivalent size
            int countBiggest = 0;
            for (String t : partition.keySet()) {
                if (partition.get(t).size() == biggest) {
                    Pattern p = new Pattern(t);
                    biggestPatterns.add(p);
                    countBiggest++;
                }
            }

            for (Pattern p : biggestPatterns) {
                for (int i = 0; i < p.getPattern().toString().length(); i++) {
                    if (p.getPattern().toString().charAt(i) == guess) {
                        p.increment();
                        p.addIndex(i);
                    }
                }
            }

            //If there is only one set with largest size, that set is now the current set
            if (countBiggest == 1) {
                currentWord.setPattern(biggestPatterns.get(0).getPattern());
                currentSet = partition.get(biggestPatterns.get(0).getPattern().toString());
            } else { //or else, run the priority algorithm
                priority(biggestPatterns, guess, partition);
            }

//        for (String t : currentSet){
//            System.out.println(t);
//        }
            boolean win = true;
            for (int i = 0; i < currentWord.getPattern().toString().length(); i++) {
                if (currentWord.getPattern().toString().charAt(i) == '-') {
                    win = false;
                }
            }
            if (win == true) {
                System.out.println("You Win!");
                System.out.println("The word was: " + currentWord.getPattern().toString());
                end = true;
                return currentSet;
            }

            numPattern = biggestPatterns.get(0).getCount();
            if (biggestPatterns.get(0).getCount() == 0) {
                correctGuess = false;
            } else {
                correctGuess = true;
            }

            return currentSet;
        }
    }
    public void priority(ArrayList<Pattern> list, char guess, Map<String, HashSet<String>> partition){
        //1. Choose the group in which the letter does not appear at all.
        //2. If each group has the guessed letter, choose the one with the fewest letters.
        //3. If this still has not resolved the issue, choose the one with the rightmost guessed letter.
        //4. If there is still more than one group,
        // choose the one with the next rightmost letter.
        // Repeat this step (step 4) until a group is chosen.

        int tempInt = list.get(0).getCount();
        Pattern tempPattern = new Pattern("");
        for (int i = 0; i < list.size(); i++) {
            if (tempInt > list.get(i).getCount()){
                tempInt = list.get(i).getCount();
            }
        }

        for (int i = 0; i < list.size(); i++){
            if (tempInt != list.get(i).getCount()){
                list.remove(i);
                i--;
            }
        }
        if (list.size() !=  1){
            int index = list.get(0).getIndex().size() - 1;
            rightMost(list, index, partition);
        }
        if (list.size() == 1) {
            currentWord.setPattern(list.get(0).getPattern());
            currentSet = partition.get(list.get(0).getPattern().toString());
        }
    }
    public void rightMost(ArrayList<Pattern> list, int index, Map<String, HashSet<String>> partition){
        if (list.size() == 1){
            currentWord.setPattern(list.get(0).getPattern());
            currentSet = partition.get(list.get(0).getPattern().toString());
        } else {
            int tempInt = list.get(0).getIndex().get(index);
            for (int i = 0; i < list.size(); i++){
                if (tempInt < list.get(i).getIndex().get(index)){
                    tempInt = list.get(i).getIndex().get(index);
                }
            }
            for (int i = 0; i < list.size(); i ++){
                if (tempInt != list.get(i).getIndex().get(index)){
                    list.remove(i);
                    i--;
                }
            }
            index--;
            rightMost(list, index, partition);
        }
    }

    public Set<String> getDictionarySet(){
        return dictionarySet;
    }
    public Set<String> getCurrentSet(){
        return currentSet;
    }
    public boolean getEnd(){
        return end;
    }
    public String getCurrentWord(){
        return currentWord.getPattern().toString();
    }
    public String guessToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guessedLetters.size(); i++){
            if (i < guessedLetters.size()-1) {
                sb.append(guessedLetters.get(i) + " ");
            } else sb.append(guessedLetters.get(i));
        }
        return sb.toString();
    }
    public boolean getCorrectGuess(){
        return correctGuess;
    }
    public int getNumPattern(){
        return numPattern;
    }
}
