import javafx.scene.image.Image;

/**
 * Created by Okur on 12/03/18.
 */
public class Energy {
    private int energyAmount;
    private int energyLimit;
    private int subLevel;
    private Image image;

    Energy(int subLevel){
        this.subLevel = subLevel;
        this.energyLimit = 100 + (10 * this.subLevel);
    }

    public void update(){
        // update image
        this.energyLimit = 100 + (10 * this.subLevel);
    }

    public void setFullEnergy() { this.energyAmount = this.energyLimit; }

    public int getEnergy() { return energyAmount; }

    public void reduceEnergy(int i) { this.energyAmount = this.energyAmount - i; }

    public void increaseEnergy(int i){
        if(this.energyAmount + i < this.energyLimit)
            this.energyAmount = this.energyAmount + i;
        else
            this.energyAmount = this.energyLimit;
    }
}
