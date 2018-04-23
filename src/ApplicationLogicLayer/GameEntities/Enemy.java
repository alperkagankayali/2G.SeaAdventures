package ApplicationLogicLayer.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Enemy extends GameObject{

    private final String DEATH_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\explosion.png";

    private int health;
    private int collisionDmg;
    private int experiencePrize;
    private int scorePrize;

    // constructor
    Enemy(double xPos, double yPos){
        super(xPos, yPos);
    }

    // default constructor
    Enemy(){
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update(time);
        if( health <= 0)
            disappearAnimation();
    }

    @Override
    public void disappearAnimation() throws FileNotFoundException {
        Image death_image = new Image(new FileInputStream(DEATH_IMAGE));
        setSpriteImage(death_image);
        setVelocity(0, 0);
    }

    public int getScorePrize() {
        return scorePrize;
    }

    public void setScorePrize(int scorePrize) {
        if( scorePrize >= 0)
            this.scorePrize = scorePrize;
        else
            throw new IndexOutOfBoundsException("Not valid experiencePrize value");
    }

    public int getCollisionDmg() {
        return collisionDmg;
    }

    public void setCollisionDmg(int collisionDmg) {
        if( collisionDmg >= 0)
            this.collisionDmg = collisionDmg;
        else
            throw new IndexOutOfBoundsException("Not valid experiencePrize value");
    }

    public int getExperiencePrize() {
        return experiencePrize;
    }

    public void setExperiencePrize(int experiencePrize) {
        if( experiencePrize >= 0 )
            this.experiencePrize = experiencePrize;
        else
            throw new IndexOutOfBoundsException("Not valid experiencePrize value");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if( health >= 0)
            this.health = health;
        else
            this.health = 0;
    }
}
