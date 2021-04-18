package DancingLinkAlg;

/* just an interface to work with every node, most of the methods is implemented in the NodeBase */
public interface Node {
    Node getLeft();
    Node getRight();
    Node getUp();
    Node getDown();
    void setLeft(Node node);
    void setRight(Node node);
    void setUp(Node node);
    void setDown(Node node);
    Node get(int i); // get neightbour from certain direction ( i in 0-3 , right,down,right,up )
    void set(Node node, int i); // set neightbour from certain direction ( i in 0-3 , right,down,right,up )
    String getName();
    void detachNode(int d); //d is either 0 or 1, if 0 its detach left/right , if 1 its detach up/down
    void attachNode(int d); //d is either 0 or 1, if 0 its attach left/right , if 1 its attach up/down
    String getIdentity(); //return string stating the class it is part off and some additional info about that node
    String getNeightbourInfo(); //return the identity string of all neightbours
}
