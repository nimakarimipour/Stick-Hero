package stickHero.vision;

import java.awt.*;

/**
 * Created by nima on 6/24/15.
 */
public class OldStick2D implements Paintable {

    private boolean isExtreme;

    public void setExtreme(boolean isExtreme){
        this.isExtreme = isExtreme;
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));
        if(isExtreme) g2d.setColor(new Color(105, 4, 229));
        g2d.drawLine(0, 344, 110, 344);
    }
}
