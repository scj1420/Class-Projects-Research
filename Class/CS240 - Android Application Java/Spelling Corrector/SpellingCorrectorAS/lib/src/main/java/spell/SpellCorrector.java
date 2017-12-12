package spell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Created by Seong-EunCho on 1/18/17.
 */

public class SpellCorrector implements ISpellCorrector {
    private Trie dictionary = new Trie();
    private Trie SuggestedWord = new Trie();
    private List<String> SuggestedWordList = new Vector<String>();
    private Trie EditDistance1 = new Trie();
    private Trie EditDistance2 = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(dictionaryFileName));
        String word;
        while ((word = in.readLine()) != null){
            dictionary.add(word);
        }
        in.close();
    }

    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        if (dictionary.find(inputWord) != null){
            if (dictionary.find(inputWord).getValue() != 0) {
                SuggestedWord.add(inputWord);
//                return inputWord;
            }
            else edit(inputWord, EditDistance1);
        }
        else edit(inputWord, EditDistance1);

        if (SuggestedWord.getWordCount() == 0){
            secondEdit();
        }
        if (SuggestedWord.getWordCount() == 0){
            throw new NoSimilarWordFoundException();
        }

        suggestionList(SuggestedWord.getRoot(), "");
        java.util.Collections.sort(SuggestedWordList);
        String s = "";
        for (int i = 0; i < SuggestedWordList.size(); i++){
            if (SuggestedWord.find(s) != null){
                if (dictionary.find(s).getValue() < dictionary.find(SuggestedWordList.get(i)).getValue()){
                    s = SuggestedWordList.get(i).toString();
                }
            }
            else s = SuggestedWordList.get(i).toString();
        }

        return s;
    }
    public void suggestionList(Node n, String s){
        if (n == null){
            return;
        } else {
            String temp = s.toString();
            if (n.count != 0){
                SuggestedWordList.add(temp);
            }
            for (int i = 0; i < 26; i++){
                if (n.n[i] != null){
                    int a = 'a' + i;
                    char c = (char) a;
                    temp = s.concat(String.valueOf(c));
                    suggestionList(n.n[i], temp);
                }
            }
            return;
        }
    }
    public void edit(String inputWord, Trie t){
        deletion(inputWord, t);
        transposition(inputWord, t);
        alteration(inputWord, t);
        insertion(inputWord, t);
    }
    public void secondEdit(){
        String s = "";
        secondEditRecursive(EditDistance1.getRoot(), s);
    }
    public void secondEditRecursive(Node n, String s){
        if (n == null){
            return;
        } else {
            String temp = s.toString();
            if (n.count != 0){
                edit(temp, EditDistance2);
            }
            for (int i = 0; i < 26; i++){
                if (n.n[i] != null){
                    int a = 'a' + i;
                    char c = (char) a;
                    temp = s.concat(String.valueOf(c));
                    secondEditRecursive(n.n[i], temp);
                }
            }
            return;
        }
    }
    public void deletion(String word, Trie t){
        for (int i = 0; i < word.length(); i++){
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            AddToTrie(sb.toString(), t);
        }
    }
    public void transposition(String word, Trie t){
        for (int i = 0; i < word.length() - 1; i++){
            StringBuilder sb = new StringBuilder(word);
            char c = sb.charAt(i);
            sb.replace(i, i + 1, String.valueOf(sb.charAt(i+1)));
            sb.deleteCharAt(i + 1);
            sb.insert(i + 1, c);
            AddToTrie(sb.toString(), t);
        }
    }
    public void alteration(String word, Trie t){
        for (int i = 0; i < word.length(); i++){
            for (int j = 0; j < 26; j++){
                StringBuilder sb = new StringBuilder(word);
                int a = 'a' + j;
                char c = (char) a;
                if (c != word.charAt(i)){
                    sb.deleteCharAt(i);
                    sb.insert(i, c);
                    AddToTrie(sb.toString(), t);
                }
            }
        }
    }
    public void insertion(String word, Trie t){
        for (int i = 0; i < word.length(); i++){
            for (int j = 0; j < 26; j++){
                StringBuilder sb = new StringBuilder(word);
                int a = 'a' + j;
                char c = (char) a;
                sb.insert(i, c);
                AddToTrie(sb.toString(), t);
            }
            if (i == word.length()  - 1){
                for (int j = 0; j < 26; j++){
                    StringBuilder sb = new StringBuilder(word);
                    int a = 'a' + j;
                    char c = (char) a;
                    sb.append(c);
                    AddToTrie(sb.toString(), t);
                }
            }
        }
    }
    public void AddToTrie(String s, Trie t){
        if (dictionary.find(s) != null){
            if (dictionary.find(s).getValue() != 0) {
                SuggestedWord.add(s);
            }
            else t.add(s);
        } else {
            t.add(s);
        }
    }

    public Trie getDictionary(){
        return dictionary;
    }
    public Trie getSuggestedWord(){
        return SuggestedWord;
    }
    public List<String> getSuggestedWordList(){
        return SuggestedWordList;
    }
    public Trie getEditDistance1(){
        return EditDistance1;
    }
    public Trie getEditDistance2(){
        return EditDistance2;
    }
}
