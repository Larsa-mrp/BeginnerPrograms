
/*  Author:     Bradley Spiclin
    Date:       20/10/2021
    Class:      Java Programming
*/
import javax.swing.*;

public class BradleyA1Bank {
    // method to return customer name string
    private static String CustomerName(String bank) {
        String name;
        name = JOptionPane.showInputDialog(null, "Enter your name", bank, JOptionPane.INFORMATION_MESSAGE);
        return name;
    }

    // method to return opening bank balance
    private static double EnterOpeningBalance(String name) {
        double balance;
        balance = Double.parseDouble(
                JOptionPane.showInputDialog(null, "Enter your opening balance", name, JOptionPane.QUESTION_MESSAGE));
        return balance;
    }

    // method for transaction entry and calulate all transactions
    private static void CustomerDataEntry(double balance, String bank, String name) {
        // variables for this method
        String printResults;
        int continueProgram, depositCount = 0, withdrawlCount = 0;
        double numberEntry, closingBalance, withdrawls = 0, deposits = 0;

        // loop to prompt first transaction then ask user for another entry, otherwise
        // exit loop
        while (true) {
            // if statement to add to deposits or withdrawls
            numberEntry = Double.parseDouble(
                    JOptionPane.showInputDialog(null, "Enter transaction amount\nUse -amount for withdrawls",
                            bank + " - " + name, JOptionPane.QUESTION_MESSAGE));
            if (numberEntry >= 0) {
                deposits += numberEntry;
                depositCount++;
            }
            if (numberEntry < 0) {
                withdrawls += Math.abs(numberEntry);
                withdrawlCount++;
            }

            // if statement to ask user to enter another transaction otherwise break loop
            continueProgram = JOptionPane.showConfirmDialog(null, "Enter another transaction?", bank + " - " + name,
                    JOptionPane.YES_NO_OPTION);
            if (continueProgram == 0) {
                continue;
            } else if (continueProgram == 1) {
                break;
            }
        }

        // calculate closing balance
        closingBalance = (balance + deposits) - withdrawls;

        // using string.format method to format all transaction results
        printResults = String.format(
                "Account Name: %s\n\nOpening Balance = $%s\nTotal Deposits = $%s\nTotal Withdrawls = $%s\nClosing Balance = $%s"
                        + "\n\n%s Deposits Made\n%s Withdrawls Made",
                name, balance, deposits, withdrawls, closingBalance, depositCount, withdrawlCount);

        // printing all transaction results to dialog box
        JOptionPane.showMessageDialog(null, printResults, bank + " - Transaction Record",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static void main(String[] args) {
        // main variables
        int continueProgram;
        double openingBalance;
        String customerName, bankName = "Victoria Bank";

        // method to enter customer name
        customerName = CustomerName(bankName);

        // method to enter opening balance
        openingBalance = EnterOpeningBalance(customerName);

        // prompt user to continue the program otherwise exit
        continueProgram = JOptionPane.showConfirmDialog(null, "Enter a transaction?", bankName,
                JOptionPane.YES_NO_OPTION);
        if (continueProgram == 0) {
            // if dialog = yes, call up data entry
            CustomerDataEntry(openingBalance, bankName, customerName);
        } else if (continueProgram == 1) {
            // if dialog = no, exit application
            System.exit(0);
        }
    }
}
