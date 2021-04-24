package DancingLinkAlg;

public class PosValueIdentity extends HeaderIdentity{
    KoorPosition pos;
    char val;
    public PosValueIdentity(KoorPosition pos, char val) {
        super(pos.toString()+" | "+val);

        this.pos = pos;
        this.val = val;
    }


}
