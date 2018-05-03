import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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

    private String UpKey;
    private String DownKey;
    private String LeftKey;
    private String RightKey;
    private String ShootKey;
    private String Skill1Key;
    private String Skill2Key;
    private String Skill3Key;
    GridPane gridPaneS;
    Scene sceneS;
    Thread changeControlThread;

    SettingsMenuPane(SettingManager settingManager){

        changeControlThread = new Thread();

        double buttonWidth = 125;
        double buttonHeight = 10;

        UpKey = settingManager.UP_KEY.getName();
        DownKey = settingManager.DOWN_KEY.getName();
        LeftKey = settingManager.LEFT_KEY.getName();
        RightKey = settingManager.RIGHT_KEY.getName();
        ShootKey = settingManager.SHOOTING_KEY.getName();
        Skill1Key = settingManager.SKILL1_KEY.getName();
        Skill2Key = settingManager.SKILL2_KEY.getName();
        Skill3Key = settingManager.SKILL3_KEY.getName();

        Text MusicVText = new Text("Music Volume:");
        Text FxVText = new Text("Effects Volume:");
        Text ControlText = new Text("Controls:");
        Text UpText = new Text(UpKey);
        Text DownText = new Text(DownKey);
        Text LeftText = new Text(LeftKey);
        Text RightText = new Text(RightKey);
        Text ShootingText = new Text(ShootKey);
        Text Skill1Text = new Text(Skill1Key);
        Text Skill2Text = new Text(Skill2Key);
        Text Skill3Text = new Text(Skill3Key);

        final Button butUp = new Button("Change Up");
        final Button butDown = new Button("Change Down");
        final Button butLeft = new Button("Change Left");
        final Button butRight = new Button("Change Right");
        final Button butShoot = new Button("Change Shoot");
        final Button butSkl1 = new Button("Change Skill1");
        final Button butSkl2 = new Button("Change Skill2");
        final Button butSkl3 = new Button("Change Skill3");
        final Button butBack = new Button("Back");

        final Button button0 = new Button("Change Music");

        final Slider slider0 = new Slider(0,1,settingManager.getMusicVolume());

        final Slider slider1 = new Slider(0,1,settingManager.getFXVolume());

        button0.setPrefWidth(buttonWidth);
        butUp.setPrefWidth(buttonWidth);
        butDown.setPrefWidth(buttonWidth);
        butLeft.setPrefWidth(buttonWidth);
        butRight.setPrefWidth(buttonWidth);
        butShoot.setPrefWidth(buttonWidth);
        butSkl1.setPrefWidth(buttonWidth);
        butSkl2.setPrefWidth(buttonWidth);
        butSkl3.setPrefWidth(buttonWidth);
        slider0.setPrefWidth(buttonWidth);
        slider1.setPrefWidth(buttonWidth);
        butBack.setPrefWidth(buttonWidth);

        button0.setPrefHeight(buttonHeight);
        butUp.setPrefHeight(buttonHeight);
        butDown.setPrefHeight(buttonHeight);
        butLeft.setPrefHeight(buttonHeight);
        butRight.setPrefHeight(buttonHeight);
        butShoot.setPrefHeight(buttonHeight);
        butSkl1.setPrefHeight(buttonHeight);
        butSkl2.setPrefHeight(buttonHeight);
        butSkl3.setPrefHeight(buttonHeight);
        slider0.setPrefHeight(buttonHeight);
        slider1.setPrefHeight(buttonHeight);
        butBack.setPrefHeight(buttonHeight);





        button0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) { settingManager.changeBackGroundMusic(); }
        });

        /**
         * Music slider
         */
        slider0.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                settingManager.changeMusicVolume(new_val.doubleValue());

            }
        } );

        /**
         * Fx slider
         */

        slider1.valueProperty().addListener(new ChangeListener<Number> (){
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                settingManager.changeFXVolume(new_val.doubleValue());

            }
        } );

        /**
         * Controller buttons
         */
        butUp.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();


                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeUpKey();

                        UpKey = settingManager.UP_KEY.getName();

                        UpText.setText(UpKey);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butDown.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeDownKey();

                        DownKey = settingManager.DOWN_KEY.getName();

                        DownText.setText(DownKey);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butLeft.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeLeftKey();

                        LeftKey = settingManager.LEFT_KEY.getName();

                        LeftText.setText(LeftKey);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butRight.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeRightKey();

                        RightKey = settingManager.RIGHT_KEY.getName();

                        RightText.setText(RightKey);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butShoot.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeShootKey();

                        ShootKey = settingManager.SHOOTING_KEY.getName();

                        ShootingText.setText(ShootKey);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butSkl1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeSkill1Key();

                        Skill1Key = settingManager.SKILL1_KEY.getName();

                        Skill1Text.setText(Skill1Key);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butSkl2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeSkill2Key();

                        Skill2Key = settingManager.SKILL2_KEY.getName();

                        Skill2Text.setText(Skill2Key);

                        return 3;

                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        butSkl3.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(changeControlThread.isAlive())
                    changeControlThread.stop();

                Task task = new Task() {
                    protected Integer call() {
                        settingManager.changeSkill3Key();

                        Skill3Key = settingManager.SKILL3_KEY.getName();

                        Skill3Text.setText(Skill3Key);

                        return 3;
                    }
                };

                changeControlThread = new Thread(task);
                changeControlThread.start();
            }
        });

        /**
         * Back Button
         */

        butBack.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Tester.getPrimaryStage().setScene(Tester.getPrimaryScene());

            }
        });





        gridPaneS = new GridPane();

        gridPaneS.setHgap(10);
        gridPaneS.setVgap(10);

        gridPaneS.getColumnConstraints().add(new ColumnConstraints(0));
        gridPaneS.getColumnConstraints().add(new ColumnConstraints(276));
        gridPaneS.getRowConstraints().add(new RowConstraints(20));

        gridPaneS.add(button0,2,2);
        gridPaneS.add(slider0,3,3);
        gridPaneS.add(slider1,3,4);

        gridPaneS.add(MusicVText,2,3);
        gridPaneS.add(FxVText,2,4);
        gridPaneS.add(ControlText,2,6);
        gridPaneS.add(UpText,2,7);
        gridPaneS.add(DownText,2,8);
        gridPaneS.add(LeftText,2,9);
        gridPaneS.add(RightText,2,10);
        gridPaneS.add(ShootingText,2,11);
        gridPaneS.add(Skill1Text,2,12);
        gridPaneS.add(Skill2Text,2,13);
        gridPaneS.add(Skill3Text,2,14);

        gridPaneS.add(butUp,3,7);
        gridPaneS.add(butDown,3,8);
        gridPaneS.add(butLeft,3,9);
        gridPaneS.add(butRight,3,10);
        gridPaneS.add(butShoot,3,11);
        gridPaneS.add(butSkl1,3,12);
        gridPaneS.add(butSkl2,3,13);
        gridPaneS.add(butSkl3,3,14);

        gridPaneS.add(butBack,1,14);

        final Image backgroundImage = new Image (SettingsMenuPane.class.getResource("Settings_Background.bmp").toString(),852,480,false,false);

        final BackgroundImage backgi = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        final Background backg = new Background(backgi);

        gridPaneS.setBackground(backg);
        gridPaneS.setPrefSize(852,480);
        //gridPaneS.setGridLinesVisible(true);

        sceneS = new Scene(gridPaneS,852,480);
    }

    /**
     *
     * @return this.Pane
     */
    public GridPane getPane(){ return gridPaneS; }

    public Scene getScene(){ return sceneS; }

}
