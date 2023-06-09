import java.util.ArrayList;
public class Card {
    private String type;
    private int value;
    Card(String type, int value){
        this.type = type;
        this.value = value;
    }
    public void setValue(int a){
        value = a;
    }
    public int getValue(){
        return value;
    }
    public String getType(){
        return type;
    }
    public int getHandValue(ArrayList<Card> hand){
        int totalVal = 0;
        for (int i = 0; i < hand.size(); i++){
            totalVal += hand.get(i).getValue();
        }
        return totalVal;
    }
}
