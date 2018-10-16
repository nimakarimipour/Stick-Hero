package gameEngine;

/**
 * Created by nima on 5/31/15.
 */
public class GameOverException extends Exception {

    private GameEngine gameEngine;

    public GameOverException(GameEngine gameEngine){
        super("Game Over");
        this.gameEngine = gameEngine;
    }


    public void endGame(){
        gameEngine.fellDown();
    }
}
