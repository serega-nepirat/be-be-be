import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ціле число N (верхня межа діапазону):");
        int N = scanner.nextInt();
        System.out.println("Прості числа у діапазоні від 1 до " + N + ":");

        for (int i = 2; i <= N; i++) {
            boolean isPrime = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.print(i + " ");
            }
        }

        System.out.println();
    }
}