public class SeeTheFuture extends Card{
    private int see;

    public SeeTheFuture(){
        this(3);
    }

    public SeeTheFuture(int n){
        super("See the future");
        see=n;
    }

    public void play(Table table){
        table.getDeck().seeTopCards(see);
    }
}
