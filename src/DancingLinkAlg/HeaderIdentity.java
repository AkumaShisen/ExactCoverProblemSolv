package DancingLinkAlg;

import java.util.Map;

public class HeaderIdentity implements Identity{


    String name;
    public HeaderIdentity(String name) {
        this.name = name;
    }
    // sort of default
    public boolean match(Identity toCheck) {
        return false;
    }
    public String getName() {
        return name;
    }

}
