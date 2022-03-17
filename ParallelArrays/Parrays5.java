/**
 * Parrays2
 * Bradley Spiclin  
 * 30/10/2021
 */

import java.util.ArrayList;
import javax.swing.*;
public class Parrays5 
{
    // declare all array list to store student details
    // using arraylist here as this is a far better method than arrays []

    public static ArrayList<Integer> studentIdArray = new ArrayList<Integer>();
    public static ArrayList<String> studentNameArray = new ArrayList<String>();
    public static ArrayList<Integer> studentMarkArray = new ArrayList<Integer>();
    public static ArrayList<Integer> marksOverFiftyArray = new ArrayList<Integer>();
    public static ArrayList<String> studentGradeArray = new ArrayList<String>();
    public static ArrayList<Double> studentFeeArray = new ArrayList<Double>();

    // method to check if input is Integer
    public static boolean isParsable(String temp)
    {
        try {
            Integer.parseInt(temp);
            return true;
        } catch (final NumberFormatException e)
        {
            return false;
        }
    }

    // method to get number of student entries and return the integer
    public static int getStudentEntries()
    {       
        int number = 0;             
        do
        {  
            String temp = JOptionPane.showInputDialog(null, "Number of students to enter"); 
            if (isParsable(temp))
            {
                number = Integer.parseInt(temp);
                if (number > 0 && number <= 10)
                {
                    break;
                }    
                JOptionPane.showMessageDialog(null, "Student entries must be between 1 - 10");         
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Student entries must be between 1 - 10");
            }  
                         
        } while (true);
        return number;
    }

    // method to enter student details into the arraylists
    public static void getStudentDetails(int entries)
    {
        String tempString;
        int studentMarkEntry;
        for (int i = 0; i < entries; i++)
        {  
            tempString = JOptionPane.showInputDialog(null, "Enter student ID [exit to end]"); 
            if (!tempString.contains("exit"))
            {                             
                studentIdArray.add(Integer.parseInt(tempString));
          
                studentNameArray.add(JOptionPane.showInputDialog(null, "Enter student name"));

                studentMarkEntry = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student mark"));
                studentMarkArray.add(studentMarkEntry);
    
                studentGradeArray.add(JOptionPane.showInputDialog(null, "Enter student grade"));
                studentFeeArray.add(Double.parseDouble(JOptionPane.showInputDialog(null, "Enter student Fee")));               
            }
            else
            {
                break;
            }
        }
    }

    // method displaying all student details stored in the arraylists
    public static void displayStudentDetails()
    {
        String buildString = "";
        for (int i = 0; i < studentIdArray.size(); i++)
        {
            buildString += String.format("Student ID: %s | Student Name: %s | Student Mark: %s | Student Grade: %s | Student Fee : $%s\n",
            studentIdArray.get(i), studentNameArray.get(i), studentMarkArray.get(i), studentGradeArray.get(i), studentFeeArray.get(i));
        }    
        JOptionPane.showMessageDialog(null, buildString);   
    }

    // method displaying students with a mark > 50
    public static void displayStudentPassed()
    {
        int selection;
        String printMarksOverFifty = "";
        selection = JOptionPane.showConfirmDialog(null, "Show marks greater than 50?", "Marks", JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION)
        {       
            for (int i = 0; i < studentMarkArray.size(); i++)
            {
                if (studentMarkArray.get(i) >= 50)
                {
                    printMarksOverFifty += String.format("Student ID: %s | Student Name: %s | Student Mark: %s | Student Grade: %s | Student Fee : $%s\n",
                    studentIdArray.get(i), studentNameArray.get(i), studentMarkArray.get(i), studentGradeArray.get(i), studentFeeArray.get(i));  
                                                                              
                }                
            }   

            JOptionPane.showMessageDialog(null, printMarksOverFifty);               
        }       
    }

    // method to find arraylist index by student ID entered
    public static int findStudentById()
    {
        int selection, stdId;
        selection = JOptionPane.showConfirmDialog(null, "Search for student?", "Student Lookup", JOptionPane.YES_NO_OPTION);
        if (selection == 0)
        {
            stdId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ID to search"));
            int idIndex = studentIdArray.indexOf(stdId);
            return idIndex;
        }
        else 
        {
            System.exit(0);
        }
        return -1;     
    }

    // method to update student info based on user input
    public static void updateStudent()
    {
        String grade;
        int mark, studentIdIndex;
          
        // calling up method to get the student ID index
        studentIdIndex = findStudentById();

        // using string format method to print string of student details to dialog box
        JOptionPane.showMessageDialog(null, String.format("Student ID: %s | Student Name: %s | Student Mark: %s | Student Grade: %s | Student Fee : $%s\n",
        studentIdArray.get(studentIdIndex), studentNameArray.get(studentIdIndex), studentMarkArray.get(studentIdIndex), studentGradeArray.get(studentIdIndex), studentFeeArray.get(studentIdIndex)), "Student Lookup", JOptionPane.INFORMATION_MESSAGE);

        // setting the variables here to alter the mark and grade
        mark = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new mark to update"));
        grade = JOptionPane.showInputDialog(null, "Enter new grade to update");

        // using findStudentById method to place the elements at the correct index
        studentMarkArray.set(studentIdIndex, mark);
        studentGradeArray.set(studentIdIndex, grade);                  
    }

    public static void main(String[] args) 
    {
        int numberOfEntries;

        // getting number of students to enter
        numberOfEntries = getStudentEntries();

        // passing numberOfEntries to data entry to enter student details
        getStudentDetails(numberOfEntries);

        // print student details to dialog box
        displayStudentDetails();

        // print only students with marks >= 50 to dialog box
        displayStudentPassed();

        // method to update student details by student ID
        updateStudent();

        // displaying all student details with updated information
        displayStudentDetails();

        System.exit(0);
    }
}