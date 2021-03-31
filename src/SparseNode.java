public class SparseNode extends NodeBase   {
    Node[] headers;
    public SparseNode() {
        super();
        headers = new Node[2];
    }
    public Node getRow() { return headers[0];}
    public Node getCol() { return headers[1];}
}
