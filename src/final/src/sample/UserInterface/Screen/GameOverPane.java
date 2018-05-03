import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * Created by Okur on 03/05/18.
 */
public class GameOverPane {
    GridPane gridPaneGO;
    Scene sceneGO;

    GameOverPane(){

        double buttonWidth = 125;
        double buttonHeight = 10;

        final Button butBack = new Button("Back");
        final Text gameOver = new Text("Game Over");

        gameOver.setScaleX(12);
        gameOver.setScaleY(12);

        butBack.setPrefWidth(buttonWidth);
        butBack.setPrefHeight(buttonHeight);

        /**
         * Back Button
         */

        butBack.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Tester.getPrimaryStage().setScene(Tester.getPrimaryScene());

            }
        });

        gridPaneGO = new GridPane();

        gridPaneGO.setHgap(0);
        gridPaneGO.setVgap(0);

        gridPaneGO.getColumnConstraints().add(new ColumnConstraints(10));
        gridPaneGO.getColumnConstraints().add(new ColumnConstraints(370));
        gridPaneGO.getColumnConstraints().add(new ColumnConstraints(72));
        gridPaneGO.getColumnConstraints().add(new ColumnConstraints(370));
        gridPaneGO.getRowConstraints().add(new RowConstraints(120));
        gridPaneGO.getRowConstraints().add(new RowConstraints(120));
        gridPaneGO.getRowConstraints().add(new RowConstraints(120));
        gridPaneGO.getRowConstraints().add(new RowConstraints(120));

        gridPaneGO.add(butBack,1,3);
        gridPaneGO.add(gameOver,2,1);



        final Image backgroundImage = new Image (GameOverPane.class.getResource("Settings_Background.bmp").toString(),852,480,false,false);

        final BackgroundImage backgi = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        final Background backg = new Background(backgi);

        gridPaneGO.setBackground(backg);
        gridPaneGO.setPrefSize(852,480);
        //gridPaneGO.setGridLinesVisible(true);

        sceneGO = new Scene(gridPaneGO,852,480);
    }

    /**
     *
     * @return this.Pane
     */
    public GridPane getPane(){ return gridPaneGO; }

    public Scene getScene(){ return sceneGO; }
}
