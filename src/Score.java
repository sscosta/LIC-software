
public class Score {
    int points;
    String name;
    Score(int points, String name){
        this.points = points;
        this.name = name;
    }
    public String toString(){
        return ""+ points + " " + name;
    }
}
