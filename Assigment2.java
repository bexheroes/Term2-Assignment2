
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assigment2 {
    public static void main(String[] args){
        
        employer Employer[] = new employer[5];
        waiter Waiter[] = new waiter[5];
        item Item[] = new item[100];
        table Tables[] = new table[5];
        order Orders[] = new order[200];
        
        int count_employer = 0;
        int count_waiter = 0;
        int count_item = 0;
        int count_table = 0;
        int count_order = 0;
        int i,ok=0,get=100,table=0,there_free=0,j,item_ok=0,k=0,food=0,hok=0;
        int m=0,okk=0,ct=0,ct2=0,ct3=0,mm=0,a=0,b=0,c=0,get2=0,dood=0,kut=0;
        double calculate = 0,getid=0,bill=0,getcost=0,keep=0;
        int control=0,getWaiter=0;
        
        int MAX_TABLES = 5;
        int ALLOWED_MAX_TABLES = 2;
        int MAX_EMPLOYERS = 5;
        int MAX_WAITERS = 5;
        
        String line;
        String firstfile = new File("setup.dat").getAbsolutePath();
        String secondfile = new File("commands.dat").getAbsolutePath();
        
        File f1 = new File(firstfile);
        
        try{
            Scanner s = new Scanner(f1);
            while(s.hasNextLine()){
                line = s.nextLine();
                String parts[] = line.split(" ");
                if(parts[0].equals("add_item")){
                    String parts2[] = parts[1].split(";");
                    if(parts2.length==3){
                        String name = parts2[0];
                        double cost = Double.parseDouble(parts2[1]);
                        int amount = Integer.parseInt(parts2[2]);
                        item i1 = new item(name,cost,amount);
                        Item[count_item] = i1;
                        count_item++;
                    }
                }else if(parts[0].equals("add_employer")){
                    String parts2[] = parts[1].split(";");
                    if(parts2.length==2){
                        if(count_employer==5){
                            System.out.println("Not allowed to exceed max. number of employers, "+MAX_EMPLOYERS);
                        }else{
                            String name = parts2[0];
                            double salary = Double.parseDouble(parts2[1]);
                            employer e1 = new employer(name,salary);
                            Employer[count_employer] = e1;
                            count_employer++;
                        }
                        
                    }
                    
                }else if(parts[0].equals("add_waiter")){
                    String parts2[] = parts[1].split(";");
                    if(parts2.length==2){
                        if(count_waiter==5){
                            System.out.println("Not allowed to exceed max. number of employers, "+MAX_WAITERS);
                        }else{
                            String name = parts2[0];
                            double salary = Double.parseDouble(parts2[1]);
                            waiter w1 = new waiter(name,salary);
                            Waiter[count_waiter] = w1;
                            count_waiter++;
                        }
                    }
                }
            }
            
        }catch(FileNotFoundException e){
            System.out.println("Can't found setup.dat");
        }
        
        File f2 = new File(secondfile);
        try{
            Scanner x = new Scanner(f2);
            while(x.hasNextLine()){
                line = x.nextLine();
                String parts[] = line.split(" ");
                if(parts[0].equals("create_table")){
                    String parts2[] = parts[1].split(";");
                    if(parts2.length==2){
                        
                        if(count_table==5){
                            System.out.println("***********************************");
                            System.out.println("PROGRESSING COMMAND: create_table");
                            System.out.println("Not allowed to exceed max. number of tables, "+MAX_TABLES);
                        }else{
                            for(i=0;i<count_employer;i++){
                                if(Employer[i].name.equals(parts2[0])){
                                    ok=1;
                                    get = i;
                                    break;
                                }else{
                                    ok=0;
                                }
                            }
                            if(ok==1){
                                if(Employer[get].created==2){
                                    System.out.println("***********************************");
                                    System.out.println("PROGRESSING COMMAND: create_table");
                                    System.out.println(parts2[0]+" has already created "+ALLOWED_MAX_TABLES+" tables!");
                                }else{
                                    table t1 = new table(count_table,parts2[0],Integer.parseInt(parts2[1]));
                                    Tables[count_table] = t1;
                                    count_table++;
                                    Employer[get].createTable();
                                    System.out.println("***********************************");
                                    System.out.println("PROGRESSING COMMAND: create_table");
                                    System.out.println("A new table has successfully been added");
                                    control=1;
                                }
                            }else{
                                System.out.println("***********************************");
                                System.out.println("PROGRESSING COMMAND: create_table");
                                System.out.println("There is no employer named "+parts2[0]);
                            }
                        }
                    }
                }else if(parts[0].equals("new_order")){
                    dood=0;
                    food=0;
                    control=0;
                    kut=0;
                    hok=0;
                    String parts2[] = parts[1].split(";");
                    if(parts2.length == 3){
                        
                        for(i=0;i<count_waiter;i++){
                                if(Waiter[i].name.equals(parts2[0])){
                                    ok=1;
                                    get = i;
                                    food=1;
                                    break;
                                }else{
                                    ok=0;
                                    food=0;
                                }
                            }
                        
                        if(ok==1){
                            
                            if(Waiter[get].operated == 3){
                                    System.out.println("***********************************");
                                    System.out.println("PROGRESSING COMMAND: new_order");
                                    System.out.println("Not allowed to service max. number of tables, MAX_TABLE_SERVICES");
                                    food=1;
                                }else{
                            
                                    for(i=0;i<count_table;i++){
                                        if(Tables[i].state.equals("free") && Tables[i].capacity>=Integer.parseInt(parts2[1])){
                                            table = i;
                                            there_free = 1;
                                            food=1;
                                            break;
                                        }else{
                                            there_free = 0;
                                            food=0;
                                        }
                                    }
                                    if(there_free==1){

                                            System.out.println("***********************************");
                                            System.out.println("PROGRESSING COMMAND: new_order");
                                            System.out.println("Table (= ID "+table+") has been taken into service");

                                            String parts3[] = parts2[2].split(":");
                                            for(i=0;i<parts3.length;i++){

                                                String parts4[] = parts3[i].split("-");
                                                for(j=0;j<count_item;j++){
                                                    if(Item[j].name.equals(parts4[0])){
                                                        item_ok = 1;
                                                        break;
                                                    }else{
                                                        item_ok = 0;
                                                    }
                                                }
                                                if(item_ok==1){
                                                    for(k=0;k<Integer.parseInt(parts4[1]);k++){
                                                        if(Item[j].amount>0){
                                                            Item[j].ordered();
                                                            for(m=0;m<count_order;m++){
                                                                if(Orders[m].name.equals(parts4[0]) && Orders[m].table == table){
                                                                    okk=1;
                                                                    break;
                                                                }else{
                                                                    okk=0;
                                                                }
                                                            }
                                                            if(okk==1){
                                                                if(kut!=10){
                                                                    Orders[m].amount = Orders[m].amount + 1;
                                                                    Tables[table].plus_order();
                                                                    control=1;
                                                                    dood+=1;
                                                                    kut++; 
                                                                }
                                                                
                                                            }else{
                                                                if(kut!=10){
                                                                    Tables[table].operated_by = parts2[0];
                                                                    Tables[table].state = "full";
                                                                    order o = new order(parts4[0],table,1);
                                                                    Orders[m] = o;
                                                                    Tables[table].plus_order();
                                                                    count_order++;
                                                                    control=1;
                                                                    dood+=1;
                                                                    kut++;
                                                                }
                                                                   
                                                            }
                                                            if(kut==10){
                                                                if(hok==0){
                                                                    System.out.println("Not allowed to exceed max number of orders!"); 
                                                                    hok++;
                                                                }
                                                            }else{
                                                                System.out.println("Item "+parts4[0]+" added into order");
                                                            }
                                                            

                                                        }else{
                                                            System.out.println("Sorry! No "+parts4[0]+" in the stock!");
                                                        }
                                                    }

                                                }else{
                                                        for(int kq = 0; kq < Integer.parseInt(parts4[1]) ; kq++ ){
                                                            System.out.println("Unknown item "+parts4[0]);   
                                                        }

                                               }
                                            }
                                    }else{
                                        System.out.println("***********************************");
                                        System.out.println("PROGRESSING COMMAND: new_order");
                                        System.out.println("There is no appropriate table for this order!");
                                    }
                                }
                            
                        }else{
                            System.out.println("***********************************");
                            System.out.println("PROGRESSING COMMAND: new_order");
                            System.out.println("There is no waiter named "+parts2[0]);
                        }
                    }
                    if(control==1){
                        Waiter[get].opareteOrder();
                        Tables[table].plus_total_order();
                    }
                    if(dood==0 && food==1){
                        System.out.println("Table (= ID "+table+") is no longer in service");
                    }
                }else if(parts[0].equals("add_order")){
                    control=0;
                    kut=0;
                    hok=0;
                    System.out.println("***********************************");
                    System.out.println("PROGRESSING COMMAND: add_order");
                    ok=0;
                    String parts2[] = parts[1].split(";");
                    keep=0;
                    
                    for(i=0;i<count_waiter;i++){
                        if(Waiter[i].name.equals(parts2[0])){
                            keep=1;
                            getWaiter=i;
                            break;
                        }
                    }
                    
                    if(keep==1){
                        for(i=0;i<count_table;i++){
                           if(Integer.parseInt(parts2[1])==Tables[i].id  && Tables[i].state.equals("full")){
                               ok=1;
                               get=i;
                               break;
                           }
                        }
                        if(ok==1){
                            if(Tables[get].operated_by.equals(parts2[0])){
                                if(Tables[get].total_order==5){
                                    System.out.println("Not allowed to exceed max number of orders!");
                                }else{
                                    String[] parts3 = parts2[2].split(":");
                                    for(i=0;i<parts3.length;i++){
                                        String[] parts4 = parts3[i].split("-");
                                        mm=0;
                                        for(j=0;j<count_item;j++){
                                            if(Item[j].name.equals(parts4[0])){
                                                mm=1;
                                                get2=j;
                                                break;
                                            }
                                        }
                                        if(mm==1){
                                            for(k=0;k<Integer.parseInt(parts4[1]);k++){
                                                if(Item[get2].amount>0){
                                                    for(a=0;a<count_order;a++){
                                                        if(Orders[a].table==get && Orders[a].name.equals(parts4[0])){
                                                            b=1;
                                                            break;
                                                        }else{
                                                            b=0;
                                                        }
                                                    }
                                                        if(b==1){
                                                            if(kut==10){
                                                                if(hok==0){
                                                                  System.out.println("Not allowed to exceed max number of orders!");
                                                                    hok++;  
                                                                }
                                                            }else{
                                                                Orders[a].plusAmount();
                                                                Item[get2].ordered();
                                                                Tables[get].plus_order();
                                                                System.out.println("Item "+parts4[0]+" added into order");
                                                                control=1;  
                                                                kut++;
                                                            }
                                                            
                                                        }else{
                                                            if(kut==10){
                                                                if(hok==0){
                                                                    System.out.println("Not allowed to exceed max number of orders!");
                                                                    hok++;
                                                                }
                                                            }else{
                                                                order oo = new order(parts4[0], get, 1);
                                                                Orders[count_order] = oo;
                                                                Item[get2].ordered();
                                                                Tables[get].plus_order();
                                                                System.out.println("Item "+parts4[0]+" added into order");
                                                                count_order++;
                                                                control=1;
                                                                kut++;
                                                            }
                                                            
                                                        }
                                                }else{
                                                    System.out.println("Sorry! No "+parts4[0]+" in the stock!");
                                                }
                                            }
                                            
                                        }else{
                                            for(int kq = 0; kq < Integer.parseInt(parts4[1]) ; kq++ ){
                                                System.out.println("Unknown item "+parts4[0]);   
                                            }
                                        }
                                    }
                                }
                            }else{
                                System.out.println("This table is either not in service now or "+parts2[0]+" cannot be assigned this table!");
                            }
                        }else{
                            System.out.println("This table is either not in service now or "+parts2[0]+" cannot be assigned this table!");
                        }
                    }else{
                        System.out.println("There is no waiter named "+parts2[0]);
                    }
                    if(control==1){
                        Waiter[getWaiter].opareteOrder();
                        Tables[get].plus_total_order();
                    }
                }else if(parts[0].equals("check_out")){
                    System.out.println("***********************************");
                    System.out.println("PROGRESSING COMMAND: check_out");
                    String parts2[] = parts[1].split(";");
                    if(Integer.parseInt(parts2[1]) < count_table){
                        ok=0;
                        for(i=0;i<count_waiter;i++){
                            if(Waiter[i].name.equals(parts2[0])){
                                ok=1;
                                break;
                            }
                        }
                        if(ok==0){
                            System.out.println("There is no waiter named "+parts2[0]);
                        }else if(Tables[Integer.parseInt(parts2[1])].state=="free"){
                            System.out.println("This table is either not in service now or "+parts2[0]+" cannot be assigned this table!");
                        }else{
                            j=0;
                            for(i=0;i<count_waiter;i++){
                                if(Waiter[i].name.equals(parts2[0])){
                                    j=1;
                                    break;
                                }
                            }
                            if(j==1){
                                if(Tables[Integer.parseInt(parts2[1])].operated_by.equals(parts2[0])){
                                    bill=0;
                                    for(i=0;i<count_order;i++){
                                        for(k=0;k<count_item;k++){
                                            if(Item[k].name.equals(Orders[i].name)){
                                                getcost = Item[k].cost;
                                            }
                                        }
                                        if(Orders[i].table == Integer.parseInt(parts2[1])){
                                            calculate = getcost*Orders[i].amount;
                                            bill+=calculate;
                                            System.out.println(Orders[i].name+":\t"+String.format("%.3f", getcost)+" (x "+Orders[i].amount+") "+String.format("%.3f", calculate)+" $");
                                        }
                                    }
                                    System.out.println("Total:\t"+String.format("%.3f", bill)+" $");
                                    Tables[Integer.parseInt(parts2[1])].reset();
                                   
                                    
                                }else{
                                    System.out.println("This table is either not in service now or "+parts2[0]+" cannot be assigned this table!");
                                }
                            }else{
                                System.out.println("There is no waiter named "+parts2[0]);
                            }
                        }
                    }else{
                        System.out.println("There is no such a table");
                    }
                    
                }else if(parts[0].equals("stock_status")){
                    System.out.println("***********************************");
                    System.out.println("PROGRESSING COMMAND: stock_status");
                    for(i=0;i<count_item;i++){
                        System.out.println(Item[i].name+":\t"+Item[i].amount);
                    }
                }else if(parts[0].equals("get_table_status")){
                    System.out.println("***********************************");
                    System.out.println("PROGRESSING COMMAND: get_table_status");
                    for(i=0;i<count_table;i++){
                        if(Tables[i].state.equals("free")){
                            System.out.println("Table "+Tables[i].id+": Free");
                        }else{
                            System.out.println("Table "+Tables[i].id+": Reserved ("+Tables[i].operated_by+")");
                       }
                   }
                }else if(parts[0].equals("get_order_status")){
                    ct = 0;
                    System.out.println("***********************************");
                    System.out.println("PROGRESSING COMMAND: get_order_status");
                    for(i=0;i<count_table;i++){
                        System.out.println("Table: "+Tables[i].id);
                        if(Tables[i].state.equals("free")){
                            System.out.println("\t"+0+" order(s)"); 
                        }else{
                            System.out.println("\t"+Tables[i].total_order+" order(s)");
                            for(j=0;j<Tables[i].total_order;j++){
                                System.out.println("\t\t"+Tables[i].get_order(j)+" item(s)");
                            }
                        }
                    }
                }else if(parts[0].equals("get_employer_salary")){
                        System.out.println("***********************************");
                        System.out.println("PROGRESSING COMMAND: get_employer_salary");
                    for(i=0;i<count_employer;i++){
                        calculate = (Employer[i].salary * Employer[i].created * 0.1) + Employer[i].salary;
                        System.out.println("Salary for "+Employer[i].name+": "+calculate);
                    }
                }else if(parts[0].equals("get_waiter_salary")){
                        System.out.println("***********************************");
                        System.out.println("PROGRESSING COMMAND: get_waiter_salary");
                    for(i=0;i<count_waiter;i++){
                        calculate = (Waiter[i].salary * Waiter[i].operated * 0.05) + Waiter[i].salary;
                        System.out.println("Salary for "+Waiter[i].name+": "+calculate);
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Can't found commands.dat"); 
        }
        
    }
}
