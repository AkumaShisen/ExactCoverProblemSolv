package DancingLinkAlg;

public class HeaderNode extends NodeBase {
    public MainNode root;
    public int nodes;
    Identity identity;
    public int rowHeader;
    HeaderNode(Identity name, int isRowHeader) {
        super();
        for(int i=0;i<4;i++) neighbours[i] = this;
        rowHeader = isRowHeader; //either 0 or 1, 0 == false , 1 == true
        this.nodes = 0;
        this.identity = name;
    }
    public void detachNode(int d) {
        super.detachNode(d);
        this.root.col -= d;
        this.root.row -= d;
    }
    public void attachNode(int d) {
        super.attachNode(d);
        this.root.col += d;
        this.root.row += d;
    }

    public String getName() { return identity.getName(); }
    @Override
    public String getIdentity() {
        return (this.rowHeader == 0 ? "RowHeader " : "ColHeader ")+this.identity.getName()+" nodes : "+this.nodes;
    }

    @Override
    public String toString() {
        return getName();
    }
}
