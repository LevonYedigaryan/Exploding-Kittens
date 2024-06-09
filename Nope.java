public class Nope extends Card{
    private Card actingCard;

    public Nope(){
        super("Nope");
    }

    public void play(Table table){
        try{
            if(actingCard==null){
                table.getTopDiscard().nope(table);
                actingCard=table.getTopDiscard();
                setReversable();
                table.removePending(actingCard);
            }
            else{
                actingCard.nope(table);
            }
        }
        catch(Exception e){}
    }

    protected void reverse(Table table){
        actingCard.play(table);
        table.addPending(actingCard);
    }
}