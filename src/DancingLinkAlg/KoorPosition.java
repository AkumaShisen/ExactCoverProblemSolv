package DancingLinkAlg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KoorPosition implements PositionInGrid{
    Map<Character, Integer> koorPosition;
    Set<Character> dimensions;

    public KoorPosition(char[] dimensions , int[] val) {
        if(dimensions.length != val.length) throw new IllegalArgumentException("both given arrays must be of same size");
        this.koorPosition = new HashMap<>();
        this.dimensions = new HashSet<>();
        for(int i=0; i<dimensions.length;i++) {
            this.koorPosition.put(Character.valueOf(dimensions[i]), Integer.valueOf(val[i]));
            this.dimensions.add(Character.valueOf(dimensions[i]));
        }
    }
    public boolean hasDimension(Character dimension) {
        return this.dimensions.contains(dimension);
    }

    @Override
    public boolean isInside(KoorPosition toCheck) {
        for(Character key : this.dimensions) {
            if(!toCheck.hasDimension(key)) return false;
            if(! this.koorPosition.get(key).equals(toCheck.koorPosition.get(key))) return false;
        }
        return true;
    }
}
