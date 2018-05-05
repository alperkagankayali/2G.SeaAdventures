package sample.ApplicationLogic.GameEntities;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import sample.ApplicationLogic.GameManagement.GameEngine;
import sample.FileManagement.FileManager;
import sample.UserInterface.Screen.GameEndPane;
import sample.UserInterface.Screen.GameOverPane;
import sample.UserInterface.Screen.Main;
import sample.UserInterface.Screen.PauseMenu;

import java.util.ArrayList;
import java.util.Random;

public class Map implements Runnable{
    private Parent root;
    private int score;
    private Text head;
    private Parent pauseRoot;
    private final String FIRST_LEVEL_BACKGROUND_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\stylus-hex-bg-sea-color-final.jpg";
    private final String SECOND_LEVEL_BACKGROUND_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\472963309.jpg";
    private final String THIRD_LEVEL_BACKGROUND_IMAGE = System.getProperty("user.dir") + "\\src\\sample\\ApplicationLogic\\GameEntities\\images\\stylus-hex-bg-sea-color-final.jpg";
    private final String[] backgroundImages = {FIRST_LEVEL_BACKGROUND_IMAGE, SECOND_LEVEL_BACKGROUND_IMAGE, THIRD_LEVEL_BACKGROUND_IMAGE};
    private static int mapLevel;
    private static boolean level1Completed;
    private static boolean level2Completed;
    private static Map map;
    private static boolean level3Completed;
    private int deadCount;
    private Thread t;
    private int totalCountOfEnemies;
    private BackgroundImage backgroundImage;
    private ObjectRandomLocationManager locationManager;
    private ArrayList<GameObject> gameObjects;
    private Submarine submarine;
    private int powerUpDisappeared;
    private AnimationTimer at;

    public void setSurvivalMode(boolean survivalMode) {
        this.survivalMode = survivalMode;
    }

    private boolean survivalMode = false;

    public static boolean isLevel1Completed() {
        return level1Completed;
    }

    public static void setLevel1Completed(boolean level1Completed) {
        Map.level1Completed = level1Completed;
    }

    public static boolean isLevel2Completed() {
        return level2Completed;
    }

    public static void setLevel2Completed(boolean level2Completed) {
        Map.level2Completed = level2Completed;
    }

    public static boolean isLevel3Completed() {
        return level3Completed;
    }

    public static void setLevel3Completed(boolean level3Completed) {
        Map.level3Completed = level3Completed;
    }


    public int getScore() {
        return score;
    }

