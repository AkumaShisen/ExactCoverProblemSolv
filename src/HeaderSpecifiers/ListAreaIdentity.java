package HeaderSpecifiers;

import DancingLinkAlg.HeaderIdentity;
import DancingLinkAlg.Identity;

import java.util.List;

public class ListAreaIdentity extends HeaderIdentity {
    List<Identity> areaValues;
    public ListAreaIdentity(List<Identity> list) {
        super("");
        StringBuilder name= new StringBuilder();
        for(Identity id : list) name.append(id.getName()).append(" | ");
        this.name = name.toString();
        this.areaValues = list;
    }
    @Override
    public boolean match(Identity toCheck) {
        if(toCheck instanceof PosValueIdentity) return match((PosValueIdentity) toCheck);
        return false;
    }

    public boolean match(PosValueIdentity toCheck) {
        for(Identity e : areaValues) {
            if(e.match(toCheck)) return true;
        }
        return false;
    }
}
