package sample.UserInterface.Screen;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;

public class Credits {
    private int width;
    private int height;
    private final URL DIR_LOC = getClass().getResource(".");
    private Parent root;
    public Credits() {
        this.width = 852;
        this.height = 480;
        BackgroundImage myBI= new BackgroundImage(new Image(DIR_LOC +  "\\images\\7.jpg",852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.root = new GridPane();

        ((GridPane)this.root).setBackground(new Background(myBI));
        ((GridPane)this.root).setPrefSize(852,480);
    }
    public void createContent(){
        Text header = new Text("Credits");
        Text explanation = new Text();
        explanation.setText("Developers:\n\n" +
                "Alper Kağan Kayalı\n" +
                "Salih Zeki Okur\n" +
                "Büşra Oğuzoğlu\n" +
                "Kasymbek Tashbaev\n");
        header.setId("header-help");
        header.setTranslateX(375);
        header.setTranslateY(5);
        explanation.setFont(Font.font ("Verdana", 20));
        explanation.setFill(Color.RED);
        explanation.setTranslateX(350);
        explanation.setTranslateY(150);
        Button backbutton = new Button("Back");
        backbutton.setTranslateX(350);
        backbutton.setTranslateY(325);
        backbutton.getStyleClass().add("menu-button");
        backbutton.setOnAction(e -> {Main.getPrimaryStage().getScene().setRoot(Main.getPane());});
        ((GridPane)root).getChildren().addAll(header,explanation, backbutton);

    }
    public Parent load(){
        root.getStylesheets().add("sample/UserInterface/Screen/style.css");
        createContent();
        return root;
    }
}
