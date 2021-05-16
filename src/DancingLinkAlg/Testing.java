package DancingLinkAlg;

import HeaderSpecifiers.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Testing {
    public static void main(String[] args) {
        /*MainNode test = getAllVariationMatrix(3,3);
        System.out.println(test);
        DancingLink alg = new DancingLink(test,0);
        Tree<HeaderNode> root = alg.solve();
        TreeIterator<HeaderNode> it = new TreeIterator<>(root);
        System.out.println(alg.solutionCount);
        System.out.println(root.toString());*/
        DancingLink alg = ConstraintsOptionsTest(9,9,3,3,9);
        //System.out.println(alg.root);
        char[] dim = {'R','C'};


        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,0,2), "9"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,0,4), "8"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,0,7), "8"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,1,0), "2"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,1,1), "5"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,1,6), "9"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,1,7), "8"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,2,3), "4"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,3,1), "2"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,3,3), "6"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,3,8), "8"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,4,0), "6"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,4,3), "3"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,4,5), "5"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,4,8), "7"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,5,0), "9"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,5,5), "4"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,5,7), "3"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,6,5), "2"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,7,1), "1"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,7,2), "8"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,7,7), "2"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,7,8), "6"));

        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,8,1), "3"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,8,4), "6"));
        alg.selectIdentity(new PosValueIdentity(new KoorPosition(dim,8,6), "4"));

        alg.solve();
        System.out.println(alg.solutionTree);
        System.out.println(alg.solutionCount);


    }

    public static DancingLink ConstraintsOptionsTest(int rows,int columns,int rowBox, int colBox,int valueAmount) {
        rows--;
        columns--;
        String[] val = new String[valueAmount];
        for(int i=0;i<valueAmount;i++) val[i] = String.valueOf((char) ((i>8 ? i+7 : i) +'1'));

        List<Constrains> constrains = new LinkedList<>();
        constrains.add(new ValueInRangeConstrainOption('R','C',0,0,rows,columns,null));
        constrains.add(new ValueInRangeConstrainOption('R',0,rows,val));
        constrains.add(new ValueInRangeConstrainOption('C',0,columns,val));
        constrains.add(new ValueInGridConstrain('R','C',0,0,rows,columns, rowBox,colBox,val));

        List<Options> options = new LinkedList<>();
        options.add(new ValueInRangeConstrainOption('R','C',0,0,rows,columns,val));


        return new DancingLink(constrains,options,0);
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

    public static MainNode getMatrixExampleWithIdentityCheck() {
        MainNode root = new MainNode();
        root.addHeader(new HeaderNode(new HeaderIdentity("A"), 0));
        root.addHeader(new HeaderNode(new HeaderIdentity("B"), 0));
        root.addHeader(new HeaderNode(new HeaderIdentity("C"), 0));
        root.addHeader(new HeaderNode(new HeaderIdentity("D"), 0));
        root.addRowHeader(new HeaderIdentity("A"));
        root.addRowHeader(new HeaderIdentity("B"));
        root.addRowHeader(new HeaderIdentity("C"));
        root.addRowHeader(new HeaderIdentity("D"));
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

    public static MainNode getMatrixWithListAreaIdentity() {
        MainNode root = new MainNode();
        char[] dimension= {'R','C'};
        int[] val = {2,3};
        KoorPosition pos1 = new KoorPosition(dimension,val);
        int[] val2 = {2,4};
        KoorPosition pos2 = new KoorPosition(dimension,val2);
        char[] dimension1 = {'R'};
        int[] val3 = {2};
        KoorPosition pos3 = new KoorPosition(dimension1,val3);

        String[] colVal= {"2"};
        AreaValueIdentity equal = new AreaValueIdentity(pos1, colVal, AreaValueIdentity.Comparator.EQUAL);
        AreaValueIdentity bigger = new AreaValueIdentity(pos2, colVal, AreaValueIdentity.Comparator.BIGGER);
        List<AreaValueIdentity> areaIdentityList = new ArrayList<>();
        areaIdentityList.add(equal);
        areaIdentityList.add(bigger);
        ListAreaIdentity id = new ListAreaIdentity(areaIdentityList);
        root.addColumnHeader(new AreaValueIdentity(pos1,null, AreaValueIdentity.Comparator.EQUAL));
        root.addColumnHeader(new AreaValueIdentity(pos2,null, AreaValueIdentity.Comparator.EQUAL));
        root.addOptColHeader(id);
        root.addOptColHeader(new AreaValueIdentity(pos3,colVal, AreaValueIdentity.Comparator.SMALLER));

        KoorPosition[] positions = {pos1,pos2};
        for(KoorPosition e : positions) {
            for(int i=0; i<4;i++) {
                PosValueIdentity rowId = new PosValueIdentity(e,String.valueOf( (char) (i + '0')));
                System.out.println(rowId.name);
                root.addRowHeader(rowId);
            }
        }


        return root;
    }



}
