public class Player {
	private Hand hand;
	
	public Player() {
		hand = new Hand();
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void addCard(Card c) {
		hand.addCard(c);
	}
	
	public boolean hasBlackjack() {
		return hand.getHand().get(0).getValue() + hand.getHand().get(1).getValue() == 21;
	}
}