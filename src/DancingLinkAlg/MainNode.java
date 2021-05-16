package DancingLinkAlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* the root of matrix, the upperleft corner of the 2D matrix */
public class MainNode extends NodeBase {
    public int col;
    public int row;
    /* model here is that optional columns are added on the right from the lastoptcol, while mainColumns are
    *  added at the left from the mainnode, so starting from the right of optcolheader and ending at the left of the
    *  mainnode will only be maincolumns, while optional columns will be starting from the right of mainnode and until
    *  the optcolheader pointer
    */
    public Node lastOptCol;

    // typically contains 2 entries, the first being a list that contains all colheaders, the second all rowheaders
    private List<Map<String, HeaderNode>> headerNodeMapList;


    public MainNode() {
        super();
        for (int i = 0; i < 4; i++) neighbours[i] = this;
        headerNodeMapList = new ArrayList<>();
        headerNodeMapList.add(new HashMap<>()); //colheaders, index 0
        headerNodeMapList.add(new HashMap<>()); //rowheaders, index 1
        col = 0;
        row = 0;
        lastOptCol = this;
    }

    public Map<String,HeaderNode> getColHeaderMap() {
        return headerNodeMapList.get(0);
    }
    public Map<String,HeaderNode> getRowHeaderMap() {
        return headerNodeMapList.get(1);
    }

    public void addHeader(HeaderNode node) throws UnsupportedOperationException {
        //depending if DancingLinkAlg.HeaderNode should be a rowheader or col header, add to the correct count
        if(headerNodeMapList.get(node.rowHeader).containsKey(node.identity.getName())) {
            throw new UnsupportedOperationException("already contains a "+
                    (node.rowHeader == 1 ? "row":"col") + " with this identity name in the matrix");
        }
        row += node.rowHeader;
        col += 1 - node.rowHeader;
        headerNodeMapList.get(node.rowHeader).put(node.identity.getName(), node);
        node.root = this;
        /*
        if col header: 0
        node.left = main.left
        node.right = main
        main.left.right = node
        main.left = node

        if row header: 1
        node.up = main.up
        node.down = main
        main.up.down = node
        main.up = node
        */
        int i = node.rowHeader;
        node.set(this.get(i+2), i+2);
        node.set(this, i);
        this.get(i+2).set(node, i);
        this.set(node,i+2);
    }

    public void addColumnHeader(Identity name) {
        HeaderNode header = new HeaderNode(name, 0);
        this.addHeader(header);
    }

    public void addOptColHeader(Identity name) {
        HeaderNode header = new HeaderNode(name,0);
        this.lastOptCol.getRight().setLeft(header);
        header.setRight(this.lastOptCol.getRight());
        header.setLeft(this.lastOptCol);
        this.lastOptCol.setRight(header);
        this.lastOptCol = header;
    }

    public void addRowHeader(Identity name) {
        HeaderNode rowHeader = new HeaderNode(name, 1);
        this.addHeader(rowHeader);
        Node it = this.getRight();
        while(it instanceof HeaderNode) {
            HeaderNode colHeader = (HeaderNode) it;
            if(colHeader.identity.match(name)) {
                new SparseNode(rowHeader,colHeader );
            }
            it=it.getRight();
        }
    }

    public boolean addSparseNode(String rowName, String colName) {
        HeaderNode colHeader = getColHeaderMap().get(colName);
        HeaderNode rowHeader = getRowHeaderMap().get(rowName);
        if(colHeader == null || rowHeader == null) return false;
        new SparseNode(rowHeader, colHeader);
        return true;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("columns: ");
        Node iter = this;
        while(! (iter.getRight() instanceof MainNode)) {
            iter = iter.getRight();
            result.append(iter.getName()).append(" | ");
        }
        result.append("\nrows: ");
        iter = iter.getRight();

        while(! (iter.getDown() instanceof MainNode)) {
            iter = iter.getDown();
            result.append(iter.getName()).append(" | ");
        }
        result.append("\n");
        Node rowIt = this.get(1);
        Node colIt = this.get(0);

        while(!(rowIt instanceof MainNode)) {

            SparseNode nextSparseNode = rowIt.get(0) instanceof SparseNode ? (SparseNode) rowIt.get(0)  : null;
            while(!(colIt instanceof MainNode)) {

                if(nextSparseNode != null && colIt.equals(nextSparseNode.getCol())) {
                    result.append("1 ");
                    nextSparseNode = nextSparseNode.get(0) instanceof SparseNode ? (SparseNode) nextSparseNode.get(0) : null;
                } //else result.append("0 ");
                else result.append("  ");

                colIt = colIt.get(0);
            }
            result.append("\n");
            colIt = colIt.get(0);
            rowIt = rowIt.get(1);
        }



        return result.toString();
    }
    // returns a new  instance of MainNode that is connected to a new sparsematrix that
    // represents the same matrix as the instance it is called from
    public MainNode copy() {
        MainNode root = new MainNode();
        Node it = this.getRight(); // get rightNode, should be ColHeaders
        while(it instanceof HeaderNode) {
            root.addColumnHeader(((HeaderNode)it).identity);
            it = it.getRight();
        }
        it = this.getDown(); // it trough rowHeaders
        while(it instanceof HeaderNode) {
            String rowName = ((HeaderNode) it).identity.getName();
            root.addRowHeader(((HeaderNode)it).identity);
            Node sparseIt = it.getRight();

            while(sparseIt instanceof SparseNode) { // it trough sparsenodes in row
                root.addSparseNode(rowName, ((SparseNode)sparseIt).getCol().identity.getName());
                sparseIt = sparseIt.getRight();
            }
            it = it.getDown();
        }
        return root;
    }

    @Override
    public String getIdentity() {
        return "MainNode | "+this.row+"/"+this.col;
    }

}
