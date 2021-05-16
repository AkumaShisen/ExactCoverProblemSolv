package HeaderSpecifiers;

import DancingLinkAlg.MainNode;

import java.security.InvalidParameterException;

public class ValueInGridConstrain implements Constrains{
    String[] val;
    char[] dimensions;
    int[] min;
    int[] max;
    int[] gridDim;

    /**
     * represents a range of constraints inside a grid for each box with each possible value
     *
     * @param dimensions characterarray for each dimension
     * @param min at which number for a certain dimension to start at
     * @param max at which number for a certain dimension to stop at
     * @param gridDim width of the box in a certain dimension
     * @param val characterarray for values for each of which a separate constraint for each box should be created
     */
    public ValueInGridConstrain(char[] dimensions, int[] min, int[] max, int[] gridDim, String[] val) {
        setValues(dimensions,min,max,gridDim,val);
    }

    public ValueInGridConstrain(char dim1,char dim2, int min1,int min2, int max1, int max2, int gridDim1, int gridDim2, String[] val) {
        char[] dim = {dim1,dim2};
        int[] min = {min1,min2};
        int[] max = {max1,max2};
        int[] gridDim = {gridDim1,gridDim2};
        setValues(dim,min,max,gridDim,val);
    }
    private void setValues(char[] dimensions,int[] min, int[] max, int[] gridDim, String[] val) {
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

    /**
     * creates all possible KoorRectArea inside the grid with set widths for all dimensions and
     * adds for each value a AreaValueIdentity with KoorRectArea, one value and EQUAL comparator as Constrain
     * @param root MainNode to add those options
     */
    @Override
    public void addConstrainsToMatrix(MainNode root) {
        String[] value = new String[1];
        int[] current = new int[dimensions.length];
        int[] currentMax = new int[dimensions.length];
        for(int i=0;i<dimensions.length;i++) {
            current[i] = min[i];
            currentMax[i] = min[i] + gridDim[i]-1;
        }

        getAllCellsInRange:
        while(true) {

            PositionInGrid pos = new KoorRectArea(dimensions,current, currentMax);
            for (String s : val) {
                value[0] = s;
                root.addColumnHeader(new AreaValueIdentity(pos, value, AreaValueIdentity.Comparator.EQUAL));
            }
            int i = 0;

            do {
                if(current[i]>max[i]) {
                    current[i] = min[i];
                    currentMax[i] = min[i] + gridDim[i]-1;
                    i++;
                }
                if(i==dimensions.length) break getAllCellsInRange;
                current[i]+=gridDim[i];
                currentMax[i] += gridDim[i];
            } while(current[i]>max[i]);
        }
    }
}

