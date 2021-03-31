import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainNode extends NodeBase{
    int col;
    int row;
    List<Map<String,Node>> headerNodeMapList;


    //the MainNode represents the leftupper corner of the 2D matrix
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
        //depending if HeaderNode should be a rowheader or col header, add to the correct count
        row += node.rowHeader;
        col += 1 - node.rowHeader;
        headerNodeMapList.get(node.rowHeader).put(node.name, node);
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
        node.get(i+2).set(node, i);
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

}
