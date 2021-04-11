package DisplayDancingLinkAlg;

import DancingLinkAlg.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MatrixViewerPanel extends JPanel {
    MatrixViewerPanel(MainNode root) {
        setLayout(new GridBagLayout());

        int xGrid=1;
        int yGrid=0;
        Node itCol = root.get(0);
        addInfoPanelForNode(root, 0,yGrid);

        while(!(itCol instanceof MainNode)) {
            addInfoPanelForNode(itCol, xGrid,yGrid);
            xGrid++;
            itCol = itCol.get(0);
        }
        Node itRow = root.get(1);

        while(!(itRow instanceof MainNode)) {
            itCol = root.get(0);
            xGrid = 1;
            yGrid++;
            addInfoPanelForNode(itRow,0,yGrid);
            SparseNode nextSparseNode = itRow.get(0) instanceof SparseNode ? (SparseNode) itRow.get(0) : null;
            while(!(itCol instanceof MainNode)) {
                if(nextSparseNode == null) break;
                if(nextSparseNode.getCol() == itCol) {
                    addInfoPanelForNode(nextSparseNode, xGrid,yGrid);
                    nextSparseNode = nextSparseNode.get(0) instanceof SparseNode ? (SparseNode) nextSparseNode.get(0) : null;
                }
                xGrid++;
                itCol = itCol.get(0);
            }
            itRow = itRow.get(1);
        }


    }
    public void addInfoPanelForNode(Node node, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(5,5,10,10);

        String text = node.getIdentity() +"\n\n"+node.getNeightbourInfo();
        JTextArea textArea = new JTextArea();
        textArea.append(text);
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(100,200,0));
        textArea.setBorder(new EmptyBorder(10,10,10,10));

        add(textArea,c);
    }


}
