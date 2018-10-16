package gameEngine.Act;

import gameEngine.Fruit;
import gameEngine.GameEngine;

/**
 * Created by nima on 6/27/15.
 */
public class EatFriut extends Act {

    Fruit fruit;

    public EatFriut(GameEngine gameEngine){
        super(gameEngine);
        fruit = gameEngine.getFruit();
    }


    @Override
    public void action() {
        if(!fruit.isEaten()) fruit.shrink();
    }

    @Override
    public void endAct() {

    }

    @Override
    public boolean mustEnd() {
        return fruit.isEaten();
    }
}
