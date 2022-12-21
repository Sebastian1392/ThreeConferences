package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presenters.Commands;

public class PanelHeader extends JPanel{

    private static final long serialVersionUID = 1L;
    
    private static final String TEXT_PERMISSIONS = "Permissions";
    private static final String TEXT_CONFERENCES = "Conferences";
    private static final String TITLE_HEADER = "Conferences App.Admin";
    public static final String LOGO_PATH = "src/images/logo.png";
    private static final int HEIGTH_BUTTONS = 90;
    private static final int WIDTH_BUTTONS = 200;

    public PanelHeader(ActionListener listener){
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(50);
        setLayout(layout);
        setBackground(UtilitiesViews.BLUE_COLOR);
        addLogo();
        addButtonsMenu(listener);
    }

    private void addLogo(){
        JLabel header = new JLabel(UtilitiesViews.getImage(LOGO_PATH, 150, 150));
        header.setForeground(Color.WHITE);
        header.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setVerticalTextPosition(SwingConstants.BOTTOM);
        header.setFont(UtilitiesViews.TITLE_FONT);
        header.setText(TITLE_HEADER);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        add(header);
    }

    private void addButtonsMenu(ActionListener listener) {
        ButtonActions conferences = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, TEXT_CONFERENCES, Color.WHITE, 
        UtilitiesViews.BLUE_COLOR);
        conferences.addActionListener(listener);
        conferences.setActionCommand(Commands.SHOW_CONFERENCES_TREE.toString());
        add(conferences);
        ButtonActions permissions = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, TEXT_PERMISSIONS, Color.WHITE, 
        UtilitiesViews.BLUE_COLOR);
        permissions.addActionListener(listener);
        permissions.setActionCommand(Commands.SHOW_PERMISSIONS_TREE.toString());
        add(permissions);
    }

    
}
