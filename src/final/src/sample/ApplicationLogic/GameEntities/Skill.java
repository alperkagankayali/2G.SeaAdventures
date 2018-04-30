package sample.ApplicationLogic.GameEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.ApplicationLogic.GameEntities.Enemy;
import sample.ApplicationLogic.GameEntities.GameObject;
import sample.ApplicationLogic.GameEntities.Map;
import sample.ApplicationLogic.GameEntities.Submarine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Skill {

    private static final String LOCKED_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\skill_locked.png";
    private static final String COOLDOWN_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\skill_cd.png";
    private static final String SKILL1_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\skill_1.png";
    private static final String SKILL2_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\skill_2.png";
    private static final String SKILL3_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\skill_3.png";
    private static final String SUBMARINE_RED_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\Submarin_red.png";
    private static final String SUBMARINE_BLUE_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\Submarin_blue.png";
    private static final String SUBMARINE_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\Submarin.png";
    private static final String DECTRUCTION_IMAGE = System.getProperty("user.dir") +  "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\destruction.png";

    private double maxEffectTime;
    private double maxCooldownTime;

    //delegation pattern
    private GameObject object;
    private GameObject destr;
    private double timeOfEffect;
    private double cooldownTime;
    private int damage;
    private double finalAttackSpeed;
    private int skillID;
    private boolean onEffect;
    private boolean unlocked;
    private boolean onCooldown;
    private int energyCost;
    private int subLvl;

    //Constructor
    public Skill(int ID, int subLvl) throws FileNotFoundException {
        setSkillID( ID);
        destr = null;
        object = new GameObject(15 + 40 * (ID - 1), 435);
        object.setSpriteImage(new Image( new FileInputStream(LOCKED_IMAGE)));
        updateStats(subLvl);
        setTimeOfEffect(0);
        setCooldownTime(0);
        setOnEffect();
    }


    //Methods
    private void updateStats( int subLvl) throws FileNotFoundException {
        this.subLvl = subLvl;
        if( skillID == 1){
            maxEffectTime = 0.6;
            maxCooldownTime = 30 - 3 * ( subLvl - 1);
            setDamage( 30 + 10 * subLvl);
            setFinalAttackSpeed(0);
            setEnergyCost(30);
        }
        if( skillID == 2){
            maxEffectTime = 10;
            maxCooldownTime = 40 - 3 * ( subLvl - 2);
            setDamage( 0);
            setFinalAttackSpeed( subLvl);
            setEnergyCost(30);
        }
        if( skillID == 3){
            maxEffectTime = 10;
            maxCooldownTime = 30 - 3 * ( subLvl - 3);
            setDamage( 0);
            setFinalAttackSpeed(0);
            setEnergyCost(40);
        }
        setUnlocked(subLvl);
        restoreImages();
    }

    public void massDestruction(Map gameMap) throws FileNotFoundException {
        ArrayList<Enemy> enemies = gameMap.getVisibleEnemies();
        for( Enemy enemy: enemies){
            enemy.decreaseHealth( damage);
        }
        useSkill();
        setOnEffect();
        setTimeOfEffect( maxEffectTime);
        destr = new GameObject( 330, 125);
        destr.setSpriteImage( new Image( new FileInputStream(DECTRUCTION_IMAGE)));
    }
    public void speedBooster(Submarine sub) throws FileNotFoundException {
        sub.setAttackSpeed( finalAttackSpeed / 3);
        useSkill();
        setOnEffect();
        setTimeOfEffect( maxEffectTime);
        sub.setSpriteImage( new Image( new FileInputStream(SUBMARINE_RED_IMAGE)));
    }
    public void invulnerability(Submarine sub) throws FileNotFoundException {
        useSkill();
        setOnEffect();
        setTimeOfEffect( maxEffectTime);
        sub.setSpriteImage( new Image( new FileInputStream(SUBMARINE_BLUE_IMAGE)));
    }

    public void useSkill() throws FileNotFoundException {
        setCooldownTime( maxCooldownTime);
        object.setSpriteImage( new Image( new FileInputStream(COOLDOWN_IMAGE)));
    }


    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        if( energyCost >= 0)
            this.energyCost = energyCost;
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
                destr.setSpriteImage(null);
            }
            if( skillID == 2){
                sub.setAttackSpeed( finalAttackSpeed);
                object.setSpriteImage( new Image( new FileInputStream(COOLDOWN_IMAGE)));
            }
            if( skillID == 3){
                object.setSpriteImage( new Image( new FileInputStream(COOLDOWN_IMAGE)));
            }
        }
    }

    public double getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(double cooldownTime) {
        if( cooldownTime < 0){
            this.cooldownTime = 0;
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
        if( destr != null)
            destr.draw( g);
    }

    public void update( double time, Submarine sub) throws FileNotFoundException {
        if( sub.getSubLevel() != subLvl){
            updateStats( sub.getSubLevel());
        }
        if( isOnEffect()){
            updateTimeEffect( time, sub);
        }
        if( !isOnCooldown()){
            //System.out.println("entered here!");
            restoreImages();
        }else{
            cooldownTime -= time;
        }
    }

    private void restoreImages() throws FileNotFoundException {
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

    public boolean isOnCooldown() {
        setOnCooldown();
        return onCooldown;
    }

    public void setOnCooldown() {
        onCooldown = cooldownTime > 0;
    }
}