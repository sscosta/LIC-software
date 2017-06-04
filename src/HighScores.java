import java.io.*;
import java.util.*;

/**
 * Created by pedro on 2017-06-01.
 */
public class HighScores implements Iterable<HighScore> {
    public static void main(String[] args) {
        HighScores hsc = new HighScores();

        hsc.load();

        hsc.AddScore("PEDRO",989);
        hsc.AddScore("PEDRO",989);

        for (HighScore hs: hsc.highscores) {
            System.out.println(hs.toString());
        }
        hsc.save();
    }

    final private String HIGHSCORESFILENAME ="SIG_scores.txt";
    final private int MAXHIGHSCORES=5;

    private ArrayList<HighScore> highscores;

    public HighScores() {
        super();
        this.highscores = new ArrayList<HighScore>();
    }

    public void AddScore(String name,int score){
       this.highscores.add(new HighScore(name,score));

        this.SortScores();

        while (this.highscores.size() >MAXHIGHSCORES) {
            this.highscores.remove(MAXHIGHSCORES);
        }
    }

    private void SortScores(){
        Collections.sort(this.highscores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore hs2, HighScore hs1)
            {
                return  Integer.compare(hs1.GetScore(),hs2.GetScore());
            }
        });
    }

    public void load(){
        this.highscores.clear();
        Scanner in = null;

        try {
            HighScore hs;

            in = new Scanner(new FileInputStream(HIGHSCORESFILENAME));
            while (in.hasNextLine()) {
                hs=HighScore.FromText(in.nextLine());
                if (hs != null) this.highscores.add(hs);
            }

        } catch (FileNotFoundException | InputMismatchException e) {
            System.out.println("Error loading file \""+HIGHSCORESFILENAME+"\":\n"+e.getMessage());
        } finally {
            if (in!=null) in.close();   // Close the file
        }
    }


    public void save(){
        BufferedWriter out =null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("_"+HIGHSCORESFILENAME)));
            for (HighScore hs: this.highscores) {
                out.write(hs.toString());
                out.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file \""+HIGHSCORESFILENAME+"\":\n"+e.getMessage());
        }

        try {
            if (out!=null) {
                out.flush();
                out.close();  // Close the file
            }
        } catch ( IOException e) {
            System.out.println("Error saving file \""+HIGHSCORESFILENAME+"\":\n"+e.getMessage());
        }

    }

    @Override
    public Iterator<HighScore> iterator() {
        return this.highscores.iterator();
    }

    public boolean IsNewScoreHighScore(int score){
        if (this.highscores.size()>0) {
            return score >= this.highscores.get(this.highscores.size() - 1).GetScore();
        }
        return false;
    }

}
