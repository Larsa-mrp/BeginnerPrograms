using System;
using System.Collections.Generic;
using System.Linq;
using static System.Console;

namespace MarshallsRevenue
{
    class Program
    {
        // Lists storing murals ordered and customer names
        private static List<string> InteriorCustomers = new List<string>();
        private static List<string> InteriorMurals = new List<string>();       
        private static List<string> ExteriorCustomers = new List<string>();
        private static List<string> ExteriorMurals = new List<string>();

        // List of mural codes and mural names
        private static List<string> AllMuralCodes = new List<string>(5)
        {"L", "S", "A", "C", "O" };
        private static List<string> AllMuralNames = new List<string>(5)
        { "Landscape", "Seascape", "Abstract", "Childrens", "Other" };

        // method to get mural name by mural code
        static private string GetMuralName(string muralCode) 
        {            
            for (int i = 0; i < AllMuralCodes.Count; i++)
            {
                if (AllMuralCodes[i] == muralCode)
                {
                    return AllMuralNames[i];
                }                
            }
            return "";
        }

        // enum for month names
        public enum Months
        {
            January = 1, February, March, Arpil, May, June,
            July, August, September, October, November, December
        }

        // method to check month is valid
        static private int CheckMonthInput()
        {
            int monthInput;
            Write("Enter the month to schedule murals: ");
            while (!int.TryParse(ReadLine(), out monthInput) || monthInput < 1 || monthInput > 12)
            {
                Write("Invalid entry.. Please enter a month between 1 - 12: ");
            }
            return monthInput;
        }

        // method to check if Q press = exit application, otherwise return a valid mural code
        static private string GetValidMuralCode()
        {
            Write("\nEnter mural style code: ");
            string temp = ReadLine();
            if (temp.ToLower() == "q")
                Environment.Exit(0);
            string muralCode = temp.ToUpper();
            while (true)
            {
                while (!AllMuralCodes.Contains(muralCode))
                {
                    Write("Invalid mural code, try again: ");
                    muralCode = ReadLine().ToUpper();
                }
                return muralCode;
            }
        }

        // method to check number of entered murals are valid 0-30, return muralNumber
        static private int GetNumberOfMurals(string localString)
        {
            int muralNumber;
            const int MIN_MURALS = 0;
            const int MAX_MURALS = 30;
            Write($"Enter number of {localString} murals scheduled: ");
            while (!int.TryParse(ReadLine(), out muralNumber) || muralNumber < MIN_MURALS || muralNumber > MAX_MURALS)
            {
                Write($"Scheduled Murals must be between {MIN_MURALS} and {MAX_MURALS}: ");
            }
            return muralNumber;
        }

        // counting murals ordered by mural code
        static private int GetNumberMuralsByCode(string muralCode, List<string> StoredMurals)
        {
            var counter = 0;          
            foreach(var item in StoredMurals)
            {
                if (item == muralCode)
                {
                    counter++;
                }
            }
            return counter;
        }

        // method for data entry customer names and mural type
        static private int CustomerDataEntry(int muralNumber, string jobString, List<string> CustomerNamesList, List<string> OrderedMuralsList)
        {
            if (muralNumber > 0)
            {
                WriteLine($"\n<< Entering {jobString} jobs >>");
            }
            for (int i = 0; i < muralNumber; i++)
            {
                Write("Enter customer name: ");
                CustomerNamesList.Add(ReadLine());
                WriteLine("\n<< Mural style options >>");
                // combined list for all mural style options
                var combinedList = AllMuralCodes.Zip(AllMuralNames, (fst, snd) => { return fst + " - " + snd; });
                foreach (var item in combinedList)
                {
                    WriteLine(" " + item);
                }
                OrderedMuralsList.Add(GetValidMuralCode());
            }
            return muralNumber;
        }       

        // method to print customers ordering mural type entered
        static private void GetSheduledCustomerMurals(List<string> InteriorMuralList, List<string> InteriorCustomerList, List<string> ExteriorMuralList, List<string> ExteriorCustomerList)
        {
            while (true)
            {
                WriteLine($"\n<< Display scheduled murals or press 'Q' to exit >>");
                string muralEntry = GetValidMuralCode();
                var muralName = GetMuralName(muralEntry);
                if (InteriorMuralList.Contains(muralEntry) || ExteriorMuralList.Contains(muralEntry))
                {
                    WriteLine($"\nCustomers ordering {muralName} murals are:\n--------------------------------------");
                    // stored mural codes that have been entered - mural 
                    for (int i = 0; i < InteriorMurals.Count; i++)
                    {
                        var customerName = InteriorCustomerList[i];
                        if (InteriorMurals[i] == muralEntry)
                        {                           
                            WriteLine($" {customerName} - Interior");                        
                        }
                    }                   
                    for (int i = 0; i < ExteriorMuralList.Count; i++)
                    {
                        var customerName = ExteriorCustomerList[i];
                        if (ExteriorMuralList[i] == muralEntry)
                        {
                            WriteLine($" {customerName} - Exterior");
                        }
                    }
                }
                else
                {
                    WriteLine($"No customers have ordered {muralName} murals");
                }
            }         
        }

