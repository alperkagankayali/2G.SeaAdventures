package ApplicationLogicLayer.GameEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Skill {

    private static final String LOCKED_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\skill_locked.png";
    private static final String COOLDOWN_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\skill_cd.png";
    private static final String SKILL1_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\skill_1.png";
    private static final String SKILL2_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\skill_2.png";
    private static final String SKILL3_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\skill_3.png";
    private static final String SUBMARINE_RED_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Submarin_red.png";
    private static final String SUBMARINE_BLUE_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Submarin_blue.png";
    private static final String SUBMARINE_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Submarin.png";

    //Attributes
    private double maxEffectTime;
    private double maxCooldownTime;

    //delegation pattern
    private GameObject object;
    private double timeOfEffect;
    private double cooldownTime;
    private int damage;
    private double finalAttackSpeed;
    private int skillID;
    private boolean onEffect;
    private boolean unlocked;

    //Constructor
    public Skill(int ID, int subLvl) throws FileNotFoundException {
        setSkillID( ID);
        object = new GameObject(10 + 40 * (ID - 1), 51);
        object.setSpriteImage(new Image( new FileInputStream(LOCKED_IMAGE)));
        if( ID == 1){
            maxEffectTime = 0;
            maxCooldownTime = 30 - 3 * ( subLvl - 1);
            setDamage( 30 + 10 * subLvl);
            setFinalAttackSpeed(0);
        }
        if( ID == 2){
            maxEffectTime = 10;
            maxCooldownTime = 40 - 5 * ( subLvl - 2);
            setDamage( 0);
            setFinalAttackSpeed( subLvl);
        }
        if( ID == 3){
            maxEffectTime = 10;
            maxCooldownTime = 30 - 3 * ( subLvl - 3);
            setDamage( 0);
            setFinalAttackSpeed(0);
        }
        setTimeOfEffect(0);
        setCooldownTime(0);
        setOnEffect();
        setUnlocked(subLvl);
    }


    //Methods
    public void massDestruction(Map gameMap) throws FileNotFoundException {
        ArrayList<Enemy> enemies = gameMap.getVisibleEnemies();
        for( Enemy enemy: enemies){
            enemy.decreaseHealth( damage);
        }
        setCooldownTime( maxCooldownTime);

    }
    public void speedBooster(Submarine sub) throws FileNotFoundException {
        sub.setAttackSpeed( finalAttackSpeed / 2);
        setCooldownTime( maxCooldownTime);
        setOnEffect();
        setTimeOfEffect( maxEffectTime);
        sub.setSpriteImage( new Image( new FileInputStream(SUBMARINE_RED_IMAGE)));
    }
    public void invulnerability(Submarine sub) throws FileNotFoundException {
        setCooldownTime( maxCooldownTime);
        setOnEffect();
        setTimeOfEffect( maxEffectTime);
        sub.setSpriteImage( new Image( new FileInputStream(SUBMARINE_BLUE_IMAGE)));
    }

    public void useSkill() throws FileNotFoundException {
        setCooldownTime( maxCooldownTime);
        object.setSpriteImage( new Image( new FileInputStream(COOLDOWN_IMAGE)));
    }

    public double getTimeOfEffect() {
        return timeOfEffect;
    }

    public void setTimeOfEffect(double timeOfEffect) {
        if( timeOfEffect < 0){
            this.timeOfEffect = 0;
        }
        else
            this.timeOfEffect = timeOfEffect;
    }

    public void updateTimeEffect(double time, Submarine sub) throws FileNotFoundException {
        setTimeOfEffect( timeOfEffect - time);
        if( timeOfEffect == 0){
            sub.setSpriteImage( new Image( new FileInputStream(SUBMARINE_IMAGE)));
            if( skillID == 1){
                object.setSpriteImage( new Image( new FileInputStream(SKILL1_IMAGE)));
            }
            if( skillID == 2){
                sub.setAttackSpeed( finalAttackSpeed);
                object.setSpriteImage( new Image( new FileInputStream(SKILL2_IMAGE)));
            }
            if( skillID == 3){
                object.setSpriteImage( new Image( new FileInputStream(SKILL3_IMAGE)));
            }
        }
    }

    public double getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(double cooldownTime) {
        if( cooldownTime < 0){
            cooldownTime = 0;
        }
        else
            this.cooldownTime = cooldownTime;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if(damage >= 0) {
            this.damage = damage;
        }
    }

    public double getFinalAttackSpeed() {
        return finalAttackSpeed;
    }

    public void setFinalAttackSpeed(double finalAttackSpeed) {
        this.finalAttackSpeed = finalAttackSpeed;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        if( skillID == 1 || skillID == 2 || skillID == 3) {
            this.skillID = skillID;
        }
    }

    public boolean isOnEffect() {
        return timeOfEffect > 0;
    }

    public void setOnEffect() {
        onEffect = timeOfEffect > 0;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(int subLevel) {
        unlocked = subLevel >= skillID;
    }

    public void draw(GraphicsContext g){
        object.draw( g);
    }

    public void update( double time, Submarine sub) throws FileNotFoundException {
        if( isOnEffect()){
            updateTimeEffect( time, sub);
        }
        if( isUnlocked() ){
            if( skillID == 1){
                object.setSpriteImage( new Image( new FileInputStream(SKILL1_IMAGE)));
            }
            if( skillID == 2){
                object.setSpriteImage( new Image( new FileInputStream(SKILL2_IMAGE)));
            }
            if( skillID == 3){
                object.setSpriteImage( new Image( new FileInputStream(SKILL3_IMAGE)));
            }
        }
    }
}
