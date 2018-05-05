package sample.ApplicationLogic.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PowerUp extends GameObject {
    private final String ID1_IMAGE = System.getProperty("user.dir") +  "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\powerUp_regen.png";
    private final String ID2_IMAGE = System.getProperty("user.dir") +  "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\pwup_fuel_energy.png";

    private int quantityOfEffect;
    private int ID;
    private int score;
    private boolean used;

    // default constructor
    public PowerUp() {
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    // ID constructor
    public PowerUp( double xPos, double yPos, int ID, int mapLvl) throws FileNotFoundException {
        super(xPos, yPos);
        setID( ID);

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
        setType("Power Up");
        used = false;

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

    public boolean isUsed() {
        return used;
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update(time);
        if(used){
            disappearAnimation();

        }
        if( ( (getYPos() > 480 - getHeight()) && getYVelocity() > 0) || (getYPos() < 10 && getYVelocity() < 0))
            setVelocity(getXVelocity(), -getYVelocity());
    }
}
