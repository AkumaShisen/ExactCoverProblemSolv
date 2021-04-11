package DancingLinkAlg;



import java.util.Stack;
public class HeaderManager {
    Stack<HeaderNode> stack = new Stack<>();
    MainNode root;
    public HeaderManager(MainNode root) {
        this.root = root;
    }

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
    public void attach(HeaderNode node) {
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
