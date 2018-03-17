import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

/**
 * Created by Okur on 13/03/18.
 */
public class SettingsMenuPane {

    GridPane gridPaneS;


    SettingsMenuPane(SettingManager settingManager){

        final Button button0 = new Button("Change Music");
        //final Button button1 = new Button("Change Up Key");
        final Slider slider0 = new Slider(0,1,settingManager.getMusicVolume());
        final Slider slider1 = new Slider(0,1,settingManager.getFXVolume());

        button0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) { settingManager.changeBackGroundMusic(); }
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
        //gridPaneS.add(button1,2,5);


    }

    public GridPane getPane(){ return gridPaneS; }

}
