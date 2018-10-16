package gameEngine;

import java.awt.*;

/**
 * Created by nima on 5/30/15.
 */
public class Stick {

    private int length  = 0, angle  = 90, firstX, firstY, defaultX, defaultY;
    private double angleVel = 3;
    private Color color;

    public Stick(int firstX, int firstY) {
        defaultX = this.firstX = firstX;
        defaultY = this.firstY = firstY;
        this.firstX += 2;
        this.firstY += 2;
        color = new Color(100, 52, 0);
    }


    public void fellDown(){
        angle -= angleVel;
    }

    public void reset(){
        length = 0;
        angle = 90;
        angleVel = 2;
        firstX = defaultX + 2;
        firstY = defaultY + 2;
        color = new Color(100, 52, 0);
    }

    public void raise(){
        length += 8;
    }

    public void getBack(){
        firstX -= 8;
    }

    public void getExtreme() {color = new Color(255, 56, 0);}


    public boolean hasFallDown(){
        return angle == 0;
    }

    public int getEndX(){
        return firstX + (int)(length * Math.cos(Math.toRadians(angle)));
    }

    public int getEndY(){
        return firstY - (int)(length * Math.sin(Math.toRadians(angle)));
    }

    public int getFirstX(){
        return firstX;
    }

    public int getFirstY(){
        return firstY;
    }

    public int getLength(){
        return length;
    }

    public Color getColor(){
        return color;
    }
}
