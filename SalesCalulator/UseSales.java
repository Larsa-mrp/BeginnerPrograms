/*  UseSales.java
    Calculates and displays item sales details
    Programmer  : Bradley Spiclin
    Date        : 02/11/2021
*/

import javax.swing.*;
public class UseSales
{
     public static void main (String [] args )
     {
          String item, message="", temp;
          int numberPurchased;
          double itemPrice, totalPrice, discountAmount, finalPrice;
          
          item = JOptionPane.showInputDialog(null,"Enter item purchased [ENTER to end]","Brunnings Sales System",JOptionPane.QUESTION_MESSAGE);
          while(!item.equals(""))
          {
            temp = JOptionPane.showInputDialog(null,"How many " + item + "s purchased?","Brunnings Sales System",JOptionPane.INFORMATION_MESSAGE);
            numberPurchased = Integer.parseInt(temp);
            temp = JOptionPane.showInputDialog(null,"Enter price for one " + item,"Brunnings Sales System",JOptionPane.INFORMATION_MESSAGE);
            itemPrice = Double.parseDouble(temp);
            totalPrice = Sales.calculateTotalPrice(itemPrice, numberPurchased);
            discountAmount = Sales.calculateDiscountAmount(totalPrice);
            finalPrice = Sales.calculateDiscountPrice(totalPrice, discountAmount);
            message = Sales.showSalesDetails(item, numberPurchased, itemPrice, totalPrice, discountAmount, finalPrice);
            JOptionPane.showMessageDialog(null,message,"Brunnings Sales System", JOptionPane.WARNING_MESSAGE);
            item = JOptionPane.showInputDialog(null,"Enter item purchased [ENTER to end]","Brunnings Sales System",JOptionPane.QUESTION_MESSAGE);
          }
          
          System.exit(0);

     }
}

