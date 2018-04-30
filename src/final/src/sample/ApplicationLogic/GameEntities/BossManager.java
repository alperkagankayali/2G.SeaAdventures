package sample.ApplicationLogic.GameEntities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BossManager extends BigEnemy {

    private final String KRAKEN_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\boss_kraken.png";

    private double abilityCooldown;
    private double currentCooldown;
    private double quantityOfEffect;

    public BossManager(Image image, double xPos, double yPos, double xVelocity, double yVelocity, int health, int collisionDmg, int xp, int score, boolean visibility, double attackSpeed, int amountOfProjectile, int attackDamage, double abilityCooldown, double quantityOfEffect) {
        super(image, xPos, yPos, xVelocity, yVelocity, health, collisionDmg, xp, score, visibility, attackSpeed, amountOfProjectile, attackDamage);
        this.abilityCooldown = abilityCooldown;
        this.quantityOfEffect = quantityOfEffect;
    }

    public BossManager() {
    }

    public BossManager(double xPos, double yPos, boolean visibility, int mapLvl) throws FileNotFoundException {
        // create Kraken
        setLocation(xPos, yPos);
        setVelocity(-150, -50);
        setSpriteImage(new Image(new FileInputStream(KRAKEN_IMAGE)));
        setHealth(100 + 150 * mapLvl);
        setCollisionDmg(100);
        setExperiencePrize(150 + 200 * mapLvl);
        setScorePrize( 150 + 200 * mapLvl);
        setAttackDamage(10 + 5 * mapLvl);
        setAmountOfProjectile( 1 + 2 * mapLvl);
        setAttackSpeed(1);
        setAbilityCooldown( 15 - 3 * mapLvl);
        setQuantityOfEffect( 20 + 5 * mapLvl);
        setVisible(visibility);
        setType("Boss");
    }

    public double getAbilityCooldown() {
        return abilityCooldown;
    }

    public void setAbilityCooldown(double abilityCooldown) {
        if( abilityCooldown >= 0) {
            this.abilityCooldown = abilityCooldown;
            currentCooldown = abilityCooldown;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public double getCurrentCooldown() {
        return currentCooldown;
    }

    public double getQuantityOfEffect() {
        return quantityOfEffect;
    }

    public void setQuantityOfEffect(double quantityOfEffect) {
        if( quantityOfEffect >= 0)
            this.quantityOfEffect = quantityOfEffect;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    public double[] useAbility(){
        if( currentCooldown <= 0)
        {
            double[] arr = {getXPos(), getYPos() + getSpriteImage().getHeight() / 2, quantityOfEffect};
            return arr;
        }
        else {
            double[] arr = {-1};
            return arr;
        }
    }

    @Override
    public void update(double time){
        super.update(time);
        if( currentCooldown > 0)
            currentCooldown -= time;
        if(getXPos() <= 850){
            setVelocity(0,-50);
        }
    }
}
