/**
 * Created by Okur on 07/03/18.
 */


public class SettingManager {
    SoundEngine soundEngine;

    //Integers that hold key ID's
    public static int UP_KEY = 0;
    public static int DOWN_KEY = 1;
    public static int LEFT_KEY = 2;
    public static int RIGHT_KEY = 3;
    public static int SHOOTING_KEY = 4;
    public static int SKILL1_KEY = 5;
    public static int SKILL2_KEY = 6;
    public static int SKILL3_KEY = 7;

    SettingManager(SoundEngine soundEngine) {
        this.soundEngine = soundEngine;
    }

    /*
    public void changeControls(int index,int ID){
        UP_KEY = ID;
    }
    */

    public void changeBackGroundMusic(){
        soundEngine.changeMusic();
    }

    public void changeFXVolume(double volume){
        soundEngine.changeFXVolume(volume);
    }

    public void changeMusicVolume(double volume){ soundEngine.changeMusicVolume(volume); }

    public double getFXVolume() { return soundEngine.getFXVolume(); }

    public double getMusicVolume() { return soundEngine.getMusicVolume(); }

    public void muteUnMuteMusic(){ soundEngine.pauseUnPauseMusic(); }
}
