import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Calculator calculator = new Calculator();
            System.out.println("Введіть перше число:");
            double num1 = Double.parseDouble(scanner.nextLine());

            System.out.println("Введіть друге число:");
            double num2 = Double.parseDouble(scanner.nextLine());

            System.out.println("Оберіть операцію (+, -, *, /, sqrt):");
            String operation = scanner.nextLine();

            double result = 0;

            switch (operation) {
                case "+":
                    result = calculator.add(num1, num2);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2);
                    break;
                case "*":
                    result = calculator.multiply(num1, num2);
                    break;
                case "/":
                    result = calculator.divide(num1, num2);
                    break;
                case "sqrt":
                    System.out.println("Для якої цифри обчислити корінь (1 або 2)?");
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        result = calculator.squareRoot(num1);
                    } else if (choice == 2) {
                        result = calculator.squareRoot(num2);
                    } else {
                        throw new InvalidInputException("Невірний вибір для квадратного кореня.");
                    }
                    break;
                default:
                    throw new InvalidInputException("Невідома операція: " + operation);
            }

            System.out.println("Результат: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: введено некоректне число.");
        } catch (ArithmeticException | InvalidInputException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        } finally {
            System.out.println("Операція завершена.");
        }
    }
}
