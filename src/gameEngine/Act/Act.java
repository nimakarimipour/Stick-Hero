package gameEngine.Act;

import gameEngine.GameEngine;

/**
 * Created by nima on 6/16/15.
 */
abstract public class Act {

    protected GameEngine gameEngine;

    public Act(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    public abstract void action();
    public abstract void endAct();
    public abstract boolean mustEnd();
}