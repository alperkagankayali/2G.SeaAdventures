import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


/**
 * Created by Okur on 05/03/18.
 */
public class SoundEngine {

    //boolean variables that hold whether sounds will be played or not
    private boolean musicEnabled;
    private boolean soundEnabled;

    private boolean song1Playing;
    private boolean song2Playing;

    //Integers that hold effect ID's
    public static final int COLLUSION_SOUND = 0;
    public static final int BULLET_FIRE_SOUND = 1;
    public static final int BULLET_HIT_SOUND = 2;
    public static final int SKILL_USED_SOUND = 3;
    public static final int POWER_UP_SOUND = 4;
    public static final int DESTROYED_SOUND = 5;

    //AudioClips that hold in game effects
    AudioClip collusion =  new AudioClip(getClass().getClassLoader().getResource("Sounds/collusion.wav").toString());
    AudioClip bulletFire =  new AudioClip(getClass().getClassLoader().getResource("Sounds/bulletFire.wav").toString());
    AudioClip bulletHit =  new AudioClip(getClass().getClassLoader().getResource("Sounds/bulletHit.wav").toString());
    AudioClip skillUsed =  new AudioClip(getClass().getClassLoader().getResource("Sounds/skillUsed.wav").toString());
    AudioClip powerUp =  new AudioClip(getClass().getClassLoader().getResource("Sounds/powerUp.wav").toString());
    AudioClip destroyed =  new AudioClip(getClass().getClassLoader().getResource("Sounds/destroyed.wav").toString());

    //Media player holds music
    Media song1 = new Media(getClass().getClassLoader().getResource("Sounds/20.wav").toString());
    MediaPlayer music1 = new MediaPlayer(song1);
    Media song2 = new Media(getClass().getClassLoader().getResource("Sounds/21.wav").toString());
    MediaPlayer music2 = new MediaPlayer(song2);



    public SoundEngine(){
        this.musicEnabled = true;
        this.soundEnabled = true;

        //Set music loops
        this.music1.setStartTime(Duration.seconds(0));
        this.music1.setStopTime(Duration.seconds(65));
        this.music1.setCycleCount(MediaPlayer.INDEFINITE);

        this.music2.setStartTime(Duration.seconds(0));
        this.music2.setStopTime(Duration.seconds(72));
        this.music2.setCycleCount(MediaPlayer.INDEFINITE);

        //Start first song from the beginning
        //this.music1.setAutoPlay(musicEnabled);
        //this.music1.play();
        this.music1.setMute(!this.musicEnabled);
        song1Playing = true;
        song2Playing = false;
    }



    public void playSound (int i){
        if (soundEnabled) {
            switch (i) {
                case COLLUSION_SOUND:
                    collusion.play();
                    break;
                case BULLET_FIRE_SOUND:
                    bulletFire.play();
                    break;
                case BULLET_HIT_SOUND:
                    bulletHit.play();
                    break;
                case SKILL_USED_SOUND:
                    skillUsed.play();
                    break;
                case POWER_UP_SOUND:
                    powerUp.play();
                    break;
                case DESTROYED_SOUND:
                    destroyed.play();
                    break;
            }
        }
    }

    public void startMusic(){
        this.music1.play();
    }

    public void pauseUnPauseMusic(){
        /*
        if (!music1.isAutoPlay())
            music1.setAutoPlay(true);
            */
        this.musicEnabled = !this.musicEnabled;
        this.music1.setMute(!musicEnabled);
        this.music2.setMute(!musicEnabled);
    }

    public void changeFXVolume(double i){
        collusion.setVolume(i);
        bulletFire.setVolume(i);
        bulletHit.setVolume(i);
        skillUsed.setVolume(i);
        powerUp.setVolume(i);
        destroyed.setVolume(i);
    }

    public void changeMusicVolume(double i){
        music1.setVolume(i);
        music2.setVolume(i);
    }

    public void changeMusic(){
        if(song1Playing){
            song1Playing = false;
            song2Playing = true;
            music1.stop();
            music2.play();
        }
        else if(song2Playing){
            song2Playing = false;
            song1Playing = true;
            music2.stop();
            music1.play();
        }
    }

    public double getMusicVolume(){ return music1.getVolume(); }

    public double getFXVolume() { return collusion.getVolume(); }

    public void setmusicEnabled(boolean bool){
        this.musicEnabled = bool;
    }

    public void setSoundEnabled(boolean bool){
        this.soundEnabled = bool;
    }



}
