/* Irene Feng 10/12/2022
A question class with Answers.
*/ 
import java.util.Scanner;

public class Question {
    // Fields
    String label;
    Answer[] possibleAnswers = new Answer[7];

    Question(String label) {
        this.label = label;
    }

    // ask a question, and return the category that corresponds to the answer
    Category ask(Scanner sc) {
        System.out.println(this.label);
        // prints out all the answer choices
        for (int i = 0; i < this.possibleAnswers.length; i++) {
            String choice = Integer.toString(i + 1);
            System.out.println("[" + choice + "]:" +
                    this.possibleAnswers[i].label);
        }
        int ans = getInput(sc);
        return possibleAnswers[ans - 1].cat;
    }


    public static int getInput(Scanner sc) {
                int num;
                try {
                        num = sc.nextInt();
                        if (num > 7 || num < 1) {
                                throw new Exception();
                        }
                } catch (Exception e) {
                        System.out.println("Please enter a valid answer from 1 to 7");
                        return getInput(sc);
                }    
                
                return num;

        }
}
