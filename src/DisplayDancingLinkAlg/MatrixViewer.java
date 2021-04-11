package DisplayDancingLinkAlg;

import DancingLinkAlg.*;
import javax.swing.*;

public class MatrixViewer {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame();
        MainNode root = Testing.getMatrixExample();
        HeaderManager manager = new HeaderManager(root);
        System.out.println(root.toString());

        manager.detachCascade((HeaderNode)root.headerNodeMapList.get(0).get("C"),1);
        JPanel myPanel = new MatrixViewerPanel(root);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setVisible(true);
    }


}

