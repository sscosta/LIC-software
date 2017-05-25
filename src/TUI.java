public class TUI {
    private static char ENTER_KEY = '5';
    private static char LEFT_ARROW = '4';
    private static char RIGHT_ARROW = '6';
    private static char UP_ARROW = '2';
    private static char DOWN_ARROW = '0';
    private static char DELETE_KEY = '*';

    public static void main(String[] args) {
        HAL.init();
        SerialEmitter.init();
        LCD.init();
        KBD.init();

        TUI t = new TUI();
        String s =t.getString(5);

        System.out.println(s);

    }


    public String getString(int size){
        char[] sarry =new char[size];
        int pos =0;
        for (char c: sarry) c=' ';
        LCD.ClearLCD();

        char keyPressed =0;
        while (keyPressed!=ENTER_KEY) {
            keyPressed=KBD.getKey();

            switch(keyPressed) {
                case '6': //RIGHT_ARROW
                    if (pos<size-1) pos++;
                    if (sarry[pos]<'A' || sarry[pos]>'Z')
                        sarry[pos]='A';
                    break;

                case '4': //LEFT_ARROW
                    if (pos>0) pos--;
                    break;

                case '2': //UP_ARROW
                    if (sarry[pos]<'A' || sarry[pos]>='Z')
                        sarry[pos]='A';
                    else if (sarry[pos]<'Z')
                        sarry[pos]++;
                    break;

                case '0': //DOWN_ARROW
                    if (sarry[pos]<'A' || sarry[pos]>'Z')
                        sarry[pos]='A';
                    else if (sarry[pos]>'A')
                        sarry[pos]--;
                    break;

                case '9': //DELETE_KEY
                    if (pos==size-1) {
                        sarry[pos] = ' ';
                        pos--;
                    }
                    else if (pos>0 && sarry[pos+1]==' ') {
                        sarry[pos] = ' ';
                        pos--;
                    }
                    break;
            }
        }

        return sarry.toString().trim();

    }
}
