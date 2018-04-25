package ApplicationLogicLayer.GameEntities;

import javafx.scene.image.Image;

import javax.swing.plaf.SeparatorUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Boss extends BigEnemy {

    private final String KRAKEN_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\boss_kraken.png";
    private final String WHALE_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\boss_whale.png";
    private final String OCTOPUS_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\boss_octopus.png";

    private double abilityCooldown;
    private double currentCooldown;
    private double abilityDamage;

    public Boss() {
    }

    public Boss(double xPos, double yPos, int mapLvl) throws FileNotFoundException {
        super(xPos, yPos);

        // setting boss image
        if( mapLvl == 1){
            setSpriteImage(new Image(new FileInputStream(OCTOPUS_IMAGE)));
        }
        else if( mapLvl == 2){
            setSpriteImage(new Image(new FileInputStream(WHALE_IMAGE)));
        }
        else{
            setSpriteImage(new Image(new FileInputStream(KRAKEN_IMAGE)));
        }

        setVelocity(0, -50);
        setHealth(100 + 150 * mapLvl);
        setCollisionDmg(100);
        setExperiencePrize(150 + 200 * mapLvl);
        setScorePrize( 150 + 200 * mapLvl);
        setAttackDamage(10 + 5 * mapLvl);
        setAmountOfProjectile( 1 + 2 * mapLvl);
        setAttackSpeed(1);
        setAbilityCooldown( 15 - 3 * mapLvl);
        setAbilityDamage( 20 + 5 * mapLvl);
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

    public double getAbilityDamage() {
        return abilityDamage;
    }

    public void setAbilityDamage(double abilityDamage) {
        if( abilityDamage >= 0)
            this.abilityDamage = abilityDamage;
        else
            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
    }

    /*  index 0 = damage of the bullets
        index 1 = ID of bullets
        index 2 = x position of bullets
        index 3 = y position of bullets
     */
    public double[] useAbility(){
        if( currentCooldown <= 0)
        {
            double[] arr = {getAbilityDamage(), 3, getXPos(), getYPos() + getSpriteImage().getHeight() / 2};
            return arr;
        }
        else {
            double[] arr = {-1};
            return arr;
        }
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update(time);
        if( currentCooldown > 0)
            currentCooldown -= time;
    }
}
