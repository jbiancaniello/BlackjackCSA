import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int handValue;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getValue() {
		return handValue;
	}
	
	public Card getCard(int i) {
		return hand.get(i);
	}
	
	public void addCard(Card c) {
		hand.add(c);
		handValue = handValue + c.getValue();
	}
	
	public void clearHand()
	{
		hand.clear();
		updateValue();
	}
	
	public boolean isSoft()
	{
		for (Card card: hand)
		{
			if (card.isSoft())
				return true;
		}
		
		return false;
	}
	
	public void checkSoft()
	{
		for (Card card : hand)
		{
			if (handValue > 21)
			{
				if (card.isSoft())
				{
					card.makeHard();
					updateValue();
				}
			}
			else
				return;
		}
	}
	
	private void updateValue()
	{
		handValue = 0;
		
		for (Card card : hand)
			handValue += card.getValue();
	}
	
	public String toString()
	{
		return hand.toString().replace("[", "").replace("]", "");
	}
}
