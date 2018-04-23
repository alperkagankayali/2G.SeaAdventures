package ApplicationLogicLayer.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bullet extends GameObject {
    private final String ID1_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Yellow_projec_L.png";
    private final String ID2_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Blue_projectile_R.png";
    private final String ID3_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\projectile_red_circle.png";

    private double damage;
    private int ID; // 1 for submarine bullets
                    // 2 for enemy bullets
                    // 3 for enemy skills

    // ID constructor
    public Bullet(double xPos, double yPos, double damage, int ID) throws FileNotFoundException {
        super(xPos, yPos);

        setID(ID);
        setDamage(damage);

        if( ID == 1){

            setVelocity(450, 0);
            setSpriteImage( new Image(new FileInputStream(ID1_IMAGE)));
        }
        else if( ID == 2){

            setVelocity(-300, 0);
            setSpriteImage( new Image(new FileInputStream(ID2_IMAGE)));
        }
        else if( ID == 3){

            setVelocity(-500, 0);
            setSpriteImage( new Image(new FileInputStream(ID3_IMAGE)));
        }
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        if( damage >= 0)
            this.damage = damage;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        if( ID == 1 || ID == 2 || ID ==3)
            this.ID = ID;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }
}
