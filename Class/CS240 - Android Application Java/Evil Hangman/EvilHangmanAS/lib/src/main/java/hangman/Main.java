package hangman;

import java.io.File;
import java.util.Scanner;

import hangman.IEvilHangmanGame.GuessAlreadyMadeException;

/**
 * Created by Seong-EunCho on 1/26/17.
 */

public class Main {
    public static void main(String[] args) throws GuessAlreadyMadeException{
        File f = new File(args[0]);
        int i = Integer.parseInt(args[1]);
        int j = Integer.parseInt(args[2]);
        EvilHangman eh = new EvilHangman();
        eh.startGame(f, i);

        while (eh.getEnd() == false){
            try {
                System.out.println("You have " + j + " guesses left");
                System.out.println("Used letters: " + eh.guessToString());
                System.out.println("Word: " + eh.getCurrentWord());
                System.out.print("Enter guess: ");
                Scanner in = new Scanner(System.in);
                String s = in.next();
                if (s.length() != 1) {
                    System.out.println("Invalid input");
                } else if (!Character.isLetter(s.charAt(0))) {
                    System.out.println("Invalid input");
                } else {
                    eh.makeGuess(Character.toLowerCase(s.charAt(0)));
                    if (!eh.getCorrectGuess()) {
                        System.out.println("Sorry, there are no " + s + "'s");
                        j--;
                    } else {
                        System.out.println("Yes, there is " + eh.getNumPattern() + " " + s);
                    }
                    if (j == 0) {
                        System.out.println("You lose!");
                        for (String str : eh.getCurrentSet()) {
                            System.out.println("The word was: " + str);
                            break;
                        }
                        break;
                    }
                }
            }
            catch (GuessAlreadyMadeException e){
                System.out.println("You already used that letter");
            }
            System.out.println();
        }
    }
}
