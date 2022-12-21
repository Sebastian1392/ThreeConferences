package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import models.IOConference;
import models.IPermission;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer{
    
    private static final long serialVersionUID = 1L;

    private static final Font FONT = new Font("Whitney",Font.PLAIN,16);
    private static final Font FONT_BOLD = new Font("Whitney",Font.BOLD,16);
    private static final Color COLOR_GREEN = Color.decode("#249210");

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object node, boolean selected, boolean expanded,
    boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, node, selected,expanded, leaf, row, hasFocus);
        tree.setRowHeight(40);
        setOpaque(true);     
        setFont(FONT);
        setForeground(Color.BLACK);
        if( selected ){
            setForeground(COLOR_GREEN);        
            setFont(FONT_BOLD);
        }
        if( ((DefaultMutableTreeNode) node).getUserObject() instanceof IOConference){
            setIcon(((IOConference)((DefaultMutableTreeNode)node).getUserObject()).getIcon());                       
        }else{
            setIcon(((IPermission)((DefaultMutableTreeNode)node).getUserObject()).getImage());                       
        }
        return this;
    }
    
}
