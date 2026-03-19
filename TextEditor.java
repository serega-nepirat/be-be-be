import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextEditor {
    
    private static final String FILE_NAME = "textfile.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Вітаємо у Базовому Текстовому Редакторі!");
        while (isRunning) {
            displayMenu();
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Будь ласка, введіть цифру (1, 2 або 3).");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    isRunning = exitEditor();
                    break;
                default:
                    System.out.println("Невідома команда. Оберіть пункт від 1 до 3.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Головне меню ---");
        System.out.println("1. Записати до файлу");
        System.out.println("2. Прочитати увесь вміст файлу");
        System.out.println("3. Вийти з редактора");
        System.out.print("Ваш вибір: ");
    }

    private static void writeToFile(Scanner scanner) {
        System.out.println("Введіть рядок, який хочете записати до файлу (натисніть Enter для завершення):");
        String inputLine = scanner.nextLine();
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(inputLine + "\n");
            System.out.println("Успіх: Рядок успішно записано до файлу!");
        } catch (IOException e) {
            System.out.println("Критична помилка при записі до файлу: " + e.getMessage());
        }
    }

    private static void readFromFile() {
        System.out.println("\n--- Вміст файлу (" + FILE_NAME + ") ---");

        try (FileReader fileReader = new FileReader(FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            boolean isEmpty = true;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }

            if (isEmpty) {
                System.out.println("[Файл порожній]");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Помилка: Файл ще не існує. Спробуйте спочатку щось записати (Опція 1).");
        } catch (IOException e) {
            System.out.println("Критична помилка при читанні файлу: " + e.getMessage());
        }

        System.out.println("-----------------------------------");
    }

    private static boolean exitEditor() {
        System.out.println("Роботу завершено. Дякуємо за використання редактора!");
        return false;
    }
}