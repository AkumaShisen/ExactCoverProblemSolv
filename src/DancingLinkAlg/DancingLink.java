package DancingLinkAlg;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import HeaderSpecifiers.*;

public class DancingLink {
    MainNode root;
    List<Options> options;
    Stack<HeaderManager> managers;
    Tree<HeaderNode> solutionTree;
    Tree<HeaderNode> lastPointer;
    int solutionCount;
    int maxSolutions;
    boolean calculated = false;
    public DancingLink(List<Constrains> constrains, List<Options> options, int stopAtSolutions) {
        this.root = new MainNode();
        this.managers = new Stack<>();
        this.solutionTree = new Tree<>(null);
        this.lastPointer = solutionTree;
        this.solutionCount = 0;
        this.maxSolutions = stopAtSolutions;
        for (Constrains constrain : constrains) {
            constrain.addConstrainsToMatrix(this.root);
        }
        this.options = options;
    }
    public void addConstrain(Constrains constrain) {
        if(isGenerated()) return;
        constrain.addConstrainsToMatrix(this.root);
    }
    public void addConstrain(List<Constrains> constrains) {
        if(isGenerated()) return;
        for(Constrains constrain : constrains) constrain.addConstrainsToMatrix(this.root);
    }
    public void gen() {
        if(isGenerated()) return;
        for (Options option : options) {
            option.addOptionsToMatrix(this.root);
        }
    }
    public boolean isGenerated() {
        return root != root.getDown();
    }
    public DancingLink(MainNode root, int stopAtSolutions) {
        this.root = root;
        this.managers = new Stack<>();
        this.solutionTree = new Tree<>(null);
        this.lastPointer = solutionTree;
        this.solutionCount = 0;
        this.maxSolutions = stopAtSolutions;
    }
    public void scanInGrid(String[][] input, char[] dim) {
        for(int y=0;y<input.length;y++) {
            for(int x=0;y<input[y].length;y++) {
                if(input[y][x].equals("0")) continue;
                selectIdentity(new PosValueIdentity(new KoorPosition(dim,y,x), input[y][x]));
            }
        }
    }
    public void scanInGrid(int[][] input, char[] dim) {
        for(int y=0;y<input.length;y++) {
            for(int x=0;x<input[y].length;x++) {
                if(input[y][x]==0) continue;
                selectIdentity(new PosValueIdentity(new KoorPosition(dim,y,x), String.valueOf(input[y][x])));
            }
        }
    }

    public void selectIdentities(List<Identity> list) {
        if(!isGenerated()) gen();
        for(Identity id : list) choseRow(root.getRowHeaderMap().get(id.getName()));
    }
    public void selectIdentity(Identity id) {
        if(!isGenerated()) gen();
        choseRow(root.getRowHeaderMap().get(id.getName()));
    }
    public Tree<HeaderNode> solve() {
        if(!isGenerated()) gen();
        if(calculated) return this.solutionTree;
        solveRec(this.lastPointer);
        calculated = true;
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

    public void choseRow(HeaderNode choosen) {
        if(choosen == null || choosen!=choosen.getUp().getDown()) {
            calculated = true;
            return;
        }
        Tree<HeaderNode> nextTree = new Tree<>(choosen);
        lastPointer.addChild(nextTree);
        lastPointer = nextTree;
        HeaderManager rowSelect = new HeaderManager();
        rowSelect.detachEntryHeaders(choosen);
        managers.add(rowSelect);
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
            if(nodes == 0) {
                return null;
            }
            if(!(bestCandidate instanceof HeaderNode) || ((HeaderNode) bestCandidate).nodes > nodes) {
                bestCandidate = mainColIter;
            }
            mainColIter = mainColIter.getRight();
        }
        return bestCandidate;
    }

    public String toString(char rowDim, char colDim, int rowVal, int colVal) {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        String[][] solutionGrid = new String[rowVal][colVal];

        TreeIterator<HeaderNode> it = solutionTree.iterator();

        while (it.hasNext()) {
            Iterator<Tree<HeaderNode>> nextPath = it.nextIt();
            while (nextPath.hasNext()) {
                Tree<HeaderNode> nextVal = nextPath.next();
                if(nextVal.val==null) continue;
                if(! (nextVal.val.identity instanceof PosValueIdentity)){
                    result.append(nextVal.val.identity.getName()).append("     ");
                    continue;
                }
                PosValueIdentity id = (PosValueIdentity)nextVal.val.identity;
                if(!id.pos.hasDimension(rowDim) || !id.pos.hasDimension(colDim) ||
                        id.pos.get(rowDim)<0 || id.pos.get(rowDim)>=rowVal ||
                        id.pos.get(colDim)<0 || id.pos.get(colDim)>=colVal) {

                    result.append(nextVal.val.identity.getName()).append( "     ");
                    continue;
                }
                solutionGrid[id.pos.get(rowDim)][id.pos.get(colDim)] = id.val;

            }
            for(int row=0;row<rowVal;row++) {
                for(int col=0;col<colVal;col++) {
                    if(solutionGrid[row][col]==null) return "\n";
                    result.append(solutionGrid[row][col]).append(" ");
                }
                result.append("\n");
            }
            result.append("\n");

        }
        return result.toString();
    }
}
