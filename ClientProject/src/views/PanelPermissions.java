package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class PanelPermissions extends JPanel{

    private static final long serialVersionUID = 1L;

    private static final Color BACKGRAUND_COLOR = Color.decode("#006594");
    private static final Font FONT = new Font("Whitney",Font.BOLD,23);
    private static final String TITLE = "Permissions";
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 2, 5, 2);

    private JPanel panel;

    public PanelPermissions(){
        setPreferredSize(new Dimension(200,getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGRAUND_COLOR);
        addTitle();
        addPanel();
    }

    private void addTitle(){
        JLabel label = new JLabel(TITLE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(FONT);
        label.setForeground(Color.WHITE);
        add(label);
    }

    private void addPanel(){
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BORDER);
        JScrollPane scroll = new JScrollPane(panel);
        scroll.getViewport().setBackground(BACKGRAUND_COLOR);
        // scroll.getViewport().setOpaque(false);
        add(scroll);
    }

    public void addPermissions(HashMap<String,ImageIcon> permissions){
        panel.removeAll();
        panel.setLayout(new GridLayout(permissions.size(),1,0,5));
        for (String key : permissions.keySet()) {
            panel.add(new LabelPermission(key, permissions.get(key)));
        }
        panel.revalidate();
        panel.repaint();
    }
    
}
