import java.util.Scanner;

public class Player {
    private int number;
    private Card[] hand;
    private boolean alive;
    private int length;

    public Player(int n, Card[] cards){
        hand=new Card[40];
        alive=true;
        for(int i=0;i<cards.length;i++){
            hand[i]=cards[i];
        }
        length=cards.length;
    }

    public Player(int n){
        number=n;
        hand=new Card[40];
        alive=true;
        length=0;
    }

    public Player(){
        this(0);
    }

    public Player(Card[] cards){
        this(0, cards);
    }

    public Card[] getHand(){
        return hand;
    }

    public int getLength(){
        return length;
    }

    public void getCard(Card card){
        if(card instanceof Card){
            hand[length++]=card;
            card=null;
        }
    }

    public void getCard(Card[] card){
        for(int i=0;i<card.length;i++){
            if(card[i] instanceof Card){
                hand[length++]=card[i];
                card[i]=null;
            }
        }
    }

    public void explode(){
        System.out.println("KABOOM!");
        alive=false;
    }

    public Card play(String message){
        Scanner scanner=new Scanner(System.in);
        System.out.println(message);
        for(int i=0;i<length;i++){
            System.out.println(i+1+": "+hand[i]);
        }
        System.out.println();
        int play=scanner.nextInt();
        play--;
        if(play>=0 && play<length){
            Card played=hand[play];
            for(int i=play;i<length;i++){
                hand[i]=hand[i+1];
            }
            length--;
            return played;
        }
        return null;
    }

    public Card play(){
        return play("Please select the card you wanna play:");
    }

    public boolean isAlive(){
        return alive;
    }

    public String toString(){
        return "Player "+number;
    }
}