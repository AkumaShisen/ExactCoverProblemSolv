package DancingLinkAlg;

import java.util.List;

public class ListAreaIdentity extends HeaderIdentity{
    List<AreaValueIdentity> areaValues;
    public ListAreaIdentity(List<AreaValueIdentity> list) {
        super("");
        StringBuilder name= new StringBuilder();
        for(AreaValueIdentity id : list) name.append(id.name).append(" | ");
        this.name = name.toString();
        this.areaValues = list;
    }

    public boolean match(PosValueIdentity toCheck) {
        for(AreaValueIdentity e : areaValues) {
            if(e.match(toCheck)) return true;
        }
        return false;
    }
}
