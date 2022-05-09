import java.util.ArrayList;
import java.util.Collections;

public class Shoe {
	private ArrayList<Deck> shoe;
	private final int numDecks = 8;
	
	public Shoe() {
		shoe = new ArrayList<Deck>();
		for (int i = 0; i < numDecks; i++) {
			shoe.add(new Deck());
		}
	}
	
	public Deck get(int i) {
		return shoe.get(i);
	}
	
	public Card nextCard() {
		int i = 0;
		if (shoe.get(i).isEmpty()) {
			i++;
			return shoe.get(i).nextCard();
		}
		return shoe.get(i).nextCard();
	}
}
