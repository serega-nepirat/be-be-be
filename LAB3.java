import java.util.Scanner;
import java.util.Locale;

public class LAB3 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле число: ");
        int intVal = scanner.nextInt();

        System.out.print("Введіть число з плаваючою точкою: ");
        double floatVal = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Введіть рядок: ");
        String strVal = scanner.nextLine();

        System.out.print("Введіть логічне значення (true/false): ");
        boolean boolVal = scanner.nextBoolean();

        System.out.println("\n--- Результат Виведення (10 Форматів) ---\n");
        System.out.printf("1. Ціле число (D): %d%n", intVal);
        System.out.printf("2. Ціле число (X): %#x%n", intVal);
        System.out.printf("3. Ціле число (O): %o%n", intVal);
        System.out.printf("4. Число з пл. точкою (.3f): %10.3f%n", floatVal);
        System.out.printf("5. Число з пл. точкою (e): %e%n", floatVal);
        String formatStr6 = String.format("6. Рядок (лівий край, 20): %-20s", strVal);
        System.out.println(formatStr6);
        String formatStr7 = String.format("7. Рядок (обмеження .7s): %.7s", strVal);
        System.out.println(formatStr7);
        System.out.printf("8. Число з пл. точкою (з роздільником): %,.2f%n", floatVal);
        System.out.printf("9. Ціле число (зі знаком +): %+d%n", intVal);
        System.out.println("10. Логічне значення: " + boolVal);

        scanner.close();
    }
}
