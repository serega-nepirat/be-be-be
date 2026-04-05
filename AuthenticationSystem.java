import java.util.Scanner;
import java.util.InputMismatchException;

public class AuthSystem {

    static class UserLimitExceededException extends Throwable {
        public UserLimitExceededException(String message) { super(message); }
    }
    static class InvalidUsernameException extends Throwable {
        public InvalidUsernameException(String message) { super(message); }
    }
    static class InvalidPasswordException extends Throwable {
        public InvalidPasswordException(String message) { super(message); }
    }
    static class UserNotFoundException extends Throwable {
        public UserNotFoundException(String message) { super(message); }
    }
    static class AuthenticationFailedException extends Throwable {
        public AuthenticationFailedException(String message) { super(message); }
    }
    static class InvalidInputException extends Throwable {
        public InvalidInputException(String message) { super(message); }
    }

    private static final int MAX_USERS = 15;
    private static String[] usernames = new String[MAX_USERS];
    private static String[] passwords = new String[MAX_USERS];

    private static String[] forbiddenWords = new String[100];
    private static int forbiddenWordsCount = 0;

    public static void main(String[] args) {
        initForbiddenWords();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("Оберіть дію: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                processChoice(choice, scanner);

            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введено не число! Спробуйте ще раз.");
                scanner.nextLine();
            } catch (UserLimitExceededException e) {
                System.out.println("Помилка реєстрації: " + e.getMessage());
            } catch (InvalidUsernameException e) {
                System.out.println("Помилка імені користувача: " + e.getMessage());
            } catch (InvalidPasswordException e) {
                System.out.println("Помилка пароля: " + e.getMessage());
            } catch (UserNotFoundException e) {
                System.out.println("Помилка пошуку: " + e.getMessage());
            } catch (AuthenticationFailedException e) {
                System.out.println("Помилка доступу: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Помилка вводу: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("1.Зареєструвати нового користувача");
        System.out.println("2.Видалити користувача");
        System.out.println("3.Виконати дію (Аутентифікація)");
        System.out.println("4.Додати заборонене слово для пароля");
        System.out.println("5.Вийти з програми");
    }

    private static void processChoice(int choice, Scanner scanner) throws
            UserLimitExceededException, InvalidUsernameException, InvalidPasswordException,
            UserNotFoundException, AuthenticationFailedException, InvalidInputException {

        if (choice == 1) {
            System.out.print("Введіть ім'я користувача: ");
            String username = scanner.nextLine();
            System.out.print("Введіть пароль: ");
            String password = scanner.nextLine();
            registerUser(username, password);
            System.out.println("Користувача успішно зареєстровано!");

        } else if (choice == 2) {
            System.out.print("Введіть ім'я користувача для видалення: ");
            String username = scanner.nextLine();
            deleteUser(username);
            System.out.println("Користувача успішно видалено!");

        } else if (choice == 3) {
            System.out.print("Введіть ім'я користувача: ");
            String username = scanner.nextLine();
            System.out.print("Введіть пароль: ");
            String password = scanner.nextLine();
            authenticateUser(username, password);
            System.out.println("Користувача було аутентифіковано! Доступ дозволено.");

        } else if (choice == 4) {
            System.out.print("Введіть нове заборонене слово: ");
            String word = scanner.nextLine();
            addForbiddenWord(word);
            System.out.println("Слово додано до списку заборонених.");

        } else if (choice == 5) {
            System.out.println("Дякуємо за використання системи.До побачення!");
            System.exit(0);
        } else {
            throw new InvalidInputException("Такого пункту меню не існує.");
        }
    }

    private static void registerUser(String username, String password) throws UserLimitExceededException, InvalidUsernameException, InvalidPasswordException {
        int freeIndex = findFreeSlot();
        if (freeIndex == -1) {
            throw new UserLimitExceededException("Досягнуто ліміт. Більше 15 користувачів додати не можна.");
        }

        validateUsername(username);
        validatePassword(password);

        usernames[freeIndex] = username;
        passwords[freeIndex] = password;
    }

    private static void deleteUser(String username) throws UserNotFoundException {
        int index = findUserIndex(username);
        if (index == -1) {
            throw new UserNotFoundException("Користувача з ім'ям '" + username + "' не знайдено.");
        }

        usernames[index] = null;
        passwords[index] = null;
    }

    private static void authenticateUser(String username, String password) throws AuthenticationFailedException {
        int index = findUserIndex(username);
        if (index == -1) {
            throw new AuthenticationFailedException("Невірне ім'я користувача або пароль.");
        }

        if (!passwords[index].equals(password)) {
            throw new AuthenticationFailedException("Невірне ім'я користувача або пароль.");
        }
    }

    private static void addForbiddenWord(String word) throws InvalidInputException {
        if (word == null || word.trim().isEmpty()) {
            throw new InvalidInputException("Заборонене слово не може бути порожнім.");
        }
        if (forbiddenWordsCount >= forbiddenWords.length) {
            throw new InvalidInputException("Список заборонених слів переповнений.");
        }
        forbiddenWords[forbiddenWordsCount] = word;
        forbiddenWordsCount++;
    }

    private static void validateUsername(String username) throws InvalidUsernameException {
        if (username.length() < 5) {
            throw new InvalidUsernameException("Ім'я має складатись не менше ніж з 5 символів.");
        }
        if (username.indexOf(' ') != -1) {
            throw new InvalidUsernameException("Ім'я не має містити пробіли.");
        }
        if (findUserIndex(username) != -1) {
            throw new InvalidUsernameException("Користувач з таким ім'ям вже існує.");
        }
    }

    private static void validatePassword(String password) throws InvalidPasswordException {
        if (password.length() < 10) {
            throw new InvalidPasswordException("Довжина паролю має бути не менше 10 символів.");
        }
        if (password.indexOf(' ') != -1) {
            throw new InvalidPasswordException("Пароль не має містити пробілів.");
        }

        int digitCount = 0;
        int specialCount = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (isDigit(c)) {
                digitCount++;
            } else if (isLatinLetter(c)) {
            } else if (isSpecialChar(c)) {
                specialCount++;
            } else {
                throw new InvalidPasswordException("Пароль містить недопустимий символ: '" + c + "'. Дозволені лише латиниця, цифри та спецсимволи.");
            }
        }

        if (digitCount < 3) {
            throw new InvalidPasswordException("Пароль має містити хоча б 3 цифри.");
        }
        if (specialCount < 1) {
            throw new InvalidPasswordException("Пароль має містити хоча б 1 спеціальний символ.");
        }

        String lowerCasePassword = password.toLowerCase();
        for (int i = 0; i < forbiddenWordsCount; i++) {
            if (lowerCasePassword.indexOf(forbiddenWords[i].toLowerCase()) != -1) {
                throw new InvalidPasswordException("Пароль містить заборонене слово: " + forbiddenWords[i]);
            }
        }
    }

    private static int findFreeSlot() {
        for (int i = 0; i < MAX_USERS; i++) {
            if (usernames[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private static int findUserIndex(String username) {
        for (int i = 0; i < MAX_USERS; i++) {
            if (usernames[i] != null && usernames[i].equals(username)) {
                return i;
            }
        }
        return -1;
    }

    private static void initForbiddenWords() {
        String[] initialWords = {"admin", "pass", "password", "qwerty", "ytrewq"};
        for (int i = 0; i < initialWords.length; i++) {
            forbiddenWords[i] = initialWords[i];
            forbiddenWordsCount++;
        }
    }

    private static boolean isLatinLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    private static boolean isSpecialChar(char c) {
        String specialChars = "!@#$%^&*()-_=+[]{};:'\",.<>/?\\|`~";
        return specialChars.indexOf(c) != -1;
    }
}
