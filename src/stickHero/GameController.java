package stickHero;

import gameEngine.GameEngine;
import gameEngine.GameOverException;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by nima on 5/31/15.
 */
public class GameController implements MouseListener {


    private static final int FPS = 80;

    private GamePanel panel;
    private GameEngine engine;

    Thread gameLoop;

    boolean running = false;
    boolean paused = false;
    boolean isRaising = false;


    public void init(GamePanel panel, GameEngine engine) {
        this.panel = panel;
        this.engine = engine;
    }



    public void pause () {
        paused = true;
    }

    public void resume () {
        paused = false;
    }

    public void reset(){
        engine.reset();
        resume();
    }

    private void gameRender() {
        panel.repaint();
    }

    private void gameUpdate() {

        try {
            engine.update();
            if(isRaising)
                engine.raiseStick();

        } catch (GameOverException e) {
            running = false;
            panel.showGameOverMessage();
        }
    }

    public void start() {
        gameLoop = new Thread (new Runnable() {

            @Override
            public void run() {
                running = true;
                while (running) {
                    if (!paused) {
                        gameUpdate ();
                        gameRender ();
                    }
                    try {
                        Thread.sleep(1000 / FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        gameLoop.start();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setUserName(){
        engine.setPlayerName(JOptionPane.showInputDialog("Enter Your Name"));
    }




    @Override
    public void mouseClicked(MouseEvent e) {
       System.out.println("X : " + e.getX() + " " + "Y : " + e.getY());
        engine.toggleSituation();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isRaising = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isRaising = false;
        engine.releaseStick();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
