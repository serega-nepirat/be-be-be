import java.util.Scanner;

public class ex5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст для цензури:");
        String text = scanner.nextLine();
        scanner.close();
        String[] badWords = {"погане", "жахливе", "дурне"};
        String replacement = "[ЦЕНЗУРА]";

        for (String word : badWords) {
            text = text.replace(word, replacement);
        }

        System.out.println("Цензурований текст:");
        System.out.println(text);
    }
}