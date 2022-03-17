/* AssignmentEmployeeWages.java
   Description :  Assessment Corio Constructions Calculator
   Programmer  :  Bradley Spiclin
   Date        :  03/11/2021
*/

// class to contain the methods to calculate employee wages
public class Wages {
    // method to return a double of employee gross pay
    public static double calculateGrossWage(double rate, double hours) {
        // declaring const double for 38 hour week
        final double STANDARD_RATE_HOURS = 38;

        if (hours <= STANDARD_RATE_HOURS) {
            // returning gross for hours worked <= 38
            return rate * hours;
        }
        // declaring local variables here
        double overtimeHours, overtimePay, overtimeRate = rate * 1.5;

        // calculating the overtime pay
        overtimeHours = hours - STANDARD_RATE_HOURS;
        overtimePay = overtimeRate * overtimeHours;

        // returning the gross pay including overtime
        return rate * STANDARD_RATE_HOURS + overtimePay;
    }

    // method to return the tax payable as a double
    public static double calculateTaxPayable(double gross) {
        // if else to determine tax rate and tax payable and return that amount
        if (gross <= 350.00) {
            return 0;
        } else if (gross <= 700.00) {
            return (gross - 350.00) * 0.10;
        } else if (gross <= 1500.00) {
            return 0.15 * (gross - 700.00) + 35;
        } else {
            return 0.25 * (gross - 1500.00) + 155;
        }
    }

    // method to return the nett pay as a double using a simple calculation
    public static double calculateNettWage(double gross, double tax) {
        return gross - tax;
    }
}