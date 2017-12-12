package spell;

/**
 * Created by Seong-EunCho on 1/18/17.
 */

public class Node implements ITrie.INode {
    public Node[] n = new Node[26];
    public int count = 0;

    @Override
    public int getValue(){
        return count;
    }
}
