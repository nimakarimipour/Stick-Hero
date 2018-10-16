package gameEngine.Act;

import gameEngine.GameEngine;
import gameEngine.Stick;

/**
 * Created by nima on 6/16/15.
 */
public class RotateStick extends Act {

    private Stick stick;

    public RotateStick(GameEngine gameEngine) {

        super(gameEngine);
        stick = gameEngine.getStick();
        gameEngine.wait(10);
    }


    @Override
    public void action() {
        stick.fellDown();
    }

    @Override
    public void endAct() {

        gameEngine.wait(10);
        gameEngine.move();
    }

    @Override
    public boolean mustEnd() {
        return stick.hasFallDown();
    }
}