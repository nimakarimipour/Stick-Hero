package stickHero.vision;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextButton extends JButton implements MouseListener {

    private boolean hover;
    private boolean clicked;
    private String buttonText;

    public TextButton(String buttonText) {

        this.buttonText = buttonText;
        setLayout(null);
        addMouseListener(this);
        makeLabel();
    }

    private void makeLabel() {

        JLabel label = new JLabel(buttonText);
        label.setBounds(27, 20, 100, 100);
        label.setFont(new Font("Chalkduster", Font.ITALIC, 20));
        label.setForeground(Color.WHITE);
        add(label);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!clicked) g2d.setColor(new Color(255, 122, 0));
        else g2d.setColor(new Color(239, 28, 0));
        g2d.fillOval(20, 20, 100, 100);

        if (hover) drawFadeArc(g2d);
    }

    private void drawFadeArc(Graphics2D g2d) {
        Graphics2D graphics2D = (Graphics2D) g2d.create();
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i < 20; ++i) {
            AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (1.0 - i * 0.05));
            graphics2D.setComposite(acomp);
            graphics2D.drawOval(20 - i, 20 - i, 100 + i * 2, 100 + i * 2);
        }
    }


    @Override
    public boolean contains(int x, int y) {
        return Math.sqrt(((x - 70) * (x - 70)) + ((y - 70) * (y - 70))) <= 50;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hover = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;
        repaint();
    }
}