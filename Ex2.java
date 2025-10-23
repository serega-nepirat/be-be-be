import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст для підрахунку речень:");
        String text = scanner.nextLine();
        int sentenceCount = 0;
        int index = 0;
        while (index < text.length()) {
            char currentChar = text.charAt(index);
            if (currentChar == '.' || currentChar == '!' || currentChar == '?') {
                sentenceCount++;
            }

            index++;
        }

        System.out.println("Кількість речень у тексті (використовуючи while): **" + sentenceCount + "**");
    }
}