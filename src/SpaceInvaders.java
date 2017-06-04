import isel.leic.utils.Time;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class SpaceInvaders {
    private static final String TITLE = " Space Invaders ";
    private static String CREDITS;
    private static int SCORE;
    private static Scores highScores;
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

    private static void startMenu() {
        char keyPressed=0;
        tui.write(TITLE,0,0, false);
        highScores.add(new Score(12, "ABC"));
        highScores.add(new Score(4, "fFC"));
        highScores.add(new Score(6, "sfsawer"));
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

    // initiates the model
    public static void init(){
        tui= new TUI();
        tui.init();
        CREDITS="0";
        highScores = new Scores();
        COIN_INSERTED=false;
    }
}
