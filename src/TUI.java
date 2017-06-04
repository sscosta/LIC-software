import java.security.PublicKey;

public class TUI {
    public static void main(String[] args) {
        HAL.init();
        SerialEmitter.init();
        LCD.init();
        KBD.init();

        // LCD.ClearLCD();
        String s =TUI.getString(5,0,6);

        System.out.println(s);

    }

    private static final char ENTER_KEY = '5';
    private static final char LEFT_ARROW = '4';
    private static final char RIGHT_ARROW = '6';
    private static final char UP_ARROW = '2';
    private static final char DOWN_ARROW = '8';
    private static final char DELETE_KEY = '*';

    private static final String SCORETXT="SCORE:";

    public void init(){
        HAL.init();
        SerialEmitter.init();
        SoundGenerator.init();
        LCD.init();
        LCD.SetcursorPosition(0,0);
    }

    public static String getString(int size,int linStart, int colStart) {
        char[] sarry = new char[size];

        int pos = 0;
        LCD.SetcursorPosition(linStart, colStart + pos);

        sarry[0] = 'A';
        writeFinalPos(sarry[0], linStart, colStart + pos);

        char keyPressed = 0;

        while (keyPressed != ENTER_KEY) {
            keyPressed = KBD.getKey();

            switch (keyPressed) {
                case 0:
                    break;
                case RIGHT_ARROW:
                    if (pos < size - 1) {
                        pos++;
                        LCD.SetcursorPosition(linStart, colStart + pos);
                    }
                    if (sarry[pos] < 'A' || sarry[pos] > 'Z') {
                        sarry[pos] = 'A';
                        writeFinalPos(sarry[pos], linStart, colStart + pos);
                    }
                    break;

                case LEFT_ARROW:
                    if (pos > 0) {
                        pos--;
                        LCD.SetcursorPosition(linStart, colStart + pos);
                    }
                    break;

                case UP_ARROW:
                    if (sarry[pos] < 'A' || sarry[pos] >= 'Z') {
                        sarry[pos] = 'A';
                        writeFinalPos(sarry[pos], linStart, colStart + pos);
                    } else if (sarry[pos] < 'Z') {
                        sarry[pos]++;
                        writeFinalPos(sarry[pos], linStart, colStart + pos);
                    }
                    break;

                case DOWN_ARROW:
                    if (sarry[pos] < 'A' || sarry[pos] > 'Z') {
                        sarry[pos] = 'A';
                        writeFinalPos(sarry[pos], linStart, colStart + pos);
                    } else if (sarry[pos] == 'A') {
                        sarry[pos] = 'Z';
                        writeFinalPos(sarry[pos], linStart, colStart + pos);
                    } else if (sarry[pos] > 'A') {
                        sarry[pos]--;
                        writeFinalPos(sarry[pos], linStart, colStart + pos);
                    }
                    break;

                case DELETE_KEY:
                    if (pos == size - 1 || (pos > 0 && sarry[pos + 1] == 0)) {
                        sarry[pos] = 0;
                        writeFinalPos(sarry[pos], linStart, colStart + (--pos));
                    }
                    break;
            }
        }

        return String.valueOf(sarry).trim();
    }


    // Escreve um caráter na posição corrente e define posição final.
    public static void writeFinalPos(char c,int linFinal,int colFinal) {
        LCD.SetCursorBlink(false);
        LCD.write(c);
        LCD.SetcursorPosition(linFinal,colFinal);
        LCD.SetCursorBlink(true);
    }


    public void write(String str, boolean cursor){
        LCD.SetCursorBlink(cursor);
        LCD.write(str);
    }
  /*
    int writeFrom (int l,int col, String str){
        int ret= 16-col-str.length();
        if(ret<0) return -1;
        LCD.SetcursorPosition (l,col);
        LCD.write(str);
        return ret;
    }
    int writeFrom(int l, int col, char c){
        int ret= 16-col-1;
        if(ret<0) return -1;
        LCD.SetcursorPosition(l,c);
        LCD.write(c);
        return ret;
    }
    */
    void setCursorToLine(int l){
        LCD.SetcursorPosition(l,0);
    }

    void write (String text, int line, int col,boolean cursor){
        LCD.SetCursorBlink(cursor);
        LCD.SetcursorPosition(line,col);
        LCD.write(text);
    }
    void write (int ordinal,HighScore sc,boolean cursor){
        String str = ordinal/10==0?"0":"";
        str+=ordinal+"-"+sc.GetName();
        int n = LCD.COLS - str.length();
        write(str,cursor);
    }

 /*   void SetCursorBlink(boolean blink){
        LCD.SetCursorBlink(blink);
    }
*/
    public static char getKey(){
        return KBD.getKey();
    }

    public static char waitKey(long timeout) {
        return KBD.waitKey(timeout);
    }

    public static void PrintScoreText(){
        LCD.SetcursorPosition(1,0);
        LCD.write(SCORETXT);
    }
    public static void PrintScore(int score){
        LCD.SetcursorPosition(1,SCORETXT.length()+1);
        LCD.write(""+score);
    }


}
