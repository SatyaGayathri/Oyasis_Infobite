package Task2;

import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame {
    public static void main(String[] args) {
        System.out.println("\nWELCOME TO THE NUMBER GUESSING GAME:");
        System.out.println("********************************************************************\n");
        Boolean PlayAgain=true;
        Scanner sc=new Scanner(System.in);
        while(PlayAgain){
            Random rm=new Random();
            int RandomNum=rm.nextInt(100)+1;

            int MaxAttemps=10;
            int numberOfAttempts=0;
            Boolean hasGuessedCorrectly=false;

            while(numberOfAttempts<MaxAttemps && !hasGuessedCorrectly){

                System.out.println("Guess the number between 1 to 100 :");
                int GuessedNum=sc.nextInt();

                if(GuessedNum<RandomNum){
                    System.out.println("It is too low");
                }
                else if(GuessedNum>RandomNum){
                    System.out.println("It is too High");
                }else{
                    System.out.println("\nCongratulations! You've Guessed the number");
                    hasGuessedCorrectly=true;
                }
                numberOfAttempts++;

            }
            if(!hasGuessedCorrectly){
                System.out.println("Sorry, you've used all your attempts. The number was: "+RandomNum);
            }

           //Score

           int Score=hasGuessedCorrectly?(MaxAttemps-numberOfAttempts+1)*10:0;
           System.out.println("\n\nYour Score:"+Score);

   
           System.out.print("\nDo you want to play again? (yes/no): ");
           String response = sc.next();
           if (!response.equalsIgnoreCase("yes")) {
               PlayAgain = false;
           }

           
          

        }

        System.out.println("\n***THANK YOU***");
        sc.close();
    }
}

