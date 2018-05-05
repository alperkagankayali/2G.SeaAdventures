package sample.ApplicationLogic.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BigEnemy extends Enemy{

    private final String JELLY_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\Enemy_Medusa.png";
    private final String SEAHORSE_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\enemy_seahorse.png";
    private final String CORALL_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\enemy_corall.png";

    private double attackSpeed;
    private double shootCooldown;
    private int amountOfProjectile;
    private ArrayList<Bullet> bullets;
    private int attackDamage;

    // constructor
    public BigEnemy(Image image, double xPos, double yPos, double xVelocity, double yVelocity, int health, int collisionDmg, int xp, int score, boolean visibility, double attackSpeed, int amountOfProjectile, int attackDamage) {
        super(image, xPos, yPos, xVelocity, yVelocity, health, collisionDmg, xp, score, visibility, "Big Enemy");
        setAttackSpeed(attackSpeed);
        setAmountOfProjectile(amountOfProjectile);
        setAttackDamage(attackDamage);
        bullets = new ArrayList<>();
    }

    // default constructor
    public BigEnemy(){
    }
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
            setVelocity(-10, 25);
            setSpriteImage(new Image(new FileInputStream(SEAHORSE_IMAGE)));
            setHealth(30 + 30 * mapLvl);
            setCollisionDmg(20 + 15 * mapLvl);
            setExperiencePrize(25 + 25 * mapLvl);
            setScorePrize( 25 + 25 * mapLvl);
            setAttackDamage(10 + 8 * mapLvl);
            setAmountOfProjectile(1);
            setAttackSpeed(1.25);
        }
        setType("Big Enemy");
        bullets = new ArrayList<>();
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

    public void setAttackDamage(int attackDamage) {
        if( attackDamage >= 0)
            this.attackDamage = attackDamage;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /*  index 0 = damage of the bullets
            index 1 = x position of bullets
            others = y positions of bullets
         */
    public void shoot(){
        try{
            if( shootCooldown <= 0 && isVisible()) {
                double[] arr = new double[amountOfProjectile + 2];
                arr[1] = getXPos();
                for (int i = 2; i < amountOfProjectile + 2; i++) {
                    arr[i] = getYPos() + (getSpriteImage().getHeight() / 2) + (12 * (int)(( i - 1)  / 2) * Math.pow(-1, i - 1));
                    Bullet x = new Bullet(arr[1], arr[i], attackDamage, 2);
                    bullets.add(x);
                }
                shootCooldown = attackSpeed;

            }
            double[] arr = {-1};

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(double time){
        super.update(time);
        if( shootCooldown > 0)
            shootCooldown = shootCooldown - time;
        if( (getYPos() > 430 - getHeight()) || (getYPos() <= 10))
            setVelocity(getXVelocity(), -getYVelocity());
    }

    @Override
    public void disappearAnimation() throws FileNotFoundException {
        super.disappearAnimation();
    }
}
