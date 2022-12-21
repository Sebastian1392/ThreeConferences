package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonActions extends JButton{

	private static final long serialVersionUID = 1L;
	
	private static final Dimension DIMENSION_ARCS = new Dimension(25,25);
	private static final Font FONT = new Font("Roboto", Font.BOLD, 27);

    public ButtonActions(int sizeWidth, int sizeHigh, String text, Color colorButton, Color colorFont) {
		setPreferredSize(new Dimension(sizeWidth,sizeHigh));
		setText(text);
		setBackground(colorButton);
		setFont(FONT);
		setForeground(colorFont);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.CENTER);
		setBorderPainted(false);
		setOpaque(false);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
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