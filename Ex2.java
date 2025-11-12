import java.util.Scanner;
import java.lang.Math;

public class Ex2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double f_x = Double.NaN;

        System.out.println("--- Розрахунок за Варіантом 2 ---");
        System.out.print("Введіть значення a: ");
        double a = scanner.nextDouble();
        System.out.print("Введіть значення b: ");
        double b = scanner.nextDouble();
        System.out.print("Введіть значення x: ");
        double x = scanner.nextDouble();

        if (x > -2 && x < 10) {
            f_x = Math.sqrt(b * x + 3);
        } else if (x == 10) {
            f_x = Math.abs(x * x - a * b * x + 7);
        } else if (x > 10) {
            f_x = Math.cos(x - 1);
        }

        System.out.println("----------------------------------");
        System.out.printf("Введені дані: a=%.2f, b=%.2f, x=%.2f%n", a, b, x);
        System.out.printf("Результат f(x) = %.6f%n", f_x);

        scanner.close();
    }
}