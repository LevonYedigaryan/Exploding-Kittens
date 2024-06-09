public class Defuse extends Card{
    public Defuse(){
        super("Defuse");
    }

    public void play(Table table){
        System.out.println("You Will Survive!");
    }

    protected void reverse(Table table){}
}
