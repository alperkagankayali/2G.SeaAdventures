package sample.UserInterface.Screen;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import sample.ApplicationLogic.GameManagement.GameEngine;

import java.net.URL;

public class ScreenManager {
    private int width;
    private final URL DIR_LOC = getClass().getResource(".");
    private int height;
    static private Parent root;
    private static ScreenManager sm;
    private ScreenManager(int width, int height, Parent root){
        this.height = height;
        this.width = width;
        this.root = root;
        BackgroundImage myBI= new BackgroundImage(new Image(DIR_LOC +  "\\images\\7.jpg",852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.root = new GridPane();

        ((GridPane)this.root).setBackground(new Background(myBI));
        ((GridPane)this.root).setPrefSize(852,480);
        this.root.getStylesheets().add("sample/UserInterface/Screen/style.css");
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
        if(text.equals("Play Scenario Game")){
            GameEngine ge = GameEngine.getGameEngine();
            ge.gameLoop();
            root = ge.getGameMap().load();
        }
        else if(text.equals("Settings")){
            root = new GridPane();
        }
        else if(text.equals("Credits")){
            root = new Credits().load();
        }
        else if(text.equals("View Help")){
            root = new HelpManager().load();
        }
        return root;
    }
    public static ScreenManager getInstance(int width, int height, Parent root){
        if(sm == null){
            sm = new ScreenManager(width, height, root);
        }
        return sm;
    }
}
