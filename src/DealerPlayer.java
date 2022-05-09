public class DealerPlayer extends Player {
	public DealerPlayer() {
		super();
	}
	
	public boolean atLimit() {
		return super.getHand().getValue() >= (super.getHand().isSoft() ? 18 : 17);
	}
	
	public String toString() {
		return getHand().toString();
	}
} 