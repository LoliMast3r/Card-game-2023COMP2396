public class Card {
    private String Name;
    private int pts;

    Card(String Name,int pts){
        this.Name = Name;
        this.pts = pts;
    }

    public String getName(){
        return Name;
    }

    public int getPoints(){
        return pts;
    }
}
