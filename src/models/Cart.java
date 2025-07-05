package src.models;

import src.exceptions.InsufficientQuantityException;
import src.models.CartItem;
import src.models.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public void addItem(Product product) throws InsufficientQuantityException {
        for (CartItem item:this.cartItems) {
            if (item.getProduct().equals(product)){
                item.increaseQuantity();
                return;
            }
        }
        this.cartItems.add(new CartItem(product));
    }

    public boolean removeItem(Product product) {
        for (CartItem item:this.cartItems) {
            if (item.getProduct().equals(product)){
                this.cartItems.remove(item);
                return true;
            }
        }
        // item not found
        return false;
    }

    public void increaseItem(Product product) throws InsufficientQuantityException {
        this.addItem(product);
    }

    public boolean reduceItem(Product product) {
        for (CartItem item:this.cartItems) {
            if (item.getProduct().equals(product)){
                boolean decreased = item.decreaseQuantity();
                if(!decreased) {
                    this.cartItems.remove(item);
                    return true;
                }
            }
        }
        // item not found
        return false;
    }

    public boolean isShippable() {
        for (CartItem item:this.cartItems) {
            if (item.isShippable()){
                return true;
            }
        }
        return false;
    }

    public double getSubTotal() {
        double subtotal = 0;
        for (CartItem item:this.cartItems) {
            subtotal += item.getPrice();
        }
        return subtotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}