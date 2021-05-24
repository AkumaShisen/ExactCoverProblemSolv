package HeaderSpecifiers;

import DancingLinkAlg.HeaderIdentity;
import DancingLinkAlg.Identity;


public class PosValueIdentity extends HeaderIdentity {
    public KoorPosition pos;
    public String val;
    public PosValueIdentity(KoorPosition pos,String val) {
        super(pos.toString()+" | "+val);

        this.pos = pos;
        this.val = val;
    }
    public boolean match(Identity toCheck) {
        if(toCheck instanceof PosValueIdentity) return this.match((PosValueIdentity)toCheck);
        return false;
    }

    public boolean match(PosValueIdentity toCheck) {
        return this.pos.isInside(toCheck.pos) && (this.val==null || this.val.equals(toCheck.val));
    }
    public String toString() {
        return name;

    }


}
