package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import models.OptionsPass;

public class DialogPermission extends JDialog{
    
    private static final long serialVersionUID = 1L;
    
    private static final String HTML_TAG = "<html>";
    private static final String NO_ALL = HTML_TAG + "No All";
    private static final String YES_ALL = HTML_TAG + "Yes All";
    private static final String ICON_PATH = "/images/key.png";
    private static final String EMPTY = " ";
    private static final String COMPLEMENT = " is requesting permission on the ";
    private static final Border BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    private static final String TITLE = "Permission Notification";
    private static final Dimension SIZE = new Dimension(500,400);
    private static final Font FONT = new Font("Whitney",Font.BOLD,15);
    private static final Font TITLE_FONT = new Font("Whitney",Font.BOLD,23);
    private static final int WIDTH_BUTTONS = 200;
    private static final int HEIGTH_BUTTONS = 50;
    private static final Color ORANGE_COLOR = Color.decode("#DE7123");

    private JPanel panel;
    private ArrayList<PanelButtons> panelList;

    public DialogPermission(Component component){
        setTitle(TITLE);
        setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
        setSize(SIZE);
        setLocationRelativeTo(component);
        setResizable(false);
        setModal(true);
        addJPanel();
    }

    private void addJPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BORDER);
        panel.setBackground(UtilitiesViews.BLUE_COLOR);
        add(panel);
    }

    private void addTitle(){
        JLabel label = new JLabel(TITLE);
        label.setFont(TITLE_FONT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);
        panel.add(label);
    }

    public void addComponents(ActionListener listener, String userName,String typeNode,
    String nodeName){
        panel.removeAll();
        addTitle();
        addText(userName, typeNode, nodeName);
        addPanelButtons(listener,userName);
        // addButtons(listener);
        panel.revalidate();
        panel.repaint();
        setVisible(true);
    }

    private void addText(String userName, String typeNode, String nodeName) {
        JLabel label = new JLabel(HTML_TAG + userName + COMPLEMENT + nodeName + EMPTY + typeNode);
        label.setFont(FONT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);
        panel.add(label);
    }

    private void addPanelButtons(ActionListener listener,String fatherName) {
        panelList = new ArrayList<>();
        for (OptionsPass option : OptionsPass.values()) {
            PanelButtons panelButtons = new PanelButtons(listener,option.getText(),fatherName);
            panelButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelList.add(panelButtons);
        }
        addPanels();
    }

    private void addPanels(){
        for (PanelButtons panelButtons : panelList) {
            panel.add(panelButtons);
        }
    }

    public void addButtons(ActionListener listener) {
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setOpaque(false);
        ButtonActions yesAll = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, YES_ALL, ORANGE_COLOR, Color.WHITE);
        panelButtons.add(yesAll);
        ButtonActions noAll = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, NO_ALL, ORANGE_COLOR, Color.WHITE);
        panelButtons.add(noAll);
        panelButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(panelButtons);
    }

    public String disableButtons(String optionName){
        String fatherName = "";
        for (PanelButtons panelButtons : panelList) {
            if(panelButtons.getOption().equals(optionName)){
                panelButtons.disableButtons();
                fatherName = panelButtons.getName();
                panelList.remove(panelButtons);
                break;
            }
        }
        if(panelList.size() == 0){
            this.setVisible(false);
        }
        return fatherName;
    }

    // private void setSize(int listLength){
    //     if(listLength == 1){
    //         setSize(SIZE_ONE);
    //     }else if(listLength == 2){
    //         setSize(SIZE_TWO);
    //     }else if(listLength == 3){
    //         setSize(SIZE_THREE);
    //     }else{
    //         setSize(SIZE);
    //     }
    // }
}
