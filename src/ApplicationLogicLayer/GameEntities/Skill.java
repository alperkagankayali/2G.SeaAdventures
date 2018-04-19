package GameEntities;

public class Skill {

//Attributes
private int cooldownTime;
private int damage;
private int timeOfEffect;
private Health health;
private double finalAttackSpeed;
private int skillID;

//Constructor
public Skill(int cooldownTime, int damage, int timeOfEffect, int attackS, Health health) {
	
	this.cooldownTime = cooldownTime;
	this.damage = damage;
	this.timeOfEffect = timeOfEffect;
	this.finalAttackSpeed = attackS;
	this.health = health;
}


//Methods
private void affectMassDestruction(Map gameMap) {
	// Decrease health of everything in the map.
	
	//Map -> get all enemies.
	//All enemies -> decrease health.
	/*
	 * Map.enemies ? --> List of all enemies
	 * while(traverse list){
	 *       enemy.health.reduce() ??
	 * }
	 */
}
private void changeAttackSpeed() {
	//Changes attack speed of submarine
	Submarine.setAttackSpeed(finalAttackSpeed); 

}
private void affectInvulnerability() {
	//Changes invulnerability of submarine?? Increase health?
	//10 saniye can gitmeyecek. submarine�in can�n� ayn� kalcak �ekilde updatele. 

	/*
	health = submarine.getHealth();
	cooldown = new CooldownManager(1, affectInvulnerabilityCooldown, 10);
	while (coolDown.isInCooldown){
		submarine.setHealth (health);
	}
*/	
}
}
