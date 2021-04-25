package HeaderSpecifiers;

import DancingLinkAlg.MainNode;

import java.security.InvalidParameterException;

public class ValueInRangeConstrainOption implements Constrains, Options{
    char[] val;
    char[] dimensions;
    int[] min;
    int[] max;

    public ValueInRangeConstrainOption(char[] dimensions, int[] min, int[] max, char[] val) {
        if(dimensions.length != min.length || dimensions.length != max.length)
            throw new InvalidParameterException("all 3 parameter arrays need to be of the same length");
        for(int i=0;i<dimensions.length;i++)
            if(max[i]<min[i]) throw new InvalidParameterException("minimum is bigger than maximum for dimension "+dimensions[i]);
        this.dimensions = dimensions;
        this.min = min;
        this.max = max;
        this.val = val;
    }

    @Override
    public void addOptionsToMatrix(MainNode root) {
        int[] current = new int[dimensions.length];
        System.arraycopy(min, 0, current, 0, dimensions.length);

        getAllCellsInRange:
        while(true) {
            for(char c : val) root.addRowHeader(new PosValueIdentity(new KoorPosition(dimensions,current), c));
            int i = 0;

            do {
                if(current[i]>max[i]) {
                    current[i] = min[i];
                    i++;
                }
                if(i==dimensions.length) break getAllCellsInRange;
                current[i]++;
            } while(current[i]>max[i]);
        }
    }

    @Override
    public void addConstrainsToMatrix(MainNode root) {
        int[] current = new int[dimensions.length];
        System.arraycopy(min, 0, current, 0, dimensions.length);

        getAllCellsInRange:
        while(true) {
            if(val == null) root.addColumnHeader(new PosValueIdentity(new KoorPosition(dimensions,current), null));
            else for(char c : val) root.addColumnHeader(new PosValueIdentity(new KoorPosition(dimensions,current), c));

            int i = 0;

            do {
                if(current[i]>max[i]) {
                    current[i] = min[i];
                    i++;
                }
                if(i==dimensions.length) break getAllCellsInRange;
                current[i]++;
            } while(current[i]>max[i]);
        }
    }
}
