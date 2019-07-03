
public class item {
    String name;
    double cost;
    int amount;
    
    public item(String name,double cost ,int amount){
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }
    
    public String getName(){
        return name;
    }
    public double getCost(){
        return cost;
    }
    public int getAmount(){
        return amount; 
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public void ordered(){
        amount--;
    }
}
