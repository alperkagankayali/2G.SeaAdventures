import java.util.ArrayList;

public class SkillManager {

//Attributes
private int subLevel;
private ArrayList<Skill> skills; 

private int basiccooldownTime;
private int basicdamage;
private int basictimeOfEffect;
private Health basichealth;
private double basicfinalAttackSpeed;

//Constructor
public SkillManager(int subLevel) {
	this.subLevel = subLevel;
}
	
//Methods
public void update(Submarine s ) {
	if(s.level != subLevel){
	subLevel = s.level;
}
}
public ArrayList<Skill> getSkills(int subLevel){
	return skills;
}
public Skill getSkill1() {
	return skills.get(0);
}
public Skill getSkill2() {
	return skills.get(1);
}
public Skill getSkill3() {
	return skills.get(2);
}	
}
