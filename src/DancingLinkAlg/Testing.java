package DancingLinkAlg;



public class Testing {
    public static void main(String[] args) {


    }


    public static MainNode getMatrixExample() {
        MainNode root = new MainNode();
        String[] choice1 = {"A", "C"};
        String[] choice2 = {"B", "C","D"};
        String[] choice3 = {"A"};
        String[][] choices = {choice1, choice2, choice3};
        root.addHeader(new HeaderNode("A", 0));
        root.addHeader(new HeaderNode("B", 0));
        root.addHeader(new HeaderNode("C", 0));
        root.addHeader(new HeaderNode("D", 0));
        for(int i=0; i<3;i++) {
            root.addHeader(new HeaderNode(String.valueOf(i), 1));
            for(String e : choices[i]) {
                root.addSparseNode(String.valueOf(i),e);
            }
        }
        return root;
    }



}