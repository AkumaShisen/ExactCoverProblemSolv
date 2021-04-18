package DancingLinkAlg;

import java.util.List;
import java.util.Stack;

public class DancingLink {
    MainNode root;
    Stack<HeaderManager> managers;
    Tree<HeaderManager> solutionTree;
    int solutionCount;

    public DancingLink(MainNode root) {
        this.root = root;
        managers = new Stack<>();
        solutionTree = new Tree<>(null);
        solutionCount = 0;
    }

}
