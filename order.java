
public class order {
    String name;
    int amount;
    int table;
    public order(String name,int table,int amount){
        this.name = name;
        this.table = table;
        this.amount = amount;
    }
    public void plusAmount(){
        amount++;
    }
    public void reset(){
        amount = 0;
    }
}
