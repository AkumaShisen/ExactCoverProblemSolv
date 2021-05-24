package HeaderSpecifiers;

import DancingLinkAlg.HeaderIdentity;
import DancingLinkAlg.Identity;

public class AreaConsequValueIdentity extends HeaderIdentity {
    PositionInGrid pos;
    int[][] boundaries = new int[2][2];
    String val;
    boolean negate;
    boolean inclusive;

    /**
     * similar to AreaValueIdentity, checks if PosValueIdentity is inside pos then compares the value of the PosValueIdentity
     * with the val of this identity, it uses the getAssociatedInt() method if the absolute difference of ranking is either between
     * upperMin/upperMax or lowerMin/lowerMax depending if PosValueIdentity is higher on the list than this identity val.
     * the boolean negate reverses the behaviour to whatever is outside those min and max values
     *
     * example: upperMin/upperMax/lowerMin/lowerMax = 1/1/2/3 , val = "3"
     * assuming the ranking starting at  0 going up 1, 2, 3 ....
     * this would match 4 (4-3==1, 1<=1<=1) and 1 (3-1==2, 1<=2<=2) and 2 (2-1==1, 1<=1<=2)
     * any other number wouldnt match
     *
     *
     * @param pos positionInGrid
     * @param upperMin if higher Minimum difference of ranking
     * @param upperMax if higher Maximum difference of ranking
     * @param lowerMin if lower Minimum difference of ranking
     * @param lowerMax if lower Maximum difference of ranking
     * @param negate boolean if it should negate the behaviour
     * @param inclusive boolean if it should match when the difference is the same as a limit
     * @param val value of this identity
     */
    public AreaConsequValueIdentity(PositionInGrid pos, int upperMin, int upperMax, int lowerMin, int lowerMax,
                                    boolean negate, boolean inclusive, String val) {
        super("");
        this.pos = pos;
        boundaries[0][0] = lowerMin;
        boundaries[0][1] = lowerMax;
        boundaries[1][0] = upperMin;
        boundaries[1][1] = upperMax;
        this.name = pos.toString() +" | "+boundaries[1][1]+":"+boundaries[1][0]+(negate?"!":"")+"~"+(inclusive?"*":"")+boundaries[0][0]+":"+boundaries[0][1]+" | "+val;
        this.val = val;
        this.negate = !negate;
        this.inclusive = !inclusive;
    }
    @Override
    public boolean match(Identity toCheck) {
        if(toCheck instanceof PosValueIdentity) return match((PosValueIdentity) toCheck);
        return false;
    }
    public boolean match(PosValueIdentity toCheck) {
        if (!this.pos.isInside(toCheck.pos)) return false;
        if (this.val == null) return true;

        int associateIntDiff = getAssociatedInt(toCheck.val)-getAssociatedInt(this.val);
        int upper = associateIntDiff<0 ? 0: 1;
        associateIntDiff *= 2*upper-1;

        return  (inclusive&&(boundaries[upper][1]==associateIntDiff || boundaries[upper][0]==associateIntDiff))||
                (negate && (boundaries[upper][1]<associateIntDiff || boundaries[upper][0]>associateIntDiff)) ||
                (!negate && (boundaries[upper][0]<associateIntDiff && boundaries[upper][1]>associateIntDiff)) ;
    }
}
