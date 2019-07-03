
public class waiter {
    String name;
    double salary;
    String permission = "order";
    int operated = 0;
    
    public waiter(String name,double salary){
        this.name = name;
        this.salary = salary;
    }
    
    public String getName(){
        return name;
    }
    public double getSalary(){
        return salary;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public void opareteOrder(){
        operated++;
    }
}
