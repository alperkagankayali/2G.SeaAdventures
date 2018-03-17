package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.image.Image;
//import java.awt.*;

public class Main extends Application implements EventHandler<ActionEvent>{
    private Parent root;
    private ScreenManager sm = new ScreenManager(852,480,root);
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = sm.getRoot();
        this.primaryStage = primaryStage;
        //Header
        Text header = new Text("Sea Adventures");
        header.setId("header-string");
        header.setTranslateX(250);
        header.setTranslateY(100);
        //e -> primaryStage.getScene().setRoot(sm.update(playbutton.getText()))
        //Buttons
        Button playbutton = new Button("Play Game");
        System.out.println(playbutton.getText());
        playbutton.setTranslateX(300);
        playbutton.setTranslateY(175);
        playbutton.getStyleClass().add("menu-button");
        playbutton.setOnAction(this);

        Button settingsbutton = new Button("Settings");
        settingsbutton.setTranslateX(300);
        settingsbutton.setTranslateY(225);
        settingsbutton.getStyleClass().add("menu-button");
        settingsbutton.setOnAction(this);

        Button creditsbutton = new Button("Credits");
        creditsbutton.setTranslateX(300);
        creditsbutton.setTranslateY(275);
        creditsbutton.getStyleClass().add("menu-button");
        creditsbutton.setOnAction(this);

        Button helpbutton = new Button("View Help");
        helpbutton.setTranslateX(300);
        helpbutton.setTranslateY(325);
        helpbutton.getStyleClass().add("menu-button");
        helpbutton.setOnAction(this);

        Button exitbutton = new Button("Quit Game");
        exitbutton.setTranslateX(300);
        exitbutton.setTranslateY(375);
        exitbutton.getStyleClass().add("menu-button");
        exitbutton.setOnAction(this);

        ((GridPane)root).getChildren().addAll(playbutton, header, settingsbutton, creditsbutton, helpbutton, exitbutton);

        System.out.println(((GridPane)root).getChildren());
        Scene x = new Scene(root);

        this.primaryStage.setScene(x);
        this.primaryStage.show();
    }
    @Override
    public void handle(ActionEvent e){
        try {
            root = sm.update(((Button)(e.getSource())).getText());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if(((Button)(e.getSource())).getText().equals("Quit Game")){
            primaryStage.close();
        }
        primaryStage.getScene().setRoot(root);
    }

    public static void main(String[] args) { launch(args); }
}
