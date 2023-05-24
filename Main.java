import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    Scanner scn = new Scanner(System.in);
    double years = 0;
    double playerNetWorth = 0;
    double playerBankAccount = 0;
    double income = 0;
    Loans loan = new Loans(0.0, 0);
    double debt = 0;
    ArrayList<Houses> property = new ArrayList<Houses>();
    Houses test = new Houses(3, 2.5, 3500, 250000, 2);

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
            if(jobChoice.equalsIgnoreCase("a") || jobChoice.equalsIgnoreCase("lawn mower")){
                income = 11247;
            }
            if(jobChoice.equalsIgnoreCase("b") || jobChoice.equalsIgnoreCase("dog walker")){
                income = 10564;
            }
            if(jobChoice.equalsIgnoreCase("c") || jobChoice.equalsIgnoreCase("pizza maker")){
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
                        //casino();
                        System.out.println("Thanks for playing! \n Would you like to leave the casino? (yes/no)");
                        String temp = scn.next();
                        if (temp.equals("yes")){
                            leave = true;
                        } else{
                            leave = false;
                        }
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
        for (int i = 0; i < property.size(); i++) {
            property.get(i).setValue((property.get(i).getTermPercentIncrease() + 0.2) * property.get(i).getValue());
            System.out.println(property.get(i).getTermPercentIncrease());
            System.out.println(property.get(i).getValue());
            debt = loan.getRemainingBal();
            playerBankAccount += income/2;
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
        System.out.println("Welcome to the casino! \n Would you like to: \n a) play blackjack \n b) play roulette c) bet on horse races");
        String gambleChoice = scn.next();
        if (gambleChoice.equalsIgnoreCase("a")){
            //blackjack();
        } else if (gambleChoice.equalsIgnoreCase("b")){
            //roulette();
        } else if (gambleChoice.equalsIgnoreCase("c")){
            //horseRace();
        }
    }
    public void blackjack() { //blackjack method
        ArrayList<Cards> playerHand = new ArrayList<Cards>();
        ArrayList<Cards> dealerHand = new ArrayList<Cards>();
        ArrayList<Cards> deck = new ArrayList<Cards>();
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Cards("" + i, i));
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 1) {
                    deck.add(new Cards("Jack", 10));
                } else if (i == 2) {
                    deck.add(new Cards("Queen", 10));
                } else if (i == 3) {
                    deck.add(new Cards("King", 10));
                } else if (i == 4) {
                    deck.add(new Cards("Ace", 11));
                }
            }
        }
        System.out.println("Welcome to the blackjack table! \n How much would you like to bet?");
        int bet = scn.nextInt();
        System.out.println("Fantastic! \n Alright then, lets get started!");
        dealerHand.add(deck.remove((int) (Math.random() * 52) + 1));
        playerHand.add(deck.remove((int) (Math.random() * 52) + 1));
        dealerHand.add(deck.remove((int) (Math.random() * 52) + 1));
        playerHand.add(deck.remove((int) (Math.random() * 52) + 1));
        System.out.println("Dealers hand: \n" + dealerHand.get(0).getType());
        System.out.println("Your hand: ");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(playerHand.get(i).getType());
        }
        while (getHandValue(playerHand) < 21){
            System.out.println("Would you like to (H)it, or (S)tand?");
            String playerChoice = scn.next();
            if (playerChoice.equalsIgnoreCase("H")) {
                playerHand.add(deck.remove((int) (Math.random() * 52) + 1));
            }
            if (getHandValue(playerHand) > 21 && findAce(playerHand)){
                for (int i = 0; i < playerHand.size(); i++) {
                    if (playerHand.get(i).getType().equals("Ace")){
                        playerHand.get(i).setValue(1);
                    }
                }
            }
        }
    }
    public int getHandValue(ArrayList<Cards> hand){
        int totalVal = 0;
        for (int i = 0; i < hand.size(); i++){
            totalVal += hand.get(i).getValue();
        }
        return totalVal;
    }
    public boolean findAce(ArrayList<Cards> hand){
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getType().equals("Ace")){
                return true;
            }
        }
        return false;
    }
    public void setNetWorth(){
        int netWorth = 0;
        for(int i = 0; i < property.size(); i++){
            netWorth += property.get(i).getValue();
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

