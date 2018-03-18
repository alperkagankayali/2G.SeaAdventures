package sample;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SettingManager {
    private int pressedKey;
    private boolean isKeyReleased;

    public boolean isKeyReleased() {
        return isKeyReleased;
    }
    public SettingManager(){

    }
    public int getPressedKey(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (SettingManager.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            pressedKey = ke.getKeyCode();
                            isKeyReleased = false;
                            break;
                        case KeyEvent.KEY_RELEASED:
                            pressedKey = 0;
                            isKeyReleased = true;
                            break;
                    }
                    return false;
                }
            }
        });
        //return new KeyEvent(this).getKeyCode();
        return pressedKey;
    }
}
