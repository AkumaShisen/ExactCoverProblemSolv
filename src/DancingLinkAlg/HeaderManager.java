package DancingLinkAlg;



import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/* usecase is to create an instance of HeaderManager, then detach HeaderNodes in some way then be able to restore
*  the matrix to the point before the detach method was called from this instance with attachAll
*  reason for that is that in general nodes should be attached in the reverse order as they were detached or the
*  matrix wont get restored (principle last detached, first attached -> using a Stack) */
public class HeaderManager {
    Stack<HeaderNode> stack = new Stack<>();

    // same as detach, but recursivly, iterating trough the given row/col and calling detachCascade on each sparseNode
    // header (if node is a row, it calls on colheader of sparsenode and analog to if node is a col)
    // if casc = 0 , behaves exactly like detach(HeaderNode node)
    // in general just a method to play around
    public void detachCascade(HeaderNode node, int casc) {
        stack.push(node);
        node.detachNode(node.rowHeader);

        Node it = node;
        int d = 1 - node.rowHeader;
        while(it.get(d) instanceof SparseNode) {
            it.get(d).detachNode(node.rowHeader);
            if(casc>0)  detachCascade(((SparseNode)it.get(d)).headers[node.rowHeader], casc-1);
            it = it.get(d);
        }
    }

    // exactly the same as detachCascade(node,1)
    // gets rid of the if statement
    public void detachCascade(HeaderNode node) {
        stack.push(node);
        node.detachNode(node.rowHeader);

        Node it = node;
        int d = 1 - node.rowHeader;
        while(it.get(d) instanceof SparseNode) {
            it.get(d).detachNode(node.rowHeader);
            detach(((SparseNode)it.get(d)).headers[node.rowHeader]);
            it = it.get(d);
        }
    }
    /*
     * given a certain headerrow (row/ col) iterate trough all entries in that row/col and detachCascade
     * the col/row of each sparsenode in that row/col
     */
    public void detachEntryHeaders(HeaderNode node) {
        Node iter = node.get(1-node.rowHeader);
        while(iter instanceof SparseNode) {
            detachCascade(((SparseNode) iter).headers[node.rowHeader]);
            iter = iter.get(1-node.rowHeader);
        }
    }

    // detach the entire row/col (depending on the headernode rowheader value) , calling first detach on HeaderNode
    // then on each sparsenode in that row/col
    public void detach(HeaderNode node) {
        this.stack.push(node);
        node.detachNode(node.rowHeader);


        Node it = node;
        int d = 1 - node.rowHeader;
        while(it.get(d) instanceof SparseNode) {
            it.get(d).detachNode(node.rowHeader);
            it = it.get(d);
        }
    }

    // not encouraged to freely use, as to regain the original matrix, you have to attach the last detached
    // HeaderNode
    // attaches a given HeaderNode and all its sparseNodes in row/col
    public static void attach(HeaderNode node) {
        node.attachNode(node.rowHeader);


        Node it = node;
        int d = 1 - node.rowHeader;
        while(it.get(d) instanceof SparseNode) {
            it.get(d).attachNode(node.rowHeader);
            it = it.get(d);
        }
    }

    public void attachAll() {
        while(!this.stack.empty()) attach(this.stack.pop());
    }
}
