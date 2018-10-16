package stickHero.vision;

import gameEngine.Block;

import java.awt.*;

/**
 * Created by nima on 6/24/15.
 */
public class Block2D implements Paintable {

    private Block block;

    public Block2D(Block block) {
        this.block = block;
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        final Graphics2D g2d = (Graphics2D) graphics2D.create();
        g2d.setColor(new Color(87, 54, 20));

        AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        g2d.setComposite(transparency);
        g2d.fillRoundRect(block.getX(), block.getY() + 2, block.getLength(), block.getHeight() + 10, 10, 10);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRoundRect(block.getX(), block.getY() + 2, block.getLength(), block.getHeight() + 10, 10, 10);
        g2d.setColor(Color.RED);
        g2d.fillRect(block.getX() + block.getLength()/2 - 5, block.getY(), 10, 5);
        g2d.dispose();
    }
}
