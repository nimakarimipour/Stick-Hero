package stickHero.vision;

import gameEngine.Hero;

import java.awt.*;

/**
 *  Created by nima on 6/24/15.
 */
public class Hero2D implements Paintable{

    private Hero hero;
    private float velX = 0, velHat = 0, hatRate = 1.5f, xRate = 1;
    private Graphics2D g2d;
    private int x, y, width, ruler, transferer = 0;

    public Hero2D(Hero hero) {
        this.hero = hero;
    }


    @Override
    public void paint(Graphics2D graphics2D) {

        g2d = (Graphics2D) graphics2D.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        getCoordinates();
        drawBody();
        moveLegs();
        moveHat();
        drawHat();
        drawLegs();
    }


    private void getCoordinates() {
        x = hero.getX();
        y = hero.getY();
        ruler = 50;
        width = hero.getWidth();
        if(hero.isOnTheStick()) {
            ruler = 50;
            transferer = 0;
        }
        else {
            ruler = -10;
            transferer = 20;
        }
    }

    private void drawBody() {

        g2d.drawRoundRect(x, y - ruler, width, 40, 15, 15);
        g2d.setColor(new Color(67, 64, 67));
        g2d.fillRoundRect(x, y - ruler, width, 40, 15, 15);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + width - 9, y - (ruler - (int)(transferer / 2.9)) + 12, 9, 9);
    }

    private void moveHat() {
        velHat += hatRate;
        if(velHat == 6 || velHat == -6)
            hatRate *= -1;
    }

    private void drawHat() {
        g2d.setColor(new Color(255, 134, 0));
        int[] upperHatX = {x - 2, x - 11, x - 15};
        int[] upperHatY = {y - ruler + 7 + transferer, y - ruler + 10 + (int)velHat + transferer, y - ruler + (int)velHat + transferer};
        int[] downHatX =  {x - 2, x - 9, x - 18};
        int[] downHatY =  {y - ruler + 12 + transferer, y - ruler + 7 - (int)velHat + transferer, y - ruler + 20 - (int)velHat + transferer};
        g2d.fillPolygon(upperHatX, upperHatY, 3);
        g2d.fillPolygon(downHatX, downHatY, 3);
        g2d.fillRoundRect(x -  4, y - ruler + 5 + transferer, width + 7, 10, 5, 5);
    }

    private void drawLegs() {
        if(hero.isOnTheStick())
            ruler = 10;
        g2d.setColor(new Color(67, 64, 67));
        g2d.setStroke(new BasicStroke(7));
        g2d.drawLine(x + 6 , y - ruler, x + 6 - (int)velX, y);
        g2d.drawLine(x + 19, y - ruler, x + 19 +(int)velX, y);
    }

    private void moveLegs() {
        if(hero.isStanding()){
            velX = 0;
        }
        else {
            velX += xRate;
            if(velX == 4 || velX == -4)
                xRate *= -1;
        }
    }
}
