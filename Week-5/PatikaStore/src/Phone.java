public class Phone extends  Product{
    private int camera;
    private double battery;
    private String color;

    public Phone(int productID, String productName, double price, int stock, Brand brand, int ram, int storage, double screen, int camera, double battery, String color) {
        super(productID, productName, price, stock, brand, ram, storage, screen);
        this.camera = camera;
        this.battery = battery;
        this.color = color;
    }
}
