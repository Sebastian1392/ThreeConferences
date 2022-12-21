package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;

import presenters.Commands;


public class DialogActionNode extends JDialog{

    private static final long serialVersionUID = 1L;
    
    private static final String EMPTY = "";
    private static final String ICON_PATH = "/images/addIcon.png";
    private static final String ACCEPT = "Accept";
    private static final Dimension SIZE = new Dimension(400,250);
    private static final Border BODER = BorderFactory.createEmptyBorder(30, 20, 20, 20);
    public static final int WIDTH_BUTTONS = 100;
    public static final int HEIGTH_BUTTONS = 50;

    private PanelTextField textField;
    private JPanel panel;

    public DialogActionNode(Component component,ActionListener listener){
        setSize(SIZE);
        setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
        setModal(true);
        setLocationRelativeTo(component);
        createPanel();
        addTetxtField();
        addButton(listener);
    }

    private void createPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BODER);
        add(panel);
    }

    private void addTetxtField(){
        textField = new PanelTextField(EMPTY);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(textField);
    }

    private void addButton(ActionListener listener) {
        ButtonActions action = new ButtonActions(WIDTH_BUTTONS, HEIGTH_BUTTONS, ACCEPT, UtilitiesViews.BLUE_COLOR, 
        Color.WHITE);
        action.setAlignmentX(Component.CENTER_ALIGNMENT);
        action.addActionListener(listener);
        action.setActionCommand(Commands.ADD_NODE.toString());
        panel.add(action);
    }

    public void showDialog(String title){
        setTitle(title);
        textField.setTitle(title);
        setVisible(true);
    }

    public String[] getData(){
        setVisible(false);
        return new String[]{getTitle(),textField.getText()};
    }
}
