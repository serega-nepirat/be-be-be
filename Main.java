import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyVector<Integer> vector = new MyVector<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Демонстрація роботи");

        while (running) {
            System.out.println("\nПоточний вектор: " + vector);
            System.out.println("Size: " + vector.size() + " | Capacity: " + vector.capacity());
            System.out.println("Меню: 1. Додати в кінець 2. Додати за індексом 3. Видалити 4. Отримати 5. Вихід");
            System.out.print("Вибір: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Введіть число: ");
                        vector.add(scanner.nextInt());
                        break;
                    case 2:
                        System.out.print("Введіть індекс та число: ");
                        vector.add(scanner.nextInt(), scanner.nextInt());
                        break;
                    case 3:
                        System.out.print("Введіть індекс для видалення: ");
                        vector.remove(scanner.nextInt());
                        break;
                    case 4:
                        System.out.print("Введіть індекс: ");
                        int idx = scanner.nextInt();
                        System.out.println("Елемент за індексом " + idx + " = " + vector.get(idx));
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Невірний вибір!");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
                scanner.nextLine();
            }
        }
        System.out.println("Програму завершено.");
    }
}