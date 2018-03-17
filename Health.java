import javafx.scene.image.Image;

/**
 * Created by Okur on 12/03/18.
 */
public class Health {
    private int healthAmount;
    private int healthLimit;
    private int subLevel;
    private Image image;

    Health(int subLevel){
        this.subLevel = subLevel;
        this.healthLimit = 100 + (10 * this.subLevel);
    }

    public void update(){
        // update image
        this.healthLimit = 100 + (10 * this.subLevel);
    }

    public void setFullHealth() { this.healthAmount = this.healthLimit; }

    public int getHealth() { return healthAmount; }

    public void reduceHealth(int i) { this.healthAmount = this.healthAmount - i; }

    public void increaseHealth(int i){
        if(this.healthAmount + i < this.healthLimit)
            this.healthAmount = this.healthAmount + i;
        else
            this.healthAmount = this.healthLimit;
    }
}
