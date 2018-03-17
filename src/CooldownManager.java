import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

//Attributes:
	
	 private static Map<String, CooldownManager> cooldownList =
			 		new HashMap<String, CooldownManager>(); //List of all cooldowns that work simultaneously.
	 
	 //Attributes for differentiating cooldowns
	 /*
	  * ex: attack cooldown for enemy: cooldownName: enemyAttackCooldown
	  * 							   id: 1,2,3.... (for every enemy)
	  */
	 private final UUID id; 			//128-bit unique long id
	 private final String cooldownName; //type of cooldown.

	 //Attributes for taking time
	 private long counter;				//Counter
	 private final int cooldownTime;   //Desired cooldown time in seconds.
	 
//Constructor:
	
	public CooldownManager(UUID id, String cooldownName, int timeInSeconds ) {
		this.cooldownTime = timeInSeconds; 
		this.id = id;
        this.cooldownName = cooldownName;
	}
	
//Methods:
	
	//Just adds cooldown to cooldown list
	public void addCooldown(){
		cooldownList.put(this.id.toString()+this.cooldownName, this); //this --> cooldownManager
    }
	
	//Starts the specified cooldown in list
	public void startCooldown(UUID id, String cooldownName) {
		CooldownManager thisCooldown = getCooldown(id, cooldownName); //instance of specified cooldown
		this.counter = System.currentTimeMillis();
		cooldownList.put(id.toString()+ cooldownName, thisCooldown); //this --> cooldownManager
	}
	
	//Returns remaining time of the specified cooldown
	 public static int getTimeLeft(UUID id, String cooldownName){
	        CooldownManager thisCooldown = getCooldown(id, cooldownName); //instance of specified cooldown
	        int timeLeft = thisCooldown.cooldownTime;
	        if(thisCooldown!=null){
	            long currentTime = System.currentTimeMillis();
	            long passedCooldownTime = thisCooldown.counter; //
	            int totalCooldownTime = thisCooldown.cooldownTime;
	            int passedTime = (int) (currentTime - passedCooldownTime) / 1000; //in seconds. System time when we started cooldown - system time now.
	            timeLeft = (totalCooldownTime - passedTime);
	        }
	        return timeLeft;
	    }
	 
	 //Controls if the cooldown should still run, if it is, returns true otherwise;
	 //stops the cooldown and returns false.
	 public static boolean inCooldown(UUID id, String cooldownName){
	        if(getTimeLeft(id, cooldownName)>=1){
	            return true;
	        } else {
	            stopCooldown(id, cooldownName);
	            return false;
	        }
	    }
	 
	    public static void stopCooldown(UUID id, String cooldownName){
	        CooldownManager.cooldownList.remove(id+cooldownName);
	    }
	 
	    public static CooldownManager getCooldown(UUID id, String cooldownName){
	        return cooldownList.get(id.toString()+cooldownName);
	    }    
	 
	}

/*
References:
[1] https://bukkit.org/threads/cooldown-manager.290459/
[2] https://bukkit.org/threads/simple-cooldown-manager.404256/
*/