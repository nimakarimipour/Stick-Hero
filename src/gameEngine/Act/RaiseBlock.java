package gameEngine.Act;

import gameEngine.Block;
import gameEngine.GameEngine;

/**
 * Created by nima on 6/17/15.
 */
public class RaiseBlock extends Act {

    private Block block;

    public RaiseBlock(GameEngine gameEngine) {

        super(gameEngine);
        block = gameEngine.getDestBlock();
    }


    @Override
    public void action() {
        block.raise();
    }

    @Override
    public void endAct() {
        gameEngine.getHero().setUsedStickOnce(false);
        gameEngine.sleep();
    }

    @Override
    public boolean mustEnd() {
        return block.hasDefaultHeight();
    }
}