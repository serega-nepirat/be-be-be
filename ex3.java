import java.util.Scanner;

public class ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть строку (речення):");
        String text = scanner.nextLine();
        scanner.close();

        String[] words = text.split(" ");

        if (words.length == 0 || (words.length == 1 && words[0].isEmpty())) {
            return;
        }

        String minWord = words[0];
        String maxWord = words[0];

        for (String currentWord : words) {
            if (currentWord.isEmpty()) continue;

            if (currentWord.length() < minWord.length()) {
                minWord = currentWord;
            }
            if (currentWord.length() > maxWord.length()) {
                maxWord = currentWord;
            }
        }

        System.out.println("Найменше слово: " + minWord + " (" + minWord.length() + " символів)");
        System.out.println("Найдовше слово: " + maxWord + " (" + maxWord.length() + " символів)");
    }
}