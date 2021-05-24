package HeaderSpecifiers;

import DancingLinkAlg.HeaderIdentity;
import DancingLinkAlg.Identity;


import java.util.Map;

import static HeaderSpecifiers.RelationalPositionConstrains.getComparatorString;

public class AreaValueIdentity extends HeaderIdentity {
    // this class rn equal to "check pos, equal match val", could be generalized to having variable matching
    // method of match, equal, not equal, bigger, smaller match, need static list for ranking, if that variable
    // is null, use default ranking (use integer value of characters

    int comparator;
    PositionInGrid pos;
    String[] val;


    public AreaValueIdentity(PositionInGrid pos, String[] val, int comparator) {
        super("");
        StringBuilder name = new StringBuilder(pos.toString() +" | "+getComparatorString(comparator)+" | ");
        if(val==null) name.append("*");
        else for (String c : val) name.append(c).append("|");
        this.name = name.toString();

        this.pos = pos;
        this.val = val == null? null : val.clone();
        this.comparator = comparator;
    }

    public AreaValueIdentity(PositionInGrid pos, int comparator, String val) {
        super("");
        StringBuilder name = new StringBuilder(pos.toString() +" | "+getComparatorString(comparator)+" | ");
        if(val==null) name.append("*");
        else name.append(val);
        this.name = name.toString();

        this.pos = pos;
        String[] valArr= {val};
        this.val = val == null? null : valArr;
        this.comparator = comparator;
    }

    @Override
    public boolean match(Identity toCheck) {
        if(toCheck instanceof PosValueIdentity) return match((PosValueIdentity) toCheck);
        return false;
    }

    //first checks if point from toCheck is inside area from this, then checks if toCheck.val is inside this.val
    public boolean match(PosValueIdentity toCheck) {
        if (!this.pos.isInside(toCheck.pos)) return false;
        if (this.val == null) return true;

        switch (this.comparator) {
            case 0:
                for (String e : this.val) {
                    if (e.equals(toCheck.val)) return true;
                }
                return false;
            case 5:
                for (String e : this.val) {
                    if (e.equals(toCheck.val)) return false;
                }
                return true;
            case 1:
                return getAssociatedInt(toCheck.val) > getAssociatedInt(this.val[0]);
            case 2:
                return getAssociatedInt(toCheck.val) < getAssociatedInt(this.val[0]);
            case 3:
                return getAssociatedInt(toCheck.val) >= getAssociatedInt(this.val[0]);
            case 4:
                return getAssociatedInt(toCheck.val) <= getAssociatedInt(this.val[0]);
            default:
                return false;
        }
    }
}
