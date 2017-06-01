public class TUI {
    private static char ENTER_KEY = '5';
    private static final char LEFT_ARROW = '4';
    private static final char RIGHT_ARROW = '6';
    private static final char UP_ARROW = '2';
    private static final char DOWN_ARROW = '8';
    private static final char DELETE_KEY = '*';

    public static void main(String[] args) {
        HAL.init();
        SerialEmitter.init();
        LCD.init();
        KBD.init();

       // LCD.ClearLCD();
        String s =TUI.getString(5,0,6);

        System.out.println(s);

    }


    public static String getString(int size,int linStart, int colStart){
        char[] sarry =new char[size];

        int pos =0;
        LCD.cursor(linStart,colStart+pos);

        sarry[0]='A';
        LCD.write(sarry[0],linStart,colStart+pos);

        char keyPressed =0;

        while (keyPressed!=ENTER_KEY) {
            keyPressed=KBD.getKey();

            if (keyPressed>0) {
                switch (keyPressed) {
                    case RIGHT_ARROW:
                        if (pos < size - 1) {
                            pos++;
                            LCD.cursor(linStart, colStart + pos);
                        }
                        if (sarry[pos] < 'A' || sarry[pos] > 'Z') {
                            sarry[pos] = 'A';
                            LCD.write(sarry[pos], linStart, colStart + pos);
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
                            LCD.write(sarry[pos], linStart, colStart + pos);
                        } else if (sarry[pos] < 'Z') {
                            sarry[pos]++;
                            LCD.write(sarry[pos], linStart, colStart + pos);
                        }
                        break;

                    case DOWN_ARROW:
                        if (sarry[pos] < 'A' || sarry[pos] > 'Z') {
                            sarry[pos] = 'A';
                            LCD.write(sarry[pos], linStart, colStart + pos);
                        } else if (sarry[pos] == 'A') {
                            sarry[pos] = 'Z';
                            LCD.write(sarry[pos], linStart, colStart + pos);
                        } else if (sarry[pos] > 'A') {
                            sarry[pos]--;
                            LCD.write(sarry[pos], linStart, colStart + pos);
                        }
                        break;

                    case DELETE_KEY:
                        if (pos == size - 1 || (pos > 0 && sarry[pos + 1] == 0)) {
                            sarry[pos] = 0;
                            LCD.write(sarry[pos], linStart, colStart + (--pos));
                        }
                        break;
                }
            }
        }


        return String.valueOf( sarry).trim();

    }
}
