import java.io.*;
import java.util.*;

/**
 * Created by pedro on 2017-06-01.
 */
public class HighScores {
    final private String HIGHSCORESFILENAME ="SIG_scores.txt";
    final private int MAXHIGHSCORES=5;

    public ArrayList<HighScore> table;

    public static void main(String[] args) {
        HighScores hsc = new HighScores();

        hsc.load();

        hsc.AddScore("PEDRO",989);
        hsc.AddScore("PEDRO",989);

        for (HighScore hs: hsc.table) {
            System.out.println(hs.toString());
        }
        hsc.save();
    }




    public  HighScores() {
        this.table = new ArrayList<HighScore>();
    }

    public void AddScore(String name,int score){
       this.table.add((new HighScore(name,score)));

        this.SortScores();

        while (this.table.size() >MAXHIGHSCORES) {
            this.table.remove(MAXHIGHSCORES);
        }
    }

    private void SortScores(){
        Collections.sort(this.table, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore hs2, HighScore hs1)
            {
                return  Integer.compare(hs1.GetScore(),hs2.GetScore());
            }
        });
    }

    public void load(){
        this.table.clear();
        Scanner in = null;

        try {
            HighScore hs;

            in = new Scanner(new FileInputStream(HIGHSCORESFILENAME));
            while (in.hasNextLine()) {
                hs=HighScore.FromText(in.nextLine());
                if (hs != null) this.table.add(hs);
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
            for (HighScore hs: this.table) {
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
}
