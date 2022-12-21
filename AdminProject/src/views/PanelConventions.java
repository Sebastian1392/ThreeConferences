package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelConventions extends JPanel{

    private static final long serialVersionUID = 1L;
    
    private static final Color BACKGRAUND_COLOR = Color.decode("#006594");
    private static final String[] ROUTES_CONFERENCES = {"src/images/rootConferences.png",
    "src/images/topicConferences.png","src/images/subtopicConference.png","src/images/conferenceConference.png",
    "src/images/lecturerConference.png","src/images/assistantConference.png"};
    private static final String[] TEXTS_CONFERENCES = {"Root","Topic","Subtopic","Conference","Lecturer","Assistant"};
    private static final String[] ROUTES_PERMISSIONS = {"src/images/key.png","src/images/user.png",
    "src/images/topicConferences.png","src/images/permission.png"};
    private static final String[] TEXTS_PERMISSIONS = {"Root","User","Conference","Pemission"};
    private static final Font FONT = new Font("Whitney",Font.BOLD,15);
    private static final int WIDTH = 50;
    private static final int HEIGTH = 50;
    private static final Border BORDER = BorderFactory.createEmptyBorder(5, 0, 5, 10);


    public PanelConventions(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGRAUND_COLOR);
    }

    public void addConventionsConferences(){
        removeAll();
        for (int i = 0; i < ROUTES_CONFERENCES.length; i++) {
            JLabel label = new JLabel(TEXTS_CONFERENCES[i]);
            label.setBorder(BORDER);
            label.setIcon(UtilitiesViews.getImage(ROUTES_CONFERENCES[i], WIDTH, HEIGTH));
            label.setForeground(Color.WHITE);
            label.setFont(FONT);
            add(label);
        }
        revalidate();
        repaint();
    }

    public void addConventionsPermissions(){
        removeAll();
        for (int i = 0; i < ROUTES_PERMISSIONS.length; i++) {
            JLabel label = new JLabel(TEXTS_PERMISSIONS[i]);
            label.setBorder(BORDER);
            label.setIcon(UtilitiesViews.getImage(ROUTES_PERMISSIONS[i], WIDTH, HEIGTH));
            label.setForeground(Color.WHITE);
            label.setFont(FONT);
            add(label);
        }
        revalidate();
        repaint();
    }
    
}