    public void run(){
        try{
            while(true && survivalMode){
                setEnemyForSurvival();
                Thread.sleep(3000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private Map(){
        //setEnemyForSurvival();
        this.mapLevel = 1;
        root = new GridPane();
        gameObjects = new ArrayList<>();
        locationManager = new ObjectRandomLocationManager();
        setBackgroundImage(FIRST_LEVEL_BACKGROUND_IMAGE);
        setEnemies();
        setPowerUps();
        pauseRoot = PauseMenu.getInstance().getRoot();
        try{
            submarine = Submarine.getSubmarine();
        }catch (Exception e){
            e.printStackTrace();
        }
        score = 0;
    }
    public void clearGameObjects(){
        totalCountOfEnemies = 0;
        gameObjects.clear();
    }
    public void setBackgroundImage(String backgroundImage){
        this.backgroundImage = new BackgroundImage(new javafx.scene.image.Image("file:\\" + backgroundImage,852,480,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        ((GridPane)this.root).setBackground(new Background(this.backgroundImage));
        ((GridPane)this.root).setPrefSize(852,480);
    }
    public int getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }


    public void setEnemyForSurvival(){
        try{
            Random rand = new Random();
            GameObject gameObject;
            locationManager.generateLocation(850, 1700, 100, 300);
            double x = locationManager.getX();
            double y = locationManager.getY();

            int num = rand.nextInt(2);
            if(num == 0){
                gameObject = new SmallEnemy(x, y, false, mapLevel);
            }
            else{
                gameObject = new BigEnemy(x, y, mapLevel);
            }
            gameObjects.add(gameObject);
            totalCountOfEnemies++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void setEnemies(){
        try{
            gameObjects.clear();
            int loopnumber;
            if(mapLevel == 1)
                loopnumber = 20;
            else if(mapLevel == 2){
                loopnumber = 40;
                //System.out.println(gameObjects.size());
            }
            else
                loopnumber = 60;
            //System.out.println(loopnumber);
            int count = 0;
            totalCountOfEnemies = loopnumber;
            for(int i = 0; i < loopnumber; i++){
                GameObject gameObject;
                //SmallEnemy xx;
                //BigEnemy xy;
                //BossManager xz;


                if(i < ((8.5)*loopnumber)/10){
                    if(i < 5){
                        locationManager.generateLocation(400, 850, 100, 300);
                        double x = locationManager.getX();
                        double y = locationManager.getY();
                        gameObject = new SmallEnemy(x, y, true, mapLevel);
                        gameObjects.add(gameObject);
                    }
                    else{
                        locationManager.generateLocation(850, 1700, 100, 300);
                        double x = locationManager.getX();
                        double y = locationManager.getY();
                        gameObject = new SmallEnemy(x, y, false, mapLevel);
                        gameObjects.add(gameObject);
                        count++;
                    }
                }

                else if(i < loopnumber-1){
                    //System.out.println("entered here");
                    locationManager.generateLocation(1700, 2550, 0, 300);
                    double x = locationManager.getX();
                    double y = locationManager.getY();
                    gameObject = new BigEnemy(x, y, mapLevel);
                    gameObject.setVisible(false);
                    gameObjects.add(gameObject);

                }
                else{
                    locationManager.generateLocation(2550, 3400, 180, 300);
                    double x = locationManager.getX();
                    double y = locationManager.getY();
                    gameObject = new Boss(x, y, mapLevel);
                    //gameObject.setVisible(false);
                    gameObjects.add(gameObject);
                    //System.out.println(gameObject.getHeight());
                }

            }
            //System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setPowerUps(){
        try{
            GameObject powerUp1;
            GameObject powerUp2;
            for(int i = 0; i < 2; i++){
                locationManager.generateLocation(850*(i+1), 850*(i+2), 0, 480);
                powerUp1 = new PowerUp(locationManager.getX(), locationManager.getY(), 1, mapLevel);
                locationManager.generateLocation(850*(i+1), 850*(i+2), 0, 480);
                powerUp2 = new PowerUp(locationManager.getX(), locationManager.getY(), 2, mapLevel);
                powerUp1.setVisible(false);
                powerUp2.setVisible(false);
                gameObjects.add(powerUp1);
                gameObjects.add(powerUp2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        for(int i = 0; i < gameObjects.size(); i++){
            try {
                if(gameObjects.get(i).toString().equals("Big Enemy") || gameObjects.get(i).toString().equals("Small Enemy") || gameObjects.get(i).toString().equals("Boss")){
                    Enemy enemy = ((Enemy) gameObjects.get(i));
                    /*if(enemy.getXPos() < 800 && enemy.getYPos() < 480)
                        enemy.setVisible(true);*/
                    if(enemy.getHealth() <= 0 || enemy.getXPos() <= 0 || enemy.getYPos() < 0){
                        //gameObjects.get(i).disappearAnimation();
                        gameObjects.remove(i);
                        deadCount++;
                    }
                }
                else if(gameObjects.get(i).toString().equals("Power Up")){
                    PowerUp powerUp = (PowerUp)gameObjects.get(i);
                    //System.out.println(powerUp.getXPos());
                    if(powerUp.getXPos() <= 0 || powerUp.getYPos() <= 0 || powerUp.isUsed()){
                        gameObjects.remove(i);
                        powerUpDisappeared++;
                    }
                }
                //if()
            }catch (Exception e){

            }
        }
        if(deadCount == totalCountOfEnemies) {
            updateMap();
        }

        //System.out.println(deadCount);
    }
    public void updateMap(){
        setBackgroundImage(backgroundImages[mapLevel]);
        if(mapLevel == 1){
            mapLevel = 2;
        }
        else if(mapLevel == 2){
            mapLevel = 3;
        }
        else{
            Platform.runLater(
                () -> {
                    Main.getPrimaryStage().setScene(GameEndPane.getInstance().getScene());
                }
            );
        }
        deadCount = 0;
        setEnemies();
    }
    public void updateMapSurvival() {
        if(t == null){
            t = new Thread(this);
            t.start();
        }
        int count = gameObjects.size();
        for (int i = 0; i < count; i++) {
            try {
                if (gameObjects.get(i).toString().equals("Big Enemy") || gameObjects.get(i).toString().equals("Small Enemy") || gameObjects.get(i).toString().equals("Boss")) {
                    Enemy enemy = ((Enemy) gameObjects.get(i));
                    /*if(enemy.getXPos() < 800 && enemy.getYPos() < 480)
                        enemy.setVisible(true);*/
                    if (enemy.getHealth() <= 0 || enemy.getXPos() <= 0 || enemy.getYPos() < 0) {
                        //gameObjects.get(i).disappearAnimation();
                        gameObjects.remove(i);
                        totalCountOfEnemies--;
                    }
                }
            }
                //if()
            catch (Exception e) {

            }
        }
    }

    public int getTotalObjects() {
        return gameObjects.size();
    }

    public void createContent(){
        try{

            //Creating an image
            // Image image = new Image(new FileInputStream("C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\Enemy_Crab.png"));
            Canvas canvas = new Canvas(850, 480);
            ((GridPane)root).getChildren().add( canvas );

            GraphicsContext gc = canvas.getGraphicsContext2D();

            //double lastNanoTime = System.nanoTime();
            at = new AnimationTimer()
            {
                double lastNanoTime = System.nanoTime();
                public void handle(long currentNanoTime)
                {
                    try{
                        head.setText("Score: " + score);
                        //update();
                        // calculate time since last update.
                        double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                        lastNanoTime = currentNanoTime;

                        // draw

                        gc.clearRect(0, 0, 850,480);

                        submarine.update(elapsedTime);
                        submarine.draw(gc);
                        submarine.controlSubmarine();
                        for(int k = 0; k < submarine.getBullets().size(); k++){
                            submarine.getBullets().get(k).update(elapsedTime);
                            submarine.getBullets().get(k).draw(gc);
                        }
                        System.out.println(submarine.getAttackSpeed());
                        if(!survivalMode){
                            for(int i = 0;i < totalCountOfEnemies - deadCount + (4 - powerUpDisappeared); i++){


                                gameObjects.get(i).update(elapsedTime);

                                gameObjects.get(i).draw(gc);
                                if(gameObjects.get(i).toString().equals("Big Enemy")){
                                    //System.out.println("entered");
                                    ((BigEnemy)gameObjects.get(i)).shoot();
                                    for(int j = 0; j < ((BigEnemy)gameObjects.get(i)).getBullets().size(); j++){
                                        ((BigEnemy)gameObjects.get(i)).getBullets().get(j).update(elapsedTime);
                                        ((BigEnemy)gameObjects.get(i)).getBullets().get(j).draw(gc);
                                    }
                                }
                                if(gameObjects.get(i).toString().equals("Boss")){
                                    ((Boss)gameObjects.get(i)).useAbility();
                                    for(int j = 0; j < ((Boss)gameObjects.get(i)).getBullets().size(); j++){
                                        ((Boss)gameObjects.get(i)).getBullets().get(j).update(elapsedTime);
                                        ((Boss)gameObjects.get(i)).getBullets().get(j).draw(gc);
                                    }
                                }
                            }
                        }
                        else{
                            for(int i = 0; i < totalCountOfEnemies; i++){
                                gameObjects.get(i).update(elapsedTime);

                                gameObjects.get(i).draw(gc);
                                if(gameObjects.get(i).toString().equals("Big Enemy")){
                                    //System.out.println("entered");
                                    ((BigEnemy)gameObjects.get(i)).shoot();
                                    for(int j = 0; j < ((BigEnemy)gameObjects.get(i)).getBullets().size(); j++){
                                        ((BigEnemy)gameObjects.get(i)).getBullets().get(j).update(elapsedTime);
                                        ((BigEnemy)gameObjects.get(i)).getBullets().get(j).draw(gc);
                                    }
                                }
                            }
                        }
                        //score = score + 50;

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };
            at.start();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent load(){
        root.getStylesheets().add("sample/UserInterface/Screen/style.css");
        createContent();
        head = new Text("Score: " + score);
        head.setTranslateX(350);
        head.setTranslateY(-200);
        head.setId("header-help");
        if(t == null){
           t = new Thread(this);
            t.start();
        }
        ((GridPane)root).getChildren().add(head);
        /*if(t == null){
            t = new Thread();
            t.start();
        }*/
        return root;
    }

    public static void setMap(Map map) {
        Map.map = map;
    }

    public void setScore(int score) {
        this.score = this.score + score;

    }

    public GameObject getGameObject(double x, double y){
        GameObject returnval = null;
        for(int i = 0; i < gameObjects.size(); i++){
            if(gameObjects.get(i).getXPos() == x && gameObjects.get(i).getYPos() == y){
                returnval = gameObjects.get(i);
            }
        }
        return returnval;
    }
    public ArrayList<Enemy> getVisibleEnemies(){
        ArrayList <Enemy> enemies = new ArrayList<>();
        int count = gameObjects.size();
        for(int i = 0; i < count; i++){
            if(gameObjects.get(i) != null)
                if(gameObjects.get(i).isVisible())
                    if(gameObjects.get(i).toString().equals("Big Enemy") || gameObjects.get(i).toString().equals("Small Enemy") || gameObjects.get(i).toString().equals("Boss"))
                        enemies.add((Enemy)gameObjects.get(i));
        }
        return enemies;
    }
    public static Map getMap(){
        if(map == null)
            map = new Map();
        return map;
    }

    public Submarine getSubmarine() {
        return submarine;
    }

    public ArrayList<PowerUp> getVisiblePowerUps(){
        ArrayList <PowerUp> powerUps = new ArrayList<>();
        int count = gameObjects.size();
        for(int i = 0; i < count; i++){
            if(gameObjects.get(i) != null)
                if(gameObjects.get(i).isVisible())
                    if(gameObjects.get(i).toString().equals("Power Up"))
                        powerUps.add((PowerUp) gameObjects.get(i));
        }
        return powerUps;
    }
    public Parent pauseGame(boolean gamePaused){
        //root = new GridPane();
        if(gamePaused){
            at.stop();

        }
        else{
            at.start();

        }
        return pauseRoot;
    }
}
