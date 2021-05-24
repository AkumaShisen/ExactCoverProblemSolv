package HeaderSpecifiers;

import DancingLinkAlg.Identity;
import DancingLinkAlg.MainNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsequPositionConstrains implements Constrains{

    PositionInGrid equal;
    PositionInGrid compare;
    String[] val;
    int upperMin;
    int upperMax;
    int lowerMax;
    int lowerMin;
    boolean inclusive;
    boolean negate;

    public ConsequPositionConstrains(PositionInGrid toEqual, PositionInGrid toCompare,String[] val,
                                     int upperMax, int upperMin, int lowerMin, int lowerMax,
                                     boolean inclusive, boolean negate){
        equal = toEqual;
        compare = toCompare;
        this.val = val;
        this.upperMax = upperMax;
        this.upperMin = upperMin;
        this.lowerMin = lowerMin;
        this.lowerMax = lowerMax;
        this.inclusive = inclusive;
        this.negate = negate;
    }

    public ConsequPositionConstrains(PositionInGrid toEqual, PositionInGrid toCompare,String[] val,
                                     int diff) {
        this(toEqual,toCompare,val,diff,diff,diff,diff,true,false);
    }
    public ConsequPositionConstrains(PositionInGrid toEqual, PositionInGrid toCompare,String[] val,
                                     int upperMax, int upperMin, int lowerMin, int lowerMax){
        this(toEqual,toCompare,val,upperMax,upperMin,lowerMin,lowerMax,true,false);
    }

    public ConsequPositionConstrains(PositionInGrid toEqual, PositionInGrid toCompare,String[] val,
                                     int max,int min) {
        this(toEqual,toCompare,val,max,min,min,max,true,false);
    }

    /**
     * is always optional
     * @return itself
     */
    public ConsequPositionConstrains setOptional() {
        //is always optional
        return this;
    }

    public List<Identity> getIdentityList() {
        List<Identity> resultList= new LinkedList<>();
        for(String s : val) {
            List<Identity> list = new ArrayList<>();
            list.add(new AreaValueIdentity(equal,RelationalPositionConstrains.EQUAL,s));
            list.add(new AreaConsequValueIdentity(compare,upperMin,upperMax,lowerMin,lowerMax,negate,inclusive,s));
            resultList.add(new ListAreaIdentity(list));
        }
        return resultList;
    }


    @Override
    public void addConstrainsToMatrix(MainNode root) {
        for(Identity id : getIdentityList()) root.addOptColHeader(id);
    }
}
