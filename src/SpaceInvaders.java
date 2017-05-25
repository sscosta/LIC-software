import isel.leic.utils.Time;

import java.util.Random;

public class SpaceInvaders {

    public static void main(String[] args) {
        //HAL.init();
        //SerialEmitter.init();
        KBD.init();
        LCD.init();
        char key;
        while(true) {
            key = KBD.getKey();
            if(key!=0)
            LCD.write(key);
        }
    }
}
