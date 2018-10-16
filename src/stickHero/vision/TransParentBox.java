package stickHero.vision;

import java.awt.*;

/**
 * Created by nima on 6/27/15.
 */
public class TransParentBox implements Paintable {

    @Override
    public void paint(Graphics2D graphics2D) {

        Graphics2D g2d = (Graphics2D) graphics2D.create();
        AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        g2d.setComposite(transparency);
        g2d.setColor(new Color(56, 56, 56));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(5, 0, 100, 50, 15, 15);
    }
}
