public class Nope extends Card{
    private Card actingCard;
    private Table table;

    public Nope(){
        super("Nope");
    }

    public void play(Table table){
        try{
            table.getTopDiscard().nope(table);
            actingCard=table.getTopDiscard();
            this.table=table;
        }
        catch(Exception e){}
    }

    private void reverse(){
        actingCard.play(table);
    }
}