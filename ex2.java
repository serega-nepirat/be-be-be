import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть строку для інверсії:");
        String input = scanner.nextLine();
        scanner.close();
        String result = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            result = result + input.charAt(i);
        }

        System.out.println("Інвертована строка: " + result);
    }
}