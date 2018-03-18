package sample;

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
    public void start(final Stage stage) throws FileNotFoundException, InterruptedException {

        //Creating an image
        Image image = new Image(new FileInputStream("C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\Enemy_Crab.png"));
        BigEnemy crab = new BigEnemy( 1000, 200, false, 3 );


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

        ArrayList<Bullet[]> bList = new ArrayList<>();

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
                    crab.update( elapsedTime);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                crab.draw( gc );

                if( crab.getXPos() <= 200)
                    crab.setHealth( 0);
                double[] arr = crab.shoot();


                if( arr[0] != -1) {
                    bList.add(new Bullet[crab.getAmountOfProjectile()]);
                    for (int i = 2; i < arr.length; i++) {
                        try {
                            bList.get(bList.size() - 1)[i - 2] = new Bullet(arr[1], arr[i], arr[0], 2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }

                for( int j = 0; j < bList.size(); j++) {
                    for (int i = 0; i < bList.get(j).length; i++) {
                        try {
                            bList.get(j)[i].update(elapsedTime);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        bList.get(j)[i].draw(gc);
                    }
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