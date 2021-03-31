public interface Node {
    Node getLeft();
    Node getRight();
    Node getUp();
    Node getDown();
    void setLeft(Node node);
    void setRight(Node node);
    void setUp(Node node);
    void setDown(Node node);
    Node get(int i);
    void set(Node node, int i);
}
