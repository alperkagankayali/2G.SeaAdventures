package ApplicationLogicLayer.GameEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Health {
    private static final int imageSize = 6;
    private int maxHealth;
    private int healthAmount;
    private int subLevel;
    private Image[] IMAGES;
    private GameObject object;

    public Health(int sLvl) throws FileNotFoundException {
        object = new GameObject( 5, 5);
        setSubLevel(sLvl);
        setMaxHealth(75 + 25 * subLevel);
        setHealthAmount(maxHealth);
        IMAGES = new Image[imageSize];
        for(int i = 0; i < imageSize; i++ ){
            IMAGES[i] = new Image(new FileInputStream(System.getProperty("user.dir") +  "\\src\\ApplicationLogicLayer\\GameEntities\\images\\health_" + i + ".png"));
        }
        object.setSpriteImage( IMAGES[ (healthAmount + maxHealth / (2 * (imageSize - 1) ) ) / (maxHealth / (imageSize - 1) )]);
        update( 0);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        if( maxHealth >= 0) {
            this.maxHealth = maxHealth;
        }
        else
            this.maxHealth = 0;
    }

    public int getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        if( healthAmount >= 0) {
            this.healthAmount = healthAmount;
        }
        else
            this.healthAmount = 0;
    }

    public void setSubLevel(int subLevel) {
        if( subLevel >= 0)
            this.subLevel = subLevel;
        else
            throw new IndexOutOfBoundsException("Not valid subLevel value");
    }

    public void update( int increaseAmount) throws FileNotFoundException {
        setHealthAmount( getHealthAmount() + increaseAmount);
        object.setSpriteImage( IMAGES[ (healthAmount + maxHealth / (2 * (imageSize - 1) ) ) / (maxHealth / (imageSize - 1) )]);
        object.update( 0);
    }

    public void draw(GraphicsContext gc)
    {
        object.draw(gc);
    }

}
