package HeaderSpecifiers;

import DancingLinkAlg.MainNode;

import java.security.InvalidParameterException;

public class ValueInGridConstrain implements Constrains{
    Character val;
    char[] dimensions;
    int[] min;
    int[] max;
    int[] gridDim;

    public ValueInGridConstrain(char[] dimensions, int[] min, int[] max, int[] gridDim, Character val) {
        if(dimensions.length != min.length || dimensions.length != max.length)
            throw new InvalidParameterException("all 3 parameter arrays need to be of the same length");
        for(int i=0;i<dimensions.length;i++) {
            if(max[i]<min[i]) throw new InvalidParameterException("minimum is bigger than maximum for dimension "+dimensions[i]);
            if ((max[i] - min[i]+1) % gridDim[i] != 0)
                throw new InvalidParameterException("Grid cannot be spaced perfectly for dimension "+dimensions[i]);
        }
        this.dimensions = dimensions;
        this.min = min;
        this.max = max;
        this.val = val;
        this.gridDim = gridDim;
    }

    @Override
    public void addConstrainsToMatrix(MainNode root) {
        char[] value = {this.val};
        int[] current = new int[dimensions.length];
        int[] currentMax = new int[dimensions.length];
        for(int i=0;i<dimensions.length;i++) {
            current[i] = min[i];
            currentMax[i] = min[i] + gridDim[i];
        }

        getAllCellsInRange:
        while(true) {

            PositionInGrid pos = new KoorRectArea(dimensions,current, currentMax);
            root.addColumnHeader(new AreaValueIdentity(pos,value, AreaValueIdentity.Comparator.EQUAL));
            int i = 0;

            do {
                if(current[i]>max[i]) {
                    current[i] = min[i];
                    currentMax[i] = min[i] + gridDim[i];
                    i++;
                }
                if(i==dimensions.length) break getAllCellsInRange;
                current[i]+=gridDim[i];
                currentMax[i] += gridDim[i];
            } while(current[i]>max[i]);
        }
    }
}

