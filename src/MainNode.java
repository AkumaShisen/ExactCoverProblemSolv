public class MainNode extends NodeBase{
    int col;
    int row;
    //the MainNode represents the leftupper corner of the 2D matrix
    public MainNode() {
        super();
        col = 0;
        row = 0;
    }

    public void addHeader(HeaderNode node) {
        //depending if HeaderNode should be a rowheader or col header, add to the correct count
        row += node.rowHeader;
        col += 1 - node.rowHeader;
        /*
        if col header: 0
        node.left = main.left ,,,  node[2] = main[2]
        node.right = main ,,,,     node[0] = main
        main.left.right = node     main[2].[0] = node
        main.left = node           main[2] = node

        if row header: 1
        node.up = main.up          node[3] = main[3]
        node.down = main           node[1] = main
        main.up.down = node        main[3].[1] = node
        main.up = node             main[3] = node
        */
        int i = node.rowHeader;
        node.set(this.get(i+2), i+2);
        node.set(this, i);
        node.get(i+2).set(node, i);
        this.set(node,i+2);
    }
}
