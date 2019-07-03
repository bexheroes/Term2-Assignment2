
public class table {
    int id;
    int capacity;
    String state = "free";
    int total_order = 0;
    int order1=0,order2=0,order3=0,order4=0,order5=5;
    String created_by;
    String operated_by;
    
    public void reset(){
        state="free";
        total_order=0;
        order1=0;
        order2=0;
        order3=0;
        order4=0;
        order5=0;
        created_by="";
        operated_by="";
    }
    
    public table(int id,String created_by,int capacity){
        this.id = id;
        this.capacity = capacity;
        this.created_by = created_by;
    }
    public void plus_total_order(){
        total_order++;
    }
    
    public void plus_order(){
        if(total_order==0){
            order1++;
        }else if(total_order==1){
            order2++;
        }else if(total_order==2){
            order3++;
        }else if(total_order==3){
            order4++;
        }else if(total_order==4){
            order5++;
        }
    }
    
    public int get_order(int x){
        if(x==0){
            return order1;
        }else if(x==1){
            return order2;
        }else if(x==2){
            return order3;
        }else if(x==3){
            return order4;
        }else if(x==4){
            return order5;
        }else{
            return 0;
        }
    }
    
}
