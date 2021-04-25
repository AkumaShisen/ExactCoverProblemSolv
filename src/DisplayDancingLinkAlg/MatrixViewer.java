package DisplayDancingLinkAlg;

import DancingLinkAlg.*;
import javax.swing.*;

public class MatrixViewer {
    public static void main(String[] args) {
        JFrame myFrame = new JFrame();
        //MainNode root = Testing.getMatrixExampleWithIdentityCheck();
        MainNode root = Testing.getMatrixWithListAreaIdentity();
        HeaderManager manager = new HeaderManager();
        System.out.println(root.toString());

        //manager.detachCascade((HeaderNode)root.headerNodeMapList.get(0).get("C"),1);
        JPanel myPanel = new MatrixViewerPanel(root);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }


}

