import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    Scanner scn = new Scanner(System.in);
    double years = 0;
    double playerNetWorth = 0;
    double playerBankAccount = 1000;
    double income = 0;
    Loan loan = new Loan(0.0, 0);
    double debt = 0;
    ArrayList<House> property = new ArrayList<>();
    House test = new House(3, 2.5, 3500, 250000, 2);

    public static void main(String[] args){
        new Main();
    }
    public Main(){
        Game();
    }

    public void Game(){
        property.add(test);
        boolean keepPlaying = true;
        System.out.println("Welcome to Real Estate Investor! We are glad to have you!");
        System.out.println(" ");
        System.out.println("For starters, you will begin with a small sum of money. From there, you will choose a job, so you can build your dream real estate empire!");
        System.out.println("There are four main sectors, numbered 1-4. The numbers indicate the risk of investment, with 4 being highest risk, and 1 being lowest. \nBut remember, with high risk comes high reward!");
        System.out.println("Each term will have a new house market, from which you can choose as many properties as you can afford to purchase.");
        System.out.println("You will also have the ability to take out loans, but be careful, if you go into debt, it will be hard to get out!");
        System.out.println("And one final thing before we get started. There is a casino...");
        System.out.println("Alright, well are you ready to get started?");
        String ready = scn.next().toLowerCase();
        if (ready.equals("yes")) {
            System.out.println("Well then, here we go!");
            System.out.println("For starters, which job would you like? \n a) lawn mower ($11,247/yr) \n b) dog walker ($10,564/yr) \n c) pizza maker ($15,476/yr)");
            String jobChoice = scn.next();
            if(jobChoice.equalsIgnoreCase("a") || jobChoice.equalsIgnoreCase("lawn")){
                income = 11247;
            }
            if(jobChoice.equalsIgnoreCase("b") || jobChoice.equalsIgnoreCase("dog")){
                income = 10564;
            }
            if(jobChoice.equalsIgnoreCase("c") || jobChoice.equalsIgnoreCase("pizza")){
                income = 15476;
            }
            System.out.println("Great! That means your income is " + income);
            System.out.println("Now that you have a job,");
            while(keepPlaying) {
                System.out.println("do you want to:\n a) check your stats \n b) check the market \n c) check the bank \n d) visit the casino \n e) progress one term");
                setNetWorth();
                String choice = scn.next();
                if (choice.equalsIgnoreCase("a")) {
                    playerStats();
                } else if (choice.equalsIgnoreCase("b")) {
                    //houseMarket();
                } else if (choice.equalsIgnoreCase("c")) {
                    bank();
                } else if (choice.equalsIgnoreCase("d")) {
                    boolean leave = false;
                    while (!leave) {
                        casino();
                        System.out.println("Thanks for playing! \n Would you like to leave the casino? (yes/no)");
                        String temp = scn.next();
                        leave = temp.equals("yes");
                    }
                } else if (choice.equalsIgnoreCase("e")) {
                    nextTerm();
                    setNetWorth();
                    playerStats();
                }
                }

        }
    }
    public void nextTerm(){
        for (House house : property) {
            house.setValue((house.getTermPercentIncrease() + 0.2) * house.getValue());
            System.out.println(house.getTermPercentIncrease());
            System.out.println(house.getValue());
            debt = loan.getRemainingBal();
            playerBankAccount += income / 2;
            years += 0.5;
        }
    }
    public void bank() { //bank method
        System.out.println("Welcome to the bank! would you like to: \n a) Take out a loan \n b) Check loan status \n c) Leave");
        String choice = scn.next();
        if (choice.equalsIgnoreCase("a")) {
            System.out.println("How much would you like to take out? (no $)");
            int loanVal = scn.nextInt();
            System.out.println("Great! And how long would you like your term to be? \n a) 60 months \n b) 72 months \n c) 84 months");
            int loanTerm = 0;
            choice = scn.next();
            if (choice.equalsIgnoreCase("a")) {
                loanTerm = 60;
            } else if (choice.equalsIgnoreCase("b")) {
                loanTerm = 72;
            } else if (choice.equalsIgnoreCase("c")) {
                loanTerm = 84;
            }
            System.out.println("Fantastic! \n Your total loan value is - $" + loanVal + "\nAnd the term is - " + loanTerm + " months" + "\nAnd your monthly payment will be - " + ((loanVal + (loanVal*0.073))/loanTerm));
        }
        else if (choice.equalsIgnoreCase("b")){
            System.out.println("Loan value - " + loan.getLoanAmount());
            System.out.println("Loan term - " + loan.getTerm());
            System.out.println("Remaining balance - " + loan.getRemainingBal());
        }
        else if (choice.equalsIgnoreCase("c")){
            return;
        }
    }
    public void casino(){ //casino method
        System.out.println("Welcome to the casino! \n Would you like to: \n a) play blackjack \n b) play roulette \n c) bet on horse races");
        String gambleChoice = scn.next();
        if (gambleChoice.equalsIgnoreCase("a")){
            blackjack();
        } else if (gambleChoice.equalsIgnoreCase("b")){
            //roulette();
        } else if (gambleChoice.equalsIgnoreCase("c")){
            //horseRace();
        }
    }
    public void horserace(){ //horse race method

    }
    public void blackjack() { //blackjack method
        ArrayList<Card> playerHand = new ArrayList<Card>();
        ArrayList<Card> dealerHand = new ArrayList<Card>();
        ArrayList<Card> deck = new ArrayList<Card>();
        for (int i = 2; i < 11; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card("" + i, i));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    deck.add(new Card("Jack", 10));
                } else if (i == 1) {
                    deck.add(new Card("Queen", 10));
                } else if (i == 2) {
                    deck.add(new Card("King", 10));
                } else {
                    deck.add(new Card("Ace", 11));
                }
            }
        }
        System.out.println("Welcome to the blackjack table! \nHow much would you like to bet?");
        int bet = scn.nextInt();
        boolean gameOver = false;
        if (bet <= playerBankAccount){
            System.out.println("Fantastic! \nAlright then, lets get started!");
            dealerHand.add(deck.remove((int) (Math.random() * 52)));
            playerHand.add(deck.remove((int) (Math.random() * 52)));
            dealerHand.add(deck.remove((int) (Math.random() * 52)));
            playerHand.add(deck.remove((int) (Math.random() * 52)));
            System.out.println("Dealers hand - " + dealerHand.get(0).getType());
            System.out.print("Your hand - ");
            for (Card card : playerHand) {
                System.out.print(card.getType() + " ");
            }
            while (!gameOver) {
                System.out.print("Total value - ");
                System.out.println(getHandValue(playerHand));

                System.out.println("Would you like to (H)it, or (S)tand?");
                String playerChoice = scn.next();
                if (playerChoice.equalsIgnoreCase("h")) {
                    playerHand.add(deck.remove((int) (Math.random() * 52) + 1));
                    System.out.println(playerHand.get(playerHand.size() - 1).getType());
                } else if (playerChoice.equalsIgnoreCase("s")){
                    while (getHandValue(dealerHand) < 17) {
                        if (getHandValue(playerHand) < 21) {
                            dealerHand.add(deck.remove((int) (Math.random() * 52)));
                        }
                    }
                }
                if (getHandValue(playerHand) > 21 && findAce(playerHand)) {
                    for (Card i : playerHand) {
                        if (i.getType().equals("Ace")) {
                            i.setValue(1);
                        }
                    }
                }
                System.out.println("Player hand - " + getHandValue(playerHand));
                System.out.println("Dealers hand - " + getHandValue(dealerHand));


                if (getHandValue(playerHand) == getHandValue(dealerHand)){
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else if (getHandValue(playerHand) > 21) {
                    System.out.println("Player busted, dealer wins.");
                    playerBankAccount -= bet;
                    System.out.println("-$" + bet);
                    gameOver = true;
                } else if (getHandValue(dealerHand) > 21){
                    System.out.println("Dealer busted, player wins");
                    playerBankAccount += bet * 2;
                    System.out.println("+$" + bet*2);
                    gameOver = true;
                } else if (getHandValue(playerHand) < getHandValue(dealerHand)){
                    System.out.println("Dealer wins");
                    playerBankAccount -= bet;
                    System.out.println("-$" + bet);
                    gameOver = true;
                } else if (getHandValue(playerHand) > getHandValue(dealerHand)){
                    System.out.println("Player wins");
                    playerBankAccount += bet * 2;
                    System.out.println("+$" + bet*2);
                    gameOver = true;
                }
            }
        }
    }
    public int getHandValue(ArrayList<Card> hand){
        int totalVal = 0;
        for (Card card : hand) {
            totalVal += card.getValue();
        }
        return totalVal;
    }
    public boolean findAce(ArrayList<Card> hand){
        for (Card card : hand) {
            if (card.getType().equals("Ace")) {
                return true;
            }
        }
        return false;
    }
    public void setNetWorth(){
        int netWorth = 0;
        for (House house : property) {
            netWorth += house.getValue();
        }
        netWorth += playerBankAccount;
        netWorth -= debt;
        playerNetWorth = netWorth;
        System.out.println(netWorth);
    }
    public void playerStats(){
        System.out.println("Year - " + years);
        System.out.println("Player bank account - " + playerBankAccount);
        System.out.println("Player net worth - " + playerNetWorth);
        System.out.println("Income - " + income);
        System.out.println("Debt - " + debt);
        System.out.println("Number of properties - " + property.size());
        System.out.println(" ");
    }
}

