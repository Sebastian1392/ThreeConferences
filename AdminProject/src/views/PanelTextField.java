package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PanelTextField extends JPanel{

    private static final long serialVersionUID = 1L;
    
    public static final Border BORDER = BorderFactory.createEmptyBorder(0, 20, 45, 20);
    public static final Border BORDER_TEXT_FIEL = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE);
    public static final Font LABEL_FONT = new Font("Roboto",Font.BOLD,16);
    public static final Font LABEL_ERROR_FONT = new Font("Roboto",Font.BOLD,14);
    public static final Font TEXTFIELD_FONT = new Font("Roboto",Font.PLAIN,20);

    private JTextField textField;

    public PanelTextField(String titleField){
        setLayout(new BorderLayout());
        setBorder(BORDER);
        setOpaque(false);
        addLabel(titleField);
        TextFieldRegistration();
    }

    private void addLabel(String titleField){
        JLabel label = new JLabel(titleField);
        label.setFont(LABEL_FONT);
        label.setForeground(Color.WHITE);
        add(label, BorderLayout.PAGE_START);
    }

    private void TextFieldRegistration(){ 
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(this.getWidth(),22));
        textField.setFont(TEXTFIELD_FONT);
        textField.setBackground(UtilitiesViews.BLUE_COLOR);
        textField.setForeground(Color.WHITE);
        textField.setOpaque(true);
        textField.setBorder(BORDER_TEXT_FIEL);
        add(textField, BorderLayout.CENTER);
    }

    public void addErrorMessage(String message){
        removeComponent();
        JLabel label = new JLabel(message);
        label.setFont(LABEL_ERROR_FONT);
        label.setForeground(Color.WHITE);
        add(label, BorderLayout.PAGE_END);
        revalidate();
        repaint();
    }

    private void removeComponent(){
        if(this.getComponents().length > 2){
            this.remove(this.getComponents().length - 1);
        }
    }

    public String getText(){
        return textField.getText();
    }
}