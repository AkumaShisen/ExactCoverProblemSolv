package HeaderSpecifiers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KoorPosition implements PositionInGrid {
    Map<Character, Integer> koorPosition;
    Set<Character> dimensions;

    public KoorPosition(char[] dimensions , int[] val) {
        setValues(dimensions,val);
    }
    public KoorPosition(char[] dim, int val1, int val2) {
        int[] val = {val1,val2};
        setValues(dim,val);
    }

    public KoorPosition(char dim1, char dim2, int val1, int val2) {
        char[] dim ={dim1,dim2};
        int[] val = {val1,val2};
        setValues(dim,val);
    }
    private void setValues(char[] dimensions, int[] val) {
        if(dimensions.length != val.length) throw new IllegalArgumentException("both given arrays must be of same size");
        this.koorPosition = new HashMap<>();
        this.dimensions = new HashSet<>();
        for(int i=0; i<dimensions.length;i++) {
            this.koorPosition.put(dimensions[i], val[i]);
            this.dimensions.add(dimensions[i]);
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
    public Integer get(Character c) {
        return koorPosition.get(c);
    }
    public KoorPosition copy() {
        int len = dimensions.size();
        char[] dim = new char[len];
        int[] val = new int[len];
        int i=0;
        for(char c : dimensions) {
            dim[i] = c;
            val[i] = get(c);
            i++;
        }
        return new KoorPosition(dim,val);
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Character c : koorPosition.keySet()) {
            result.append(c).append(":").append(koorPosition.get(c)).append("|");
        }
        return result.toString();
    }
}
