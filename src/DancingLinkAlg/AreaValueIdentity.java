package DancingLinkAlg;

import java.util.Map;

public class AreaValueIdentity extends HeaderIdentity{
    // this class rn equal to "check pos, equal match val", could be generalized to having variable matching
    // method of match, equal, not equal, bigger, smaller match, need static list for ranking, if that variable
    // is null, use default ranking (use integer value of characters)

    enum Comparator {
        EQUAL,
        NOTEQUAL,
        BIGGEREQUAL,
        BIGGER,
        SMALLEREQUAL,
        SMALLER
    }
    private static Map<Character,Integer> characterRanking;
    Comparator comparator;
    PositionInGrid pos;
    char[] val;


    public AreaValueIdentity(PositionInGrid pos, char[] val, Comparator comparator) {
        super(pos.toString() +" | "+(val==null ? "*": val.toString()));

        this.pos = pos;
        this.val = val;
        this.comparator = comparator;
    }

    //first checks if point from toCheck is inside area from this, then checks if toCheck.val is inside this.val
    public boolean match(PosValueIdentity toCheck) {
        if (!this.pos.isInside(toCheck.pos)) return false;
        if (this.val == null) return true;

        switch(this.comparator) {
            case EQUAL:
                for (char e : this.val) {
                    if (e == toCheck.val) return true;
                }
                return false;
            case NOTEQUAL:
                for (char e : this.val) {
                    if (e == toCheck.val) return false;
                }
                return true;
            case BIGGER:
                return getAssociatedInt(toCheck.val)>getAssociatedInt(this.val[0]);
            case SMALLER:
                return getAssociatedInt(toCheck.val)<getAssociatedInt(this.val[0]);
            case BIGGEREQUAL:
                return getAssociatedInt(toCheck.val)>=getAssociatedInt(this.val[0]);
            case SMALLEREQUAL:
                return getAssociatedInt(toCheck.val)<=getAssociatedInt(this.val[0]);
            default:
                return false;
        }
    }

    public static void setCharacterRanking(Map<Character,Integer> characterRanking) {
        AreaValueIdentity.characterRanking = characterRanking;
    }
    public static int getAssociatedInt(char c) {
        if(characterRanking == null || !characterRanking.containsKey(c)) return (int) c;
        else return characterRanking.get(c);
    }
}
