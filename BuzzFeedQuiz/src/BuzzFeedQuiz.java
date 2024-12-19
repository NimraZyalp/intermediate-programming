import java.util.Scanner;

public class BuzzFeedQuiz {

    public static void main(String[] args) {
        // Noble gas categories
        String[] categories = {"Helium", "Neon", "Argon", "Krypton", "Xenon", "Radon", "Oganesson"};
        int[] scores = new int[categories.length]; // store points for each category

        // Questions and answers with their assigned categories
        Question[] questions = {
            new Question("What is your favorite season of Game of Thrones?",
                new String[]{"Season 1", "Season 2", "Season 3", "Season 4", "Season 5", "Season 6", "Season 7"},
                new int[]{0, 1, 2, 3, 4, 5, 6}),
            new Question("Favorite video game",
                new String[]{"Fortnite", "Valorant", "Clash of Clans", "Brawl Stars", "Minecraft", "Clash Royale", "Block Blast"},
                new int[]{3, 0, 5, 2, 6, 1, 4}),
            new Question("Favorite day of the week",
                new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"},
                new int[]{6, 2, 3, 0, 4, 5, 1}),
            new Question("Favorite subject in school",
                new String[]{"Science", "History", "English", "Language", "Computer Science", "Math", "Art"},
                new int[]{1, 2, 3, 4, 5, 6, 0}),
            new Question("Favorite periodic table group",
                new String[]{"Alkali Metals", "Alkaline Earth Metals", "Halogens", "Noble Gases", "Transition Metals", "Inner Transition Metals", "Metalloids"},
                new int[]{0, 1, 2, 3, 4, 5, 6}),
            new Question("Favorite type of bear",
                new String[]{"Polar Bear", "Brown Bear", "Black Bear", "Pandas", "Grizzly Bear", "Sun Bear", "Spectacle Bear"},
                new int[]{4, 5, 1, 6, 3, 2, 0}),
            new Question("Favorite mode of transportation",
                new String[]{"Car", "Bicycle", "Train", "Airplane", "Boat", "Bus", "Walking"},
                new int[]{2, 3, 4, 5, 6, 0, 1}),
            new Question("Favorite genre of music",
                new String[]{"Pop", "Rock", "Classical", "Jazz", "Hip Hop", "Electronic", "Country"},
                new int[]{5, 6, 0, 1, 2, 3, 4})
        };

        // Loop through questions and get answers
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            int choice = getUserInput(scanner);
            if (choice >= 1 && choice <= 7) {
                // Add points to category
                scores[question.getCategory(choice - 1)]++;
            } else {
                System.out.println("Invalid input. Skipping this question.");
            }
        }

        // Figure out the category with the highest score
        int maxScoreIndex = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[maxScoreIndex]) {
                maxScoreIndex = i;
            }
        }

        System.out.println("Based on your answers, you're a(n) " + categories[maxScoreIndex] + " type of person!");
        scanner.close();
    }

    private static int getUserInput(Scanner scanner) {
        System.out.print("Enter your choice (1-7): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number between 1 and 7: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

class Question {
    private String questionText;
    private String[] answers;
    private int[] categories; // Maps each answer to a noble gas category 0-6

    public Question(String questionText, String[] answers, int[] categories) {
        this.questionText = questionText;
        this.answers = answers;
        this.categories = categories;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ". " + answers[i]);
        }
    }

    public int getCategory(int answerIndex) {
        return categories[answerIndex];
    }
}
