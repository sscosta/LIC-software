
public class TUI {
    char ENTER_KEY = '5';
    char LEFT_ARROW = '4';
    char RIGHT_ARROW = '6';
    char UP_ARROW = '2';
    char DOWN_ARROW = '0';
    char DELETE_KEY = '9';

    public static void main(String[] args) {


    }

    public String getString(int size) {
        int i = 0;
        String str = "";
        while (KBD.getKey() != ENTER_KEY) {

            char c = 'A';
            char key = KBD.getKey();
            if (key == UP_ARROW)
                ++c;
            if (key == DOWN_ARROW && c != 'A') {
                --c;
                if (key == RIGHT_ARROW && size - i != 0) {
                    i++;
                    str += c;
                }
                if (key == LEFT_ARROW && i > 0) {
                    i--;
                    str += c;

                }
                size--;
                //setas

            }
            return str;
        }
        return str;
    }
}