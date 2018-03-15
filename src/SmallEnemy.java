package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SmallEnemy extends Enemy {

    private final String HCRAB_IMAGE = "C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\Enemy_Crab.png";
    private final String ACRAB_IMAGE = "C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\Enemy_Crab2.png";
    private final String MINE_IMAGE = "C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\enemy_mine.png";

    SmallEnemy(Image image, double xPos, double  yPos, double xVelocity, double yVelocity, int health, int collisionDmg, int xp, int score, boolean visible){
        super(image, xPos, yPos, xVelocity, yVelocity, health, collisionDmg, xp, score, visible);
    }


    // map level constructor

    SmallEnemy( double xPos, double yPos, boolean visible, int mapLvl) throws FileNotFoundException {
        int choice = (int) (Math.random() * 3) + 1;

        setLocation(xPos, yPos);
        setVisible(visible);

        if (choice == 1) {
            // create Happy Crab
            setVelocity(-100, 0);
            setSpriteImage(new Image(new FileInputStream(HCRAB_IMAGE)));
            setHealth(10 + 5 * mapLvl);
            setCollisionDmg(10 + 5 * mapLvl);
            setExperiencePrize(20 + 10 * mapLvl);
            setScorePrize( 20 + 10 * mapLvl);
        } else if (choice == 2) {
            // create Angry Crab
            setVelocity(-200, 0);
            setSpriteImage(new Image(new FileInputStream(ACRAB_IMAGE)));
            setHealth(10 + 5 * mapLvl);
            setCollisionDmg(10 + 10 * mapLvl);
            setExperiencePrize(20 + 20 * mapLvl);
            setScorePrize( 20 + 20 * mapLvl);
        } else {
            // create Mine
            setVelocity(-75, 0);
            setSpriteImage(new Image(new FileInputStream(MINE_IMAGE)));
            setHealth(20 + 15 * mapLvl);
            setCollisionDmg(20 + 15 * mapLvl);
            setExperiencePrize(20 + 20 * mapLvl);
            setScorePrize( 20 + 20 * mapLvl);
        }
    }


    // default constructor
    SmallEnemy(){
    }

}
