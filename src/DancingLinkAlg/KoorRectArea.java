package DancingLinkAlg;

import java.util.*;

public class KoorRectArea implements PositionInGrid{

    Map<Character, List<Integer>> koorPosition;
    Set<Character> dimensions;

    public KoorRectArea(char[] dimensions , int[] min, int[] max) {
        if(dimensions.length != min.length || dimensions.length != max.length)
            throw new IllegalArgumentException("all given arrays must be of same size");
        this.koorPosition = new HashMap<>();
        this.dimensions = new HashSet<>();
        for(int i=0; i<dimensions.length;i++) {
            List<Integer> range = new ArrayList<>();
            range.add(Integer.valueOf(min[i]));
            range.add(Integer.valueOf(max[i]));
            this.koorPosition.put(Character.valueOf(dimensions[i]), range);
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
            if( this.koorPosition.get(key).get(0).compareTo(toCheck.koorPosition.get(key)) >=0
                ||this.koorPosition.get(key).get(1).compareTo(toCheck.koorPosition.get(key)) <=0) return false;
        }
        return true;
    }
}
