public class NodeBase implements Node {
    Node[] neighbours;
    public Node getLeft() { return neighbours[2]; }
    public Node getRight() { return neighbours[0]; }
    public Node getUp() { return neighbours[3]; }
    public Node getDown() { return neighbours[1]; }
    public void setLeft(Node node) { neighbours[2] = node; }
    public void setRight(Node node) { neighbours[0] = node; }
    public void setUp(Node node) { neighbours[3] = node; }
    public void setDown(Node node) { neighbours [1] = node; }
    public Node get(int i) { return neighbours[i%4]; }
    public void set(Node node, int i) { neighbours[i%4] = node; }
    public NodeBase() {
        neighbours = new Node[4];
    }
}
