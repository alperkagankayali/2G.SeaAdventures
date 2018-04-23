package ApplicationLogicLayer.GameEntities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage stage) throws FileNotFoundException {

        //Creating a Group object
        Group root = new Group();

        //Creating a scene object
        Scene scene = new Scene(root, 850, 480);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        Canvas canvas = new Canvas( 3200, 480 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        double lastNanoTime = System.nanoTime();

        Submarine sub = new Submarine(5);

        new AnimationTimer()
        {
            double lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                // draw

                gc.clearRect(0, 0, 850,480);

                try {
                    sub.update( elapsedTime);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                sub.draw(gc);
                try {
                    sub.healthDecrease(1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}