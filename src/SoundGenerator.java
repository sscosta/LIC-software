import isel.leic.utils.Time;
import isel.leic.utils.*;

public class SoundGenerator {
    private static int CMD_STOP_MASK =0;
    private static int CMD_PLAY_MASK =0b1;
    private static int CMD_SETSOUND_MASK =0b10;
    private static int CMD_SETVOL_MASK =0b11;

    public static void main(String[] args) {
        HAL.init();
        SerialEmitter.init();
        init();
        play(1);
        play(2);
        setVolume(1);
        setVolume(2);
        stop();
    }
    // Inicia a classe, estabelecendo os valores iniciais.
    public static void init() {
        play(0);
        stop();
        setVolume(0);
    }

    //    // Envia comando para reproduzir um som, com a identificação deste
    public static void play(int sound) {
        SerialEmitter.send(SerialEmitter.Destination.SSC,4, CMD_PLAY_MASK);
        Time.sleep(10);
        int data = (sound<<2) + CMD_SETSOUND_MASK;
        SerialEmitter.send(SerialEmitter.Destination.SSC,4,data);
    }

    //    // Envia comando para parar o som
    public static void stop() {
        SerialEmitter.send(SerialEmitter.Destination.SSC,4,CMD_STOP_MASK);
    }

    //    // Envia comando para definir o volume do som
    public static void setVolume(int volume) {
        SerialEmitter.send(SerialEmitter.Destination.SSC,4,((volume<<2) + CMD_SETVOL_MASK));
    }
}
