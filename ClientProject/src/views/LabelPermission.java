package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LabelPermission extends JButton{

	private static final long serialVersionUID = 1L;
	
	private static final Dimension DIMENSION_ARCS = new Dimension(25,25);
    private static final Font FONT = new Font("Whitney", Font.BOLD, 18);

    public LabelPermission(String nodeName, ImageIcon icon) {
        setText(nodeName);
        setName(nodeName);
        setAlignmentX(Component.CENTER_ALIGNMENT);
		setBackground(Color.WHITE);
        setFont(FONT);
        setIcon(icon);
		setForeground(Color.BLACK);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setBorderPainted(false);
		setOpaque(false);
		setFocusable(false);
	}
	
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, DIMENSION_ARCS.width, DIMENSION_ARCS.height);
		graphics.setColor(getBackground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, DIMENSION_ARCS.width, DIMENSION_ARCS.height);
		super.paintComponent(g);
	}
}
