//Aniv Surana - 1001912967 - Coding Assignment 3
package code3_1001912967;
import java.util.Scanner;

public class Code3_1001912967
{
    public static void main(String[] args) 
    {
        CokeMachine MyCokeMachine = new CokeMachine("\nCSE 1325 Coke Machine", 50, 500, 100);

        int choice = -1;
        Scanner sc = new Scanner(System.in);
        do 
        {
            choice = CokeMenu(MyCokeMachine.getMachineName());
            switch (choice) 
            {
                case 0:
                    if (MyCokeMachine.getNumberOfCokesSold() == 0) 
                    {
                        System.out.println("Are you sure you aren't really THIRSTY and need a Coke?");
                    } 
                    else 
                    {
                        System.out.println("Thanks for buying coke");
                    }

                    break;
                case 1:
                    System.out.printf("A coke costs %s\n", displayMoney(MyCokeMachine.getCokePrice()));
                    System.out.print("Insert payment ");
                    int payamount = sc.nextInt();
                    if(MyCokeMachine.getChangeLevel()== MyCokeMachine.getMaxChangeCapacity())
                    {
                        System.out.println("Unable to sell a Coke -  call 1800WEDONTCARE to register a complaint...returning your payment");
                        break;
                    }
                    else
                    {
                        switch (MyCokeMachine.buyACoke(payamount)) 
                        {
                            case DISPENSECHANGE:
                                System.out.printf("\nHere's your Coke and your change of %s\n\n",displayMoney(MyCokeMachine.getChangeDispensed()));
                                break;
                            case INSUFFICIENTCHANGE:
                                System.out.print("\nUnable to give change at this time...returning your payment\n");
                                break;
                            case INSUFFICIENTFUNDS:
                                System.out.print("\nInsufficient payment...returning your payment\n\n");
                                break;
                            case EXACTPAYMENT:
                                System.out.printf("\nThank you for exact payment.\nHere's your Coke\n");
                                break;
                            case NOINVENTORY:
                                System.out.println("\nSorry we are out of coke.");
                                break;
                        }
                    }
                    break;
                    
                case 2:
                    System.out.print("How much product are you adding to the machine? ");
                    int addinv = sc.nextInt();
                    if (MyCokeMachine.incrementInventoryLevel(addinv)) 
                    {
                        System.out.println("Your machine has been restocked");
                        System.out.printf("Your inventory level is %d", MyCokeMachine.getInventoryLevel());
                    }
                    else 
                    {
                        System.out.println("You have exceeded your machine's max capacity - no inventory was added");
                        System.out.printf("Your inventory level is %d", MyCokeMachine.getInventoryLevel());
                    }

                    break;
                case 3:
                    System.out.print("How much change are you adding to the machine? ");
                    int addchng = sc.nextInt();
                    if (MyCokeMachine.incrementChangeLevel(addchng)) 
                    {
                        System.out.println("Your change level has been updated");
                        System.out.printf("Your change level is %s with a max capcity of %s",displayMoney(MyCokeMachine.getChangeLevel()),displayMoney(MyCokeMachine.getMaxChangeCapacity()));
                    } 
                    else 
                    {
                        System.out.println("You exceeded your machine's max change capacity - no change added");
                        System.out.printf("Your change level is %s with a max capcity of %s",displayMoney(MyCokeMachine.getChangeLevel()),displayMoney(MyCokeMachine.getMaxChangeCapacity()));
                    }
                    break;
                case 4:
                    System.out.print(MyCokeMachine);
                    break;
            }
        } 
        while (choice != 0);
    }


    public static String displayMoney(int cash) 
    {
        int dollar = cash / 100;
        int cents = cash % 100;
        int backupzero = -1;
        if(cents<10)
        {
            backupzero = 0; // to make sure the amount displays properly when the number of cents is less than 10
        }
        String fa = ""; // final amount
        if(backupzero == 0)
        {
            fa = String.format("$%d.%d%d",dollar,backupzero,cents);
        }
        else
        {
            fa = String.format("$%d.%d",dollar,cents);
        }
        return fa;
    }

    public static int CokeMenu(String name) 
    {
        System.out.printf("\n%s\n",name);
        System.out.println("\n\n0. Walk Away");
        System.out.println("1. Buy a Coke");
        System.out.println("2. Restock Machine");
        System.out.println("3. Add change");
        System.out.println("4. Display Machine Info");
        Scanner in = new Scanner(System.in);
        System.out.print("\n\nChoice : ");
        int choice = -1;
        do {
            try 
            {
                choice = in.nextInt();
            } 
            catch (Exception e) 
            {
                choice = 'a';
                in.next();
            }
            if (choice < 0 || choice > 5) 
            {
                System.out.print("Invalid choice.  Please reenter ");
            }

        } 
        while (choice < 0 || choice > 5);

        return choice;
    }
}