public class Shuffle extends Card{
    public Shuffle(){
        super("Shuffle");
    }    

    public void play(Table table){
        table.getDeck().shuffle();
    }
}
