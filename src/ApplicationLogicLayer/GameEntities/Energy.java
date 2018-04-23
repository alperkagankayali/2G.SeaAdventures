package ApplicationLogicLayer.GameEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Energy {
    private static final int imageSize = 6;
    private int maxEnergy;
    private int energyAmount;
    private int subLevel;
    private Image[] IMAGES;
    private GameObject object;

    public Energy(int sLvl) throws FileNotFoundException {
        object = new GameObject( 20, 40);
        setSubLevel(sLvl);
        setMaxEnergy(75 + 25 * subLevel);
        setEnergyAmount(maxEnergy);
        IMAGES = new Image[imageSize];
        for(int i = 0; i < imageSize; i++ ){
            IMAGES[i] = new Image(new FileInputStream(System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\energy_" + i + ".png"));
        }
        object.setSpriteImage( IMAGES[ (energyAmount + maxEnergy / (2 * (imageSize - 1) ) ) / (maxEnergy / (imageSize - 1) )]);
        update( 0);
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        if( maxEnergy >= 0) {
            this.maxEnergy = maxEnergy;
        }
        else
            this.maxEnergy = 0;
    }

    public int getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(int energyAmount) {
        if( energyAmount >= 0) {
            this.energyAmount = energyAmount;
        }
        else
            this.energyAmount = 0;
    }

    public void setSubLevel(int subLevel) {
        if( subLevel >= 0)
            this.subLevel = subLevel;
        else
            throw new IndexOutOfBoundsException("Not valid subLevel value");
    }

    public void update( int increaseAmount) throws FileNotFoundException {
        setEnergyAmount( getEnergyAmount() + increaseAmount);
        object.setSpriteImage( IMAGES[ (energyAmount + maxEnergy / (2 * (imageSize - 1) ) ) / (maxEnergy / (imageSize - 1) )]);
        object.update( 0);
    }

    public void draw(GraphicsContext gc)
    {
        object.draw(gc);
    }
}
