import java.util.Timer;
import java.util.TimerTask;

public class SpaceInvaders {
    private static final String TITLE = " SPACE INVADERS ";
    private static int CREDITS;
    private static int SCORE;
    private static Scores highScores;
    private static TUI tui;
    public static void main(String[] args) {
        init();
        startMenu();
        while(!play()){

        }



    //LOOP DO JOGO

    }

    private static void startMenu() {
        tui.write(TITLE,false);
        tui.setCursorToLine(1);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

            }
        }, 1000,1000);
        while(true){

        }
        //t.cancel();
    }

    private static boolean play() {
    return false;
    }


    // initiates the model
    public static void init(){
        tui= new TUI();
        tui.init();
        CREDITS=0;
        highScores = LoadHighScores();
    }

    private static Scores LoadHighScores() {
        return null;
    }
}
