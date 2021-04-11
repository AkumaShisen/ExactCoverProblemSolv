package DancingLinkAlg;

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
    String getName();
    void detachNode(int d); //d is either 0 or 1, if 0 its detach left/right , if 1 its detach up/down
    void attachNode(int d);
    String getIdentity();
    String getNeightbourInfo();
}
