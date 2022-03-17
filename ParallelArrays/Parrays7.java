/**
 * Parrays7
 * Bradley Spiclin
 * 31/10/2021
 */

import javax.swing.*;
import java.util.ArrayList;
public class Parrays7
{
  // declaring ArrayLists to store customer data 
  // I used the ArrayList as the methods in java are far better than array[]
   static final int MAX=10;
   static ArrayList<String> carRego = new ArrayList<String>();
   static ArrayList<String> ownersName = new ArrayList<String>();
   static ArrayList<Integer> kmService = new ArrayList<Integer>();
   static ArrayList<Double> cost = new ArrayList<Double>();
   static ArrayList<Boolean> completedJobArray = new ArrayList<Boolean>();
   static int countEntries;
   
   public static void main (String [] args )
   {
     // calling up main menu method which contains all other methods
	  mainMenuDialog();
	 }

   // method for main menu dialog with switch for menu options
   public static void mainMenuDialog()
   {
     String temp;
     int choice;
     do
     {
      temp = JOptionPane.showInputDialog(null, "SELECT OPTION OR 'Q' to EXIT\nEnter new job = 1\nDisplay all jobs = 2\nDisplay completed jobs = 3\nSearch car record = 4\nUpdate car record = 5", "Main Menu", JOptionPane.QUESTION_MESSAGE);
      choice = Integer.parseInt(temp);
      switch (choice)
      {
        // switch the user input to call up the appropriate function
        case 1:
          getCarRecords();
          break;       
        case 2:
          displayAllCarRecords();
          break;      
        case 3:
          displayCompletedCarRecords();
          break;      
        case 4:
          searchCarRecords();
          break;
        case 5:
          updateCarRecords();
          break;
      }
     } while (!temp.equals("Q".toLowerCase()));
     System.exit(0);
   }
	 
   // method for data entry into arraylists
   public static void getCarRecords()
   {
     String temp, carRegoTemp;
     int i=0, response;
     double incompleteJob = 0;

     carRegoTemp = JOptionPane.showInputDialog(null, "Enter car registration [ENTER to end]");
     while(!carRegoTemp.equals("") && i < MAX)
     {
        carRego.add(carRegoTemp);
        ownersName.add(JOptionPane.showInputDialog(null, "Enter owners name"));
        kmService.add(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter km service")));

        response = JOptionPane.showConfirmDialog(null,"Is service completed?","Service Completed",JOptionPane.YES_NO_OPTION);

        if(response == JOptionPane.YES_OPTION)
        {
          completedJobArray.add(true);
          temp = JOptionPane.showInputDialog(null, "Enter service cost");
          cost.add(Double.parseDouble(temp));
        }
        else
          completedJobArray.add(false);
          cost.add(incompleteJob);
        ++i;
        if(i < MAX)
        {
          carRegoTemp = JOptionPane.showInputDialog(null, "Enter car registration [ENTER to end]");
        }
     }
     countEntries = i;
	 }

   // method to print all records stored in arraylists
   public static void displayAllCarRecords()
   {
     String printDetails = "";
     for (int i = 0; i < carRego.size(); i++)
     {
        printDetails += "Rego: "+carRego.get(i)+" | Owner: " +ownersName.get(i)+ " | KM Service: "+ kmService.get(i)+" | Service Cost: $"+cost.get(i)+" | Completed: "+completedJobArray.get(i)+"\n";
     }
     
     JOptionPane.showMessageDialog(null, printDetails, "Displaying All Cars", JOptionPane.INFORMATION_MESSAGE);
	 }
  	
   // method to display all completed jobs only
   public static void displayCompletedCarRecords()
   {
     String completedJobString = "";
     for (int i = 0; i < completedJobArray.size(); i++)
     {
      if (completedJobArray.get(i) == true)
      {
        completedJobString += "Rego: "+carRego.get(i)+" | Owner: " +ownersName.get(i)+ " | KM Service: "+ kmService.get(i)+" | Service Cost: $"+cost.get(i)+" | Completed: "+completedJobArray.get(i)+"\n";
      }
     }
     JOptionPane.showMessageDialog(null, completedJobString, "Displaying Completed Jobs", JOptionPane.INFORMATION_MESSAGE);

	 }

   // method to search a record by rego number and return the index
   public static int searchCarRecords()
   {
     int indexPosition = 0;
     String temp, carRegoSearch = "";
     temp = JOptionPane.showInputDialog(null, "Enter car rego to search record", "Car Search", JOptionPane.QUESTION_MESSAGE);

     for (int i = 0; i < carRego.size(); i++)
     {
      if (carRego.get(i).equals(temp))
      {
        carRegoSearch += "Rego: "+carRego.get(i)+" | Owner: " +ownersName.get(i)+ " | KM Service: "+ kmService.get(i)+" | Service Cost: $"+cost.get(i)+" | Completed: "+completedJobArray.get(i)+"\n";
        indexPosition = i;
        break;
      }
      else if (!carRego.contains(temp))
      {
        carRegoSearch += "Rego not found";
        System.exit(0);
      }
     }
     JOptionPane.showMessageDialog(null, carRegoSearch, "Car Search Results", JOptionPane.INFORMATION_MESSAGE);

     return indexPosition;
	}

  // method to update records with index retturn from searchCarRecords() function
   public static void updateCarRecords()
   {
     int carRegoIndex, completeJob;
     double completeCost;
  
     carRegoIndex = searchCarRecords();

     completeJob = JOptionPane.showConfirmDialog(null, "Update Job to completed?", "Update Job", JOptionPane.YES_NO_OPTION);

     if (completeJob == 0)
     {
       completedJobArray.set(carRegoIndex, true); 
      
        completeCost = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter service cost", "Complete Job", JOptionPane.INFORMATION_MESSAGE));
        cost.set(carRegoIndex, completeCost);
     }
	}

  // method to add new data to the arraylists
   public static void addNewCarRecord()
   {
     int response;
     response = JOptionPane.showConfirmDialog(null, "Add another record?", "New Entry", JOptionPane.YES_NO_OPTION);
     if (response == 0)
     {
      getCarRecords();
     }
     else
     {
       System.exit(0);
     }
	 } 
}
