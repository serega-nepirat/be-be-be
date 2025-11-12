import java.lang.Math;

public class Ex1 {

    public static void main(String[] args) {

        double a = -0.5;
        double b = 1.7;
        double t = 0.44;
        double x = Math.exp(-b * t) * Math.sin(a * t + b) - Math.sqrt(Math.abs(b * t + a));
        double y = b * Math.sin(a * t * t * Math.cos(2 * t)) - 1;

        System.out.println("--- Розрахунок за Варіантом 1 ---");
        System.out.println("Задані константи:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("t = " + t);
        System.out.println("----------------------------------");
        System.out.println("Обчислені значення:");
        System.out.printf("x = %.6f%n", x);
        System.out.printf("y = %.6f%n", y);
    }
}