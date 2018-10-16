package stickHero.vision;

import gameEngine.Stick;

import java.awt.*;

/**
 * Created by nima on 6/24/15.
 */
public class Stick2D implements Paintable {

    private Stick stick;


    public Stick2D(Stick stick) {
        this.stick = stick;
    }

    @Override
    public void paint(Graphics2D g2d) {


        g2d.setColor(stick.getColor());
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(stick.getFirstX() - 5, stick.getFirstY(), stick.getEndX() - 5, stick.getEndY());
    }
}
