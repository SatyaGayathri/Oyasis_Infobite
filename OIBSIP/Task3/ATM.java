package Task3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

class Account{
    static int acc_number=1111;
    String acc_holder_name;
    int pin;
    double balance;
    String UniqueId;
    int a_no;
    void createAcc(){
        a_no=acc_number;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Account Holder name:");
        acc_holder_name=sc.nextLine();
        System.out.println("Enter userName:");
        UniqueId=sc.nextLine();
        int length_pin=0;
      
        do{
            System.out.println("Enter 4 digit Secret PIN:");
            pin=sc.nextInt();
            length_pin=String.valueOf(pin).length();
        }
        while(length_pin!=4);
        System.out.println("Enter initial deposit:");
        balance=sc.nextDouble();
        System.out.println("Account was created Successfully");

        System.out.println("Account Details:\n"+"Account No:"+a_no+"\nAccount Holder Name:"+acc_holder_name+"\nBalance Amount:"+balance);

        String fileName=acc_number+".txt";
        File file=new File(fileName);

        try{
            file.createNewFile();
            FileWriter fw=new FileWriter(file);
            fw.write("Account Number:"+acc_number);
            fw.write("\nAccount Holder Name:"+acc_holder_name);
            fw.write("\nUserId:"+UniqueId);
            fw.write("\n Pin:"+pin);
            fw.write("\nBalance:"+balance);
            fw.write("\nDate:"+new Date()+"\n\n\n");
            fw.close();

        }catch(IOException e){
            System.out.println("Error Occured while creating file"+fileName);
            e.printStackTrace();
        }
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        acc_number++;

    }

}
class ATMOperations{
       void withdraw(Account ac){
        Scanner sc=new Scanner(System.in);
        System.out.println("Withdraw money mode.");
        System.out.println("Enter the amount in multiplies of 100");
        double amount=sc.nextDouble();
        if(amount%100==0){
            if(ac.balance>=amount){
                ac.balance-=amount;
                System.out.println("Your Transaction is in Processing..\n Remaing balance:"+ac.balance);
                try{

                    String  fileName=ac.a_no+".txt";
                    FileWriter fw=new FileWriter(fileName, true);
                    BufferedWriter bw=new BufferedWriter(fw);
                    bw.write("Date:"+new Date()+"\n");
                    bw.write("WithDrawl:"+amount+"\n");
                    bw.write("Account Number:"+ac.a_no+"\n");
                    bw.write("Remaing Balance:"+ac.balance+"\n");
                    bw.close();
                    fw.close();
                }catch(IOException e){
                    System.out.println("ERROR occured while writing into the file");
                    e.printStackTrace();
                }

                System.out.println("Thankyou! for Banking withus.");
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace() ;
                }
            }else{
                System.out.println("Insufficient funds!!");
            }
        }else{
            System.out.println("Amount is not in the multiple of 100\n Try Again!");
        }
    }

    void deposit(Account ac) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the amount to deposit:");
        double amount=sc.nextDouble();
        ac.balance+=amount;
        try{

            String  fileName=ac.a_no+".txt";
            FileWriter fw=new FileWriter(fileName, true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("Date:"+new Date()+"\n");
            bw.write("Deposit:"+amount+"\n");
            bw.write("Account Number:"+ac.a_no+"\n");
            bw.write("Remaing Balance:"+ac.balance+"\n");
            bw.close();
            fw.close();
        }catch(IOException e){
            System.out.println("ERROR occured while writing into the file");
            e.printStackTrace();
        }

        System.out.println("Thankyou! for Banking withus.");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace() ;
        }
    }
        
          

    void transfer(Account acc, Account acc2, double amount) {
        if(acc.balance>=amount){
            acc.balance-=amount;
            ATMOperations obj=new ATMOperations();
            obj.deposit_by_transfer(acc2,amount);
            try{

                String  fileName=acc.a_no+".txt";
                FileWriter fw=new FileWriter(fileName, true);
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write("Date:"+new Date()+"\n");
                bw.write("Transferred:"+amount+"\n");
                bw.write("Account Number:"+acc.a_no+"\n");
                bw.write("Remaing Balance:"+acc.balance+"\n");
                bw.close();
                fw.close();
            }catch(IOException e){
                System.out.println("ERROR occured while writing into the file");
                e.printStackTrace();
            }
             System.out.println("Amount transferred successfully");
            System.out.println("Thankyou! for Banking withus.");
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace() ;
            }

        }
    }

    void deposit_by_transfer(Account ac, double amount) {
        ac.balance+=amount;
        try{

            String  fileName=ac.a_no+".txt";
            FileWriter fw=new FileWriter(fileName, true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("Date:"+new Date()+"\n");
            bw.write("Deposit:"+amount+"\n");
            bw.write("Account Number:"+ac.a_no+"\n");
            bw.write("Remaing Balance:"+ac.balance+"\n");
            bw.close();
            fw.close();
        }catch(IOException e){
            System.out.println("ERROR occured while writing into the file");
            e.printStackTrace();
        }

 }

    void display_details(Account ac) throws IOException{
        System.out.println("Displaying Account Details:");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        String file_name=ac.a_no+".txt";
        File file=new File(file_name);
        try{
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line=null;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found"+e.getMessage());
        }catch(IOException e){
            System.out.println("Error reading file"+e.getMessage());
        }
    }
  
    void quit() {
        System.out.println("\nThank You fro Banking WithUS!");
              return;
           }

}
class run_atm{
    int account_search_by_uniqueId( Account[] ac,String unique_id){
        for(int i=0;i<5;i++){
            if(Objects.equals(unique_id,ac[i].UniqueId)){
                return i;
            }
        }
        return -1;
    }
    int account_search_by_uniqueId(Account[] ac,int account_num){
        for(int i=0;i<5;i++){
            if(Objects.equals(account_num,ac[i].a_no)){
                return i;
            }
        }
        return -1;
    }

