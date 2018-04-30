import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * Created by Okur on 13/03/18.
 */
public class SettingsMenuPane {

    //private Image backgroundImage = new Image (SettingsMenuPane.class.getResource("Settings_Background.bmp").toString());
    private String UpKey;
    private String DownKey;
    private String LeftKey;
    private String RightKey;
    private String ShootKey;
    private String Skill1Key;
    private String Skill2Key;
    private String Skill3Key;
    GridPane gridPaneS;


    SettingsMenuPane(SettingManager settingManager){

        UpKey = settingManager.UP_KEY.getName();
        DownKey = settingManager.DOWN_KEY.getName();
        LeftKey = settingManager.LEFT_KEY.getName();
        RightKey = settingManager.RIGHT_KEY.getName();
        ShootKey = settingManager.SHOOTING_KEY.getName();
        Skill1Key = settingManager.SKILL1_KEY.getName();
        Skill2Key = settingManager.SKILL2_KEY.getName();
        Skill3Key = settingManager.SKILL3_KEY.getName();

        Text UpText = new Text(UpKey);
        Text DownText = new Text(DownKey);
        Text LeftText = new Text(LeftKey);
        Text RightText = new Text(RightKey);
        Text ShootingText = new Text(ShootKey);
        Text Skill1Text = new Text(Skill1Key);
        Text Skill2Text = new Text(Skill2Key);
        Text Skill3Text = new Text(Skill3Key);

        final Button button0 = new Button("Change Music");
        //final Button button1 = new Button("Change Up Key");
        final Button button1 = new Button("Change Controls");
        final Slider slider0 = new Slider(0,1,settingManager.getMusicVolume());
        final Slider slider1 = new Slider(0,1,settingManager.getFXVolume());


        button0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) { settingManager.changeBackGroundMusic(); }
        });
        /*
        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new Thread(settingManager).start();

                UpKey = settingManager.UP_KEY.getName();
                DownKey = settingManager.DOWN_KEY.getName();
                LeftKey = settingManager.LEFT_KEY.getName();
                RightKey = settingManager.RIGHT_KEY.getName();
                ShootKey = settingManager.SHOOTING_KEY.getName();
                Skill1Key = settingManager.SKILL1_KEY.getName();
                Skill2Key = settingManager.SKILL2_KEY.getName();
                Skill3Key = settingManager.SKILL3_KEY.getName();

                UpText.setText(DownKey);
                DownText.setText(DownKey);
                LeftText.setText(LeftKey);
                RightText.setText(RightKey);
                ShootingText.setText(ShootKey);
                Skill1Text.setText(Skill1Key);
                Skill2Text.setText(Skill2Key);
                Skill3Text.setText(Skill3Key);

            }
        });
        */
        button1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeControls();

                        UpKey = settingManager.UP_KEY.getName();
                        DownKey = settingManager.DOWN_KEY.getName();
                        LeftKey = settingManager.LEFT_KEY.getName();
                        RightKey = settingManager.RIGHT_KEY.getName();
                        ShootKey = settingManager.SHOOTING_KEY.getName();
                        Skill1Key = settingManager.SKILL1_KEY.getName();
                        Skill2Key = settingManager.SKILL2_KEY.getName();
                        Skill3Key = settingManager.SKILL3_KEY.getName();

                        UpText.setText(UpKey);
                        DownText.setText(DownKey);
                        LeftText.setText(LeftKey);
                        RightText.setText(RightKey);
                        ShootingText.setText(ShootKey);
                        Skill1Text.setText(Skill1Key);
                        Skill2Text.setText(Skill2Key);
                        Skill3Text.setText(Skill3Key);



                        return 3;
                    }
                };

                new Thread(task).start();
            }
        });

        slider0.valueProperty().addListener(new ChangeListener<Number> (){
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                settingManager.changeMusicVolume(new_val.doubleValue());

            }
        } );



        slider1.valueProperty().addListener(new ChangeListener<Number> (){
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                settingManager.changeFXVolume(new_val.doubleValue());

            }
        } );



        gridPaneS = new GridPane();
        gridPaneS.add(button0,2,2);
        gridPaneS.add(slider0,2,3);
        gridPaneS.add(slider1,2,4);
        gridPaneS.add(button1,2,5);
        gridPaneS.add(UpText,2,6);
        gridPaneS.add(DownText,2,7);
        gridPaneS.add(LeftText,2,8);
        gridPaneS.add(RightText,2,9);
        gridPaneS.add(ShootingText,2,10);
        gridPaneS.add(Skill1Text,2,11);
        gridPaneS.add(Skill2Text,2,12);
        gridPaneS.add(Skill3Text,2,13);
        //gridPaneS.add(button1,2,5);

        final Image backgroundImage = new Image (SettingsMenuPane.class.getResource("Settings_Background.bmp").toString());

        final BackgroundImage backgi = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        final Background backg = new Background(backgi);

        gridPaneS.setBackground(backg);

    }

    public GridPane getPane(){ return gridPaneS; }

}
