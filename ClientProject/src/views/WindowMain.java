package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


import models.IOConference;
import models.NodeTree;
import models.OptionsCoferences;
import presenters.Commands;

public class WindowMain extends JFrame{

    private static final long serialVersionUID = 1L;
    
    private static final Dimension SIZE = new Dimension(600,500);
    private static final String TITLE = "Conferences App";
    private static final Font FONT = new Font("Whitney",Font.PLAIN,14);

    
    private PanelMain panelMain;
    private PanelLogin panelLogin;
    private JPopupMenu popup;

    public WindowMain(ActionListener listener,MouseListener mouseListener){
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource(UtilitiesViews.LOGO_PATH)).getImage());
        setSize(SIZE);
        setLocationRelativeTo(null);

        panelLogin = new PanelLogin(listener);
        add(panelLogin);
        panelMain = new PanelMain(mouseListener);
        popup = new JPopupMenu();
        // add(panelMain);

        setVisible(true);
    }

    public void showMenu(){
        remove(panelLogin);
        add(panelMain);
        revalidate();
        repaint();
    }

    public void showTree(NodeTree<IOConference> root){
        panelMain.showTree(root);
    }

    public void showPopupPanel(OptionsCoferences[] options, ActionListener listener,Component component, int x, int y){
        popup.removeAll();
        for (int i = 0; i < options.length; i++) {
            JMenuItem option = new JMenuItem(options[i].getText());
            option.setFont(FONT);
            option.setBackground(Color.BLACK);
            option.setForeground(Color.WHITE);
            option.addActionListener(listener);
            option.setActionCommand(Commands.ACTION_NODE.toString());
            popup.add(option);
        }
        popup.show(component, x, y);
    }

    public String getUserNameLogin(){
        return panelLogin.getUserNameLogin();
    }

    public void addPermissions(HashMap<String,ImageIcon> permissionsMap){
        panelMain.addPermissions(permissionsMap);
    }
}