    void demo(Account[] ac) throws IOException{
        Scanner sc=new Scanner(System.in);
        System.out.println("\nWelcome to ATM\n************************************************\n");
        System.out.println("Enter Card/UniqueId");
        String UniqueId=sc.nextLine();
        int i=account_search_by_uniqueId(ac, UniqueId);
        if(i==-1){
            System.out.println("Account not registerd Yet!");
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return;
        }else{
            System.out.println("Enter Pin:");
            int pin=sc.nextInt();
            if(pin==ac[i].pin){
                System.out.println("Select the option Given Below\n1. Withdrawl\n2.Deposit\n3.Account Transfer\n4.Display Account Details\n5.Quit\n");
                int choice;
                ATMOperations obj=new ATMOperations();
                choice=sc.nextInt();

                switch (choice) {
                    case 1->obj.withdraw(ac[i]);
                    case 2->obj.deposit(ac[i]);
                    case 3->{
                        System.out.println("Enter the account number to transfer funds?");
                        int acc_tran=sc.nextInt();
                        int j=account_search_by_uniqueId(ac, acc_tran);
                        if(j==-1){
                            System.out.println("Account not yet Registered!");
                            return;
                        }else{
                            System.out.println("Enter amount to transfer funds?");
                            double amount=sc.nextDouble();
                            obj.transfer(ac[i],ac[j],amount);
                        }
                    }
                    case 4->obj.display_details(ac[i]);
                    case 5->obj.quit();
                       
                }
            }else{
                System.out.println("Wrong PIN enterd!\nTryAgain");
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                return;
            }
            
        }
        
    }

}

public class ATM {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        Account[] ac=new Account[5];
        for(int i=0;i<5;i++){
            System.out.println("\nAccount creating mode.");
            ac[i]=new Account();
            ac[i].createAcc();
        }
        run_atm ob=new run_atm();
        for(;;){
            ob.demo(ac);
        }
    }
    
}
