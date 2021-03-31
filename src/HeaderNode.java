public class HeaderNode extends NodeBase{
    int nodes;
    String name;
    int rowHeader;
    public HeaderNode(String name, int isRowHeader) {
        super();
        rowHeader = isRowHeader; //either 0 or 1, 0 == false , 1 == true
        nodes = 0;
        this.name = name;
    }
}
