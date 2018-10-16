package stickHero;

import gameEngine.GameEngine;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by nima on 6/26/15.
 */
public class ScreenManager {

    private GamePanel gamePanel;
    private GameController gameController;
    private CardLayout screens;
    private StickHeroPanel stickHeroPanel;
    private PauseMenu pauseMenu;
    private MenuPanel menu;
    private final String gameKey = "0", pauseKey = "1", menuKey = "2";

    public ScreenManager(GamePanel gamePanel, GameEngine gameEngine, GameController gameController) {
        this.gamePanel = gamePanel;
        this.gameController = gameController;
        stickHeroPanel = new StickHeroPanel();
        stickHeroPanel.init(gameController, gameEngine, this);
        pauseMenu = new PauseMenu(this);
        menu = new MenuPanel(this);
        setUpGamePanel();
    }


    private void setUpGamePanel(){

        screens = new CardLayout();
        gamePanel.setLayout(screens);
        gamePanel.add(stickHeroPanel, gameKey);
        gamePanel.add(pauseMenu, pauseKey);
        gamePanel.add(menu, menuKey);
        screens.show(gamePanel, menuKey);
    }


    public void showPauseMenu(BufferedImage backGround){
        pauseMenu.setBackGround(backGround);
        gameController.pause();
        screens.show(gamePanel, pauseKey);
    }

    public void showMenu(){
        gameController.reset();
        screens.show(gamePanel, menuKey);

    }

    public void restart(){
        gameController.reset();
        showGameScreen();
        stickHeroPanel.setEverything();
    }

    public void showGameScreen(){
        gameController.resume();
        screens.show(gamePanel, gameKey);
    }

    public void promptUserName(){
        gameController.setUserName();
    }
}
