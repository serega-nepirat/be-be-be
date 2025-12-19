import java.util.Scanner;
public class practice9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userString = inputString();

        System.out.println("Оберіть дію:");
        System.out.println("1 - Перевернути всю строку");
        System.out.println("2 - Перевернути кожне слово окремо");

        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Результат: " + reverseFullString(userString));
        } else if (choice == 2) {
            System.out.println("Результат: " + reverseEachWord(userString));
        }
    }

    public static String inputString() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введіть строку (мінімум 2 слова, кожне від 3 символів):");
            String input = sc.nextLine();
            String[] words = input.split(" ");
            if (words.length >= 2) {
                boolean allValid = true;
                for (String word : words) {
                    if (word.length() < 3) {
                        allValid = false;
                        break;
                    }
                }
                if (allValid) {
                    return input;
                }
            }
            System.out.println("Строка не відповідає критеріям, спробуйте ще раз.");
        }
    }

    public static String reverseFullString(String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return result;
    }

    public static String reverseEachWord(String s) {
        String[] words = s.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reversedWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord += word.charAt(j);
            }
            result += reversedWord;
            if (i < words.length - 1) {
                result += " ";
            }
        }
        return result;
    }
}