public class Table{
	private Deck deck;
	private Card[] discard;
	private int discardLength;
	private int toDraw;
	private int activePlayer;
	private Player[] player;

	public Table(int n){
		deck=new Deck();
		toDraw=1;
		discardLength=0;
		player=new Player[n];
		for(int i=0;i<n;i++){
			player[i]=new Player(i);
		}
		discard=new Card[deck.size()];
	}

	public void setToDraw(int n){
		toDraw=n;
	}

	public int getToDraw(){
		return toDraw;
	}

	public Deck getDeck(){
		return deck;
	}

	public Player[] getPlayers(){
		return player;
	}

	public int getActivePlayer(){
		return activePlayer;
	}

	public void next(){
		if(++activePlayer==player.length){
			activePlayer=0;
		}
	}

	public void start(){
		Card[] defuses=deck.separate("Defuse");
		Card[] bombs=deck.separate("Bomb");
		deck.shuffle();
		for(int i=0;i<player.length;i++){
			player[i].getCard(deck.drawCard(7));
			player[i].getCard(defuses[i]);
			defuses[i]=null;
		}
		deck.addCard(bombs);
		deck.addCard(defuses);
		deck.shuffle();
	}

	private void draw(){
		Card card=deck.drawCard();
		if(card.toString().equals("Bomb")){
			card.play(this);
			deck.addCard(card);
		}
		else{
			player[activePlayer].getCard(card);
		}
		toDraw--;
	}

	public void play(){
		System.out.println("PLAYER: "+(activePlayer+1));
		Card played=player[activePlayer].play();
		if(played==null){
			draw();
			if(toDraw<1){
				next();
			}
		}
		else{
			if("Defuse".equals(played.toString())){
				System.out.println("Are you PREGNANT? You can't play this if you didn't explode!\n");
			}
			else{
				played.play(this);
				discard[discardLength++]=played;
			}
		}
	}
}