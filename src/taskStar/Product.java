package taskStar;

import java.util.concurrent.Semaphore;

class Product {
    private static final int MAX_PRODUCTS = 3;
    private int count = 0;
    private final Semaphore productSem = new Semaphore(MAX_PRODUCTS, true);

    public void produce() {
        try {
            productSem.acquire();
            synchronized (this) {
                if (count < 5) {
                    count++;
                    System.out.println("Производитель произвел продукт. Всего в магазине: " + count);
                } else {
                    System.out.println("Производитель больше не может производить продукты.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            productSem.release();
        }
    }

    public void consume() {
        try {
            productSem.acquire();
            synchronized (this) {
                if (count > 0) {
                    count--;
                    System.out.println("Покупатель купил продукт. Осталось в магазине: " + count);
                } else {
                    System.out.println("Нет продуктов для покупки.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            productSem.release();
        }
    }
}