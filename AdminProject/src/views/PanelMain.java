package views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import models.NodeTree;

public class PanelMain extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private DefaultMutableTreeNode graphicRoot;
    private DefaultTreeModel treeModel;
    private JTree graphicTree;
    private JPanel panelCenter;
    private PanelConventions conventions;
    
    public PanelMain(ActionListener listener){
        setLayout(new BorderLayout());
        setOpaque(false);
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.setOpaque(false);
        add(panelCenter,BorderLayout.CENTER);
        addHeader(listener);
        treeModel = new DefaultTreeModel(graphicRoot);
        graphicTree = new JTree(treeModel);
        panelCenter.add(new JScrollPane(graphicTree) ,BorderLayout.CENTER);
        addConventions();
    }   

    private void addHeader(ActionListener listener) {
        add(new PanelHeader(listener),BorderLayout.PAGE_START);
    }

    public <T> void showTree(NodeTree<T> root){
        graphicRoot = new DefaultMutableTreeNode(root.getData());
        printTree(graphicRoot,root);
        treeModel.setRoot(graphicRoot);
        graphicTree.setCellRenderer(new MyTreeCellRenderer());
        expandTree();
        revalidate();
        repaint();
    }

    private <T> void printTree(DefaultMutableTreeNode graphicBase, NodeTree<T> base) {
        for (NodeTree<T> node : base.getChildren()) {
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

    public void addConventions(){
        conventions = new PanelConventions();
        panelCenter.add(conventions,BorderLayout.EAST);
    }

    public void showConferenceConventions(){
        conventions.addConventionsConferences();
    }

    public void showPermissionConventions(){
        conventions.addConventionsPermissions();
    }

    public void addPanelAdmin(String adminName){
        add(new PanelAdmin(adminName),BorderLayout.WEST);
    }
}
