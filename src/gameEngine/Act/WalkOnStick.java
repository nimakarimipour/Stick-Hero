package gameEngine.Act;

import gameEngine.Block;
import gameEngine.Fruit;
import gameEngine.GameEngine;
import gameEngine.Hero;

/**
 * Created by nima on 6/16/15.
 */
public class WalkOnStick extends Act {

    private Hero hero;
    private boolean wonThisLevel;
    private Block block;
    private Fruit fruit;


    public WalkOnStick(GameEngine gameEngine){
        super(gameEngine);
        hero = gameEngine.getHero();
        block = gameEngine.getDestBlock();
        hero.walkOnStick();
        fruit = gameEngine.getFruit();
    }


    @Override
    public void action() {
        if(!hero.isOnTheStick() && hero.getEndX() > block.getX()) gameEngine.getLost();
        if (!hero.isOnTheStick() && hero.getX()  >= fruit.getX() && hero.getX() <= (fruit.getX() + fruit.getRadius() * 2))
            gameEngine.eatFruit();
        hero.move();
    }

    @Override
    public void endAct() {}

    @Override
    public boolean mustEnd() {
        if (wonThisLevel) return hero.getEndX() + 7 >= block.getX() + block.getLength();
        else return hero.isReachedEndOfStick();
    }

    public void setWonThisLevel(boolean wonThisLevel) {
        this.wonThisLevel = wonThisLevel;
    }
}