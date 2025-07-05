package src.models;

import src.exceptions.InsufficientQuantityException;
import src.interfaces.Shippable;
import src.models.products.Product;

public class CartItem {
    private Product product;
    private boolean shippable;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.shippable = product instanceof Shippable;
        this.quantity = quantity;
    }

    public CartItem(Product product) {
        this.product = product;
        this.shippable = product instanceof Shippable;
        this.quantity = 1;
    }

    public void increaseQuantity() throws InsufficientQuantityException {
        if(this.product.getQuantity()<= this.quantity)
            throw new InsufficientQuantityException("Only " + this.product.getQuantity() + " of " + this.product.getName() + " is available.");
        this.quantity++;
    }

    public boolean decreaseQuantity() {
        this.quantity--;
        return this.quantity > 0;
    }

    public boolean isShippable() {
        return this.shippable;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.quantity*this.product.getPrice();
    }

    public Product getProduct() {
        return product;
    }
}
