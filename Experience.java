import javafx.scene.image.Image;

/**
 * Created by Okur on 12/03/18.
 */
public class Experience {
    private int experienceAmount;
    private int experienceLimit;
    private int subLevel;
    private Image image;

    Experience(int subLevel){
        this.subLevel = subLevel;
        this.experienceLimit = 300 + (100 * this.subLevel);
    }

    public void update(){
        // update image
        this.experienceLimit = 300 + (100 * this.subLevel);
    }

    public int getExperience() { return experienceAmount; }

    public void increaseExperience(int i){ this.experienceAmount = this.experienceAmount + i; }

    public boolean isLevelUp(){ return experienceAmount >= experienceLimit; }
}
