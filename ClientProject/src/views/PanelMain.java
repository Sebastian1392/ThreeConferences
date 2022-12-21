package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import models.IOConference;
import models.NodeTree;

public class PanelMain extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private static final String TITLE_HEADER = "Conferences App";
    public static final String LOGO_PATH = "src/images/logo.png";

    private DefaultMutableTreeNode graphicRoot;
    private DefaultTreeModel treeModel;
    private JTree graphicTree;
    private JPanel panelCenter;
    private PanelPermissions permissions;
    
    public PanelMain(MouseListener mouseListener){
        setLayout(new BorderLayout());
        setOpaque(false);
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.setOpaque(false);
        add(panelCenter,BorderLayout.CENTER);
        addHeader();
        treeModel = new DefaultTreeModel(graphicRoot);
        graphicTree = new JTree(treeModel);
        graphicTree.addMouseListener(mouseListener);
        panelCenter.add(new JScrollPane(graphicTree) ,BorderLayout.CENTER);
        addPanelPermissions();
    }

    private void addHeader(){
        JLabel header = new JLabel(UtilitiesViews.getImage(LOGO_PATH, 110, 100));
        header.setForeground(Color.WHITE);
        header.setHorizontalTextPosition(SwingConstants.RIGHT);
        header.setVerticalTextPosition(SwingConstants.CENTER);
        header.setFont(UtilitiesViews.TITLE_FONT);
        header.setText(TITLE_HEADER);
        header.setOpaque(true);
        header.setBackground(UtilitiesViews.BLUE_COLOR);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        add(header,BorderLayout.PAGE_START);
        panelCenter.add(new PanelConventions(),BorderLayout.NORTH);
    }

    public void showTree(NodeTree<IOConference> root){
        graphicRoot = new DefaultMutableTreeNode(root.getData());
        printTree(graphicRoot,root);
        treeModel.setRoot(graphicRoot);
        graphicTree.setCellRenderer(new MyTreeCellRenderer());
        expandTree();
        revalidate();
        repaint();
    }

    private void printTree(DefaultMutableTreeNode graphicBase, NodeTree<IOConference> base) {
        for (NodeTree<IOConference> node : base.getChildren()) {
            DefaultMutableTreeNode actual = new DefaultMutableTreeNode(node.getData());
            graphicBase.add(actual);
            printTree(actual,node);
        }
    }

    public void expandTree() {
        expandAllNodes(graphicTree, 0, graphicTree.getRowCount());
    }

    private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }

    private void addPanelPermissions(){
        permissions = new PanelPermissions();
        panelCenter.add(permissions,BorderLayout.EAST);
    }

    public void addPermissions(HashMap<String,ImageIcon> permissionsMap){
        permissions.addPermissions(permissionsMap);
    }
}
