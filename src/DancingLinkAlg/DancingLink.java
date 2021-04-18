package DancingLinkAlg;

import java.util.List;
import java.util.Stack;

public class DancingLink {
    MainNode root;
    Stack<HeaderManager> managers;
    Tree<HeaderNode> solutionTree;
    int solutionCount;
    int maxSolutions;

    public DancingLink(MainNode root, int stopAtSolutions) {
        this.root = root;
        this.managers = new Stack<>();
        this.solutionTree = new Tree<>(null);
        this.solutionCount = 0;
        this.maxSolutions = stopAtSolutions;
    }
    public Tree<HeaderNode> solve() {
        solveRec(this.solutionTree);
        return this.solutionTree;
    }

    public boolean solveRec(Tree<HeaderNode> solutionPointer) {


        Node nextCol = this.selectNextMainCol();
        if(nextCol == null) {
            return false;
        }
        else if(nextCol instanceof MainNode) {
            this.solutionCount++;
            return true;
        }
        HeaderManager initialColRemove = new HeaderManager();

        initialColRemove.detachCascade((HeaderNode) nextCol);

        Node iter = nextCol.getDown();
        while(iter instanceof SparseNode) {
            HeaderNode chosenRow = ((SparseNode) iter).getRow();
            Tree<HeaderNode> nextTree = new Tree<>(chosenRow);
            solutionPointer.addChild(nextTree);
            HeaderManager rowSelect = new HeaderManager();
            rowSelect.detachEntryHeaders(chosenRow);
            boolean found = solveRec(nextTree);

            rowSelect.attachAll();

            if(this.maxSolutions> 0 && this.solutionCount>= this.maxSolutions) {
                initialColRemove.attachAll();
                return true;
            }
            if(!found) {
                solutionPointer.removeLastChild();
            }
            iter = iter.getDown();
        }
        initialColRemove.attachAll();
        return !solutionPointer.isLeaf();
    }

    /*
     * returns null if a mainNode doesnt contain any sparsenodes -> no row candidate that could fill that requirement
     * returns the root if there are no mainColheaders left -> all requirements have been met
     * else returns the headernode with the least amount of sparseNodes -> least amount of row candidates
     */
    public Node selectNextMainCol() {
        Node bestCandidate = this.root;
        Node mainColIter = this.root.lastOptCol.getRight();
        while(mainColIter instanceof HeaderNode) {
            int nodes = ((HeaderNode) mainColIter ).nodes;
            if(nodes == 0) return null;
            if(!(bestCandidate instanceof HeaderNode) || ((HeaderNode) bestCandidate).nodes > nodes) {
                bestCandidate = mainColIter;
            }
            mainColIter = mainColIter.getRight();
        }
        return bestCandidate;
    }
}
