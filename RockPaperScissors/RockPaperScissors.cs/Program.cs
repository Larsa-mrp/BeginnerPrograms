using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Console;

namespace RockPaperScissors.cs
{
    class Program
    {
        static void Main(string[] args)
        {
            int computerChoice;
            string playerChoose;
            do
            {
                Random ranNumberGenerator = new Random();
                computerChoice = ranNumberGenerator.Next(1, 4);
                Write("Rock, Paper, Scissors! Choose either r, p, s: ");
                playerChoose = ReadLine();

                if (playerChoose == "r" || playerChoose == "p" || playerChoose == "s")
                {

                    if (computerChoice == 1)
                    {
                        if (playerChoose == "r")
                        {
                            WriteLine("Computer chose Rock");
                            WriteLine("Game is a tie...");
                        }
                        else if (playerChoose == "p")
                        {
                            WriteLine("Computer chose Rock");
                            WriteLine("Player wins!");
                        }
                        else if (playerChoose == "s")
                        {
                            WriteLine("Computer chose Rock");
                            WriteLine("Computer wins!");
                        }
                    }
                    else if (computerChoice == 2)
                    {
                        if (playerChoose == "r")
                        {
                            WriteLine("Computer chose Paper");
                            WriteLine("Computer wins!");
                        }
                        else if (playerChoose == "p")
                        {
                            WriteLine("Computer chose Paper");
                            WriteLine("Game is a tie...");
                        }
                        else if (playerChoose == "s")
                        {
                            WriteLine("Computer chose Paper");
                            WriteLine("Player wins!");
                        }
                    }
                    else if (computerChoice == 3)
                    {
                        if (playerChoose == "r")
                        {
                            WriteLine("Computer chose Scissors");
                            WriteLine("Player wins!");
                        }
                        else if (playerChoose == "p")
                        {
                            WriteLine("Computer chose Scissors");
                            WriteLine("Computer wins!");
                        }
                        else if (playerChoose == "s")
                        {
                            WriteLine("Computer chose Scissors");
                            WriteLine("Game is a tie...");
                        }
                    }
                }
                else
                {
                    WriteLine("Error! Please enter either r/p/s...");                   
                }
                Write("Would you like to play again? y/n: ");

            } while (Console.ReadLine() == "y");                              
        }
    }
}
