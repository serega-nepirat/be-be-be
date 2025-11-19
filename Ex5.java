import java.util.Random;
import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Розмір масиву: ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10);
        }

        System.out.print("Масив: ");
        printArray(arr);
        System.out.print("\nПеревірка (Z - зростання, S - спадання): ");
        String c = sc.next().toUpperCase();

        if (c.equals("Z")) {
            if (isAsc(arr)) System.out.println("Відсортований за зростанням");
            else System.out.println("Не відсортований за зростанням");
        } else if (c.equals("S")) {
            if (isDesc(arr)) System.out.println("Відсортований за спаданням");
            else System.out.println("Не відсортований за спаданням");
        } else {
            System.out.println("Невірний вибір");
        }
        sc.close();
    }

    public static boolean isAsc(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) return false;
        }
        return true;
    }

    public static boolean isDesc(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < a[i + 1]) return false;
        }
        return true;
    }

    public static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

}
