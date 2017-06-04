/**
 * Created by pedro on 2017-06-01.
 */
public class HighScore  {

    private String name;
    private int score;

    public  HighScore(String name,int score){
        this.name=name;
        this.score=score;
    }


    public String GetName(){
        return this.name;
    }

    public int GetScore(){
        return this.score;
    }

    public static HighScore FromText(String text){
        if (!text.isEmpty() ) {
            String[] splitedText;
            splitedText = text.split(";");
            if (splitedText.length == 2) {
                return new HighScore(splitedText[1], Integer.parseInt(splitedText[0]));
            }
        }
        return null;
    }

    public String toString (){
        return "" + this.score + ";" + this.name;
    }
}
