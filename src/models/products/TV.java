package src.models.products;

import src.interfaces.Shippable;

public class TV extends Product implements Shippable {
    private double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) throws Exception {
        if(weight<0) throw new Exception("Weight cannot be below zero");
        this.weight = weight;
    }
}
