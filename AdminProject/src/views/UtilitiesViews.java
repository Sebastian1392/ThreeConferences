package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class UtilitiesViews {

    public static final Font TITLE_FONT = new Font("Bauhaus 93",Font.PLAIN,30);
    public static final String LOGO_PATH = "/images/logo.png";
    public static final Color BLUE_COLOR = Color.decode("#00a8f3");

    public static Icon getImage(String routeImage, int width, int heigth){
        ImageIcon icon = new ImageIcon(routeImage);
        Icon scaleIcon = new ImageIcon(icon.getImage().getScaledInstance(width, heigth, Image.SCALE_SMOOTH));
        return scaleIcon;
    }

    public static Icon getImage(Image image,int width, int heigth){
        ImageIcon icon = new ImageIcon(image);
        Icon scaleIcon = new ImageIcon(icon.getImage().getScaledInstance(width,heigth, Image.SCALE_SMOOTH));
        return scaleIcon;
    }
}