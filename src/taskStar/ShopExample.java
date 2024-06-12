package taskStar;

public class ShopExample {
    public static void solve() {
        Product product = new Product();
        Producer producer = new Producer(product);
        Consumer consumer = new Consumer(product);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}
