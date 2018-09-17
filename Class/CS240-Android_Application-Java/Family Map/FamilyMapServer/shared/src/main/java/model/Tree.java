package model;

import java.util.*;

/**
 * Created by Seong-EunCho on 3/7/17.
 */

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.setData(rootData);
    }

    public Node<T> getRoot(){
        return root;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(root.getData().toString());
        recursiveToString(root, sb, 1);

        return null;
    }
    private void recursiveToString(Node<T> n, StringBuilder sb, int count){
        if (n.getRight() != null && n.getLeft() != null){
            sb.append(n.getRight());
            sb.append(n.getLeft());
        } else {

        }

    }
}
