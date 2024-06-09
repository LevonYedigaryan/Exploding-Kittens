public class Attack extends Card{
    private int toDraw;

    public Attack(int n){
        super("Attack");
        toDraw=n;
    }

    public Attack(){
        this(2);
    }

    public void play(Table table){
        if(table.getToDraw()==1){
            table.setToDraw(toDraw);
        }
        else{
            table.setToDraw(table.getToDraw()+toDraw);
        }
        table.next();
    }

    private void reverse(Table table){
        table.setToDraw(table.getToDraw()-toDraw);
        table.prev();
    }
}
