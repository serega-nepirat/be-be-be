public class Ex2 {

    public static void main(String[] args) {
        int[] numbers = {15, 2, 8, 21, 44, 9, 30, 7};
        int evenCount = 0;
        int oddCount = 0;

        System.out.println("Елементи масиву: ");

        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            System.out.print(currentNumber + " ");
            if (currentNumber % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        System.out.println("Кількість парних чисел: " + evenCount);
        System.out.println("Кількість непарних чисел: " + oddCount);
    }
}
