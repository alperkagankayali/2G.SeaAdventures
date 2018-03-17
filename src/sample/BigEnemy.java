package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BigEnemy extends Enemy{

    private final String JELLY_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\Enemy_Medusa.png";
    private final String OCTO_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\enemy_octopus.png";
    private final String CORALL_IMAGE = "C:\\Users\\Alper\\IdeaProjects\\draftproject\\src\\sample\\enemy_corall.png";

    private double attackSpeed;
    private double shootCooldown;
    private int amountOfProjectile;
    private ArrayList<Bullet> bullets;
    private double attackDamage;

    // constructor
    public BigEnemy(Image image, double xPos, double yPos, double xVelocity, double yVelocity, int health, int collisionDmg, int xp, int score, boolean visibility, double attackSpeed, int amountOfProjectile, double attackDamage) {
        super(image, xPos, yPos, xVelocity, yVelocity, health, collisionDmg, xp, score, visibility, "Big Enemy");
        setAttackSpeed(attackSpeed);
        setAmountOfProjectile(amountOfProjectile);
        setAttackDamage(attackDamage);
        bullets = new ArrayList<>();
    }

    // default constructor
    public BigEnemy(){
    }

    // mapLvl contructor
    public BigEnemy(double xPos, double yPos, boolean visibility, int mapLvl) throws FileNotFoundException {
        int choice = (int) (Math.random() * 3) + 1;

        setLocation(xPos, yPos);
        setVisible(visibility);

        if (choice == 1) {
            // create Jelly
            setVelocity(-50, -150);
            setSpriteImage(new Image(new FileInputStream(JELLY_IMAGE)));
            setHealth(25 + 25 * mapLvl);
            setCollisionDmg(20 + 10 * mapLvl);
            setExperiencePrize(50 + 25 * mapLvl);
            setScorePrize( 50 + 25 * mapLvl);
            setAttackDamage(5 + 5 * mapLvl);
            setAmountOfProjectile( mapLvl);
            setAttackSpeed(1);
        } else if (choice == 2) {
            // create Octobus
            setVelocity(-50, -50);
            setSpriteImage(new Image(new FileInputStream(OCTO_IMAGE)));
            setHealth(50 + 35 * mapLvl);
            setCollisionDmg(20 + 10 * mapLvl);
            setExperiencePrize(75 + 50 * mapLvl);
            setScorePrize( 75 + 50 * mapLvl);
            setAttackDamage(5 + 3 * mapLvl);
            setAmountOfProjectile(6);
            setAttackSpeed(2.5);
        } else {
            // create Corall
            setVelocity(-50, -100);
            setSpriteImage(new Image(new FileInputStream(CORALL_IMAGE)));
            setHealth(30 + 30 * mapLvl);
            setCollisionDmg(20 + 15 * mapLvl);
            setExperiencePrize(25 + 25 * mapLvl);
            setScorePrize( 25 + 25 * mapLvl);
            setAttackDamage(5 + 5 * mapLvl);
            setAmountOfProjectile(1);
            setAttackSpeed(0.5);
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

    public void setAttackDamage(double attackDamage) {
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
            if( shootCooldown <= 0) {
                double[] arr = new double[amountOfProjectile + 2];
                arr[0] = attackDamage;
                arr[1] = getXPos();
                for (int i = 2; i < amountOfProjectile + 2; i++) {
                    arr[i] = getYPos() + (getSpriteImage().getHeight() / 2) + (12 * (int)(( i - 1)  / 2) * Math.pow(-1, i - 1));
                    Bullet x = new Bullet(arr[1], arr[i], arr[0], 2);
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
    public void update(double time) throws FileNotFoundException {
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
