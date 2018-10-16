package stickHero.vision;

import gameEngine.Fruit;

import java.awt.*;

/**
 * Created by nima on 6/27/15.
 */
public class Fruit2D implements Paintable {

    private Fruit fruit;

    int x, y, radius;

    public Fruit2D(Fruit fruit) {
        this.fruit = fruit;
    }


    @Override
    public void paint(Graphics2D graphics2D) {
        if(!fruit.isEaten()) {
            x = fruit.getX();
            y = fruit.getY();
            radius = fruit.getRadius();

            Graphics2D g2d = (Graphics2D) graphics2D.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(new Color(0, 255, 4));
            g2d.drawOval(x - 9, y + 9, radius, radius);
            g2d.setColor(new Color(40, 95, 255));
            g2d.drawOval(x + 9, y + 9, radius, radius);
            g2d.setColor(new Color(148, 0, 255));
            g2d.drawOval(x, y, radius, radius);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setColor(new Color(0, 255, 4));
            g2d.fillOval(x - 9, y + 9, radius, radius);
            g2d.setColor(new Color(40, 95, 255));
            g2d.fillOval(x + 9, y + 9, radius, radius);
            g2d.setColor(new Color(148, 0, 255));
            g2d.fillOval(x, y, radius, radius);

        }
    }
}
