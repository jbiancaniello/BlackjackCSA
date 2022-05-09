import java.util.Scanner;
import java.text.*;

public class Blackjack {
	private static boolean again;
	private static HumanPlayer player = new HumanPlayer();
	private static HumanPlayer player2 = new HumanPlayer();
	private static DealerPlayer dealer = new DealerPlayer();
	private static Shoe shoe;
	private static double bet;
	private static boolean doubled;
	
	public static void main (String[] args) {
		start();
		again = true;
		while (again) {
			deal();
		}
	}
	
	public static void start() {
		shoe = new Shoe();
		System.out.println("Welcome to BlackJack! You will start with $100 in your balance. The dealer always hits on a soft 17. Lets get started.");
	}
	
	public static boolean deal() {
		doubled = false;
		bet = bet();
		player.setBal(player.getBal() - bet);
		player.addCard(shoe.nextCard());
		dealer.addCard(shoe.nextCard());
		player.addCard(shoe.nextCard());
		dealer.addCard(shoe.nextCard());
		System.out.println("Your hand is: " + player + " | Total: " + player.getHand().getValue() + "\nDealer shows a " + dealer.getHand().getCard(0));
		
		if(player.hasBlackjack() && dealer.hasBlackjack()) {
			player.setBal(player.getBal() + bet);
			System.out.println("You both have Blackjack! Push! Would you like to continue playing? (Y/N)");
		} else if(player.hasBlackjack()) {
			player.setBal(player.getBal() + bet * 2.5);
			System.out.println("You have Blackjack! Congratulations! You Win! Would you like to continue playing? (Y/N)");
		} else if(dealer.hasBlackjack()) {
			System.out.println("Dealer's second card is: " + dealer.getHand().getCard(1) + "\nDealer has Blackjack! You lose. Would you like to continue playing? (Y/N)");
		} else {
			play();
		}
		again = playAgain();
		return again;
	}
	
	public static boolean play() {
		if(!doubled && player.getBal() - bet * 2 >= 0) {
			System.out.println("Would you like to (H)it or (S)tand or (D)ouble Down?");
		} else {
			System.out.println("Would you like to (H)it or (S)tand");
		}
		Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        if(line.equalsIgnoreCase("H")) {
        	doubled = true;
        	Card newCard = shoe.nextCard();
        	player.addCard(newCard);
        	if(player.getHand().getValue() > 21 && player.getHand().isSoft()) {
        		player.getHand().checkSoft();
        	}
        	System.out.println("You received a " + newCard + "\nYou're new hand and total is " + player + " | Total: " + player.getHand().getValue());
        	if(player.getHand().getValue() > 21) {
        		System.out.println("You busted and lost. Would you like to play again? (Y/N)");
        		return false;
        	}
        	play();
        } else if(line.equalsIgnoreCase("S")) {
        	System.out.println("Dealer has " + dealer + " | Total: " + dealer.getHand().getValue());
        	if(dealer.getHand().getValue() > 17) {
        		if(dealer.getHand().getValue() > player.getHand().getValue() && dealer.getHand().getValue() <= 21) {
        			System.out.println("Dealer Wins! Would You Like to Play Again? (Y/N)");
            		return false;
            	} else {
            		System.out.println("You Win! You're credit has been awarded to your account. Would You Like to Play Again? (Y/N)");
            		player.setBal(player.getBal() + bet * 2);
            		return true;
            	}
        	} else {
        		while(dealer.getHand().getValue() <= 17) {
        			Card newCard = shoe.nextCard();
                	dealer.addCard(newCard);
                	System.out.println("Dealer received a " + newCard + "\nDealer's new hand and total is " + dealer + " | Total: " + dealer.getHand().getValue());
        		}
        		if(dealer.getHand().getValue() > player.getHand().getValue() && dealer.getHand().getValue() <= 21) {
        			System.out.println("Dealer Wins! Would You Like to Play Again? (Y/N)");
            		return false;
            	} else {
            		System.out.println("You Win! You're credit has been awarded to your account. Would You Like to Play Again? (Y/N)");
            		player.setBal(player.getBal() + bet * 2);
            		return true;
            	}
        	}
        } else if(line.equalsIgnoreCase("D")) {
        	bet = bet * 2;
        	doubled = true;
        	Card newCard = shoe.nextCard();
        	player.addCard(newCard);
        	System.out.println("You received a " + newCard + "\nYou're new hand and total is " + player + " | Total: " + player.getHand().getValue());
        	if(player.getHand().getValue() > 21) {
        		System.out.println("You busted and lost. Would you like to play again? (Y/N)");
        		return false;
        	}
        	if(dealer.getHand().getValue() > 17) {
        		if(dealer.getHand().getValue() > player.getHand().getValue() && dealer.getHand().getValue() <= 21) {
        			System.out.println("Dealer Wins! Would You Like to Play Again? (Y/N)");
            		return false;
            	} else {
            		System.out.println("You Win! You're credit has been awarded to your account. Would You Like to Play Again? (Y/N)");
            		player.setBal(player.getBal() + bet * 2);
            		return true;
            	}
        	} else {
        		while(dealer.getHand().getValue() <= 17) {
        			Card newCard2 = shoe.nextCard();
                	dealer.addCard(newCard2);
                	System.out.println("Dealer received a " + newCard + "\nDealer's new hand and total is " + dealer + " | Total: " + dealer.getHand().getValue());
        		}
        		if(dealer.getHand().getValue() > player.getHand().getValue() && dealer.getHand().getValue() <= 21) {
        			System.out.println("Dealer Wins! Would You Like to Play Again? (Y/N)");
            		return false;
            	} else {
            		System.out.println("You Win! You're credit has been awarded to your account. Would You Like to Play Again? (Y/N)");
            		player.setBal(player.getBal() + bet * 2);
            		return true;
            	}
        	}
        }
		
		return false;
	}
	
	public static double bet() {
            DecimalFormat hundredths = new DecimalFormat("#.##");
            System.out.println("How much would you like to bet?" + " Current Balance: $" + hundredths.format(player.getBal()));
            Scanner s = new Scanner(System.in);
            String b = s.nextLine();
            if(Double.parseDouble(b) > player.getBal()) {
		System.out.println("Bet exceeds current balance, please input another bet.");
		b = bet() + "";
            }
		return Double.parseDouble(b);
	}
	
	public static boolean playAgain() {
		boolean bAgain = false;
		Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        if (!line.equalsIgnoreCase("Y") && !line.equalsIgnoreCase("N")) {
        	System.out.println("Invalid Input, please enter again");
        	bAgain = playAgain();
        } else if (line.equalsIgnoreCase("Y")) {
			bAgain = true;
	} else if (line.equalsIgnoreCase("N")) {
            bAgain = false;
            System.out.println("Thank you for playing!");
        }
        reset();
		return bAgain;
	}
	
	public static void reset() {
		player.getHand().clearHand();
		dealer.getHand().clearHand();
	}
}
