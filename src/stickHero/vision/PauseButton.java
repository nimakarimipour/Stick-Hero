package stickHero.vision;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 *  Created by nima on 6/25/15.
 */
public class PauseButton extends JButton implements MouseListener {


    private boolean hover;
    private boolean clicked;
    private BufferedImage image;
    private AffineTransform at;

    public PauseButton(){
        addMouseListener(this);
        at = AffineTransform.getTranslateInstance(500, 20);
        loadImage();
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 187, 0));

        g2d.setStroke(new BasicStroke(5));

        if(hover) g2d.setColor(new Color(255, 255, 255));
        if(clicked) g2d.setColor(new Color(41, 217, 43));

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        at.scale(0.125, 0.125);
        g2d.drawOval(500, 20, 64, 64) ;
    }


    public void loadImage(){

        try {
            URL imageURL = this.getClass().getResource("/images/pause.png");
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public boolean contains(int x, int y){
        return Math.sqrt(((34 - x) * (34 - x)) + ((y - 34) * (y - 34))) <= 32;
    }
}








