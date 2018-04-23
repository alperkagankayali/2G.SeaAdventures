package ApplicationLogicLayer.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SmallEnemy extends Enemy {

    private final String HCRAB_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Enemy_Crab.png";
    private final String ACRAB_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Enemy_Crab2.png";
    private final String MINE_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\enemy_mine.png";

    // map level constructor
    SmallEnemy( double xPos, double yPos, int mapLvl) throws FileNotFoundException {
        super(xPos, yPos);

        int choice = (int) (Math.random() * 3) + 1;

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
