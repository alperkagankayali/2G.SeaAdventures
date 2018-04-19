package GameEntities;//package sample;
//
//public class Submarine extends GameObject {
//    private int subLvl;
//    private Health health;
//    private Energy energy;
//    private Experience experience;
//    private SkillManager skills;
//    private int amountOfProjectile;
//    private double attackSpeed;
//    private double shootCooldown;
//    private double attackDamage;
//
//    public Submarine( int lvl){
//
//    }
//
//    public void updateExperience(){
//
//    }
//
//    public void updateScore(){
//
//    }
//
//    public void useSkill( int ID){
//
//    }
//
//    public void healthDecrease( int dmg){
//
//    }
//
//    public void regenHealth( PowerUp pu){
//
//    }
//
//    public void energyDecrease( SkillManager sm ){
//
//    }
//
//    public double getAttackSpeed() {
//        return attackSpeed;
//    }
//
//    public void regenEnergy( PowerUp pu){
//
//    }
//
//    public void setAttackSpeed(double attackSpeed) {
//        if( attackSpeed >= 0) {
//            this.attackSpeed = attackSpeed;
//            this.shootCooldown = attackSpeed;
//        }
//        else
//            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
//    }
//
//    public int getAmountOfProjectile() {
//        return amountOfProjectile;
//    }
//
//    public void setAmountOfProjectile(int amountOfProjectile) {
//        if( amountOfProjectile >= 0)
//            this.amountOfProjectile = amountOfProjectile;
//        else
//            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
//    }
//
//    public double getAttackDamage() {
//        return attackDamage;
//    }
//
//    public void setAttackDamage(double attackDamage) {
//        if( attackDamage >= 0)
//            this.attackDamage = attackDamage;
//        else
//            throw new ArrayIndexOutOfBoundsException("Invalid value is entered");
//    }
//
//    /*  index 0 = damage of the bullets
//        index 1 = x position of bullets
//        others = y positions of bullets
//     */
//    public double[] shoot(){
//    }
//
//    @Override
//    public void update(double time) {
//        if( shootCooldown > 0)
//            shootCooldown = shootCooldown - time;
//    }
//}
