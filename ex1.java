import java.util.Scanner;

public class ex1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть слово:");
        String original = scanner.nextLine();
        scanner.close();

        boolean isPalindrome = true;
        int length = original.length();

        for (int i = 0; i < length / 2; i++) {
            if (original.charAt(i) != original.charAt(length - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("Паліндром");
        } else {
            System.out.println("Не паліндром");
        }
    }
}