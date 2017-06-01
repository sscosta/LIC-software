import java.util.Iterator;
import java.util.LinkedList;

public class Scores implements Iterable<Score> {

    LinkedList<Score> scores;

    Scores(){
        this.scores = new LinkedList<Score>();
    }
    public void add(Score s){
        scores.add(s);
    }
    public Iterator<Score> iterator() {
        return iterator();
    }
}
