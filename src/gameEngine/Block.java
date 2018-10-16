package gameEngine;

/**
 * Created by nima on 5/31/15.
 */
public class Block {

    private final int defaultY, defaultX;
    private int x, y, velY, velX, length;


    public Block(int x, int y) {

        defaultY = this.y = y;
        defaultX = this.x = x;
        velY = 2; velX = 8; length = 80;
        this.x = x - length;
    }


    public void raise(){
        y -= velY;
    }

    public void move(){
        x -= velX;
    }

    public void stop(){
        x = defaultX - length;
        y = defaultY;
    }

    public void reset(int x, int y, int length){

        this.x = x;
        this.y = y;
        this.length = length;
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getLength(){
        return length;
    }

    public int getHeight() {
        return 600 - y;
    }

    public boolean hasDefaultHeight(){
        return defaultY >= y;
    }
}