import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфон");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Комп'ютер", 51000.00, "Високопродуктивний комп'ютер для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 23000.00, "Гарний, швидкий і многофункціональний телефон", smartphones);
        Product product3 = new Product(3, "Планшет", 10000.00, "Великий єкран, швидкість і продуктивність", accessories);

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару за назвою або категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int addId = scanner.nextInt();
                    if (addId == 1) cart.addProduct(product1);
                    else if (addId == 2) cart.addProduct(product2);
                    else if (addId == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;

                case 3:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId = scanner.nextInt();
                    if (removeId == 1) cart.removeProduct(product1);
                    else if (removeId == 2) cart.removeProduct(product2);
                    else if (removeId == 3) cart.removeProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;

                case 4:
                    System.out.println(cart);
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);  // Добавляем заказ в историю
                        System.out.println("Замовлення оформлено!");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("Історія замовлень:");
                        for (Order order : orderHistory) {
                            System.out.println(order);
                            System.out.println("------------------------");
                        }
                    }
                    break;

                case 7:
                    System.out.println("Введіть назву або категорію для пошуку:");
                    scanner.nextLine();
                    String query = scanner.nextLine();
                    searchProducts(query, product1, product2, product3);
                    break;

                case 0:
                    System.out.println("Вихід...");
                    return;

                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }

    public static void searchProducts(String query, Product... products) {
        boolean found = false;
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getCategory().getName().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товари за запитом не знайдено.");
        }
    }
}
