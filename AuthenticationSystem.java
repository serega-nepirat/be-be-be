import java.util.Scanner;

class MaximumUsersReachedException extends Exception {
    public MaximumUsersReachedException(String message) { super(message); }
}
class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) { super(message); }
}
class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) { super(message); }
}
class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) { super(message); }
}
class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) { super(message); }
}
class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException(String message) { super(message); }
}
class MenuInputException extends Exception {
    public MenuInputException(String message) { super(message); }
}

public class AuthenticationSystem {
    private static final int MAX_USERS = 15;
    private static String[] usernames = new String[MAX_USERS];
    private static String[] passwords = new String[MAX_USERS];
    private static String[] forbiddenWords = new String[50];
    private static int forbiddenCount = 0;

    public static void main(String[] args) {
        initForbiddenWords();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String input = scanner.nextLine();

            try {
                int choice = parseNumber(input);
                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        deleteUser(scanner);
                        break;
                    case 3:
                        authenticateUser(scanner);
                        break;
                    case 4:
                        addForbiddenWord(scanner);
                        break;
                    case 5:
                        System.out.println("Завершення роботи програми...");
                        running = false;
                        break;
                    default:
                        throw new MenuInputException("Оберіть пункт меню від 1 до 5.");
                }
            } catch (MenuInputException | InvalidUsernameException | InvalidPasswordException |
                     MaximumUsersReachedException | UserAlreadyExistsException |
                     UserNotFoundException | AuthenticationFailedException e) {
                System.out.println("\n[ПОМИЛКА] " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }


    private static void printMenu() {
        System.out.println("=== СИСТЕМА АУТЕНТИФІКАЦІЇ ===");
        System.out.println("1. Зареєструвати нового користувача");
        System.out.println("2. Видалити користувача");
        System.out.println("3. Виконати дію (Аутентифікація)");
        System.out.println("4. Додати заборонене слово для паролів (*)");
        System.out.println("5. Вихід");
        System.out.print("Ваш вибір: ");
    }

    private static int parseNumber(String str) throws MenuInputException {
        if (str == null || str.length() == 0) {
            throw new MenuInputException("Ввід не може бути порожнім.");
        }
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                throw new MenuInputException("Потрібно ввести число.");
            }
            result = result * 10 + (c - '0');
        }
        return result;
    }


    private static void registerUser(Scanner scanner) throws MaximumUsersReachedException, InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        int emptySlot = findEmptySlot();
        if (emptySlot == -1) {
            throw new MaximumUsersReachedException("Досягнуто ліміт користувачів (15). Видаліть когось для реєстрації нового.");
        }

        System.out.print("Введіть ім'я користувача: ");
        String username = scanner.nextLine();
        validateUsername(username);

        if (isUserExists(username)) {
            throw new UserAlreadyExistsException("Користувач з таким ім'ям вже існує.");
        }

        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();
        validatePassword(password);

        usernames[emptySlot] = username;
        passwords[emptySlot] = password;
        System.out.println("\n[УСПІХ] Користувача " + username + " успішно зареєстровано!\n");
    }

    private static void deleteUser(Scanner scanner) throws UserNotFoundException, InvalidUsernameException {
        System.out.print("Введіть ім'я користувача для видалення: ");
        String username = scanner.nextLine();
        validateUsername(username);

        int index = findUserIndex(username);
        if (index == -1) {
            throw new UserNotFoundException("Користувача з ім'ям '" + username + "' не знайдено.");
        }

        usernames[index] = null;
        passwords[index] = null;
        System.out.println("\n[УСПІХ] Користувача видалено. З'явилось нове вільне місце.\n");
    }

    private static void authenticateUser(Scanner scanner) throws InvalidUsernameException, AuthenticationFailedException {
        System.out.print("Логін: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        int index = findUserIndex(username);

        if (index == -1 || !passwords[index].equals(password)) {
            throw new AuthenticationFailedException("Неправильне ім'я користувача або пароль.");
        }

        System.out.println("\n[УСПІХ] Користувача " + username + " успішно аутентифіковано. Доступ до дії дозволено!\n");
    }


    private static void initForbiddenWords() {
        forbiddenWords[0] = "admin";
        forbiddenWords[1] = "pass";
        forbiddenWords[2] = "password";
        forbiddenWords[3] = "qwerty";
        forbiddenWords[4] = "ytrewq";
        forbiddenCount = 5;
    }

    private static void addForbiddenWord(Scanner scanner) throws MenuInputException {
        if (forbiddenCount >= forbiddenWords.length) {
            throw new MenuInputException("Досягнуто ліміт заборонених слів.");
        }
        System.out.print("Введіть нове заборонене слово: ");
        String word = scanner.nextLine();
        if (word.length() == 0 || containsSpace(word)) {
            throw new MenuInputException("Слово не може бути порожнім або містити пробіли.");
        }
        forbiddenWords[forbiddenCount] = word.toLowerCase();
        forbiddenCount++;
        System.out.println("\n[УСПІХ] Заборонене слово додано.\n");
    }

    private static void validateUsername(String username) throws InvalidUsernameException {
        if (username == null || username.length() < 5) {
            throw new InvalidUsernameException("Ім'я користувача має складатись не менше ніж з 5 символів.");
        }
        if (containsSpace(username)) {
            throw new InvalidUsernameException("Ім'я користувача не має містити пробілів.");
        }
    }

    private static void validatePassword(String password) throws InvalidPasswordException {
        if (password == null || password.length() < 10) {
            throw new InvalidPasswordException("Довжина паролю має бути не менше 10 символів.");
        }
        if (containsSpace(password)) {
            throw new InvalidPasswordException("Пароль не має містити пробілів.");
        }

        int digitsCount = 0;
        int specialCount = 0;
        String specialChars = "!@#$%^&*()-_=+[]{};:'\",.<>/?\\|`~";

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (c >= '0' && c <= '9') {
                digitsCount++;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {

            } else if (customIndexOf(specialChars, c) != -1) {
                specialCount++;
            } else {
                throw new InvalidPasswordException("Пароль містить недопустимий символ: '" + c + "'. Дозволені лише латинські символи, цифри та спецсимволи.");
            }
        }

        if (digitsCount < 3) {
            throw new InvalidPasswordException("Пароль має містити хоча б 3 цифри.");
        }
        if (specialCount < 1) {
            throw new InvalidPasswordException("Пароль має містити хоча б 1 спеціальний символ.");
        }

        String lowerPassword = password.toLowerCase();
        for (int i = 0; i < forbiddenCount; i++) {
            if (customContains(lowerPassword, forbiddenWords[i])) {
                throw new InvalidPasswordException("Пароль містить заборонене слово: " + forbiddenWords[i]);
            }
        }
    }


    private static boolean containsSpace(String str) {
        return customIndexOf(str, ' ') != -1;
    }

    private static int customIndexOf(String str, char target) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) return i;
        }
        return -1;
    }

    private static boolean customContains(String mainStr, String subStr) {
        if (subStr.length() > mainStr.length()) return false;

        for (int i = 0; i <= mainStr.length() - subStr.length(); i++) {
            boolean match = true;
            for (int j = 0; j < subStr.length(); j++) {
                if (mainStr.charAt(i + j) != subStr.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;
    }

    private static int findEmptySlot() {
        for (int i = 0; i < MAX_USERS; i++) {
            if (usernames[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isUserExists(String username) {
        return findUserIndex(username) != -1;
    }

    private static int findUserIndex(String username) {
        for (int i = 0; i < MAX_USERS; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return i;
            }
        }
        return -1;
    }
}