package gameEngine;
/**
 * Created by nima on 5/30/15.
 */
public class Hero {

    private Stick stick;
    private boolean isStanding, isAlive, usedStickOnce, isOnTheStick;
    private final int defaultX;
    private int x, y, velX = 2, width;


    public Hero(int defaultX, int defaultY) {

        y = defaultY;
        x = this.defaultX = defaultX;
        isOnTheStick = isAlive = isStanding = true;
        usedStickOnce = false;
        stick = new Stick(defaultX, defaultY);
        width = 25;
        x -= width + 7;
    }


    public void raiseStick() {
        if (!usedStickOnce) {
            stick.raise();
        }
    }

    public void getKilled() {
        isAlive = false;
    }

    public void walkOnStick() {
        usedStickOnce = true;
        isStanding = false;
        velX = 2;
    }

    public void getBack() {
        isStanding = true;
        velX = -8;
    }

    public void move() {
        x += velX;
    }

    public void fellDown() {
        y += 4;
        x -= 1;
    }

    public void toggleSituation() {
        if (!isStanding())
            isOnTheStick = (!isOnTheStick);
    }

    public void stop() {
        velX = 0;
        x = defaultX - width - 7;
        isStanding = true;
    }

    public void getReadyForNextLevel() {
        isStanding = true;
        stick.reset();
    }

    public void setUsedStickOnce(boolean b) {
        usedStickOnce = b;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOnTheStick() {
        return isOnTheStick;
    }

    public int getWidth(){
        return width;
    }

    public boolean isInDefaultPlace() {
        return x <= defaultX - width;
    }

    public boolean isReachedEndOfStick() {
        return (x + width + 4) >= stick.getEndX();
    }

    public boolean isUsedStickOnce(){
        return usedStickOnce;
    }

    public boolean isStanding(){
        return isStanding;
    }

    public Stick getStick() {
        return stick;
    }

    public int getEndX() {
        return x + width;
    }
}
