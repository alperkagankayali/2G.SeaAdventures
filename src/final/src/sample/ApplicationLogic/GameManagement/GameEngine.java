package sample.ApplicationLogic.GameManagement;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.ApplicationLogic.GameEntities.*;
import sample.UserInterface.InputManagement.InputManager;
import sample.UserInterface.Screen.Main;
import sample.UserInterface.Screen.ScreenManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameEngine implements Runnable{
    private boolean isGamePaused;
    private Map gameMap;
    private CollisionManager cm;
    private static GameEngine gameEngine;

    public Map getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        isGamePaused = gamePaused;
    }

    private Thread t;

    private GameEngine(){
        isGamePaused = false;
        gameMap = Map.getMap();
        cm = CollisionManager.getInstance();
    }
    public void run(){
        while(!isGamePaused){
            try {
                //System.out.println(isGamePaused);
                update();
                gameMap.update();
                checkCollision();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void gameLoop(){
        if(t == null){
            //System.out.println(isGamePaused);
            t = new Thread(this);
            t.start();
        }
    }
    public void update(){
        KeyCode checkKey = InputManager.getPressedKey();
        if(checkKey != null){
            //System.out.println(InputManager.getPressedKey());
            if(checkKey.toString().equals("ESCAPE")){
                isGamePaused = true;
            }
            else{
                //Main.getPrimaryStage().show();
            }
        }
    }
    public static GameEngine getGameEngine(){
        if(gameEngine == null){
            gameEngine = new GameEngine();
        }
        return gameEngine;
    }
    public void checkCollision(){
        boolean flag;
        boolean flag1 = false;
        boolean flag2 = false;
        ArrayList<Enemy> enemies = Map.getMap().getVisibleEnemies();
        System.out.println(enemies.size());
        for(int i = 0; i < enemies.size(); i++){
            try{
                flag = cm.checkGameObjectCollision(enemies.get(i), Map.getMap().getSubmarine());
                if(flag){
                    Map.getMap().getSubmarine().healthDecrease(1);
                    enemies.get(i).decreaseHealth(1);
                }
                for(int j = 0; j < Map.getMap().getSubmarine().getBullets().size() && !flag1; j++){
                    flag1 = cm.checkGameObjectCollision(enemies.get(i), Map.getMap().getSubmarine().getBullets().get(j));
                    if(flag1){
                        System.out.println("decrease enemy");
                        enemies.get(i).decreaseHealth(Map.getMap().getSubmarine().getBullets().get(j).getDamage());
                        Map.getMap().getSubmarine().getBullets().remove(j);
                        Map.getMap().getSubmarine().updateExperience(enemies.get(i).getExperiencePrize());

                    }
                }
                if(enemies.get(i).toString().equals("Big Enemy")){
                    ArrayList<Bullet> bullets = ((BigEnemy)enemies.get(i)).getBullets();
                    for(int j = 0; j < bullets.size() && !flag2; j++){
                        flag2 = cm.checkGameObjectCollision(Map.getMap().getSubmarine(), bullets.get(j));
                        if(flag2){
                            Map.getMap().getSubmarine().healthDecrease(((BigEnemy)enemies.get(i)).getBullets().get(j).getDamage());
                            ((BigEnemy)enemies.get(i)).getBullets().remove(j);
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
