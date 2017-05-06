
public class SerialEmitter { // Envia tramas para os diferentes módulos Serial Receiver.
    public static enum Destination {SLCD,SSC};
    private static int SERIALEMITTERMASK=0x0F;

    private static int SCLK_MASK = 0x2;
    private static int SDX_MASK = 0x1;

    private static int DESTINATION_MASKS[] = {0x8, 0x4};

    // Inicia a classe
    public static void init() {
        for ( int m : DESTINATION_MASKS){
            HAL.setBits(m);
        }
    }

    // Envia uma trama para o SerialReceiver identificado por addr, com a dimensão de size e os bits de ‘data’.
    public static void send(Destination addr, int size, int data) {
        int sdx;
        int parity = 0;
        HAL.clrBits(DESTINATION_MASKS[addr.ordinal()]);
        HAL.clrBits(SCLK_MASK|SDX_MASK);
        while (size > 0) {
            sdx = data % 2;
            data /= 2;
            parity ^= sdx;
            send(sdx);
           clock();
            size--;
        }
        send(parity);
        clock();
        HAL.setBits(DESTINATION_MASKS[addr.ordinal()]);
    }

    private static void clock (){
        HAL.setBits(SCLK_MASK);
        HAL.clrBits(SCLK_MASK);
    }
    private static void send (int data){
        HAL.writeBits(SDX_MASK,data);
        /*HAL.setBits(SDX_MASK);
        HAL.clrBits(SDX_MASK);*/
    }
}