package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ScreenManager {
    private int width;
    private int height;
    private Parent root;
    public ScreenManager(int width, int height, Parent root){
        this.height = height;
        this.width = width;
        this.root = root;
        BackgroundImage myBI= new BackgroundImage(new Image("file:\\C:\\Users\\Alper\\IdeaProjects\\draftproject\\7.jpg",852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.root = new GridPane();

        ((GridPane)this.root).setBackground(new Background(myBI));
        ((GridPane)this.root).setPrefSize(852,480);
        this.root.getStylesheets().add("style.css");
        //System.out.println(this.root);
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public Parent update(String text) throws InterruptedException {
        if(text.equals("Play Game")){
            GameEngine ge = new GameEngine();
            ge.gameLoop();
            root = ge.getGameMap().load();
        }
        else if(text.equals("Settings")){
            root = new GridPane();
        }
        else if(text.equals("Credits")){
            root = new GridPane();
        }
        else if(text.equals("View Help")){
            root = new HelpManager().load();
        }
        return root;
    }
}
