package stickHero;

import gameEngine.GameEngine;
import stickHero.vision.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nima on 5/31/15.
 */
public class GamePanel extends JPanel{

    public void init(final GameController controller, final GameEngine engine) {

        setPreferredSize(new Dimension(600, 600));
        ScreenManager screenManager = new ScreenManager(this, engine, controller);
        /*StickHeroPanel stickHeroPanel = new StickHeroPanel();
        stickHeroPanel.init(controller, engine);
        add(stickHeroPanel, BorderLayout.CENTER);
        stickHeroPanel.setBounds(0, 0, 600, 600);*/
    }

    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(this, "Game is over!");
    }
}


class StickHeroPanel extends JPanel {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    Graphics offG;
    BufferedImage offImage;
    BufferedImage backGround = null;
    PauseButton pauseButton;
    ScreenManager screenManager;
    GameEngine engine;
    ArrayList<Paintable> gameComponents = new ArrayList<Paintable>();
    GameController controller;
    JLabel pointLabel;


    public void init(final GameController controller, GameEngine engine, ScreenManager screenManager) {
        this.engine = engine;
        this.controller = controller;
        addMouseListener(controller);
        setFocusable(true);
        requestFocus();
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        engine.setUp(new Dimension(WIDTH, HEIGHT));
        this.screenManager = screenManager;
        setEverything();
    }

    public void setEverything(){
        fillGameComponentList();
        loadImages();
        makePointLabel();
        addPauseButton();
    }

    private void makePointLabel() {

        pointLabel = new JLabel("  " + engine.getCurrentHighScore() + "  /  " + engine.getRecord());
        pointLabel.setBounds(500, 30, 100, 50);
        pointLabel.setFont(new Font("Zapfino", Font.ITALIC, 15));
        pointLabel.setForeground(Color.WHITE);


    }

    private void fillGameComponentList() {
        gameComponents.clear();
        gameComponents.add(new Hero2D(engine.getHero()));
        gameComponents.add(new Stick2D(engine.getStick()));
        gameComponents.add(new OldStick2D());
        gameComponents.add(new Block2D(engine.getCurrentBlock()));
        gameComponents.add(new Block2D(engine.getDestBlock()));
        gameComponents.add(new TransParentBox());
        gameComponents.add(new Fruit2D(engine.getFruit()));
    }

    private void addPauseButton() {
        pauseButton = new PauseButton();
        pauseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                screenManager.showPauseMenu(offImage);
            }
        });
        pauseButton.setBounds(500, 20, 64, 64);
        add(pauseButton);
    }

    private void loadImages() {

        offImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        offG = offImage.getGraphics();

        try {
            URL imageUrl = this.getClass().getResource("/images/1.jpg");
            backGround = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getOffImage(){
        return offImage;
    }


    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g){
        g.drawImage(offImage, 0, 0, this);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        paintBackGround(g2d);
        for(Paintable p : gameComponents){
            p.paint(g2d);
        }
        paintPauseButton(g);
        pointLabel.setText("  " + engine.getCurrentHighScore() + "  /  " + engine.getRecord());
        pointLabel.paint(g);
    }

    private void paintPauseButton(Graphics g) {
        pauseButton.paint(g);
        AffineTransform at = AffineTransform.getTranslateInstance(500, 20);
        at.scale(0.25, 0.25);
        ((Graphics2D) g).drawImage(pauseButton.getImage(), at, null);
    }

    private void paintBackGround(Graphics2D g2d) {
        g2d.drawImage(backGround, null, 0, 0);
    }
}

class MenuPanel extends JPanel {

    private ScreenManager screens;
    BufferedImage offImage;
    Graphics2D offG = null;

    public MenuPanel(ScreenManager screens){

        this.screens = screens;
        setLayout(null);
        JLabel label = new JLabel("STICK HERO");
        label.setFont(new Font("Zapfino", Font.ITALIC, 30));
        label.setForeground(Color.WHITE);
        label.setBounds(120, 100, 500, 100);
        add(label);
        loadImage();
        makeButtons();
    }

    private void loadImage() {
        offImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        offG = (Graphics2D) offImage.getGraphics();
        offG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        offG.setColor(new Color(132, 140, 255));
        offG.fillRect(0, 0, 600, 600);
        for(int i = 0; i < 100; ++i){
            Circles c = new Circles();
            offG.setColor(c.getColor());
            offG.fillOval(c.getX() - c.getRadius(), c.getY() - c.getRadius(), 2 * c.getRadius(), 2 * c.getRadius());
        }

    }

    private void makeButtons() {
        TextButton playButton = new TextButton("  Try  ");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screens.promptUserName();
                screens.restart();
                screens.showGameScreen();
            }
        });
        playButton.setBounds(330, 330, 140, 140);
        add(playButton);
    }

    public void paintComponent(Graphics g){
        g.drawImage(offImage, 0, 0, null);
    }
}

class PauseMenu extends JPanel{

    private BufferedImage backGround;
    private TextButton resumeButton, leaveButton, restartButton;
    private ScreenManager screenManager;


    public PauseMenu(ScreenManager screenManager){
        setLayout(null);
        makeButtons();
        this.screenManager = screenManager;
    }

    private void makeButtons() {
        resumeButton = new TextButton(" GoOn ");
        resumeButton.setBounds(220, 20, 140, 140);
        leaveButton = new TextButton(" Leave ");
        leaveButton.setBounds(220, 210, 140, 140);
        restartButton = new TextButton("Restart");
        restartButton.setBounds(220, 400, 140, 140);
        add(restartButton);
        add(leaveButton);
        add(resumeButton);
        this.revalidate();
        setUpTasks();
    }

    public void setUpTasks(){

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showGameScreen();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.restart();
            }
        });
        leaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showMenu();
            }
        });
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) backGround.getGraphics();
        g2d.setColor(Color.WHITE);
        AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        g2d.setComposite(acomp);
        g2d.fillRect(0, 0, backGround.getWidth(), backGround.getHeight());

        g.drawImage(backGround, 0, 0, null);
    }

    public void setBackGround(BufferedImage backGround){
        this.backGround = backGround;
    }
}
