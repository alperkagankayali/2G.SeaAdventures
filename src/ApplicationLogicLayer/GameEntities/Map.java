//package ApplicationLogicLayer.GameEntities;
//
//import javafx.animation.AnimationTimer;
//import javafx.scene.Parent;
//import javafx.scene.canvas.*;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.layout.*;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//
//public class Map implements Runnable{
//    private Parent root;
//    private static int mapLevel;
//    private static boolean level1Completed;
//    private static boolean level2Completed;
//
//
//    public static boolean isLevel1Completed() {
//        return level1Completed;
//    }
//
//    public static void setLevel1Completed(boolean level1Completed) {
//        Map.level1Completed = level1Completed;
//    }
//
//    public static boolean isLevel2Completed() {
//        return level2Completed;
//    }
//
//    public static void setLevel2Completed(boolean level2Completed) {
//        Map.level2Completed = level2Completed;
//    }
//
//    public static boolean isLevel3Completed() {
//        return level3Completed;
//    }
//
//    public static void setLevel3Completed(boolean level3Completed) {
//        Map.level3Completed = level3Completed;
//    }
//
//    private static boolean level3Completed;
//    private int deadCount;
//    private Thread t;
//    private int totalCountOfEnemies;
//    private BackgroundImage backgroundImage;
//    private ObjectRandomLocationManager locationManager;
//    private ArrayList<GameObject> gameObjects;
//    public void run(){
//
//    }
//    public Map(){
//        this.mapLevel = 1;
//        root = new GridPane();
//        gameObjects = new ArrayList<>();
//        locationManager = new ObjectRandomLocationManager();
//        backgroundImage = new BackgroundImage(new javafx.scene.image.Image("file:\\C:\\Users\\Alper\\IdeaProjects\\draftproject\\stylus-hex-bg-sea-color-final.jpg",852,480,false,true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        ((GridPane)this.root).setBackground(new Background(backgroundImage));
//        ((GridPane)this.root).setPrefSize(852,480);
//        setEnemies();
//    }
//    public int getMapLevel() {
//        return mapLevel;
//    }
//
//    public void setMapLevel(int mapLevel) {
//        this.mapLevel = mapLevel;
//    }
//
//    public void setEnemies(){
//        try{
//            int loopnumber;
//            if(mapLevel == 1)
//                loopnumber = 20;
//            else if(mapLevel == 2)
//                loopnumber = 40;
//            else
//                loopnumber = 60;
//            totalCountOfEnemies = loopnumber;
//            for(int i = 0; i < loopnumber; i++){
//                GameObject gameObject;
//                //SmallEnemy xx;
//                //BigEnemy xy;
//                //Boss xz;
//
//
//                if(i < (3*loopnumber)/4){
//                    if(i < 5){
//                        locationManager.generateLocation(0, 850, 0, 480);
//
//                    }
//                    else{
//                        locationManager.generateLocation(850, 1700, 0, 480);
//                    }
//                    double x = locationManager.getX();
//                    double y = locationManager.getY();
//                    gameObject = new SmallEnemy(x, y, true, mapLevel);
//                    gameObjects.add(gameObject);
//
//                }
//                else if(i < loopnumber-1){
//                    locationManager.generateLocation(1700, 2550, 0, 480);
//                    double x = locationManager.getX();
//                    double y = locationManager.getY();
//                    gameObject = new BigEnemy(x, y, false, mapLevel);
//                    gameObjects.add(gameObject);
//
//                }
//                else{
//                    locationManager.generateLocation(2550, 3400, 0, 480);
//                    double x = locationManager.getX();
//                    double y = locationManager.getY();
//                    gameObject = new Boss(x, y, false, mapLevel);
//                    gameObjects.add(gameObject);
//                }
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public void setPowerUps(){
//        /*try{
//            File imageFileRegen = new File("C:\\Users\\Alper\\IdeaProjects\\draftproject\\powerUp_regen.png");
//            File imageFileEnergy = new File("C:\\Users\\Alper\\IdeaProjects\\draftproject\\pwup_fuel_energy.png");
//            Image imageRegen = ImageIO.read(imageFileRegen);
//            Image imageEnergy = ImageIO.read(imageFileEnergy);
//            GameObject powerUp1;
//            GameObject powerUp2;
//            for(int i = 0; i < 2; i++){
//                locationManager.generateLocation();
//                powerUp1 = new PowerUp(locationManager.getX(), locationManager.getY(), 1, false, mapLevel);
//                locationManager.generateLocation();
//                powerUp2 = new PowerUp(locationManager.getX(), locationManager.getY(), 2, false, mapLevel);
//                gameObjects.add(powerUp1);
//                gameObjects.add(powerUp2);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }*/
//
//    }
//
//    public void update(){
//        for(int i = 0; i < gameObjects.size(); i++){
//            try {
//                if(gameObjects.get(i).toString().equals("Big Enemy") || gameObjects.get(i).toString().equals("Small Enemy") || gameObjects.get(i).toString().equals("Boss")){
//                    Enemy enemy = ((Enemy) gameObjects.get(i));
//                    if(enemy.getHealth() <= 0 || enemy.getXPos() <= 0 || enemy.getYPos() < 0){
//                        //gameObjects.get(i).disappearAnimation();
//                        gameObjects.remove(i);
//                        deadCount++;
//                    }
//                }
//                //if()
//            }catch (Exception e){
//
//            }
//        }
//        if(deadCount == totalCountOfEnemies && totalCountOfEnemies == 20)
//            level1Completed = true;
//        else if(deadCount == totalCountOfEnemies && totalCountOfEnemies == 40)
//            level2Completed = true;
//        else if(deadCount == totalCountOfEnemies && totalCountOfEnemies == 60)
//            level3Completed = true;
//    }
//    public void createContent(){
//        try{
//
//            //Creating an image
//            // Image image = new Image(new FileInputStream("C:\\Users\\SnowPlace\\IdeaProjects\\Demofx_1\\src\\sample\\Enemy_Crab.png"));
//            Canvas canvas = new Canvas(850, 480);
//            ((GridPane)root).getChildren().add( canvas );
//
//            GraphicsContext gc = canvas.getGraphicsContext2D();
//
//            //double lastNanoTime = System.nanoTime();
//            new AnimationTimer()
//            {
//                double lastNanoTime = System.nanoTime();
//                public void handle(long currentNanoTime)
//                {
//                    // calculate time since last update.
//                    double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
//                    lastNanoTime = currentNanoTime;
//
//                    // draw
//
//                    gc.clearRect(0, 0, 850,480);
//                    for(int i = 0;i < totalCountOfEnemies - deadCount; i++){
//                        try {
//                            if(gameObjects.get(i).getXPos() <= 0 || gameObjects.get(i).getYPos() <= 0){
//                                gameObjects.get(i).update(elapsedTime / 31);
//                            }
//                            else{
//                                gameObjects.get(i).update(elapsedTime);
//                            }
//                            gameObjects.get(i).draw(gc);
//                            if(gameObjects.get(i).toString().equals("Big Enemy")){
//                                System.out.println("entered");
//                                ((BigEnemy)gameObjects.get(i)).shoot();
//                                for(int j = 0; j < ((BigEnemy)gameObjects.get(i)).getBullets().size(); j++){
//                                    ((BigEnemy)gameObjects.get(i)).getBullets().get(j).update(elapsedTime);
//                                    ((BigEnemy)gameObjects.get(i)).getBullets().get(j).draw(gc);
//                                }
//                            }
//                        } catch (FileNotFoundException e) {
//                            i++;
//                            e.printStackTrace();
//                        }
//                    }
//                    update();
//                }
//            }.start();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//    }
//    public Parent load(){
//        root.getStylesheets().add("style.css");
//        createContent();
//        /*if(t == null){
//            t = new Thread();
//            t.start();
//        }*/
//        return root;
//    }
//
//
//    public GameObject getGameObject(double x, double y){
//        GameObject returnval = null;
//        for(int i = 0; i < gameObjects.size(); i++){
//            if(gameObjects.get(i).getXPos() == x && gameObjects.get(i).getYPos() == y){
//                returnval = gameObjects.get(i);
//            }
//        }
//        return returnval;
//    }
//}
