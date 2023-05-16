import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    Scanner scn = new Scanner(System.in);
    double years = 0;
    double playerNetWorth = 0;
    double playerBankAccount = 0;
    String occupation;
    double income = 0;
    Loans loan = new Loans(0.0, 0);
    double debt = 0;
    ArrayList<Houses> property = new ArrayList<Houses>();
    public static void main(String[] args){
        new Main();
    }
    public Main(){
        //Game();
    }

    public void game(){
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
            while (keepPlaying) {
                System.out.println("Well then, here we go!");
                System.out.println("For starters, which job would you like? \n a) lawn mower ($11,247/yr) \n -b) dog walker ($10,564/yr) \n -c) pizza maker ($15,476/yr)");
            }
        }
    }
    public void nextTerm(){
        for (int i = 0; i < property.size(); i++) {
            property.get(i).setValue((property.get(i).getTermPercentIncrease() + 1) * property.get(i).getValue());
            debt = loan.getRemainingBal();
            playerBankAccount += income/2;

        }
    }
}

