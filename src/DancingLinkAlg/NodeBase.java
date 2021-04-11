package DancingLinkAlg;


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
    public String getName() { return "1"; }

    public void detachNode(int d) {
        /*
        row : node.up.down = node.down   ;     node.down.up = node.up
        col : node.left.right = node.right   ;  node.right.left = node.left
         */
        this.get(d+2).set(this.get(d), d);
        this.get(d).set(this.get(d+2), d+2);
    }

    public void attachNode(int d) {
        this.get(d+2).set(this, d);
        this.get(d).set(this, d+2);
    }
    public String getIdentity() {
        return "DancingLinkAlg.NodeBase";
    }
    public String getNeightbourInfo(){
        return "left: "+getLeft().getIdentity()+"\n"+
                        "up: "+getUp().getIdentity()+"\n"+
                        "right "+getRight().getIdentity()+"\n"+
                        "down: "+getDown().getIdentity();

    }
}
