/* AssignmentEmployeeWages.java
   Description :  Assessment Corio Constructions Calculator
   Programmer  :  Bradley Spiclin
   Date        :  03/11/2021
*/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class AssignmentEmployeeWages {
  // declaring String here to get date and time using the DateFormat method
  static String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

  // String for dialog box info to be displayed also using the timeStamp string
  // declared above
  static String dialogInfo = "CORIO CONTRUCTIONS - " + timeStamp;

  // declaring all arrays[] here to store employee information
  public static String[] empID = { "", "", "", "", "", "", "", "", "", "", "", "" };
  public static String[] firstName = { "Barry", "Cheryl", "Samuel", "Natalie", "Colin", "Shane", "Sharon", "William",
      "Craig", "Susan", "Blake", "Bev" };
  public static String[] lastName = { "Dove", "Jacobs", "Devlin", "Taylor", "Masters", "Clarke", "Packer", "Baker",
      "Lyons", "Parker", "Hamish", "Anderson" };
  public static double[] rate = new double[12];
  public static double[] hours = new double[12];
  public static double[] gross = new double[12];
  public static double[] tax = new double[12];
  public static double[] nett = new double[12];

  static int menu;

  public static void main(String[] args) {

    // displaying welcome message void method
    welcomeMessage();
    // calling up getMenu method and using switch to select method to call up
    getMenu();
    while (menu != 0) {
      switch (menu) {
      case 1:
        createEmpID();
        break;
      case 2:
        enterPayDetails();
        break;
      case 3:
        calcGross();
        break;
      case 4:
        calcTax();
        break;
      case 5:
        calcNett();
        break;
      case 6:
        displayEmployeeDetails();
        break;
      }
      // returning to menu after a method is called up
      getMenu();

    }

    System.exit(0);
  }

  // welcome message dialog void method
  public static void welcomeMessage() {
    JOptionPane.showMessageDialog(null, "Welcome to Corio Constructions\nContinue to menu...\n", dialogInfo,
        JOptionPane.INFORMATION_MESSAGE);
  }

  // method to display main menu options to delect which method to call
  public static void getMenu() {
    String message, temp;

    message = "EMPLOYEE WAGES MENU\n\n";
    message += " (1)   CREATE EMPLOYEE ID\n";
    message += " (2)   ENTER PAY DETAILS\n";
    message += " (3)   CALCULATE GROSS PAY\n";
    message += " (4)   CALCULATE TAX PAYABLE\n";
    message += " (5)   CALCULATE NETT PAY\n";
    message += " (6)   DISPLAY EMPLOYEE DETAILS\n\n";
    message += " (0)   QUIT\n\n";
    message += "Enter Choice";

    temp = JOptionPane.showInputDialog(null, message, dialogInfo, JOptionPane.QUESTION_MESSAGE);
    menu = Integer.parseInt(temp);
  }

  // method to populate rate[] array and hours [] arrray for each employee using
  // for loop
  public static void enterPayDetails() {
    for (int i = 0; i < empID.length; i++) {
      rate[i] = Double.parseDouble(JOptionPane.showInputDialog(null,
          "Enter Pay Rate for " + firstName[i] + " " + lastName[i], dialogInfo, JOptionPane.QUESTION_MESSAGE));

      hours[i] = Double.parseDouble(JOptionPane.showInputDialog(null,
          "Enter Hours Worked for " + firstName[i] + " " + lastName[i], dialogInfo, JOptionPane.QUESTION_MESSAGE));
    }
  }

  // method to create employee ID and enter for each employee using for loop
  public static void createEmpID() {
    for (int i = 0; i < empID.length; i++) {
      empID[i] = JOptionPane.showInputDialog(null, "Enter Employee ID for " + firstName[i] + " " + lastName[i],
          dialogInfo, JOptionPane.QUESTION_MESSAGE);
    }
  }

  // method to display all array elements using for loop and String.format method
  public static void displayEmployeeDetails() {
    String printArrays = "";
    for (int i = 0; i < empID.length; i++) {
      printArrays += String.format("%s    %s    %s    %.2f    %.2f    %.2f   %.2f    %.2f\n", empID[i], firstName[i],
          lastName[i], rate[i], hours[i], gross[i], tax[i], nett[i]);

    }
    JOptionPane.showMessageDialog(null, "EMPLOYEE DETAILS\n\n" + printArrays, dialogInfo,
        JOptionPane.INFORMATION_MESSAGE);
  }

  // method to return gross pay using class Wages.calculateWage() and populate the
  // gross [] array
  public static void calcGross() {
    for (int i = 0; i < empID.length; i++) {
      gross[i] = Wages.calculateGrossWage(rate[i], hours[i]);
    }
  }

  // method to return tax payable using Wages.calculateTaxPayable() and populate
  // the tax [] array
  public static void calcTax() {
    for (int i = 0; i < empID.length; i++) {
      tax[i] = Wages.calculateTaxPayable(gross[i]);
    }
  }

  // method to return nett wage using Wages.calculateNettWage() and populate the
  // nett [] array
  public static void calcNett() {
    for (int i = 0; i < empID.length; i++) {
      nett[i] = Wages.calculateNettWage(gross[i], tax[i]);
    }
  }
}
