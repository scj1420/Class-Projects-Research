package hangman;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Seong-EunCho on 1/31/17.
 */

public class Pattern {
    private StringBuilder pattern;
    private int count = 0;
    private ArrayList<Integer> index = new ArrayList<>();

    public Pattern(String s){
        pattern = new StringBuilder(s);
    }
    public Pattern(String s, int i, ArrayList<Integer> L){
        pattern = new StringBuilder(s);
        this.count = i;
        this.index = L;
    }

    public StringBuilder getPattern() {
        return pattern;
    }

    public void setPattern(StringBuilder pattern) {
        this.pattern = pattern;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    public ArrayList<Integer> getIndex() {
        return index;
    }

    public void addIndex(int i) {
        index.add(i);
    }
}
