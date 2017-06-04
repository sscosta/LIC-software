import isel.leic.utils.Time;

/**
 * Created by Colapso on 01/06/17.
 */
public class Jogo {



    private char missile = '1';
    private char SHOT_KEY = '*';
    private char bug = '1';
    private int upper_alien_number = 9;
    private int lower_alien_number = 0;
    private int numberOfAliens = 1;
    private char alien_incoming = (char)((Math.random() * (upper_alien_number- lower_alien_number)) + lower_alien_number+48);
    private int columnStart = 16;
    private char [] alienTrain = {0,0,0,0,0,0,0,0,0,0,0,0,0,alien_incoming};
    private int score= 0;
    private String writeInSecondLineScreen = "Score:"+score;

    private long timeAlien;

    public Jogo()
    {
        init();
        generateGame();
    }


    public  void init()
    {
        LCD.cursorBlink(false);
        timeAlien = System.currentTimeMillis();
        LCD.cursor(0,0);
        writeMissile(missile,0,0);
        writeBug(bug,0,1,columnStart);
        writeString(new String(alienTrain),0,2,columnStart);
        Time.sleep(2000);

    }

    private static void writeBug(char i, int line, int column, int columnStart) {
        LCD.cursor(line,column);
        LCD.write(i);
    }

    public  void generateGame()
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
            if(System.currentTimeMillis()-timeAlien> 2000) {
                timeAlien = System.currentTimeMillis();
                addAlienToArray(alienTrain);
                ++numberOfAliens;
                writeString(new String(alienTrain), 0, 2, columnStart);
            }

        }
    }

    private void shoot(char key) {

        for (int i = 2; i <alienTrain.length ; i++) {
            if(alienTrain[i] != 0)
                if(missile == alienTrain[i]) {
                    alienTrain[i] = 0;

                    updateScore();
                    break;
                }
        }
    }


    private void updateScore() {
        ++score;
        writeString(writeInSecondLineScreen,1,0,columnStart);
    }

    private void updateView(char key) {
        missile =(char) (key);
        writeMissile(missile,0,0);
    }


    public void addAlienToArray(char[] train)
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



    public char generateAlienNumber()
    {
        return (char)((Math.random() * (upper_alien_number- lower_alien_number)) + lower_alien_number+48);
    }

    public void writeString(String a,int line, int column,int size)
    {

        LCD.cursor(line,column);
        LCD.write(a);

    }

    public void writeMissile(char a,int line, int column)
    {
        LCD.cursor(line,column);
        LCD.write(a);
        //LCD.cursor(line,column);

    }


}

