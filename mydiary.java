import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class mydiary {
    public static void main(String[] args) {
        int maxRecords = 50;
        LocalDateTime[] dates = new LocalDateTime[maxRecords];
        String[] records = new String[maxRecords];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== МІЙ ЩОДЕННИК ===");
            System.out.println("1. Додати запис");
            System.out.println("2. Видалити запис за датою");
            System.out.println("3. Переглянути всі записи");
            System.out.println("0. Вийти");
            System.out.print("Оберіть дію: ");
            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                if (count >= maxRecords) {
                    System.out.println("Помилка, щоденник заповнений. Максимальна кількість записів — " + maxRecords);
                    continue;
                }

                LocalDateTime entryDate = null;
                while (entryDate == null) {
                    System.out.print("Введіть дату запису (у форматі ДД.ММ.РРРР): ");
                    String dateInput = scanner.nextLine();
                    try {
                        LocalDate parsedDate = LocalDate.parse(dateInput, inputFormatter);
                        entryDate = parsedDate.atStartOfDay();
                    } catch (DateTimeParseException e) {
                        System.out.println("Некоректний формат дати. Будь ласка, спробуйте ще раз (наприклад: 15.05.2023).");
                    }
                }

                System.out.println("Введіть текст запису. Для завершення введення просто натисніть Enter на порожньому рядку:");
                String text = "";
                while (true) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        break;
                    }
                    text = text + line + "\n";
                }

                dates[count] = entryDate;
                records[count] = text;
                count++;
                System.out.println("Запис успішно додано!");

            } else if ("2".equals(choice)) {
                if (count == 0) {
                    System.out.println("Щоденник порожній. Немає що видаляти.");
                    continue;
                }

                LocalDateTime targetDate = null;
                while (targetDate == null) {
                    System.out.print("Введіть дату для видалення (ДД.ММ.РРРР): ");
                    String dateInput = scanner.nextLine();
                    try {
                        LocalDate parsedDate = LocalDate.parse(dateInput, inputFormatter);
                        targetDate = parsedDate.atStartOfDay();
                    } catch (DateTimeParseException e) {
                        System.out.println("Некоректний формат дати. Спробуйте ще раз.");
                    }
                }

                int deletedCount = 0;
                int i = 0;
                while (i < count) {
                    if (dates[i].equals(targetDate)) {
                        for (int j = i; j < count - 1; j++) {
                            dates[j] = dates[j + 1];
                            records[j] = records[j + 1];
                        }
                        dates[count - 1] = null;
                        records[count - 1] = null;
                        count--;
                        deletedCount++;
                    } else {
                        i++;
                    }
                }

                if (deletedCount > 0) {
                    System.out.println("Успішно видалено записів за цією датою: " + deletedCount);
                } else {
                    System.out.println("Записів за вказаною датою не знайдено.");
                }

            } else if ("3".equals(choice)) {
                if (count == 0) {
                    System.out.println("Щоденник порожній.");
                } else {
                    System.out.println("\n--- Всі записи ---");
                    for (int i = 0; i < count; i++) {
                        System.out.println("Дата: " + dates[i].format(outputFormatter));
                        System.out.print("Текст:\n" + records[i]);
                        System.out.println("------------------");
                    }
                }

            } else if ("0".equals(choice)) {
                System.out.println("Збереження... Вихід із щоденника. До побачення!");
                isRunning = false;
            } else {
                System.out.println("Невідома команда. Будь ласка, введіть цифру від 0 до 3.");
            }
        }
        
        scanner.close();
    }
}