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

    private static char ENTER_KEY = '5';
    private static final char LEFT_ARROW = '4';
    private static final char RIGHT_ARROW = '6';
    private static final char UP_ARROW = '2';
    private static final char DOWN_ARROW = '8';
    private static final char DELETE_KEY = '*';

    public static String getString(int size,int linStart, int colStart){
        char[] sarry =new char[size];

        int pos =0;
        LCD.cursor(linStart,colStart+pos);

        sarry[0]='A';
        LCD.writeFinalPos(sarry[0],linStart,colStart+pos);

        char keyPressed =0;

        while (keyPressed!=ENTER_KEY) {
            keyPressed=KBD.getKey();

            switch (keyPressed) {
                case 0:
                    break;
                case RIGHT_ARROW:
                    if (pos < size - 1) {
                        pos++;
                        LCD.cursor(linStart, colStart + pos);
                    }
                    if (sarry[pos] < 'A' || sarry[pos] > 'Z') {
                        sarry[pos] = 'A';
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + pos);
                    }
                    break;

                case LEFT_ARROW:
                    if (pos > 0) {
                        pos--;
                        LCD.cursor(linStart, colStart + pos);
                    }
                    break;

                case UP_ARROW:
                    if (sarry[pos] < 'A' || sarry[pos] >= 'Z') {
                        sarry[pos] = 'A';
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + pos);
                    } else if (sarry[pos] < 'Z') {
                        sarry[pos]++;
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + pos);
                    }
                    break;

                case DOWN_ARROW:
                    if (sarry[pos] < 'A' || sarry[pos] > 'Z') {
                        sarry[pos] = 'A';
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + pos);
                    } else if (sarry[pos] == 'A') {
                        sarry[pos] = 'Z';
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + pos);
                    } else if (sarry[pos] > 'A') {
                        sarry[pos]--;
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + pos);
                    }
                    break;

                case DELETE_KEY:
                    if (pos == size - 1 || (pos > 0 && sarry[pos + 1] == 0)) {
                        sarry[pos] = 0;
                        LCD.writeFinalPos(sarry[pos], linStart, colStart + (--pos));
                    }
                    break;
            }
        }

        return String.valueOf( sarry).trim();

    public void init(){
        HAL.init();
        SerialEmitter.init();
        SoundGenerator.init();
        LCD.init();
        LCD.cursor(0,0);
    }

    public char getRandomNum() {
        return (char) ((int)(Math.random() * 9)+48);
    }

    public char getKey(){
        return KBD.getKey();
    }
    public void write(String str, boolean cursor){
        enableBlinkingCursor(cursor);
        LCD.write(str);
    }
    int writeFrom (int l,int col, String str){
        int ret= 16-col-str.length();
        if(ret<0) return -1;
        LCD.cursor (l,col);
        LCD.write(str);
        return ret;
    }
    int writeFrom(int l, int col, char c){
        int ret= 16-col-1;
        if(ret<0) return -1;
        LCD.cursor(l,c);
        LCD.write(c);
        return ret;
    }
    void setCursorToLine(int l){
        LCD.cursor(l,0);
    }
    void write (String text, int line, int col,boolean cursor){
        enableBlinkingCursor(cursor);
        LCD.cursor(line,col);
        LCD.write(text);
    }
    void write (int ordinal,Score sc,boolean cursor){
        String str = ordinal/10==0?"0":"";
        str+=ordinal+"-"+sc.getName();
        int n = MAX_COLS - str.length();
        write(str,cursor);
    }
    void enableBlinkingCursor(boolean blink){
        int cmd =blink?0xF:0xC;
        LCD.writeCMD(cmd);
    }
}
