public class SpaceInvaders {
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
        String TITLE = " SPACE INVADERS ";
    }

    private static boolean play() {

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
