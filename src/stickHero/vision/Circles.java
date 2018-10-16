package stickHero.vision;

import java.awt.*;
import java.util.Random;

/**
 * Created by nima on 6/26/15.
 */
public class Circles {

    private Color color;
    private int centerX, centerY, radius;

    public Circles(){

        Random random = new Random();
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        centerX = random.nextInt(600);
        centerY = random.nextInt(600);
        radius = 5 + random.nextInt(90);
    }

    public void expand(){
        radius += 2;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return centerX;
    }

    public int getY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }



}
