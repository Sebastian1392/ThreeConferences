package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presenters.Commands;

public class DialogConfirmation extends JDialog{

    private static final long serialVersionUID = 1L;

    public static final Font FONT = new Font("Roboto", Font.BOLD, 19);
    public static final String QUESTION = "Are you sure you want to get out?";
    public static final String YES_TEXT = "Yes";
    public static final String NO_TEXT = "No";
    public static final Dimension SIZE_CONFIRMATION = new Dimension(400,250);
    public static final Dimension SIZE_NOTIFICATION = new Dimension(100,30);
    public static final int WIDTH_BUTTONS = 100;
    public static final int HEIGTH_BUTTONS = 50;
    public static final int ANY_OPTION = -1;

    private JPanel panelCentral;

    public DialogConfirmation(ActionListener listener){
        setUndecorated(true);
        this.getContentPane().setBackground(Color.BLACK);
        setModal(true);
        setSize(SIZE_CONFIRMATION);
        addConfirmation(listener);
    }
    
    private void addConfirmation(ActionListener listener){
        panelCentral = new JPanel(new GridLayout(2,1));
        panelCentral.setOpaque(false);
        JLabel label = new JLabel(QUESTION);
        label.setForeground(Color.WHITE);
        label.setFont(FONT);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        panelCentral.add(label);
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        
        ButtonActions yesButton = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, YES_TEXT, UtilitiesViews.BLUE_COLOR, Color.WHITE);
        yesButton.addActionListener(listener);
        yesButton.setActionCommand(Commands.YES_OPTION.toString());
        panel.add(yesButton);
        
        ButtonActions noButton = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, NO_TEXT, UtilitiesViews.BLUE_COLOR, Color.WHITE);
        noButton.addActionListener(listener);
        noButton.setActionCommand(Commands.NO_OPTION.toString());
        panel.add(noButton);
        panelCentral.add(panel);
        add(panelCentral);
    }
    
    public void showWarning(JFrame frame){
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}