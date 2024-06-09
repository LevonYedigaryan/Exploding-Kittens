public class Attack extends PendingCard{
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
        setReversable();
        table.next();
    }

    protected void reverse(Table table){
        table.setToDraw(table.getToDraw()-toDraw);
        table.prev();
    }

    public void pend(Table table){
        if(table.getToDraw()<1){
            table.removePending(this);
        }
        setReversable(false);
    }
}
