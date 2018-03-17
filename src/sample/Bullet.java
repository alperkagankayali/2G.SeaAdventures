package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bullet extends GameObject {
    private final String ID1_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\Yellow_projec_L.png";
    private final String ID2_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\Blue_projectile_R.png";
    private final String ID3_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\projectile_red_circle.png";

    private double damage;
    private int ID; // 1 for submarine bullets
                    // 2 for enemy bullets
                    // 3 for enemy skills

    // constructor
    public Bullet(Image image, double xPos, double yPos, double xVelocity, double yVelocity, double damage, int ID) {
        super(image, xPos, yPos, xVelocity, yVelocity, true, "Bullet");
        setDamage( damage);
        setID( ID);
    }

    // ID constructor
    public Bullet(double xPos, double yPos, double damage, int ID) throws FileNotFoundException {
        try{
            setID(ID);
            setDamage(damage);

            if( ID == 1){
                setLocation(xPos, yPos);
                setVelocity(450, 0);
                setSpriteImage( new Image(new FileInputStream(ID1_IMAGE)));
            }
            else if( ID == 2){
                setLocation(xPos, yPos);
                setVelocity(-300, 0);
                File imageFile = new File(ID2_IMAGE);
                Image image = SwingFXUtils.toFXImage(ImageIO.read(imageFile),null);
                setSpriteImage(image);
            }
            else if( ID == 3){
                setLocation(xPos, yPos);
                setVelocity(-500, 0);
                setSpriteImage( new Image(new FileInputStream(ID3_IMAGE)));
            }
            setType("Bullet");
        }catch (Exception e){
            e.printStackTrace();
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
