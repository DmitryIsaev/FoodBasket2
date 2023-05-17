import java.io.*;
import java.util.Arrays;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] quantity;

    public Basket() {
    }

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.quantity = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        quantity[productNum] += amount;
    }

    public void printCart() {
        int sumProducts = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < quantity.length; i++) {
            if (quantity[i] > 0) {
                System.out.println(products[i] + " " + quantity[i] + " шт. " + prices[i] + " руб/шт "
                        + (quantity[i] * prices[i]) + " руб в сумме");
                sumProducts += quantity[i] * prices[i];
            }
        }
        System.out.println("Итоговая сумма: " + sumProducts);
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile);) {
            for (String product : products) {
                out.print(product + " ");
            }
            out.println();

            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();

            for (int qty : quantity) {
                out.print(qty + " ");
            }
        }
    }

    static Basket loadFromTxtFile(File textFile) {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String productsTxt = bufferedReader.readLine();
            String pricesTxt = bufferedReader.readLine();
            String quantityTxt = bufferedReader.readLine();

            basket.products = productsTxt.split(" ");
            basket.prices = Arrays.stream(pricesTxt.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();

            basket.quantity = Arrays.stream(quantityTxt.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return basket;
    }
}


