package DancingLinkAlg;

public class Testing {
    public static void main(String[] args) {
        MainNode test = getAllVariationMatrix(3,3);
        System.out.println(test);
        DancingLink alg = new DancingLink(test,0);
        Tree<HeaderNode> root = alg.solve();
        TreeIterator<HeaderNode> it = new TreeIterator<>(root);
        System.out.println(alg.solutionCount);
        System.out.println(root.toString());


    }


    public static MainNode getMatrixExample() {
        MainNode root = new MainNode();
        String[] choice1 = {"A", "C"};
        String[] choice2 = {"B", "C","D"};
        String[] choice3 = {"A"};
        String[] choice4 = {"B","D"};
        String[][] choices = {choice1, choice2, choice3,choice4};
        root.addHeader(new HeaderNode(new HeaderIdentity("A"), 0));
        root.addHeader(new HeaderNode(new HeaderIdentity("B"), 0));
        root.addHeader(new HeaderNode(new HeaderIdentity("C"), 0));
        root.addHeader(new HeaderNode(new HeaderIdentity("D"), 0));
        for(int i=0; i<4;i++) {
            root.addHeader(new HeaderNode(new HeaderIdentity(String.valueOf(i)), 1));
            for(String e : choices[i]) {
                root.addSparseNode(String.valueOf(i),e);
            }
        }
        return root;
    }

    public static MainNode getAllVariationMatrix(int places, int elements) {
        MainNode root = new MainNode();
        for(int i=0;i<places;i++) {
            HeaderNode col = new HeaderNode(new HeaderIdentity(""+i), 0);
            root.addHeader(col);
            for(int j=0;j<elements;j++) {
                HeaderNode row = new HeaderNode(new HeaderIdentity(i+"/"+j),1);
                root.addHeader(row);
                new SparseNode(row, col);
            }
        }
        return root;
    }



}
