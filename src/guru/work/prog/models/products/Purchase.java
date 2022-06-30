package guru.work.prog.models.products;

public abstract class Purchase {
    private String name;
    private double weight;
    private double height;
    private double price;
    private String product;

    public Purchase(String name, double weight, double height, double price, String product) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.price = price;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", price=" + price +
                ", product='" + product + '\'' +
                '}';
    }
}
