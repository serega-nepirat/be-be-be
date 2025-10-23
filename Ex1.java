import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть довжину першої сторони (a):");
        double a = scanner.nextDouble();
        System.out.println("Введіть довжину другої сторони (b):");
        double b = scanner.nextDouble();
        System.out.println("Введіть довжину третьої сторони (c):");
        double c = scanner.nextDouble();

        if (a > 0 && b > 0 && c > 0 && (a + b > c) && (a + c > b) && (b + c > a)) {
            System.out.println("Трикутник з такими сторонами **може існувати**.");

            if (a == b && b == c) {
                System.out.println("Це **рівносторонній** трикутник.");
            } else if (a == b || a == c || b == c) {
                System.out.println("Це **рівнобедрений** трикутник.");
            } else {
                System.out.println("Це **різносторонній** трикутник.");
            }
        } else {
            System.out.println("Трикутник з такими сторонами **не може існувати** (не виконується нерівність трикутника або сторони недодатні).");
        }

    }
}