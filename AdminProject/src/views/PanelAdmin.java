package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PanelAdmin extends JPanel{
    
    private static final long serialVersionUID = 1L;
    
    // private static final Color BACKGRAUND_COLOR = Color.decode("#006594");
    private static final String ICON_PATH = "src/images/adminIcon.png";
    private static final Color BACKGRAUND_COLOR = Color.decode("#DE7123");
    private static final Border BORDER = BorderFactory.createEmptyBorder(15, 15, 15, 15);
    private static final Border BORDER_LABEL = BorderFactory.createEmptyBorder(0, 0, 20, 0);
    private static final Font FONT = new Font("Whitney",Font.BOLD,25);
    private static final Font FONT_ICON = new Font("Whitney",Font.BOLD,18);
    private static final String TITLE = "Admin";

    public PanelAdmin(String adminName){
        setPreferredSize(new Dimension(200,getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BORDER);
        setBackground(BACKGRAUND_COLOR);
        addTitle();
        addIcon(adminName);
    }

    public void addTitle(){
        JLabel label = new JLabel(TITLE);
        label.setBorder(BORDER_LABEL);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(FONT);
        label.setForeground(Color.WHITE);
        add(label);
    }

    public void addIcon(String adminName){
        JLabel label = new JLabel(adminName);
        label.setIcon(UtilitiesViews.getImage(ICON_PATH, 90, 90));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setFont(FONT_ICON);
        label.setForeground(Color.WHITE);
        add(label);
    }
    
}
