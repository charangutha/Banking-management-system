import java.util.Scanner;
import java.util.Random;

class BankDetails {  
    private int accno=100000;  
    private String name; 
    private String lname; 
    private int acc_type=1;  
    private long balance;  
    private int password;
    private int faccno;
    private int acno;
    private long trans[] = new long[100];

    private int t=0;

    Scanner sc = new Scanner(System.in); 
    
    public int randomm(){
        Random rand= new Random();
        acno= rand.nextInt(100);
        faccno= accno+acno;
        return faccno;

    } 
public void record_trans(long amt) {
        trans[t]=amt;
        t++;
    }



    public void display_trans() {
        System.out.println("\nTransactions details of Account No.: "+faccno);
        for(int i=0; i<trans.length; i++) {
            if(trans[i]!=0) {
                System.out.print(trans[i]+" ");
            }
            else {
                break;
            }
        }
        System.out.println();
    }

    public void openAccount() {  
        randomm();
        System.out.println("Account Number: "+ faccno);
        do{
            if(acc_type !=1 && acc_type!=2)
              System.out.println("\nInvalid Input, Enter again.......\n");
           System.out.println("\nSelect Account type: \n1.Savings  2.Current ");  
           acc_type = sc.nextInt();
        }
        while(acc_type <1 || acc_type >2);

System.out.println("\nEnter First Name: ");  
        name = sc.next();  

        System.out.println("\nEnter Last Name");
        lname = sc.next();

        System.out.print("\nEnter Balance you want to deposit for the first time: ");  
        balance = sc.nextLong(); 
        trans[t] = balance;
        t++;

        do{
            System.out.print("Set a 4 digit passcode: ");
            password = sc.nextInt();
        }
        while(password<1000 || password > 9999);
        System.out.println("----------------------------------------");

    } 
    
    

    public void viewAccounts() {
        System.out.println("\nAccount no.: " + faccno);
        System.out.println("Name of account holder: " + name+" "+lname);   
         if(acc_type == 1){
            System.out.println("Account type: Savings");
        }  
        else if(acc_type == 2){
            System.out.println("Account type: Current");
        }
        System.out.println("----------------------------------------");

    }
public void showAccount() { 
        System.out.println("\n ----------------------------------------- ");  
        System.out.println("Welcome, " + name);
        System.out.println("\n\nName of account holder: " + name+" "+lname);  
        System.out.println("Account no.: " + faccno);  
         if(acc_type == 1){
        System.out.println("Account type: Savings");
        }  
        else if(acc_type == 2){
        System.out.println("Account type: Current");
        }
        System.out.println("Balance: " + balance); 
        System.out.println(" ----------------------------------------- \n");
        int op;
        do{
            System.out.println("------------Account Menu------------");
            System.out.println("|1. View Balance                   |\n|2. Deposit Money                  |\n|3. Withdraw Money                 |\n|4. Display Transactions           |\n|5. Exit                           |");
            System.out.println("------------------------------------");

            System.out.println("Enter your choice: ");
            op = sc.nextInt();
               switch(op) {
                case 1:
                  System.out.println("\nHello, "+name+"\n\nYour current balance is: " + balance);
                  break;

                case 2:
                  deposit();
                  break;
                 case 3:
                  withdrawal();
                  break;

                case 4:
                  display_trans();
                  break;

                case 5:
                  break;
               } 
        }while(op!=5);
        
        
    }  


    public void deposit() {  
        long amt;  
        System.out.println("\nEnter the amount you want to deposit: ");  
        amt = sc.nextLong();  
        balance = balance + amt; 
        record_trans(amt);
        System.out.println("\nDeposit Succesfull!!! \nYour updated Balance is: " + balance); 
    }  


    public void withdrawal() {  
        long amt;  
        System.out.println("\nEnter the amount you want to withdraw: ");  
        amt = sc.nextLong();  
        if (balance >= amt) {  
            balance = balance - amt; 
             record_trans((-1)*amt); 
            System.out.println("\nBalance after withdrawal: " + balance);  
        } else {  
            System.out.println("\nYour balance is less than " + amt + "\tTransaction failed...!!" );  
        }  
    }  


    public boolean search(int ac_no) {  
        if (faccno == ac_no) {   
            return (true);  
        }  
        return (false);  
    }
    
    public boolean check(int pass_code) {  
        if (password == pass_code) {  
            showAccount();  
            return (true);  
        }  
        return (false);  
    }
}  


public class BankingApp {  
    public static void main(String arg[]) {  
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------Welcome to Sunny Bank---------------");

        System.out.print("\nHow many number of customers do you want to input? ");  
        int n = sc.nextInt(); 
        System.out.println("----------------------------------------");
        BankDetails C[] = new BankDetails[100];
 
        for (int i = 0; i <n; i++) {  

            C[i] = new BankDetails();  
            C[i].openAccount();     
        }  
        // loop for running the Banking Menu  
        int ch;  
        do {  
            System.out.println("----------------------Banking System Menu--------------------------");  
            System.out.println("| 1. Display all accounts                                         | \n| 2. Add New Account                                              | \n| 3. Manage your Account                                          | \n| 4.Exit                                                          | \n------------------------------------------------------------------- \n");  
            System.out.println("Enter your choice: ");  
            ch = sc.nextInt() ;  
            System.out.println("\n");
                switch (ch) {  
                    case 1:  
                        for (int i = 0; i < n; i++) {  
                            System.out.println("Displaying Account "+ (i+1) +":");
                            C[i].viewAccounts();  
                        }  
                        break;  
                        break;  
                    case 2:
                        C[n] = new BankDetails();  
                        C[n].openAccount();
                        n++;
                        break;
                    case 3:  
                        System.out.print("Enter your account no.:");  
                        int ac_no = sc.nextInt();  
                        boolean found = false; 
                        boolean pass = false; 
                        for (int i = 0; i < n; i++) {  
                            found = C[i].search(ac_no);
                            if (found) {
                                System.out.print("Enter 4 digit password: ");  
                                int passkey = sc.nextInt(); 
                                pass = C[i].check(passkey);
                                if(pass)
                                    break;  
                            }  
                        }  
                        if (!found || !pass) {  
                            System.out.println("Search failed! Account doesn't exist..!!");  
                        }  
                        break;

                    case 4:  
                        System.out.println("\n\n----------------------Thank you for using our Bank----------------------\n\n");  
                        break;  
                }  
            }  
            while (ch != 4);  
    }
}


   

  


