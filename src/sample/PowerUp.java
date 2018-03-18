package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PowerUp extends GameObject {
    private final String ID1_IMAGE = "C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\powerUp_regen.png";
    private final String ID2_IMAGE = "C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\pwup_fuel_energy.png";

    private int quantityOfEffect;
    private int ID;
    private int score;

    // constructor
    public PowerUp(Image image, double xPos, double yPos, double xVelocity, double yVelocity, int quantityOfEffect, int ID, int score) {
        super(image, xPos, yPos, xVelocity, yVelocity, true);
        setID( ID); // 1 = Health regeneration
                    // 2 = Energy regeneration
        setQuantityOfEffect( quantityOfEffect);
        setScore( score);
    }

    // default constructor
    public PowerUp() {
    }

    // ID constructor
    public PowerUp( double xPos, double yPos, int ID, int mapLvl, boolean visible) throws FileNotFoundException {
        setVisible( visible);
        setID( ID);
        setLocation( xPos, yPos);
        setScore( 100);
        if( ID == 1){
            setQuantityOfEffect( 25 + 25 * mapLvl);
            setSpriteImage(new Image(new FileInputStream(ID1_IMAGE)));
            setVelocity(-50, -50);
        }
        else if( ID == 2){
            setQuantityOfEffect( 30 + 10 * mapLvl);
            setSpriteImage(new Image(new FileInputStream(ID2_IMAGE)));
            setVelocity(-50, -50);
        }
    }

    public int getQuantityOfEffect() {
        return quantityOfEffect;
    }

    public void setQuantityOfEffect(int quantityOfEffect) {
        if( quantityOfEffect >= 0)
            this.quantityOfEffect = quantityOfEffect;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        if( ID == 1 || ID == 2)
            this.ID = ID;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if( score >= 0)
            this.score = score;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update(time);
        if( ( (getYPos() > 480 - getHeight()) && getYVelocity() > 0) || (getYPos() < 10 && getYVelocity() < 0))
            setVelocity(getXVelocity(), -getYVelocity());
    }
}
