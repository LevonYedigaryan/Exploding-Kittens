import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class Deck{

	private Card[] cards;
	private int len;

	public Deck(){
		try{
			len=0;
			String[] cardPatterns=load(new FileReader("patterns\\cards.txt"));
			cards=new Card[Integer.parseInt(cardPatterns[0])];
			for(int i=1;i<cardPatterns.length;i++){
				String[] code=cardPatterns[i].split(":");
				int n=Integer.parseInt(code[0]);
				switch(code[1]){
					case "Bomb":
						for(int j=0;j<n;j++){
							cards[len++]=new Bomb();
						}
						break;
					case "Defuse":
						for(int j=0;j<n;j++){
							cards[len++]=new Defuse();
						}
						break;
					case "Attack":
						for(int j=0;j<n;j++){
							cards[len++]=new Attack();
						}
						break;
					case "Favor":
						for(int j=0;j<n;j++){
							cards[len++]=new Favor();
						}
						break;
					case "Nope":
						for(int j=0;j<n;j++){
							cards[len++]=new Nope();
						}
						break;
					case "Shuffle":
						for(int j=0;j<n;j++){
							cards[len++]=new Shuffle();
						}
						break;
					case "Skip":
						for(int j=0;j<n;j++){
							cards[len++]=new Skip();
						}
						break;
					case "See the future":
						for(int j=0;j<n;j++){
							cards[len++]=new SeeTheFuture();
						}
						break;
					default:
						break;
				}
			}
		}
		catch(Exception e){
			System.exit(0);
		}
	}

	public void addCard(Card[] card){
		for(int i=0;i<card.length;i++){
			if(card[i] instanceof Card){
				cards[len++]=card[i];
				card[i]=null;
			}
		}
	}

	public void addCard(Card card){
		if(card instanceof Card){
			cards[len++]=card;
			card=null;
		}
	}

	public Card[] separate(String name){
		Card[] res=new Card[40];
		int k=0;
		for(int i=0;i<len;i++){
			if(name.equals(cards[i].getName())){
				res[k++]=cards[i];
				cards[i]=null;
			}
		}
		for(int i=0;i<len;i++){
			while(cards[i]==null){
				for(int j=i;j<len-1;j++){
					cards[j]=cards[j+1];
				}
				cards[--len]=null;
				k--;
			}
			if(k==0){
				break;
			}
		}
		return res;
	}

	private String[] load(Reader r) throws IOException{
        ArrayList<String> list=new ArrayList<>();
        BufferedReader b = new BufferedReader(r);
        String line;
        while((line=b.readLine())!=null) {
            list.add(line);
        }
        String[] arr=new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i);
        }
        return arr;
    }

	public Card[] drawCard(int n){
		Card[] result=new Card[n];
		int k=0;
		for(int i=len-1;i>=len-n;i--){
			result[k++]=cards[i];
			cards[i]=null;
		}
		len-=n;
		return result;
	}

	public Card drawCard(){
		return drawCard(1)[0];
	}

	public void shuffle(){
		boolean[] shuffled=new boolean[cards.length];
		Random rand=new Random();
		for(int i=len-1; i>0; i--){
			if(!shuffled[i]){
				int j=rand.nextInt(i);
				Card temp=cards[i];
				cards[i]=cards[j];
				cards[j]=temp;
				shuffled[i]=true;
				shuffled[j]=true;
			}
		}
	}

	public void seeTopCards(int n){
		System.out.println("The top "+n+" cards are:\n");
		for(int i=len-1;i>=len-n;i--){
			System.out.println(cards[i]);
		}
		System.out.println();
	}

	public int size(){
		return len;
	}
}