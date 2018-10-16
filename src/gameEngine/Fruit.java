package gameEngine;

/**
 * Created by nima on 6/27/15.
 */
public class Fruit {

    private int x, y, radius;

    public Fruit(int y){
        this.y = y;
        radius = 25;
    }


    public void reset(int x){
        this.x = x;
        radius = 20;
    }


    public void shrink(){
        --radius;
    }

    public void move(){
        x -= 8;
    }

    public boolean isEaten() {
        return radius < 0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getRadius() {
        return radius;
    }
}

