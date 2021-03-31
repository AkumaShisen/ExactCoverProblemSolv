public class HeaderNode extends NodeBase{
    int nodes;
    String name;
    int rowHeader;
    HeaderNode(String name, int isRowHeader) {
        super();
        for(int i=0;i<4;i++) neighbours[i] = this;
        rowHeader = isRowHeader; //either 0 or 1, 0 == false , 1 == true
        nodes = 0;
        this.name = name;
    }
}
