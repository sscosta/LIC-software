import java.util.Iterator;
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
        Iterator<Score> it = highScores.iterator();
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    while(it.hasNext()){
                        it.next();
                    }
                Score curr = (Score) it;
                tui.write(curr.toString());
            }
        }, 1000,1000);
        while(CREDITS==0 && tui.getKey()!='*'){

        }
        t.cancel();
    }
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
    private void setHighScoresTimer(){


}
