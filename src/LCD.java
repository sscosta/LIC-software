
public class LCD { // Escreve no LCD usando a interface a 8 bits.

    private static int RS_MASK = 0x01;
    private static final int LINES = 2, COLS = 16; // Dimensão do display.
    // Escreve um comando/dados no LCD
    private static void writeByte(boolean rs, int data){
        data<<=1;
        int RS = rs==false ?0:1;
        data+=RS;
            SerialEmitter.send(SerialEmitter.Destination.SLCD,9,data);
    }


    // Escreve um comando no LCD
    private static void writeCMD(int data) {
        writeByte(false,data);

    }
    // Escreve um dado no LCD
    private static void writeDATA(int data) {
        writeByte(true,data);
    }
    // Envia a sequência de iniciação para comunicação a 8 bits.
    public static void init() {

    }
    // Escreve um caráter na posição corrente.
    public static void write(char c) {
        writeDATA((int) c);
    }
    // Escreve uma string na posição corrente.
    public static void write(String txt) {
        int size=txt.length();
        int i= 0;
        while (size!=0){
            write(txt.charAt(i));
            i++;
            size--;
        }
    }
    // Envia comando para posicionar cursor (‘lin’:0..LINES-1 , ‘col’:0..COLS-1)
    public static void cursor(int lin, int col) {
        
    }
}
