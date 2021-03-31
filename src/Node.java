public interface Node {
    public Node getLeft();
    public Node getRight();
    public Node getUp();
    public Node getDown();
    public void setLeft(Node node);
    public void setRight(Node node);
    public void setUp(Node node);
    public void setDown(Node node);
    public Node get(int i);
    public void set(Node node, int i);
}
