/* Bradley Spiclin
** 02/11/2021
** Sales.java
*/

public class Sales 
{
    // method to return the totalPrice for the sale
    public static double calculateTotalPrice(double itemPrice, int numberPurchased)
    {
        double totalPrice = itemPrice * numberPurchased;
        return totalPrice;

    }

    // method to determine appropriate discount and return the discountAmount
    public static double calculateDiscountAmount(double totalPrice)
    {
        double discountAmount, discountRate = 0;

        if (totalPrice >= 1000)
        {
            discountRate = 0.10;
        }
        else if (totalPrice <= 999.99)
        {
            discountRate = 0.08;
            if (totalPrice <= 499.99)
            {
                discountRate = 0.05;
                if (totalPrice <= 100.00)
                {
                    discountRate = 0.00;
                }
            }
        }
        discountAmount = totalPrice * discountRate;
        return discountAmount;
    }

    // method to return the finalPrice of the sale
    public static double calculateDiscountPrice(double totalPrice, double discountAmount)
    {
        double finalPrice;

        finalPrice = totalPrice - discountAmount;
        return finalPrice;

    }

    // method to return a string of the sales details
    public static String showSalesDetails(String item, int numberPurchased, double itemPrice, double totalPrice, double discountAmount, double finalPrice)
    {
        String displaySalesDetails = String.format("Item Purchased: %s\nNumber of Items: %s\n\n"
        +"Unit Price: $%s\nTotal Price: $%s\nDiscount: $%s\nFinal Price: $%s", item, numberPurchased, itemPrice, totalPrice, discountAmount, finalPrice);

        return displaySalesDetails;
    }
    
}
