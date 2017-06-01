
public class Score {
    int points;
    String name;
    Score(int points, String name){
        this.points = points;
        this.name = name;
    }

    int getPointsLength(){
        int aux = this.points;
        int length =0;
        while(aux>0){
            aux/=10;
            length++;
        }
        return length;
    }

    public String toString(){
        return this.name + " " + this.points;
    }
}
