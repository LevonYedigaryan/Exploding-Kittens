public abstract class Card {
    private String name;
    private boolean reversable;


    public Card(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    void setReversable(){
        reversable=true;
    }

    void setReversable(Boolean reversable){
        this.reversable=reversable;
    }

    boolean isReversable(){
        return reversable;
    }
    
    public abstract void play(Table table);

    public void nope(Table table) throws IrreversableException{
        if(isReversable()){
            reverse(table);
        }
        else{
            throw new IrreversableException();
        }
    }

    private void reverse(Table table){
    }

    public String toString(){
        return name;
    }
}
