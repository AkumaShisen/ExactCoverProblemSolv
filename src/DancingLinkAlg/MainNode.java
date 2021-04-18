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

    // typically contains 2 entries, the first being a list that contains all colheaders, the second all rowheaders
    public List<Map<String, Node>> headerNodeMapList;


    public MainNode() {
        super();
        for(int i=0;i<4;i++) neighbours[i] = this;
        headerNodeMapList = new ArrayList<>();
        headerNodeMapList.add(new HashMap<>()); //colheaders, index 0
        headerNodeMapList.add(new HashMap<>()); //rowheaders, index 1
        col = 0;
        row = 0;
    }

    public void addHeader(HeaderNode node) {
        //depending if DancingLinkAlg.HeaderNode should be a rowheader or col header, add to the correct count
        row += node.rowHeader;
        col += 1 - node.rowHeader;
        headerNodeMapList.get(node.rowHeader).put(node.name, node);
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

    public void addColumnHeader(String name) {
        HeaderNode header = new HeaderNode(name, 0);
        this.addHeader(header);
    }

    public void addRowHeader(String name) {
        HeaderNode header = new HeaderNode(name, 1);
        this.addHeader(header);
    }

    public boolean addSparseNode(String rowName, String colName) {
        HeaderNode colHeader = (HeaderNode) headerNodeMapList.get(0).get(colName);
        HeaderNode rowHeader = (HeaderNode) headerNodeMapList.get(1).get(rowName);
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
                } else result.append("0 ");

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
            root.addColumnHeader(((HeaderNode)it).name);
            it = it.getRight();
        }
        it = this.getDown(); // it trough rowHeaders
        while(it instanceof HeaderNode) {
            String rowName = ((HeaderNode) it).name;
            root.addRowHeader(((HeaderNode)it).name);
            Node sparseIt = it.getRight();

            while(sparseIt instanceof SparseNode) { // it trough sparsenodes in row
                root.addSparseNode(rowName, ((SparseNode)sparseIt).getCol().name);
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
