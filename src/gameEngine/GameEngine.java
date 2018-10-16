package gameEngine;

import gameEngine.Act.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nima on 5/29/15.
 */
public class GameEngine {

    public int windowWidth, windowHeight;
    public boolean passedOneLevel;

    private boolean isLost;
    private Hero hero;
    private Block currentBlock, destBlock;
    private Random random;
    private Actor movementActor;
    private Actor fruitActor;
    private Fruit fruit;
    private boolean wonThisLevel;
    private boolean sleep;
    private HighScore highScore;
    private int currentHighScore = 0;
    private String playerName = "Nima";

    private ArrayList<Actor> actors;



    class Actor {

        private ArrayList<Act> actions;

        public Actor() {
            actions = new ArrayList<Act>();
        }

        public void addAct(Act act) {
            actions.add(act);
        }

        public void act() {
            if(actions.size() != 0) {
                actions.get(0).action();
                check();
            }
        }

        public boolean isBusy(){
            return actions.size() != 0;
        }

        private void check() {
            Act act = actions.get(0);
            if(act.mustEnd()) {
                act.endAct();
                actions.remove(act);
            }
        }

        public void reset(){
            actions.clear();
        }
    }


    public GameEngine() {

        getLastHighScore();
        random = new Random();
        random.setSeed(System.currentTimeMillis());
        movementActor = new Actor();
        fruitActor = new Actor();
        sleep = true;
        actors = new ArrayList<Actor>();
        actors.add(movementActor);
        actors.add(fruitActor);
    }

    public void setDimension(Dimension d) {
        windowHeight = d.height;
        windowWidth = d.width;
    }

    public void setUp(Dimension dimension) {

        setDimension(dimension);
        hero = new Hero(windowWidth / 5, 4 * windowHeight / 7);
        currentBlock = new Block(windowWidth / 5, 4 * windowHeight / 7);
        destBlock = new Block(windowWidth / 5, 4 * windowHeight / 7);
        fruit = new Fruit(4 * windowHeight / 7 + 20);
        destBlock.reset(windowWidth / 5 * 3, 4 * windowHeight / 7, 70);
        fruit.reset(3 * windowWidth / 5 - 100);
    }

    public void update() throws GameOverException  {
        if (!sleep) {
            //TODO about gameOvers yeki inja bayad taghir kone ye ki ham tuye kepp play wonthislevel
            if (isLost) {
                throw new GameOverException(this);
            } else keepPlay();
        }
    }

    public void wait(int times) {
        movementActor.addAct(new Wait(this, times));
    }

    public void move() {
        WalkOnStick act = new WalkOnStick(this);
        act.setWonThisLevel(wonThisLevel);
        movementActor.addAct(act);
    }

    public void prepareNextLevel() {

        hero.getReadyForNextLevel();
        makeNewBlock();
        if(destBlock.getX() - hero.getEndX() > 60){
            fruit.reset(hero.getEndX() + 10 + random.nextInt(destBlock.getX() - hero.getEndX() - 20));
        }
        movementActor.addAct(new RaiseBlock(this));
    }

    public void fellDown() {
        movementActor.addAct(new HeroFellDown(this));
    }

    public void sleep (){
        sleep = true;
    }

    public void eatFruit() {
        fruitActor.addAct(new EatFriut(this));
    }

    private void keepPlay() {
        if (movementActor.isBusy()) {
            movementActor.act();
            fruitActor.act();
        }

        else {
            if (wonThisLevel) getBack();
            else isLost = true;
        }
    }

    private void getBack() {
        passedOneLevel = true;
        movementActor.addAct(new Wait(this, 10));
        movementActor.addAct(new MoveBack(this));
    }

    private void kickStick() {

        hero.setUsedStickOnce(true);
        movementActor.addAct(new RotateStick(this));
    }

    private void makeNewBlock() {

        Block temp = currentBlock;
        currentBlock = destBlock;
        destBlock = temp;
        destBlock.reset(windowWidth / 5 + 10 + random.nextInt(windowWidth * 3 / 5), windowHeight, 40 + random.nextInt(40));
    }

    private void seeFuture() {
        int endX = getStick().getFirstX() + getStick().getLength();
        int middleX = destBlock.getX() + (destBlock.getLength()/2);

        if ((endX - 2) >= destBlock.getX() && endX <= destBlock.getX() + destBlock.getLength()) {
            wonThisLevel = true;
            if(endX >= middleX - 5 && endX <= middleX + 5){
                ++currentHighScore;
                getStick().getExtreme();
            }
            ++currentHighScore;
        }
        recordHighScore();
    }

    private void recordHighScore() {

        highScore.lastRecord(playerName + " " + currentHighScore);
        FileOutputStream f;
        try {
            f = new FileOutputStream("Records.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(f);
            outputStream.writeObject(highScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getLastHighScore(){
        try {
            FileInputStream fileInputStream = new FileInputStream("Records.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            highScore = (HighScore) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            highScore = new HighScore("Unknown");
        }
        System.out.print(highScore.getRecord());
    }

    public void getLost(){
        isLost = true;
    }


    public Hero getHero() {
        return hero;
    }

    public Stick getStick() {
        return hero.getStick();
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public Block getDestBlock() {
        return destBlock;
    }

    public Fruit getFruit(){
        return fruit;
    }

    public int getCurrentHighScore(){
        return currentHighScore;
    }

    public int getRecord(){
        return highScore.getRecord();
    }


    public void clearHighScore(){
        highScore.clear();
    }

    public void reset(){
        sleep = true;
        isLost = false;
        wonThisLevel = false;
        movementActor.reset();
        setUp(new Dimension(windowWidth, windowHeight));
        currentHighScore = 0;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public void raiseStick() {
        hero.raiseStick();
    }

    public void releaseStick() {
        if(!hero.isUsedStickOnce()) {
            wonThisLevel = false;
            seeFuture();
            kickStick();
            hero.setUsedStickOnce(true);
            sleep = false;
        }
    }

    public void toggleSituation() {
        hero.toggleSituation();
    }
}
