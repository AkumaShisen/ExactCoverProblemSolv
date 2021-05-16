package HeaderSpecifiers;

import DancingLinkAlg.Identity;
import DancingLinkAlg.MainNode;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class ValueAtPositionOption implements Options{
    char[] dim;
    int[] pos;
    String val;
    public ValueAtPositionOption(char[] dim,int[] pos,String val) {
        setValues(dim,pos,val);
    }
    public ValueAtPositionOption(char dim1,char dim2,int pos1,int pos2,String val){
        char[] dim = {dim1,dim2};
        int[] pos = {pos1,pos2};
        setValues(dim,pos,val);
    }

    private void setValues(char[] dim, int[] pos, String val) {
        if(dim.length != pos.length)
            throw new InvalidParameterException("both parameter arrays need to be of the same length");
        this.dim = dim;
        this.pos = pos;
        this.val = val;
    }
    public List<Identity> getIdentityList() {
        List<Identity> list = new LinkedList<>();
        list.add(new PosValueIdentity(new KoorPosition(dim,pos), val));
        return list;
    }
    @Override
    public void addOptionsToMatrix(MainNode root) {
        for(Identity id : getIdentityList()) root.addColumnHeader(id);
    }
}
