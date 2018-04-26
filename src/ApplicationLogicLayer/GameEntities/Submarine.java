package ApplicationLogicLayer.GameEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Submarine extends GameObject {
    private static final String SUB_IMAGE = System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\Submarin.png";
    private static int MAX_SUB_LEVEL = 5;
    private static int VELOCITY = 50;

    private static Submarine submarine;

    private int subLevel;
    private Health health;
    private Energy energy;
//    private Experience experience;
    private SkillManager skills;
    private int amountOfProjectile;
    private double attackSpeed;
    private double attackCooldown;
    private int attackDamage;

    // default constructor
    public Submarine(){}

    private Submarine( int lvl) throws FileNotFoundException {
        super(0, 200);
        setSubLevel(lvl);
        setSpriteImage( new Image(new FileInputStream(SUB_IMAGE)));
        updateStats();
        skills = new SkillManager(subLevel);
    }

    private void updateStats() throws FileNotFoundException {
        health = new Health( subLevel);
        energy = new Energy( subLevel);
        setAmountOfProjectile( (1 + subLevel) / 2);
        setAttackSpeed(1.45 - 0.05 * subLevel);
        setAttackDamage(15 + 3 * subLevel);
    }

    public static Submarine getSubmarine() throws FileNotFoundException {
        if (null == submarine) {
            submarine = new Submarine(1);
        }
        return submarine;
    }

    public int getSubLevel() {
        return subLevel;
    }

    public void setSubLevel(int subLevel) {
        if( subLevel >= 1 && subLevel <= MAX_SUB_LEVEL) {
            this.subLevel = subLevel;
        }
    }

    public void useSkill( int ID) throws FileNotFoundException {
        if(skills.getSkill(ID) != null && skills.getSkill(ID).getEnergyCost() <= energy.getEnergyAmount() && !skills.getSkill(ID).isOnCooldown()){
            energy.update( -skills.getSkill(ID).getEnergyCost());
            if(ID == 1){
                skills.getSkill(ID).massDestruction( Map.getMap());
            }
            if( ID == 2){
                skills.getSkill(ID).speedBooster( getSubmarine());
            }
            if( ID == 3){
                skills.getSkill(ID).invulnerability( getSubmarine());
            }
        }
    }

    public void healthDecrease(int dmg) throws FileNotFoundException {
        if( skills.getSkill(3) == null){
            health.update(-dmg);
        }
        else if ( !skills.getSkill(3).isOnEffect()) {
            health.update(-dmg);
        }
    }

    public void regenHealth( PowerUp pu) throws FileNotFoundException {
        health.update( pu.getQuantityOfEffect());
    }

    public void regenEnergy( PowerUp pu) throws FileNotFoundException {
        energy.update( pu.getQuantityOfEffect());
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        if( attackSpeed >= 0) {
            this.attackSpeed = attackSpeed;
            this.attackCooldown = attackSpeed;
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

    /*  index 0 = damage of the bullets
        index 1 = ID of bullets
        index 2 = x position of bullets
        others = y positions of bullets
     */
    public double[] shoot(){
        if( attackCooldown <= 0) {
            double[] arr = new double[amountOfProjectile + 3];
            arr[0] = attackDamage;
            arr[1] = 1;
            arr[2] = getXPos();
            for (int i = 0; i < amountOfProjectile; i++) {
                arr[i + 3] = getYPos() + (getSpriteImage().getHeight() / 2) + (12 * (int)(( i + 1)  / 2) * Math.pow(-1, i + 1));
            }
            attackCooldown = attackSpeed;
            return arr;
        }
        double[] arr = {-1};
        return arr;
    }

    @Override
    public void update(double time) throws FileNotFoundException {
        super.update( time);
        skills.update( time, this);
        if( attackCooldown > 0)
            attackCooldown = attackCooldown - time;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        health.draw(gc);
        energy.draw(gc);
        skills.draw(gc);
    }
}
