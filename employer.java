
public class employer {
    String name;
    double salary;
    String permission = "table";
    int created = 0;
    static int total = 0;
    
    public employer(String name,double salary){
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
    public void createTable(){
        created++;
    }
}
