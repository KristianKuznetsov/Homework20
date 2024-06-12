package taskStar;

class Producer extends Thread {
    private final Product product;

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            product.produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
