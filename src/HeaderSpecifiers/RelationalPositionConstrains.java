package HeaderSpecifiers;

import DancingLinkAlg.Identity;
import DancingLinkAlg.MainNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RelationalPositionConstrains implements Constrains{

    public static int EQUAL=0;
    public static int NOTEQUAL=5;
    public static int BIGGER = 1;
    public static int SMALLER_EQUAL=4;
    public static int SMALLER = 2;
    public static int BIGGER_EQUAL=3;



    PositionInGrid equal;
    PositionInGrid compare;
    int comparator;
    String[] val;

    public RelationalPositionConstrains(PositionInGrid toEqual, PositionInGrid toCompare, String[] val, int comparator){
        equal = toEqual;
        compare = toCompare;
        this.comparator = comparator;
        this.val = val;
    }
    /**
     * is always optional
     * @return itself
     */
    public RelationalPositionConstrains setOptional() {
        //is always optional
        return this;
    }

    public List<Identity> getIdentityList() {
        List<Identity> resultList = new LinkedList<>();
        for(String s : val) {
            List<Identity> list = new ArrayList<>();
            list.add(new AreaValueIdentity(equal,EQUAL,s));
            list.add(new AreaValueIdentity(compare,negate(comparator),s));
            resultList.add(new ListAreaIdentity(list));
        }
        return resultList;
    }

    @Override
    public void addConstrainsToMatrix(MainNode root) {
        for(Identity id : getIdentityList()) root.addOptColHeader(id);
    }

    public static int negate(int comparator) {
        if(comparator<0 || comparator>5) return 0;
        return 5-comparator;
    }
    public static String getComparatorString(int comparator) {
        if(comparator<0 || comparator>5) return "?";
        String[] stringArr = {"==",">","<",">=","<=","!="};
        return stringArr[comparator];
    }
}
