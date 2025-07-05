package src.models.products;

import src.interfaces.Shippable;

import java.time.LocalDate;

public class Bread extends ExpirableProduct implements Shippable {
    private double weight;

    public Bread(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
