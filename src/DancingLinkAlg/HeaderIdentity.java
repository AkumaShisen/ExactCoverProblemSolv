package DancingLinkAlg;

import java.util.Map;

public class HeaderIdentity implements Identity{


    public String name;
    public HeaderIdentity(String name) {
        this.name = name;
    }
    // sort of default
    public boolean match(Identity toCheck) {
        if(toCheck instanceof HeaderIdentity) return match((HeaderIdentity)toCheck);
        return false;
    }
    public boolean match(HeaderIdentity toCheck) {
        return this.name.equals(toCheck.name);
    }
    public String getName() {
        return name;
    }

}
