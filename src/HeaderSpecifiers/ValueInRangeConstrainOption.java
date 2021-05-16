package HeaderSpecifiers;

import DancingLinkAlg.Identity;
import DancingLinkAlg.MainNode;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class ValueInRangeConstrainOption implements Constrains, Options{
    String[] val;
    char[] dimensions;
    int[] min;
    int[] max;

    /**
     * represents a range of Constraints/Options of KoorPosition and val
     * notice: if for example the main grid is 2d, but the KooPosition only has defined one dimension, that KoorPosition
     * represents a line inside the grid, if both dimensions are defined, then its a position in the grid
     * @param dimensions characterarray for each dimension
     * @param min at which number for a certain dimension to start at
     * @param max at which number for a certain dimension to stop at
     * @param val characterarray for values for each of which a separate constraint for each KoorPosition should be created
     */
    public ValueInRangeConstrainOption(char[] dimensions, int[] min, int[] max, String[] val) {
        setValues(dimensions,min,max,val);
    }
    public ValueInRangeConstrainOption(char dim1,char dim2,int min1, int min2, int max1, int max2, String[] val) {
        char[] dim = {dim1,dim2};
        int[] min = {min1,min2};
        int[] max = {max1,max2};
        setValues(dim,min,max,val);
    }
    public ValueInRangeConstrainOption(char dim1,int min1, int max1, String[] val) {
        char[] dim = {dim1};
        int[] min = {min1};
        int[] max = {max1};
        setValues(dim,min,max,val);
    }

    private void setValues(char[] dimensions, int[] min, int[] max,String[] val) {
        if(dimensions.length != min.length || dimensions.length != max.length)
            throw new InvalidParameterException("all 3 parameter arrays need to be of the same length");
        for(int i=0;i<dimensions.length;i++)
            if(max[i]<min[i]) throw new InvalidParameterException("minimum is bigger than maximum for dimension "+dimensions[i]);
        this.dimensions = dimensions;
        this.min = min;
        this.max = max;
        this.val = val;
    }

    public List<Identity> getIdentityList() {
        List<Identity> list = new LinkedList<>();
        int[] current = new int[dimensions.length];
        System.arraycopy(min, 0, current, 0, dimensions.length);

        getAllCellsInRange:
        while(true) {
            if(val == null) list.add(new PosValueIdentity(new KoorPosition(dimensions,current), null));
            else for(String c : val) list.add(new PosValueIdentity(new KoorPosition(dimensions,current), c));

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
        return list;
    }

    /**
     * creates all possible KoorPositions and for each value adds the PosValueIdentity with certain KoorPosition and value as option
     * @param root MainNode to add those options
     */
    @Override
    public void addOptionsToMatrix(MainNode root) {
        for(Identity id : getIdentityList()) root.addRowHeader(id);
    }

    /**
     * creates all possible KoorPositions and for each value adds the PosValueIdentity with certain KoorPosition and value as constraint
     * @param root MainNode to add those constraints
     */
    @Override
    public void addConstrainsToMatrix(MainNode root) {
        for(Identity id : getIdentityList()) root.addColumnHeader(id);
    }
}
