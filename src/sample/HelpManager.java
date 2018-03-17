package sample;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class HelpManager {
    private int width;
    private int height;
    private Parent root;
    public HelpManager(){
        this.width = 852;
        this.height = 480;
        BackgroundImage myBI= new BackgroundImage(new Image("file:\\C:\\Users\\Alper\\IdeaProjects\\draftproject\\help.png",852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.root = new GridPane();

        ((GridPane)this.root).setBackground(new Background(myBI));
        ((GridPane)this.root).setPrefSize(852,480);

    }
    public void createContent(){
        Text header = new Text("Help");
        header.setId("header-help");
        header.setTranslateX(375);
        header.setTranslateY(50);
        ((GridPane)root).getChildren().add(header);
    }
    public Parent load(){
        root.getStylesheets().add("style.css");
        createContent();
        return root;
    }
}
