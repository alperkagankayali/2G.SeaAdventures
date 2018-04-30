package sample.ApplicationLogic.GameManagement;

import sample.ApplicationLogic.GameEntities.GameObject;

public class CollisionManager {
    private static CollisionManager cm;
    private GameObject object1;
    private GameObject object2;
    private CollisionManager(){  }
    public boolean checkGameObjectCollision(GameObject g1, GameObject g2){
        object1 = g1;
        object2 = g2;
        double xPosOfEnemy = g2.getCollisionRectangle().getX();
        double yPosOfEnemy = g2.getCollisionRectangle().getY();
        double widthOfEnemy = g2.getCollisionRectangle().getWidth();
        double heightOfEnemy = g2.getCollisionRectangle().getHeight();
        if(g1.getCollisionRectangle().intersects(xPosOfEnemy,yPosOfEnemy,widthOfEnemy,heightOfEnemy)){
            return true;
        }
        return false;
    }
    public static CollisionManager getInstance(){
        if(cm == null){
            cm = new CollisionManager();
        }
        return cm;
    }
}
