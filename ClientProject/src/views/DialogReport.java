package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import presenters.Commands;

public class DialogReport extends JDialog{

    
    private static final long serialVersionUID = 1L;
    
    private static final String IMAGES_MESSAGE_PNG = "/images/message.png";
    private static final String TITLE = "Report Message";
    public static final Font FONT = new Font("Whitney", Font.BOLD, 17);
    public static final String QUESTION = "<html>The report has been generated, look in the Reports folder";
    public static final String YES_TEXT = "Accept";
    public static final String NO_TEXT = "No";
    public static final Dimension SIZE_CONFIRMATION = new Dimension(300,200);
    public static final int WIDTH_BUTTONS = 100;
    public static final int HEIGTH_BUTTONS = 50;
    private static final Border BORDER_PRINCIPAL_PANEL = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    private static final Border BORDER_BUTTON_PANEL = BorderFactory.createEmptyBorder(10, 0, 10, 0);

    private JPanel panelCentral;

    public DialogReport(ActionListener listener){
        this.getContentPane().setBackground(Color.BLACK);
        setIconImage(new ImageIcon(getClass().getResource(IMAGES_MESSAGE_PNG)).getImage());
        setModal(true);
        setTitle(TITLE);
        setSize(SIZE_CONFIRMATION);
        addConfirmation(listener);
    }
    
    private void addConfirmation(ActionListener listener){
        panelCentral = new JPanel(new GridLayout(2,1));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BORDER_PRINCIPAL_PANEL);
        JLabel label = new JLabel(QUESTION);
        label.setForeground(Color.WHITE);
        label.setFont(FONT);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        panelCentral.add(label);
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BORDER_BUTTON_PANEL);
        panel.setOpaque(false);
        
        ButtonActions yesButton = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, YES_TEXT, UtilitiesViews.BLUE_COLOR, Color.WHITE);
        yesButton.addActionListener(listener);
        yesButton.setActionCommand(Commands.NO_OPTION.toString());
        panel.add(yesButton);
        panelCentral.add(yesButton);
        add(panelCentral);
    }

    public void showMessage(JFrame frame){
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}
