package DancingLinkAlg;

import java.util.LinkedList;
import java.util.List;

public class Tree<T> {
    T val;
    Tree<T> parent;
    List<Tree<T>> childList;
    public Tree(T val) {
        this.val = val;
        childList = new LinkedList<>();
    }
    public void addChild(Tree<T> child) {
        childList.add(child);
    }
}
