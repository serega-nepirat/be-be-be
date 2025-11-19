import java.util.Random;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Введіть розмір масиву: ");
        int size = sc.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = rand.nextInt(50);
        }

        System.out.print("Масив: ");
        printArray(numbers);
        System.out.print("\nВведіть число, яке хочете замінити: ");
        int oldValue = sc.nextInt();
        System.out.print("На яке число замінити: ");
        int newValue = sc.nextInt();
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == oldValue) {
                numbers[i] = newValue;
                count++;
            }
        }

        if (count > 0) {
            System.out.println("Було замінено " + count + " значень.");
        } else {
            System.out.println("Значення не знайдено.");
        }

        System.out.print("Оновлений масив: ");
        printArray(numbers);
        sc.close();
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
