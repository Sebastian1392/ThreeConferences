package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelConventions extends JPanel{

    private static final long serialVersionUID = 1L;
    
    private static final Color BACKGRAUND_COLOR = Color.decode("#DE7123");
    private static final String[] ROUTES = {"/images/rootIcon.png","/images/topicIcon.png","/images/subtopicIcon.png",
    "/images/conferenceIcon.png","/images/lecturerIcon.png","/images/assistantIcon.png"};
    private static final String[] TEXTS = {"Root","Topic","Subtopic","Conference","Lecturer","Assistant"};
    private static final Font FONT = new Font("Whitney",Font.BOLD,15);


    public PanelConventions(){
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(10);
        setLayout(layout);
        setBackground(BACKGRAUND_COLOR);
        addConventions();
    }

    private void addConventions(){
        for (int i = 0; i < ROUTES.length; i++) {
            JLabel label = new JLabel(TEXTS[i]);
            label.setIcon(new ImageIcon(getClass().getResource(ROUTES[i])));
            label.setForeground(Color.WHITE);
            label.setFont(FONT);
            add(label);
        }
    }
    
}
