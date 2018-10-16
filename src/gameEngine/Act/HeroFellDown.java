package gameEngine.Act;

import gameEngine.GameEngine;
import gameEngine.Hero;

/**
 * Created by nima on 6/16/15.
 */
public class HeroFellDown extends Act {

    private Hero hero;


    public HeroFellDown(GameEngine gameEngine) {
        super(gameEngine);
        hero = gameEngine.getHero();
    }


    @Override
    public void action() {
        hero.fellDown();
    }

    @Override
    public void endAct() {
        hero.getKilled();
    }

    @Override
    public boolean mustEnd() {
        //TODO change 600 to windowHeight
        return hero.getY() > 600;
    }
}