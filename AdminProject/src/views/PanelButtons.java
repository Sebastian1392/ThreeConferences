package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import presenters.Commands;

public class PanelButtons extends JPanel{

    private static final long serialVersionUID = 1L;

    private static final Font FONT = new Font("Whitney",Font.BOLD,19);
    private static final int WIDTH_BUTTONS = 100;
    private static final int HEIGTH_BUTTONS = 50;
    private static final String YES_TEXT = "Yes";
    private static final String NO_TEXT = "No";
    private static final Border BORDER = BorderFactory.createEmptyBorder(10, 30, 30, 30);
    private static final Color ORANGE_COLOR = Color.decode("#DE7123");

    private ButtonActions yes;
    private ButtonActions no;
    
    public PanelButtons(ActionListener listener, String option, String nodeName){
        BorderLayout layout = new BorderLayout();
        setName(nodeName);
        setBorder(BORDER);
        setLayout(layout);
        setOpaque(false);
        addTextOption(option);
        addButtons(listener,option);
    }

    private void addTextOption(String option){
        JLabel optionText = new JLabel(option);
        optionText.setFont(FONT);
        optionText.setForeground(Color.WHITE);
        add(optionText,BorderLayout.CENTER);
    }

    private void addButtons(ActionListener listener, String option) {
        JPanel panel =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);
        yes = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, YES_TEXT, ORANGE_COLOR, 
        Color.WHITE);
        yes.addActionListener(listener);
        yes.setActionCommand(Commands.YES_OPTION_PERMISSION.toString());
        yes.setName(option);
        panel.add(yes);
        no = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, NO_TEXT, ORANGE_COLOR,
        Color.WHITE);
        no.addActionListener(listener);
        no.setActionCommand(Commands.NO_OPTION_PERMISSION.toString());
        no.setName(option);
        panel.add(no);
        add(panel,BorderLayout.EAST);
    }

    public void disableButtons(){
        yes.setEnabled(false);
        no.setEnabled(false);
    }

    public String getOption(){
        return yes.getName();
    }
}
