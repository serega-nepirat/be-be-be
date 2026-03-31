import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class mydiary {
    public static void main(String[] args) {
        int maxRecords= 100;
        LocalDateTime[] dates = new LocalDateTime[maxRecords];
        String[] texts = new String[maxRecords];
        int count=0;

        Scanner scanner=new Scanner(System.in);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("=== ВАС ВІТАЄ «МІЙ ЩОДЕННИК» ===");
        System.out.println("1. Відновити існуючий щоденник з файлу");
        System.out.println("2. Створити новий щоденник (почати з чистого аркуша)");
        System.out.print("Оберіть дію: ");
        String initChoice = scanner.nextLine();

        if("1".equals(initChoice)) {
            System.out.print("Введіть шлях до файлу (наприклад, diary.txt): ");
            String path = scanner.nextLine();
            try{
                Scanner fileScanner = new Scanner(new File(path));
                while(fileScanner.hasNextLine()&&count<maxRecords) {
                    String dateLine = fileScanner.nextLine();

                    if(dateLine.isEmpty()){
                        continue;
                    }

                    try {
                        dates[count] = LocalDateTime.parse(dateLine);

                        String content="";
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            if ("---END_RECORD---".equals(line))
                            {
                                break;
                            }
                            content = content+line+"\n";
                        }

                        texts[count]=content;
                        count++;
                    }catch(DateTimeParseException e) {
                        System.out.println("Увага: Знайдено пошкоджений запис. Дату неможливо розпізнати, запис пропущено.");
                    }
                }
                fileScanner.close();
                System.out.println("Щоденник успішно відновлено! Завантажено записів: "+count);
            } catch(FileNotFoundException e) {
                System.out.println("Файл не знайдено. Буде розпочато новий щоденник.");
            }
        } else {
            System.out.println("Створено новий порожній щоденник.");
        }


        boolean isRunning=true;
        while(isRunning) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
            System.out.println("1. Додати запис");
            System.out.println("2. Видалити запис за датою");
            System.out.println("3. Переглянути всі записи (з вибором формату)");
            System.out.println("0. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                if(count>=maxRecords) {
                    System.out.println("Помилка: Щоденник заповнений.");
                    continue;
                }

                LocalDateTime newDate=null;
                while (newDate==null) {
                    System.out.print("Введіть дату запису (у форматі ДД.ММ.РРРР): ");
                    String dateInput=scanner.nextLine();
                    try {
                        LocalDate parsedDate = LocalDate.parse(dateInput,inputFormatter);
                        newDate = parsedDate.atStartOfDay();
                    } catch(DateTimeParseException e) {
                        System.out.println("Некоректний формат дати. Будь ласка, спробуйте ще раз (напр. 25.12.2023).");
                    }
                }

                System.out.println("Введіть текст запису. Для завершення натисніть Enter на порожньому рядку:");
                String text="";
                while(true) {
                    String line = scanner.nextLine();
                    if(line.isEmpty()) {
                        break;
                    }
                    text=text+line+"\n";
                }

                dates[count]=newDate;
                texts[count]=text;
                count++;
                System.out.println("Запис успішно додано.");

            }
            else if("2".equals(choice)) {
                if (count==0) {
                    System.out.println("Щоденник порожній, видаляти нічого.");
                    continue;
                }

                LocalDateTime targetDate=null;
                while(targetDate==null) {
                    System.out.print("Введіть дату для видалення (ДД.ММ.РРРР): ");
                    String dateInput = scanner.nextLine();
                    try {
                        LocalDate parsedDate = LocalDate.parse(dateInput,inputFormatter);
                        targetDate = parsedDate.atStartOfDay();
                    } catch(DateTimeParseException e) {
                        System.out.println("Некоректний формат дати.");
                    }
                }

                int deletedCount=0;
                int i=0;
                while(i<count) {
                    if(dates[i].equals(targetDate)) {
                        for(int j=i; j<count-1; j++) {
                            dates[j]=dates[j+1];
                            texts[j]=texts[j+1];
                        }
                        dates[count-1]=null;
                        texts[count-1]=null;
                        count--;
                        deletedCount++;
                    }else{
                        i++;
                    }
                }
                System.out.println("Видалено записів: " + deletedCount);

            } else if("3".equals(choice)){
                if (count == 0) {
                    System.out.println("Щоденник порожній.");
                } else {
                    System.out.println("\nОберіть формат відображення дати у записах:");
                    System.out.println("1. Стандартний (ДД.ММ.РРРР 00:00)");
                    System.out.println("2. Альтернативний (РРРР/ММ/ДД)");
                    System.out.println("3. Ввести власний формат (наприклад: 'dd MMM yyyy' або 'EEEE, d MMMM')");
                    System.out.print("Ваш вибір: ");
                    String formatChoice=scanner.nextLine();

                    DateTimeFormatter outputFormatter=null;

                    if("1".equals(formatChoice)) {
                        outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                    } else if("2".equals(formatChoice)) {
                        outputFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    } else if("3".equals(formatChoice)) {
                        boolean validFormat=false;
                        while(!validFormat){
                            System.out.print("Введіть ваш формат: ");
                            String customPattern=scanner.nextLine();
                            try{
                                outputFormatter=DateTimeFormatter.ofPattern(customPattern);
                                validFormat=true;
                            } catch(IllegalArgumentException e) {
                                System.out.println("Помилка: неприпустимий формат. Спробуйте інший.");
                            }
                        }
                    } else {
                        System.out.println("Невідомий вибір. Застосовано стандартний формат.");
                        outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                    }

                    System.out.println("\n--- Ваші записи ---");
                    for (int i=0; i<count; i++) {
                        System.out.println("Дата: " + dates[i].format(outputFormatter));
                        System.out.print("Текст:\n"+texts[i]);
                        System.out.println("-------------------");
                    }
                }

            } else if("0".equals(choice)){
                isRunning=false;
            } else {
                System.out.println("Невідома команда. Будь ласка, оберіть пункт від 0 до 3.");
            }
        }

        System.out.println("\nБажаєте зберегти щоденник перед виходом?");
        System.out.println("1 - Так, зберегти");
        System.out.println("Будь-яка інша клавіша - Ні, просто вийти");
        System.out.print("Ваш вибір: ");
        String saveChoice=scanner.nextLine();

        if("1".equals(saveChoice)) {
            System.out.print("Введіть шлях до файлу для збереження (наприклад, diary.txt): ");
            String path=scanner.nextLine();

            try {
                FileWriter writer=new FileWriter(path);
                for(int i=0; i<count; i++) {
                    writer.write(dates[i].toString()+"\n");
                    writer.write(texts[i]);
                    writer.write("---END_RECORD---\n\n");
                }
                writer.close();
                System.out.println("Щоденник успішно збережено у файл: "+path);
            } catch(IOException e) {
                System.out.println("Сталася помилка під час збереження файлу: "+e.getMessage());
            }
        }

        System.out.println("Завершення роботи. До побачення!");
        scanner.close();
    }
}