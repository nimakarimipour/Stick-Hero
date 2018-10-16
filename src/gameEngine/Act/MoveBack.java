package gameEngine.Act;

import gameEngine.*;

/**
 * Created by nima on 6/16/15.
 */
public class MoveBack extends Act {

    private Stick stick;
    private Hero hero;
    private Block currentBlock;
    private Block destBlock;
    private Fruit fruit;


    public MoveBack(GameEngine gameEngine) {

        super(gameEngine);
        hero = gameEngine.getHero();
        this.stick = hero.getStick();
        currentBlock = gameEngine.getCurrentBlock();
        destBlock = gameEngine.getDestBlock();
        hero.getBack();
        fruit = gameEngine.getFruit();
    }


    @Override
    public void action() {
        currentBlock.move();
        destBlock.move();
        hero.move();
        stick.getBack();
        fruit.move();
    }

    @Override
    public void endAct() {
        currentBlock.stop();
        hero.stop();
        destBlock.stop();
        gameEngine.wait(10);
        gameEngine.prepareNextLevel();
    }

    @Override
    public boolean mustEnd() {
        return hero.isInDefaultPlace();
    }
}