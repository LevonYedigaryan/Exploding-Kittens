import java.util.Scanner;

public class Favor extends Card{
    public Favor(){
        super("Favor");
    }

    public void play(Table table){
        System.out.println("Please select 1 player:\n");
        Player[] players=table.getPlayers();
        int active=table.getActivePlayer();
        int count=1;
        for(int i=0;i<players.length; i++){
            if(i!=active){
                System.out.println(count+": "+players[i]);
                count++;
            }
        }
        Scanner scanner=new Scanner(System.in);
        boolean match=false;
        Player giving=new Player();
        while(!match){
            int choice=scanner.nextInt();
            if(choice>=1 || choice<count){
                if(choice<active){
                    giving=players[choice-1];
                }
                else{
                    giving=players[choice];
                }
                break;
            }
            System.out.println("Please select a valid player!\n");
        }
        System.out.println(giving+", DO ME A FAVOR BITCH!\n");
        players[active].getCard(giving.play(giving+", give a present to your master!"));
    }

    protected void reverse(Table table){}
}