        // passing month, interior and exterior murals ordered and calculating revenue
        static private decimal CalculateRevenue(int monthInput, int interior, ref int exterior)
        {
            // decimal variables for totals
            decimal revenueInterior, revenueExterior, total, interiorPrice, exteriorPrice;  

            // decimal variables for interior/exterior price
            const decimal REGULAR_INTERIOR_PRICE = 500;
            const decimal REGULAR_EXTERIOR_PRICE = 750;   
            const decimal DISCOUNT_INTERIOR_PRICE = 450;
            const decimal DISCOUNT_EXTERIOR_PRICE = 699;

            // setting interior and exterior price
            interiorPrice = REGULAR_INTERIOR_PRICE;
            exteriorPrice = REGULAR_EXTERIOR_PRICE;          

            // getting month name from enum to use in switch statement                     
            string month = Enum.GetName(typeof(Months), monthInput);

            // switch statement for mural discounts by monthInput
            switch (monthInput)
            {
                case (int)Months.January:
                case (int)Months.February:
                case (int)Months.December:
                    WriteLine($"<< Exterior murals cannot be painted in {month} >>");                 
                    exterior = 0;
                    break;
                case (int)Months.Arpil:
                case (int)Months.May:
                case (int)Months.September:
                case (int)Months.October:
                    WriteLine($"<< Exterior murals for {month} have a discount applied >>");
                    exteriorPrice = DISCOUNT_EXTERIOR_PRICE;
                    break;
                case (int)Months.July:
                case (int)Months.August:
                    WriteLine($"<< Interior murals for {month} have a discount applied >>");
                    interiorPrice = DISCOUNT_INTERIOR_PRICE;
                    break;
            }
            // calculate revenue
            revenueInterior = interior * interiorPrice;
            revenueExterior = exterior * exteriorPrice;
            total = revenueInterior + revenueExterior;
       
            // print interior and exterior murals ordered and revenue to console
            WriteLine($"\n{interior} Interior murals are scheduled for {month}, {interiorPrice:C} each, total of {revenueInterior:C}");
            WriteLine($"{exterior} Exterior murals are scheduled for {month}, {exteriorPrice:C} each, total of {revenueExterior:C}");
                             
            return total;           
        }
        static void Main(string[] args)
        {
            // internal variables          
            int monthInput, numInterior, numExterior;
            decimal totalPrice;

            // interior greater than exterior bool
            bool isInteriorGreater = false;

            // prompt user to enter a month integer, check if its valid, then return monthInput
            monthInput = CheckMonthInput();

            // prompt user to enter integer of murals for both interior/exterior for the entered month and return numInterior and numExterior integers
            numInterior = GetNumberOfMurals("Interior");
            numExterior = GetNumberOfMurals("Exterior");   
            
            // method calculating interior/exterior and total revenue and printing to console
            totalPrice = CalculateRevenue(monthInput, numInterior, ref numExterior);
            WriteLine($"\nTotal expected revenue is {totalPrice:C}");

            // true/false statement to print if more interior murals are scheduled than exterior ones
            if (numInterior > numExterior)
            {
                isInteriorGreater = true;
            }
            WriteLine($"\nIt is {isInteriorGreater} that there are more interior murals scheduled than exterior murals");

            // prompt user for data entry for interior and exterior murals, storing customer names and mural types to seperate Lists<>
            CustomerDataEntry(numInterior, "Interior", InteriorCustomers, InteriorMurals);
            CustomerDataEntry(numExterior, "Exterior", ExteriorCustomers, ExteriorMurals);  
            
            // printing totals for all scheduled murals to the console
            WriteLine("\n Interior murals scheduled   \n---------------------------");
            foreach (var muralCode in AllMuralCodes)
            {
                var countForMuralCode = GetNumberMuralsByCode(muralCode, InteriorMurals);
                var muralNames = GetMuralName(muralCode);
                WriteLine($" {muralNames} = {countForMuralCode}");
            }
            WriteLine("\n Exterior murals scheduled   \n---------------------------");
            foreach (var muralCode in AllMuralCodes)
            {
                var countForMuralCode = GetNumberMuralsByCode(muralCode, ExteriorMurals);
                var muralNames = GetMuralName(muralCode);
                WriteLine($" {muralNames} = {countForMuralCode}");
            }          

            // continually prompt the user to enter a valid mural code and print all customers who have ordered that mural type, 'Q' to exit application
           GetSheduledCustomerMurals(InteriorMurals, InteriorCustomers, ExteriorMurals, ExteriorCustomers);                      
        }
    }
}
