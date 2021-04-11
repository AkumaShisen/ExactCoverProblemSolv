package DancingLinkAlg;



public class SparseNode extends NodeBase {
    HeaderNode[] headers;
    /*creates a sparse node, representing a 1 in a sparse matrix, with a given row and column.
    * The arguments are the headernodes, the headers for  the column and for the row of the given node.
    * In the process of creating the instance, the node itself is properly placed into the matrix that the given
    * headernodes form by giving the 4 values in the neighbours array a pointer*/
    public SparseNode(HeaderNode rowHeader, HeaderNode colHeader) {
        super();
        headers = new HeaderNode[2];
        headers[0] = rowHeader;
        headers[1] = colHeader;
        rowHeader.nodes++;
        colHeader.nodes++;

        for(int i=0; i<2;i++) {
            neighbours[i] = headers[i];
            neighbours[i+2] = headers[i].get(i+2);
            headers[i].get(i+2).set(this, i);
            headers[i].set(this, i+2);
        }
    }
    public HeaderNode getRow() { return headers[0];}
    public HeaderNode getCol() { return headers[1];}
    public void detachNode(int d) {
        super.detachNode(d);
        headers[d].nodes--;
    }
    public void attachNode(int d) {
        super.attachNode(d);
        headers[d].nodes++;
    }

    @Override
    public String getIdentity() {
        return "SparseNode "+getRow().getName()+" / "+getCol().getName();
    }
}
