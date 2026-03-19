import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        System.out.println("Вітаємо у Розширеному Текстовому Редакторі!");
        while (isRunning) {
            displayMenu();

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Будь ласка, введіть цифру (1 - 5).");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    writeLinesToFile(scanner);
                    break;
                case 2:
                    readAllFromFile();
                    break;
                case 3:
                    readRangeFromFile(scanner);
                    break;
                case 4:
                    insertIntoFile(scanner);
                    break;
                case 5:
                    isRunning = exitEditor();
                    break;
                default:
                    System.out.println("Невідома команда. Оберіть пункт від 1 до 5.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Головне меню ---");
        System.out.println("1. Додати декілька рядків у файл (в кінець)");
        System.out.println("2. Прочитати увесь вміст файлу");
        System.out.println("3. Прочитати діапазон рядків");
        System.out.println("4. * Вставити текст у визначений рядок (із зсувом тексту вниз)");
        System.out.println("5. Вийти з редактора");
        System.out.print("Ваш вибір: ");
    }

    private static void writeLinesToFile(Scanner scanner) {
        int startLineNumber = countLines() + 1;
        System.out.println("Введіть текст. Для завершення введіть ':exit' з нового рядка.");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            int currentLine = startLineNumber;
            while (true) {
                System.out.print(currentLine + ": ");
                String input = scanner.nextLine();

                if (":exit".equals(input)) {
                    break;
                }

                writer.write(input);
                writer.newLine();
                currentLine++;
            }
            System.out.println("Успіх: Рядки успішно збережено!");
        } catch (IOException e) {
            System.out.println("Критична помилка при записі: " + e.getMessage());
        }
    }

    private static void readAllFromFile() {
        System.out.println("\n--- Вміст файлу ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int lineNumber = 1;
            boolean isEmpty = true;

            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
                isEmpty = false;
            }

            if (isEmpty) {
                System.out.println("[Файл порожній]");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Помилка: Файл ще не існує. Спробуйте спочатку щось записати.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
        System.out.println("-------------------");
    }

    private static void readRangeFromFile(Scanner scanner) {
        int startLine = -1;
        int endLine = -1;

        try {
            System.out.print("Введіть початковий номер рядка: ");
            startLine = scanner.nextInt();
            System.out.print("Введіть кінцевий номер рядка: ");
            endLine = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Помилка: Потрібно вводити числа.");
            scanner.nextLine();
            return;
        }

        if (startLine < 1 || endLine < startLine) {
            System.out.println("Помилка: Неправильно заданий діапазон.");
            return;
        }

        System.out.println("\n--- Рядки з " + startLine + " по " + endLine + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int lineNumber = 1;
            boolean foundAny = false;

            while ((line = reader.readLine()) != null) {
                if (lineNumber >= startLine && lineNumber <= endLine) {
                    System.out.println(lineNumber + ": " + line);
                    foundAny = true;
                }

                if (lineNumber > endLine) {
                    break;
                }
                lineNumber++;
            }

            if (!foundAny) {
                System.out.println("[В даному діапазоні немає рядків]");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Помилка: Файл ще не існує.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні: " + e.getMessage());
        }
        System.out.println("-----------------------------");
    }

    private static void insertIntoFile(Scanner scanner) {
        int totalLines = countLines();
        if (totalLines == 0) {
            System.out.println("Файл порожній або не існує. Скористайтесь опцією 1 для створення.");
            return;
        }

        System.out.println("У файлі " + totalLines + " рядків.");
        System.out.print("Введіть номер рядка, куди хочете вставити текст (від 1 до " + (totalLines + 1) + "): ");

        int insertIndex = -1;
        try {
            insertIndex = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Помилка: Введено не число.");
            scanner.nextLine();
            return;
        }

        if (insertIndex < 1 || insertIndex > totalLines + 1) {
            System.out.println("Помилка: Неприпустимий номер рядка.");
            return;
        }

        String tempFileName = "temp_" + FILE_NAME;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {

            String line;
            int currentLine = 1;
            boolean inserted = false;

            while ((line = reader.readLine()) != null) {
                if (currentLine == insertIndex) {
                    insertLinesLoop(scanner, writer, currentLine);
                    inserted = true;
                }

                writer.write(line);
                writer.newLine();
                currentLine++;
            }

            if (!inserted && currentLine == insertIndex) {
                insertLinesLoop(scanner, writer, currentLine);
            }

        } catch (IOException e) {
            System.out.println("Помилка при обробці вставки: " + e.getMessage());
            return;
        }

        copyFile(tempFileName, FILE_NAME);
        new File(tempFileName).delete();
        System.out.println("Успіх: Текст вставлено, а решта тексту зсунута вниз!");
    }

    private static void insertLinesLoop(Scanner scanner, BufferedWriter writer, int startLineNumber) throws IOException {
        System.out.println("Введіть текст для вставки. Для завершення введіть ':exit' з нового рядка.");
        int currentLine = startLineNumber;
        while (true) {
            System.out.print(currentLine + " (вставка): ");
            String input = scanner.nextLine();
            if (":exit".equals(input)) {
                break;
            }
            writer.write(input);
            writer.newLine();
            currentLine++;
        }
    }

    private static int countLines() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
            System.out.println("Помилка підрахунку рядків: " + e.getMessage());
        }
        return count;
    }

    private static void copyFile(String source, String destination) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка збереження змін: " + e.getMessage());
        }
    }

    private static boolean exitEditor() {
        System.out.println("Роботу завершено. Дякуємо за використання редактора!");
        return false;
    }
}