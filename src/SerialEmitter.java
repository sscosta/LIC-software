/**
 * Created by Samuel on 27/04/2017.
 */
public class SerialEmitter { // Envia tramas para os diferentes módulos Serial Receiver.
    public static enum Destination {SLCD,SSC};
    private static int SERIALEMITTERMASK=0x0F;

    private static int SCLKCONTROL = 0x2;

    private static int DESTINATION_MASKS[] = {0x8, 0x4};

    // Inicia a classe
    public static void init() {
      send(Destination.SLCD,9, 0xFF);
      //send(Destination.SLCD,9, 0x0);

    }

    // Envia uma trama para o SerialReceiver identificado por addr, com a dimensão de size e os bits de ‘data’.
    public static void send(Destination addr, int size, int data) {
        int checksum=0;
        int control;
        HAL.writeBits(SERIALEMITTERMASK, 0xff);
        int destAddress= addr == Destination.SLCD ? DESTINATION_MASKS[1] : DESTINATION_MASKS[0];
        while (size>0){
            control =destAddress;
            int sdx = data%2;
            checksum^=sdx;
            control+=sdx;
            HAL.writeBits(SERIALEMITTERMASK,control);
            HAL.writeBits(SERIALEMITTERMASK, control+SCLKCONTROL);
            data/=2;
            size--;
        }
        control=destAddress+checksum;
        HAL.writeBits(SERIALEMITTERMASK, control);
        HAL.writeBits(SERIALEMITTERMASK, control+SCLKCONTROL);
    }
}