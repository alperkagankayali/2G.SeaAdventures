package GameEntities;

import java.awt.event.KeyEvent;

public class GameEngine{
    private boolean isGamePaused;
    private Map gameMap;

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
    public GameEngine(){
        isGamePaused = false;
        gameMap = new Map();
    }
    /*public void run(){
        while(!isGamePaused){
            try {

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }*/
    public void gameLoop() throws InterruptedException {
        if(t == null){
            t = new Thread();
            t.start();
        }
        else{
            update();
            gameMap.update();
            t.sleep(10000);
        }
    }
    public void update(){
        if(new SettingManager().getPressedKey() == KeyEvent.VK_ESCAPE){
            isGamePaused = true;
        }
    }
}
