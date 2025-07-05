package src.models.products;

import src.interfaces.Shippable;

import java.time.LocalDate;

public class Yoghurt extends ExpirableProduct implements Shippable {
    private double weight;

    public Yoghurt(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
