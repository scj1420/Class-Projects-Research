package spell;

/**
 * Created by Seong-EunCho on 1/18/17.
 */

public class Trie implements ITrie {

    private Node root = new Node();
    private int nodeCount = 1;
    private int wordCount = 0;

    @Override
    public void add(String word){
        addRecursive(word, root);
    }
    public void addRecursive(String word, Node n){
        if(word.length() != 0){

            //Uppercase to lowercase
            StringBuilder sb = new StringBuilder(word);
            char c = sb.charAt(0);
            sb.setCharAt(0, Character.toLowerCase(c));
            word = sb.toString();
            Character.toLowerCase(word.charAt(0));

            if (n.n[word.charAt(0) - 'a'] == null) {
                n.n[word.charAt(0) - 'a'] = new Node();
                nodeCount++;
                n = n.n[word.charAt(0) - 'a'];
                word = new String(word.substring(1, word.length()));
                addRecursive(word, n);
            }
            else {
                n = n.n[word.charAt(0) - 'a'];
                word = new String(word.substring(1, word.length()));
                addRecursive(word, n);
            }
        }
        else{
            if (n.count == 0){
                wordCount++;
            }
            n.count++;
        }
    }

    @Override
    public INode find(String word) {
        return findRecursive(word, root);

    }
    public Node findRecursive(String word, Node n){
        if(word.length() != 0){

            //Uppercase to lowercase
            StringBuilder sb = new StringBuilder(word);
            char c = sb.charAt(0);
            sb.setCharAt(0, Character.toLowerCase(c));
            word = sb.toString();
            Character.toLowerCase(word.charAt(0));

            if (n.n[word.charAt(0) - 'a'] == null) {
                return null;
            }
            else {
                n = n.n[word.charAt(0) - 'a'];
                word = new String(word.substring(1, word.length()));
                return findRecursive(word, n);
            }
        }
        else{
            if (n.count != 0) {
                return n;
            } else return null;
        }
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    public Node getRoot(){
        return root;
    }

    @Override
    public String toString(){
        String s = "";
        StringBuilder sb = new StringBuilder(s);
        return toStringRecursive(root, s, sb).toString();
    }
    public StringBuilder toStringRecursive(Node n, String s, StringBuilder sb){

        if (n == null){
            return sb;
        } else {
            String temp = s.toString();
            if (n.count != 0){
                sb.append(temp + "\n");
            }
            for (int i = 0; i < 26; i++){
                if (n.n[i] != null){
                    int a = 'a' + i;
                    char c = (char) a;
                    temp = s.concat(String.valueOf(c));
                    toStringRecursive(n.n[i], temp, sb);
                }
            }
            return sb;
        }
    }

    @Override
    public int hashCode(){
        return nodeCount % wordCount * (nodeCount - wordCount);
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (o instanceof Trie){
            Trie t = (Trie) o;
            if (hashCode() != t.hashCode()){
                return false;
            }
            return equalsRecursive(root, t.getRoot());
        } else return false;
    }
    public boolean equalsRecursive(Node n1, Node n2){
        if ((n1 != null && n2 == null) || (n1 == null && n2 != null)){
            return false;
        } else {
            if (n1 != null) {
                if (n1.getValue() != n2.getValue()){
                    return false;
                } else {
                    boolean b = true;
                    for (int i = 0; i < 26; i++) {
                        if (equalsRecursive(n1.n[i], n2.n[i]) == false) {
                            return false;
                        }
                    }
                    return b;
                }
            }
            return true;
        }
    }

}
