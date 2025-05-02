public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[10];

        // Fill the array with numbers from 1 to 100
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        // Shuffle the array
        for (int i = 0; i < numbers.length; i++) {
            int j = (int) (Math.random() * numbers.length); 
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        // Print the array to check the numbers
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
