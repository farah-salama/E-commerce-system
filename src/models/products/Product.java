package src.models.products;

import src.exceptions.InsufficientQuantityException;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) throws Exception {
        if(price<0) throw new Exception("Price cannot be below zero");
        this.price = price;
    }

    public void setQuantity(int quantity) throws Exception {
        if(quantity<0) throw new Exception("Quantity cannot be below zero");
        this.quantity = quantity;
    }

    public void reduceQuantity(int quantity) throws InsufficientQuantityException {
        if(quantity>this.quantity) throw new InsufficientQuantityException("Only " + this.quantity + " of " + this.name + " is available.");
    }
}
