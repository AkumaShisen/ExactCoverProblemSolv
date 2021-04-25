package HeaderSpecifiers;

import DancingLinkAlg.HeaderIdentity;
import DancingLinkAlg.Identity;


public class PosValueIdentity extends HeaderIdentity {
    KoorPosition pos;
    Character val;
    public PosValueIdentity(KoorPosition pos,Character val) {
        super(pos.toString()+" | "+val);

        this.pos = pos;
        this.val = val;
    }
    public boolean match(Identity toCheck) {
        if(toCheck instanceof PosValueIdentity) return this.match((PosValueIdentity)toCheck);
        return false;
    }

    public boolean match(PosValueIdentity toCheck) {
        return this.pos.isInside(toCheck.pos) && (this.val==null || this.val == toCheck.val);
    }


}
