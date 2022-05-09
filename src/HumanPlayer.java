public class HumanPlayer extends Player {
	private double balance;
	
	public HumanPlayer() {
		balance = 100.00;
	}
	
	public double getBal() {
		return balance;
	}
	
	public void setBal(double bal) {
		balance = bal;
	}
	
	public String toString() {
		return getHand().toString();
	}
}