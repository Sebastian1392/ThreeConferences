package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import presenters.Commands;

public class PanelLogin extends JPanel{

    
    private static final long serialVersionUID = 1L;
    
    public static final String LABEL_TEXT = "Conferences App";
    public static final String REGISTER_TEXT = "Register";
    public static final String LOGIN_TEXT = "Login";
    public static final Font LABEL_FONT = new Font("Bauhaus 93",Font.PLAIN,35);
    public static final String USER_NAME = "User Name";
    public static final String PASSWORD = "Password";
    public static final String LOGO_PATH = "src/images/logo.png";
    public static final Border BORDER = BorderFactory.createEmptyBorder(40, 20, 40, 20);

    private PanelTextField userName;

    public PanelLogin(ActionListener listener){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BORDER);
        setBackground(UtilitiesViews.BLUE_COLOR);
        addLogo();
        addUserField();
        addButton(listener);
    }

    private void addLogo(){
        JLabel label = new JLabel();
        label.setIcon(UtilitiesViews.getImage(LOGO_PATH, 130, 130));
        label.setFont(LABEL_FONT);
        label.setText(LABEL_TEXT);
        label.setForeground(Color.WHITE);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(label);
    }

    private void addUserField(){
        userName = new PanelTextField(USER_NAME);
        add(userName);
    }

    private void addButton(ActionListener listener){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        ButtonActions buttonLogin = new ButtonActions(150,70,LOGIN_TEXT,Color.WHITE,UtilitiesViews.BLUE_COLOR);
        buttonLogin.addActionListener(listener);
        buttonLogin.setActionCommand(Commands.LOGIN.toString());
        panel.add(buttonLogin);
        add(panel);
    }

    public String getUserNameLogin(){
        return userName.getText();
    }

    public void addErrorMessage(String message){
        userName.addErrorMessage(message);
    }
}
