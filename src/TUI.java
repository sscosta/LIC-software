public class TUI {
    private static char ENTER_KEY = '5';
    private static final char LEFT_ARROW = '4';
    private static final char RIGHT_ARROW = '6';
    private static final char UP_ARROW = '2';
    private static final char DOWN_ARROW = '0';
    private static final char DELETE_KEY = '*';

    public static void main(String[] args) {
        HAL.init();
        SerialEmitter.init();
        LCD.init();
        KBD.init();

        TUI t = new TUI();
//        LCD.ClearLCD();
        String s =t.getString(5,0,0);

        System.out.println(s);

    }


    public String getString(int size,int linStart, int colStart){
        char[] sarry =new char[size];

        int pos =0;
        LCD.cursor(0,pos);

        sarry[0]='A';
        LCD.write(sarry[0],0,pos);

        char keyPressed =0;

        while (keyPressed!=ENTER_KEY) {
            keyPressed=KBD.getKey();

            switch(keyPressed) {
                case '6': //RIGHT_ARROW
                    if (pos<size-1) {
                        pos++;
                        LCD.cursor(0,pos);
                    }
                    if (sarry[pos]<'A' || sarry[pos]>'Z') {
                        sarry[pos] = 'A';
                        LCD.write(sarry[pos],0,pos);
                    }
                    break;

                case LEFT_ARROW:
                    if (pos>0) {
                        pos--;
                        LCD.cursor(0,pos);
                    }
                    break;

                case '2': //UP_ARROW
                    if (sarry[pos]<'A' || sarry[pos]>='Z') {
                        sarry[pos] = 'A';
                        LCD.write(sarry[pos],0,pos);
                    }
                    else if (sarry[pos]<'Z') {
                        sarry[pos]++;
                        LCD.write(sarry[pos],0,pos);
                    }
                    break;

                case '8': //DOWN_ARROW
                    if (sarry[pos]<'A' || sarry[pos]>'Z') {
                        sarry[pos] = 'A';
                        LCD.write(sarry[pos],0,pos);
                    }
                    else if (sarry[pos]=='A') {
                        sarry[pos]='Z';
                        LCD.write(sarry[pos],0,pos);
                    }
                    else if (sarry[pos]>'A') {
                        sarry[pos]--;
                        LCD.write(sarry[pos],0,pos);
                    }
                    break;

                case '*': //DELETE_KEY
                    if (pos==size-1 || (pos>0 && sarry[pos+1]==0)) {
                        sarry[pos] = 0;
                        LCD.write(sarry[pos],0,--pos);
                    }
/*
                    else if (pos>0 && sarry[pos+1]==0) {
                        sarry[pos] = 0;
                        LCD.write(sarry[pos],0,--pos);
                    }
*/
            break;
            }
        }


        return String.valueOf( sarry).trim();

    }
}
