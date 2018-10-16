package gameEngine.Act;

import gameEngine.GameEngine;

/**
 * Created by nima on 6/17/15.
 */
public class Wait extends Act{

    private int timeLeft;

    public Wait(GameEngine gameEngine, int timeLeft){
        super(gameEngine);
        this.timeLeft = timeLeft;
    }


    @Override
    public void action() {
        --timeLeft;
    }

    @Override
    public void endAct() {

    }

    @Override
    public boolean mustEnd() {
        return timeLeft == 0;
    }
}