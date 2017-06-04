import isel.leic.utils.Time;

/**
 * Created by Colapso on 01/06/17.
 */
public class Jogo {



    private static char missile = '1';
    private static char SHOT_KEY = '*';
    private static char bug = '1';
    private static int upper_alien_number = 9;
    private static int lower_alien_number = 0;
    private static int numberOfAliens = 1;
    private static char alien_incoming = (char)((Math.random() * (upper_alien_number- lower_alien_number)) + lower_alien_number+48);
    private static int columnStart = 16;
    private static char [] writeInFirstLineScreen = {missile,bug,0,0,0,0,0,0,0,0,0,0,0,0,0,alien_incoming};
    private static char [] alienTrain = {0,0,0,0,0,0,0,0,0,0,0,0,0,alien_incoming};
    private static int score= 0;
    private static String writeInSecondLineScreen = "Score:"+score;

    public Jogo()
    {
        init();
        generateGame();
    }


    public static void init()
    {

        LCD.SetcursorPosition(0,0);
        writeString(new String(writeInFirstLineScreen),0,0,columnStart);
        writeString(writeInSecondLineScreen,1,0,columnStart);
        Time.sleep(2000);

    }

    public static void generateGame()
    {

        char keyPressed =0;

        while(true) {                       // condiçao sera ate caracter chegar à nave
            keyPressed = KBD.getKey();
            if(keyPressed != 0) {
                if (keyPressed == SHOT_KEY)
                    shoot(keyPressed);
                else if (keyPressed != '#')
                    updateView(keyPressed);
            }
            addAlienToArray(alienTrain);
            ++numberOfAliens;
            addAlienToTrain(writeInFirstLineScreen, alienTrain);
            writeString(new String(writeInFirstLineScreen), 0, 0, columnStart);
            Time.sleep(2000);
        }
    }

    private static void shoot(char key) {

        for (int i = 2; i <writeInFirstLineScreen.length ; i++) {
            if(writeInFirstLineScreen[i] != 0)
                if(missile == writeInFirstLineScreen[i]) {
                    writeInFirstLineScreen[i] = 0;
                    alienTrain[i-2] = 0;
                    updateViewAfterShoot();
                    updateScore();
                    break;
                }
        }
    }

    private static void updateViewAfterShoot() {
        writeString(new String(writeInFirstLineScreen),0,0,columnStart);

    }

    private static void updateScore() {
        ++score;
        writeString(writeInSecondLineScreen,1,0,columnStart);
    }

    private static void updateView(char key) {
        missile =(char) (key);
        writeInFirstLineScreen[0] = missile;
        writeString(new String(writeInFirstLineScreen),0,0,columnStart);
    }


    public static void addAlienToTrain(char[]writeInFirstLineScreen,char[] alienTrain)
    {

        for (int i = writeInFirstLineScreen.length-1; i >1 ; i--) {
            writeInFirstLineScreen[i] = alienTrain[i-2];
        }
    }

    public static void addAlienToArray(char[] train)
    {
        alien_incoming = generateAlienNumber();
        for (int i = 0; i <train.length; i++) {
            if(i < train.length-1){
                if(train[i+1] != 0)
                    train[i] = train[i+1];
            }
            if(i == train.length-1)
                train[i] = alien_incoming;
        }

    }



    public static char generateAlienNumber()
    {
        return (char)((Math.random() * (upper_alien_number- lower_alien_number)) + lower_alien_number+48);
    }

    public static void writeString(String a,int line, int column,int size)
    {
        LCD.SetcursorPosition(line,column);
        LCD.write(a);

    }



}

