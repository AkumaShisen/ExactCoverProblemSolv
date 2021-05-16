package HeaderSpecifiers;

import java.util.*;

public class KoorPointVector implements PositionInGrid{
    KoorPosition point;
    KoorPosition vector;
    Set<Character> dimensions;
    public KoorPointVector(KoorPosition start, KoorPosition vector) {
        this.point = start;
        this.vector = vector;
        this.dimensions = new HashSet<>();
        for(char c : start.dimensions) {
            if(this.vector.dimensions.contains(c) && this.vector.get(c)!=0) {
                this.dimensions.add(c);
            }
        }
    }


    @Override
    public boolean isInside(KoorPosition toCheck) {
        if(dimensions.size() == 0) return true;
        Iterator<Character> it = dimensions.iterator();
        char c = it.next();
        float dist = (float) (toCheck.get(c) - point.get(c))/vector.get(c);
        while(it.hasNext()) {
            c = it.next();
            if((float)(toCheck.get(c) - point.get(c))/vector.get(c)!=dist) return false;
        }
        return true;
    }
}
