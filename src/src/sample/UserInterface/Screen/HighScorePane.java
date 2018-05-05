package sample.UserInterface.Screen;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.FileManagement.FileManager;

import java.net.URL;

public class HighScorePane {
    private FileManager fileManager;
    private static HighScorePane highScorePane;
    private String highScores;
    private final URL DIR_LOC = getClass().getResource(".");
    private int width;
    private int height;
    private Parent root;
    private HighScorePane(){
        this.width = 852;
        this.height = 480;
        BackgroundImage myBI= new BackgroundImage(new Image(DIR_LOC +  "\\images\\help.png",852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.root = new GridPane();

        ((GridPane)this.root).setBackground(new Background(myBI));
        ((GridPane)this.root).setPrefSize(852,480);
        fileManager = new FileManager("Scores.txt");
        highScores = fileManager.readFromFile();
        //System.out.println(highScores);
    }
    public void createContent(){
        Text header = new Text("High Scores");
        Text highScore = new Text(highScores);
        header.setId("header-help");
        header.setTranslateX(325);
        header.setTranslateY(5);
        highScore.setFont(Font.font ("Verdana", 20));
        highScore.setFill(Color.RED);
        highScore.setTranslateX(350);
        highScore.setTranslateY(150);
        Button backbutton = new Button("Back");
        backbutton.setTranslateX(350);
        backbutton.setTranslateY(325);
        backbutton.getStyleClass().add("menu-button");
        backbutton.setOnAction(e -> {Main.getPrimaryStage().getScene().setRoot(Main.getPane());});
        //playTheGame.getStyleClass().add("menu-button");
        ((GridPane)root).getChildren().addAll(header,highScore, backbutton);
    }
    public static HighScorePane getInstance(){
        if(highScorePane == null)
            highScorePane = new HighScorePane();
        return highScorePane;
    }
    public Parent load(){
        root.getStylesheets().add("sample/UserInterface/Screen/style.css");
        createContent();
        return root;
    }

}
