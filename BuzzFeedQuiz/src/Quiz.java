
/*
 * Irene Feng Nov 2022
 * This is the class where we create the Quiz and run it. It has the main method.  
 */
import java.util.Scanner;

public class Quiz {
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
                // Create Categories
                Category helium = new Category("Helium",
                                "You're Helium.");
                Category neon = new Category("Neon", "You're Neon.");

                Category argon = new Category("Argon", "You're Argon.");

                Category krypton = new Category("Krypton", "You're Krypton.");

                Category xenon = new Category("Xenon", "You're Xenon");

                Category radon = new Category("Radon", "You're Radon");

                Category oganesson = new Category ("Oganesson", "You're Oganesson");
                // Create Questions
                Question q1 = new Question("What is your favorite season of game of thrones?");
                // Attach Answers to Questions
                q1.possibleAnswers[0] = new Answer("Season 1", radon);
                q1.possibleAnswers[1] = new Answer("Season 2", neon);
                q1.possibleAnswers[2] = new Answer("Season 3", xenon);
                q1.possibleAnswers[3] = new Answer("season 4", helium);
                q1.possibleAnswers[4] = new Answer("Season 5", argon);
                q1.possibleAnswers[5] = new Answer("Season 6", oganesson);
                q1.possibleAnswers[6] = new Answer("Season 7", krypton);

                Question q2 = new Question("What's your favorite video game from the following?");
                q2.possibleAnswers[0] = new Answer("fortnite", helium);
                q2.possibleAnswers[1] = new Answer("valorant", neon);
                q2.possibleAnswers[2] = new Answer("clash of clans", radon);
                q2.possibleAnswers[3] = new Answer("brawl stars", oganesson);
                q2.possibleAnswers[4] = new Answer("minecraft", argon);
                q2.possibleAnswers[5] = new Answer("clash royale", krypton);
                q2.possibleAnswers[6] = new Answer("block blast", xenon);

                Question q3 = new Question("What's your favorite day of the week?");
                q3.possibleAnswers[0] = new Answer("monday", oganesson);
                q3.possibleAnswers[1] = new Answer("tuesday", argon);
                q3.possibleAnswers[2] = new Answer("wednesday", krypton);
                q3.possibleAnswers[3] = new Answer("thursday", helium);
                q3.possibleAnswers[4] = new Answer("friday", radon);
                q3.possibleAnswers[5] = new Answer("saturday", neon);
                q3.possibleAnswers[6] = new Answer("sunday", xenon);

                Question q4 = new Question("What's your favorite subject in school?");
                q4.possibleAnswers[0] = new Answer("science", helium);
                q4.possibleAnswers[1] = new Answer("history", neon);
                q4.possibleAnswers[2] = new Answer("english", radon);
                q4.possibleAnswers[3] = new Answer("language", oganesson);
                q4.possibleAnswers[4] = new Answer("computer science", argon);
                q4.possibleAnswers[5] = new Answer("math", krypton);
                q4.possibleAnswers[6] = new Answer("art", xenon);

                Question q5 = new Question("What's your favorite periodic table group?");
                q5.possibleAnswers[0] = new Answer("alkali metals", helium);
                q5.possibleAnswers[1] = new Answer("alkaline earth metals", neon);
                q5.possibleAnswers[2] = new Answer("halogens", radon);
                q5.possibleAnswers[3] = new Answer("noble gasses", oganesson);
                q5.possibleAnswers[4] = new Answer("transition metals", argon);
                q5.possibleAnswers[5] = new Answer("inner transition metals", krypton);
                q5.possibleAnswers[6] = new Answer("metalloids", xenon);
                
                Question q6 = new Question("What's your favorite species of bear?");
                q6.possibleAnswers[0] = new Answer("polar bear", helium);
                q6.possibleAnswers[1] = new Answer("brown bear", neon);
                q6.possibleAnswers[2] = new Answer("black bear", radon);
                q6.possibleAnswers[3] = new Answer("pandas", oganesson);
                q6.possibleAnswers[4] = new Answer("grizzly bear", argon);
                q6.possibleAnswers[5] = new Answer("sun bear", krypton);
                q6.possibleAnswers[6] = new Answer("spectacle bear", xenon);

                Question q7 = new Question("What's your favorite prefix?");
                q7.possibleAnswers[0] = new Answer("mono", helium);
                q7.possibleAnswers[1] = new Answer("di", neon);
                q7.possibleAnswers[2] = new Answer("tri", radon);
                q7.possibleAnswers[3] = new Answer("tetra", oganesson);
                q7.possibleAnswers[4] = new Answer("penta", argon);
                q7.possibleAnswers[5] = new Answer("hexa", krypton);
                q7.possibleAnswers[6] = new Answer("hepta", xenon);

                Question q8 = new Question("What's your favorite sport?");
                q8.possibleAnswers[0] = new Answer("chess", helium);
                q8.possibleAnswers[1] = new Answer("tennis", neon);
                q8.possibleAnswers[2] = new Answer("table tennis", radon);
                q8.possibleAnswers[3] = new Answer("track and field", oganesson);
                q8.possibleAnswers[4] = new Answer("soccer", argon);
                q8.possibleAnswers[5] = new Answer("swimming", krypton);
                q8.possibleAnswers[6] = new Answer("football", xenon);
                // For each question, ask, read input, store answer.
                gameIntro();
                Question[] qList = { q1, q2, q3, q4, q5, q6, q7, q8 };
                for (Question q : qList) {
                        Category c = q.ask(sc);
                        c.points++;
                }
                // Get most common category from the questions asked
                // Return Category
                Category[] cList = { helium, neon, radon, xenon, argon, krypton, oganesson };
                // these need to be in the same order or the points will be incorrect!
                int index = getMostPopularCatIndex(cList);
                System.out.println("If you were a noble gas, you would be " + cList[index].label + ". ");
                System.out.println(cList[index].description);

        }

        public static void gameIntro() {
                // requires 1 to keep going
                System.out.println("Which Noble Gas Are You?");
                System.out.println("You get to choose numbers 1-7 for every question. Enter '1' to play!");
              
      
                int play = getInput();

                while (play != 1) { 
                        
                        play = getInput(); 
                }
               
        }

        public static int getInput() {
                int num;
                try {
                        num = sc.nextInt();
                        if (num > 7 || num < 1) {
                                throw new Exception();
                        }
                } catch (Exception e) {
                        System.out.println("Please enter 1");
                        return getInput();
                }    
                
                return num;

        }

        // returns the index that is the max
        // the tie breaker is the first Category that has the count is the "max" :/ 
        public static int getMostPopularCatIndex(Category[] counts) {
                int maxCount = 0;
                int maxIndex = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points > maxCount) {
                                maxCount = counts[i].points;
                                maxIndex = i;
                        }
                }
                return maxIndex;
        }
}
