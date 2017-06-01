import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class SpaceInvaders {
    private static final String TITLE = " Space Invaders ";
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
        highScores.add(new Score(12,"ABC"));
        highScores.add(new Score(4,"fFC"));
        highScores.add(new Score(6,"sfsawer"));
        tui.write(" GAME         $"+CREDITS,false);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            Iterator<Score> it = highScores.iterator();
            @Override
            public void run() {

                    if (it.hasNext()){
                        Score curr = it.next();
                        tui.setCursorToLine(1);
                        tui.write(curr.toString(),false);
                    }else {
                        it = highScores.iterator();
                        run();
                    }
            }
        }, 1000,2000);
        while(CREDITS==0 && tui.getKey()!='*'){

        }
        t.cancel();
    }

    private static boolean play() {
    return false;
    }


    // initiates the model
    public static void init(){
        tui= new TUI();
        tui.init();
        CREDITS=0;
        highScores = new Scores();
    }

    private static Scores LoadHighScores() {
        return null;
    }
}
