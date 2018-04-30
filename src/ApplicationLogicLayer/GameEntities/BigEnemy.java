package ApplicationLogicLayer.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BigEnemy extends ApplicationLogicLayer.GameEntities.Enemy {

    private final String JELLY_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Enemy_Medusa.png";
    private final String SEAHORSE_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\enemy_seahorse.png";
    private final String CORALL_IMAGE = System.getProperty("user.dir") + "\\src\\ApplicationLogicLayer\\GameEntities\\images\\enemy_corall.png";

    private double attackSpeed;
    private double shootCooldown;
    private int amountOfProjectile;
    private double attackDamage;

    // constructor
    BigEnemy(double xPos, double yPos){
        super(xPos, yPos);
    }

    // mapLvl contructor
    public BigEnemy(double xPos, double yPos, int mapLvl) throws FileNotFoundException {
        super(xPos, yPos);

        int choice = (int) (Math.random() * 3) + 1;

        if (choice == 1) {
            // create Coral
            setVelocity(-10, -150);
            setSpriteImage(new Image(new FileInputStream(CORALL_IMAGE)));
            setHealth(25 + 25 * mapLvl);
            setCollisionDmg(20 + 10 * mapLvl);
            setExperiencePrize(50 + 25 * mapLvl);
            setScorePrize( 50 + 25 * mapLvl);
            setAttackDamage(10 + 5 * mapLvl);
            setAmountOfProjectile( mapLvl);
            setAttackSpeed(2);
        } else if (choice == 2) {
            // create Jelly
            setVelocity(-10, -50);
            setSpriteImage(new Image(new FileInputStream(JELLY_IMAGE)));
            setHealth(50 + 35 * mapLvl);
            setCollisionDmg(20 + 10 * mapLvl);
            setExperiencePrize(75 + 50 * mapLvl);
            setScorePrize( 75 + 50 * mapLvl);
            setAttackDamage(9 + 3 * mapLvl);
            setAmountOfProjectile(5);
            setAttackSpeed(4);
        } else {
            // create Sea horse
            setVelocity(0, 25);
            setSpriteImage(new Image(new FileInputStream(SEAHORSE_IMAGE)));
            setHealth(30 + 30 * mapLvl);
            setCollisionDmg(20 + 15 * mapLvl);
            setExperiencePrize(25 + 25 * mapLvl);
            setScorePrize( 25 + 25 * mapLvl);
            setAttackDamage(10 + 8 * mapLvl);
            setAmountOfProjectile(1);
            setAttackSpeed(1.25);
        }

    }

    // default constructor
    public BigEnemy(){
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        if( attackSpeed >= 0) {
            this.attackSpeed = attackSpeed;
            this.shootCooldown = 0;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public int getAmountOfProjectile() {
        return amountOfProjectile;
    }

    public void setAmountOfProjectile(int amountOfProjectile) {
        if( amountOfProjectile >= 0)
            this.amountOfProjectile = amountOfProjectile;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(double attackDamage) {
        if( attackDamage >= 0)
            this.attackDamage = attackDamage;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    /*  index 0 = damage of the bullets
        index 1 = ID of bullets
        index 2 = x position of bullets
        others = y positions of bullets
     */
    public double[] shoot(){
        if( shootCooldown <= 0 && isVisible()) {
            double[] arr = new double[amountOfProjectile + 2];
            arr[0] = attackDamage;
            arr[1] = 2;
            arr[2] = getXPos();
            for (int i = 0; i < amountOfProjectile; i++) {
                arr[i + 3] = getYPos() + (getSpriteImage().getHeight() / 2) + (12 * (int)(( i + 1)  / 2) * Math.pow(-1, i + 1));
            }
            shootCooldown = attackSpeed;
            return arr;
        }
        double[] arr = {-1};
        return arr;
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update(time);
        if( shootCooldown > 0)
            shootCooldown = shootCooldown - time;
    }

    @Override
    public void disappearAnimation() throws FileNotFoundException {
        super.disappearAnimation();
    }
}
