import isel.leic.utils.Time;

public class LCD { // Escreve no LCD usando a interface a 8 bits.
    public static final int LINES = 2, COLS = 16; // Dimensão do display.
    public static final String GUNSHIP ="\u0001";
    public static final String HUMANSHIP ="\u0002";
    public static final String ALIANSHIP ="\u0003";

    public static void main(String[] args) {

        HAL.init();
        SerialEmitter.init();
        init();
        //write('A');
        write("0123456789ABCDEF");
        SetcursorPosition(0,5);
        write("teste");
        SetcursorPosition(1,5);
        write(GUNSHIP + " " + HUMANSHIP+" " + ALIANSHIP+" " +ALIANSHIP);
        SetCursorBlink(false);
        while (true){
            SetcursorPosition(0,2);
            write("M");
            Time.sleep(1000);
            SetcursorPosition(0,2);
            write("\u0004");
            Time.sleep(150);
            SetcursorPosition(0,2);
            write("\u0005");
            Time.sleep(150);
            SetcursorPosition(0,2);
            write(" ");
            Time.sleep(1000);
        }

    }

    // Envia a sequência de iniciação para comunicação a 8 bits.
    public static void init() {
        //Function set
        writeCMD(0b00110000);
        //waits 5 milisec
        Time.sleep(5);
        writeCMD(0b00110000);
        //Display on/off control
        writeCMD(0b00110000);
        Time.sleep(1);
        //Entry mode set
        //writeCMD(0b00110000);
        //character font and number of lines
        writeCMD(0b00111000);
        //Sets entire display off, cursor on, and blinking of cursor position character (B)
        writeCMD(0b00001000);
        //Clears entire display and sets DDRAM address 0 in address counter
        writeCMD(0b00000001);
        //Sets cursor move direction and specifies display shift. Performed during data write and read
        writeCMD(0b00000110);
        //Sets display to on
        writeCMD(0b00001111);

        CreateSpecialChars();
    }

    // Escreve um comando/dados no LCD
    private static void writeByte(boolean rs, int data){
        data<<=1;
        int RS = !rs ?0:1;
        data+=RS;
        SerialEmitter.send(SerialEmitter.Destination.SLCD,9,data);
        Time.sleep(10);
    }

    // Escreve um comando no LCD
    private static void writeCMD(int data) {
        writeByte(false,data);
    }

    // Escreve um dado no LCD
    private static void writeDATA(int data) {
        writeByte(true,data);
    }


    // Escreve um caráter na posição corrente.
    public static void write(char c) {
        writeDATA((int) c);
    }


    public static void writeAT(char c,int linAT,int colAT) {
        SetCursorBlink(false);
        SetcursorPosition(linAT,colAT);
        writeDATA((int) c);
        SetCursorBlink(true);
    }
    // Escreve uma string na posição corrente.
    public static void write(String txt) {
        for (int i = 0; i < txt.length(); i++) {
            write(txt.charAt(i));
        }
    }

    // Envia comando para posicionar cursor (‘lin’:0..LINES-1 , ‘col’:0..COLS-1)
    public static void SetcursorPosition(int lin, int col) {
        if (lin>LINES-1 || col>COLS-1)
            return;
        writeCMD(0x80+ (lin==1?0x40:0x0) +col );//0x80 - set cursor cmd; line 0= 0x0; line 1 =0x40;
    }
    public static void SetCursorBlink(boolean blink) {
        writeCMD(blink?0xF:0xC);
    }

    private static void CreateSpecialChars(){
        SendSpecialChar(1, new int[]{
                0b00000111, //7
                0b00001000, //8
                0b00011110, //30
                0b00001000, //8
                0b00011110, //30
                0b00001000, //8
                0b00000111, //7
                0b00000000  //0
                });
        SendSpecialChar(2, new int[]{
                0b00011110, //30
                0b00011000, //24
                0b00011100, //28
                0b00011111, //31
                0b00011100, //28
                0b00011000, //24
                0b00011110, //30
                0b00000000  //0
                });
        SendSpecialChar(3, new int[]{
                0b00011111, //31
                0b00011111, //31
                0b00010101, //21
                0b00011111, //31
                0b00011111, //31
                0b00010001, //17
                0b00010001, //17
                0b00000000  //0
        });

        SendSpecialChar(4, new int[]{
                0b00000000, //0
                0b00001010, //5
                0b00001010, //5
                0b00000100, //4
                0b00001010, //5
                0b00001010, //5
                0b00000000, //0
                0b00000000  //0
        });

        SendSpecialChar(5, new int[]{
                0b00010101, //21
                0b00010101, //21
                0b00001010, //10
                0b00000000, //0
                0b00001010, //10
                0b00010101, //21
                0b00010101, //21
                0b00000000  //0
        });

    }

    private static void SendSpecialChar(int addrMemory,int[] data){
        int addr = 64 | addrMemory * 8;
        writeCMD( addr);
        int dataline;
        for(int i = 0; i < 8; ++i) {
            dataline = data[i];
            writeDATA(dataline);
        }
    }

}
