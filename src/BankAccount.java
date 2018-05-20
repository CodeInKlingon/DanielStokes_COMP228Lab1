import javax.swing.*;

public class BankAccount {
    //static int to increment account numbers on instantiation so each account has a unique account number
    static int accountNumberCount=100;

    private int accountNumber;
    private String name;
    private double balance;


    //Constructor
    public BankAccount(String holder, double initBalance){
        accountNumber = accountNumberCount++;
        name = holder;
        if(initBalance > 0)
            balance = initBalance;
    }

    //Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }


    //bank actions
    public void Deposit(double amount){
        if(amount > 0)
            balance += amount;
    }

    public void Withdraw(double amount){
        if(amount > 0)
            balance -= amount;

        if(balance < 0)
            balance = 0;

    }

    public String getAccountInfo(){
        return "Account holder: " + name + ". Account number: " + accountNumber + ". Balance: $" + balance;
    }
}


//driver class for bank account exercise
class DriverEx2{
    public static void main(String[] args) {

        //runmmethod to prompt for input
        String name = PromptInput("Please enter the name of the account holder: ");
        String bal = PromptInput("Please enter the amount of money you wish to deposit into your new account");

        double newBalance = Double.parseDouble(bal);

        //instantiate new Bank Account object
        BankAccount ba = new BankAccount(name, newBalance);

        JOptionPane.showMessageDialog(null, "Your account is created and has a transaction limit of 10.");


        //loop to prompt for deposit or withdraw actions from user
        for(int i = 0; i < 10; i++){
            Object[] options = {"Deposit", "Withdraw"};
            JPanel panel = new JPanel();
            JLabel lbl = new JLabel("Choose your desired action");
            panel.add(lbl);


            //alternate logic for deposit or withdraw selection from user
            int result = JOptionPane.showOptionDialog(null,panel,"Choose the transaction type:", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,null);
            if (result != JOptionPane.YES_OPTION){

                //create option panel
                double withdrawAmount;

                Object[] options2 = { "Withdraw"};
                JPanel panel2 = new JPanel();
                JLabel lbl2 = new JLabel("Enter the amount you wish to withdraw");
                panel2.add(lbl2);
                JTextField textField = new JTextField(10);
                textField.setText("0");
                panel2.add(textField);

                int result2 = JOptionPane.showOptionDialog(null,panel2,"Amount:", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,options2,null);

                //get input
                if(result2 == JOptionPane.YES_OPTION){
                    withdrawAmount = Double.parseDouble(textField.getText());
                    ba.Withdraw(withdrawAmount);
                }
            }else{
                //create option panel
                double depositAmount;
                Object[] options2 = { "Deposit"};
                JPanel panel2 = new JPanel();
                JLabel lbl2 = new JLabel("Enter the amount you wish to deposit");
                panel2.add(lbl2);
                JTextField textField = new JTextField(10);
                textField.setText("0");
                panel2.add(textField);

                int result2 = JOptionPane.showOptionDialog(null,panel2,"Amount:", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,options2,null);
                //get input
                if(result2 == JOptionPane.YES_OPTION){
                    depositAmount = Double.parseDouble(textField.getText());
                    ba.Deposit(depositAmount);
                }
            }

            //show message with current balance
            JOptionPane.showMessageDialog(null,"Your account balance is now: $" + String.format( "%.2f" ,ba.getBalance()));


        }
    }

    //method to prompt for input and repeat if input was empty
    static String PromptInput(String message){
        String s;
        do{
            System.out.println("check input.");
            s = JOptionPane.showInputDialog (null,message);
            System.out.println("Input was " + s);
        }while (s.isEmpty());

        return s;

    }
}
