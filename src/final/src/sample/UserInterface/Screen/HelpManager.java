package sample.UserInterface.Screen;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;


public class HelpManager {
    private int width;
    private int height;
    private final URL DIR_LOC = getClass().getResource(".");
    private Parent root;
    public HelpManager(){
        this.width = 852;
        this.height = 480;
        BackgroundImage myBI= new BackgroundImage(new Image(DIR_LOC +  "\\images\\help.png",852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.root = new GridPane();

        ((GridPane)this.root).setBackground(new Background(myBI));
        ((GridPane)this.root).setPrefSize(852,480);

    }
    public void createContent(){
        Text header = new Text("Help");
        Text explanation = new Text();
        explanation.setText("In order to play the game:\n" +
                "Move Up With UP ARROW\n" +
                "Move Down With DOWN ARROW\n" +
                "Move Left With LEFT ARROW\n" +
                "Move Right With RIGHT ARROW\n" +
                "Shoot with SPACEBAR\n" +
                "Use First Skill with Z\n" +
                "Use Second Skill with X\n" +
                "Use Third Skill with C\n");
        header.setId("header-help");
        header.setTranslateX(375);
        header.setTranslateY(5);
        //Button playTheGame = new Button("How to play the game");
        //explanation.setId("header-help");
        explanation.setFont(Font.font ("Verdana", 20));
        explanation.setFill(Color.RED);
        explanation.setTranslateX(350);
        explanation.setTranslateY(150);

        //playTheGame.getStyleClass().add("menu-button");
        ((GridPane)root).getChildren().addAll(header,explanation);

    }
    public Parent load(){
        root.getStylesheets().add("sample/UserInterface/Screen/style.css");
        createContent();
        return root;
    }
}
