public class Bomb extends Card{
    public Bomb(){
        super("Bomb");
    }
    public void play(Table table){
        Player player=table.getPlayers()[table.getActivePlayer()];
        int length=player.getLength();
        Card[] hand=player.getHand();
        System.out.println("You have drawn a bomb!\n");
        int i;
        for(i=0;i<length;i++){
            if("Defuse".equals(hand[i].toString())){
                break;
            }
        }
        if(i>=length){
            player.explode();
        }
        else{
            boolean saved=false;
            while(!saved){
                Card choice=player.play("Please choose a defuse, to save your ass\n");
                if(choice!=null && "Defuse".equals(choice.toString())){
                    choice.play(table);
                    saved=true;
                }
                else{
                    System.out.println("CHOOSE A VALID CARD YOU IMBICIL!\n");
                    player.getCard(choice);
                }
            }
        }
    }

    protected void reverse(Table table){}
}