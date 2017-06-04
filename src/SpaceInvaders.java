import isel.leic.utils.Time;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class SpaceInvaders {
    private static final String TITLE = " Space Invaders ";
    private static String CREDITS;
    private static int SCORE;
    private static HighScores highScores;
    private static TUI tui;
    private static boolean COIN_INSERTED;

    public static void main(String[] args) {
        init();
        startMenu();
        while(!play()) {
            //LOOP DO JOGO
            //Jogo j = new Jogo();


        }


    }

    // initiates the model
    public static void init(){
        tui= new TUI();
        tui.init();
        CREDITS="0";
        highScores = new HighScores();
        COIN_INSERTED=false;
    }


    private static void startMenu() {
        char keyPressed=0;
        Iterator<HighScore> it = highScores.iterator();
        tui.write(TITLE,0,0, false);
        highScores.AddScore("ABC",12);
        highScores.AddScore( "fFC",4);
        highScores.AddScore( "sfsawer",6);
        String game = " GAME";
        tui.write( game,1,0, false);
        tui.write("$ "+CREDITS,1,16-2 -CREDITS.length(),false);
        Time.sleep(2000);

        int beg = (int) Time.getTimeInMillis();
        Iterator<Score> it = highScores.iterator();
        int ordinal =0;
        while(CREDITS=="0" && (keyPressed==0 || keyPressed!='*') ) {
            keyPressed= tui.getKey();
            if (COIN_INSERTED){
                CREDITS+=2;
                tui.write(" GAME         $" + CREDITS,false);
                Time.sleep(1000);
            }
            if ((keyPressed!=0 && keyPressed!='*')|| (Time.getTimeInMillis()-beg)%1500==0)
                ordinal = showNextHighscore(it,ordinal);
        }
    }

    private static boolean play() {
    return false;
    }
    static int showNextHighscore(Iterator<Score> it, int ordinal){
        if (it.hasNext()) {
            ++ordinal;
            Score curr = it.next();
            tui.setCursorToLine(1);
            tui.write(ordinal,curr,false);
        } else {
            ordinal=0;
            it = highScores.iterator();
            ordinal = showNextHighscore(it,ordinal);
        }
        return ordinal;
    }

}
