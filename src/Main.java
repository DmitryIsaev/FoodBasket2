import java.io.File;
import java.util.Scanner;
import java.io.IOException;

class Main {
    static File saveFile = new File("basket.txt");

    public static void main(String[] args) {
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Scanner scanner = new Scanner(System.in);
        Basket basket = null;

        if (saveFile.exists()) {
            basket = Basket.loadFromTxtFile(saveFile);
        } else {
            basket = new Basket(products, prices);
        }

        listProducts(products, prices);
        addingInformation(products, scanner, basket);
        basket.printCart();
    }

    private static void addingInformation(String[] products, Scanner scanner, Basket basket) {
        int productCount;
        int productNumber;
        while (true) {
            System.out.println("Выберите номер товара и количество или введите `end`");
            try {
                String inputString = scanner.nextLine();
                if (inputString.equals("end")) {
                    break;
                }

                String[] parts = inputString.split(" ");

                if (parts.length != 2) {
                    System.out.println("Ошибка 1: неправильные данные!");
                    continue;
                }

                if (Integer.parseInt(parts[0]) < 1 || Integer.parseInt(parts[0]) > products.length) {
                    System.out.println("Ошибка 2: нет такого номера товара!");
                    continue;
                }

                if (Integer.parseInt(parts[1]) < 0) {
                    System.out.println("Ошибка 3: нельзя вводить отрицательное количество товара!");
                    continue;
                }

                productNumber = Integer.parseInt(parts[0]) - 1;
                productCount = Integer.parseInt(parts[1]);
                basket.addToCart(productNumber, productCount);
                basket.saveTxt(saveFile);
            } catch (NumberFormatException | IOException error) {
                System.out.println("Ошибка 4: введены буквы вместо чисел");
            }
        }
    }

    private static void listProducts(String[] products, int[] prices) {
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт.");
        }
    }
}