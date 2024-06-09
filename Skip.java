public class Skip extends Card{
    private int toSkip;

    public Skip(){
        this(1);
    }

    public Skip(int n){
        super("Skip");
        toSkip=n;
    }
    
    public void play(Table table){
        table.setToDraw(table.getToDraw()-toSkip);
    }

    protected void reverse(Table table){}
}
