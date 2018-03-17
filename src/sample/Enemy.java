package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Enemy extends GameObject{

    private final String DEATH_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\explosion.png";

    private int health;
    private int collisionDmg;
    private int experiencePrize;
    private int scorePrize;

    Enemy(Image image, double xPos, double  yPos, double xVelocity, double yVelocity, int health, int collisionDmg, int xp, int score, boolean visible, String type){
        super( image, xPos, yPos, xVelocity, yVelocity, visible, type );
        setHealth( health);
        setCollisionDmg( collisionDmg);
        setExperiencePrize( xp);
        setScorePrize( score);
    }

    //Map lvl constructor
    Enemy( double xPos, double yPos, boolean visible, int mapLvl){
    }

    // default constructor
    Enemy(){
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update(time);
        if( health <= 0 || getYPos() <= 0 || getXPos() <= 0){
            disappearAnimation();
        }
    }

    @Override
    public void disappearAnimation() throws FileNotFoundException {
        try{
            Image death_image = new Image(new FileInputStream(DEATH_IMAGE));
            setSpriteImage(death_image);
            setVelocity(0, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
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