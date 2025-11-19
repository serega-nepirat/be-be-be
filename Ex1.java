import java.util.Random;
public class Ex1 {
    public static void main(String[] args) {
        int arraySize = 10;
        int[] numbers = new int[arraySize];

        Random random = new Random();

        System.out.println("Згенерований масив:");
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = random.nextInt(100);
            System.out.print(numbers[i] + " ");
        }
        int evenCount = 0;
        int oddCount = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        System.out.println("Результати аналізу:");
        System.out.println("Кількість парних чисел: " + evenCount);
        System.out.println("Кількість непарних чисел: " + oddCount);
    }
}