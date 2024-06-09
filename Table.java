public class Table{
	private Deck deck;
	private PendingCard[] pending;
	private int pendingLength;
	private Card[] discard;
	private int discardLength;
	private int toDraw;
	private int activePlayer;
	private Player[] player;

	public Table(int n){
		deck=new Deck();
		toDraw=1;
		discardLength=0;
		pendingLength=0;
		player=new Player[n];
		for(int i=0;i<n;i++){
			player[i]=new Player(i);
		}
		discard=new Card[deck.size()];
		pending=new PendingCard[deck.size()];
	}

	public Card getTopDiscard(){
		if(discardLength-1<0){
			return null;
		}
		return discard[discardLength-1];
	}

	public void addPending(Card card){
		if(card instanceof Pending){
			pending[pendingLength++]=(PendingCard)card;
		}
	}

	private void pend(){
		for(int i=0;i<pending.length;i++){
			pending[i].pend(this);
		}
	}

	public void removePending(Card card){
		for(int i=0;i<pendingLength;i++){
			if(pending[i]==card){
				for(int j=i;j<pendingLength-1;j++){
					pending[j]=pending[j+1];
				}
				pending[--pendingLength]=null;
				break;
			}
		}
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

	public void prev(){
		if(--activePlayer<0){
			activePlayer=player.length-1;
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
		boolean action=false;
		if(played==null){
			draw();
			action=true;
		}
		else{
			if("Defuse".equals(played.toString())){
				System.out.println("Are you PREGNANT? You can't play this if you didn't explode!\n");
			}
			else if("Nope".equals(played.toString()) && (getTopDiscard()==null || !getTopDiscard().isReversable())){
				System.out.println("TO ANASUN! What are you playing this shit on?\n");
			}
			else {
				played.play(this);
				discard[discardLength++]=played;
				action=true;
			}
		}
		if(action){
			pend();
			if(toDraw<1){
				next();
			}
		}
	}
}