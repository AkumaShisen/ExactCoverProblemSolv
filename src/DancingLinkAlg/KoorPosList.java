package DancingLinkAlg;

import java.util.*;

public class KoorPosList implements PositionInGrid{
    List<PositionInGrid> koorPosList;

    public KoorPosList(List<PositionInGrid> list) {
        this.koorPosList = list;
    }

    @Override
    public boolean isInside(KoorPosition toCheck) {
        for(PositionInGrid pos : this.koorPosList) if(pos.isInside(toCheck)) return true;
        return false;
    }
}
