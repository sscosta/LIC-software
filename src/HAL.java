/**
 * Created by Samuel on 06/04/2017.
 */

/**
 *  virtualiza o acesso ao sistema usbport*/
import isel.leic.UsbPort;
public class HAL {
    static int lastval;

    public static void main(String[] args) {
        SerialEmitter.init();
    }
    public static void init(){
    }
    // Retorna os valores dos bits representados por mask presentes no UsbPort
    public static int readBits(int mask) {
        int s = UsbPort.in();
        return UsbPort.in()& mask;
    }
    // Retorna true se o bit tiver o valor lógico ‘1’
    public static boolean isBit(int mask) {
        return readBits(mask)!=0;
    }
    // Coloca os bits representados por mask no valor lógico ‘1’
    public static void setBits(int mask) {
        lastval|=  mask;
    UsbPort.out(lastval);
    }
    // Coloca os bits representados por mask no valor lógico ‘0’
    public static void clrBits(int mask){
        lastval = lastval & ~mask;
        UsbPort.out(lastval);
    }
    // Escreve nos bits representados por mask o valor de value
    public static void writeBits(int mask, int value) {
        UsbPort.out(value);
        lastval= value;
    }


}
