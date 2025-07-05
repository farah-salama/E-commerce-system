package src.services;

import src.exceptions.EmptyCartException;
import src.exceptions.InsufficientFundsException;
import src.exceptions.InsufficientQuantityException;
import src.exceptions.ProductExpiredException;
import src.models.Cart;
import src.models.CartItem;
import src.models.Customer;
import src.models.products.ExpirableProduct;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(){
        this.shippingService = new ShippingService();
    }

    public void checkout(Cart cart, Customer customer) throws InsufficientFundsException, InsufficientQuantityException, ProductExpiredException, EmptyCartException {
        if(cart.getCartItems().isEmpty()) throw new EmptyCartException();
        this.checkExpiredProducts(cart);
        double amount = calculateAmount(cart);
        customer.reduceBalance(amount);
        this.shippingService.printShipmentDetails(cart);
        this.printCheckoutReceipt(cart);
        System.out.println(customer.getName() + "'s current balance = " + customer.getBalance());
    }

    private void checkExpiredProducts(Cart cart) throws ProductExpiredException {
        for (CartItem item: cart.getCartItems()) {
            if(item.getProduct() instanceof ExpirableProduct) {
                if(((ExpirableProduct)item.getProduct()).isExpired()) throw new ProductExpiredException(item.getProduct().getName() + "is expired.");
            }
        }
    }

    private double calculateAmount(Cart cart){
        double totalPrice = 0;
        for (CartItem item: cart.getCartItems()) {
            int count = item.getQuantity();
            double price = item.getProduct().getPrice();
            double subtotalPrice = price*count;
            totalPrice+= subtotalPrice;
        }
        return totalPrice;
    }

    private void printCheckoutReceipt(Cart cart) throws InsufficientQuantityException {
        System.out.println("** Checkout receipt ** ");
        double totalPrice = 0;
        for (CartItem item: cart.getCartItems()) {
                int count = item.getQuantity();
                item.getProduct().reduceQuantity(item.getQuantity());
                String name = item.getProduct().getName();
                double price = item.getProduct().getPrice();
                System.out.print(count);
                System.out.print("x " + name + "\t\t\t");
                double subtotalPrice = price*count;
                System.out.println(subtotalPrice);
                totalPrice+= subtotalPrice;
        }
        System.out.println("--------------------------");
        System.out.printf("Subtotal\t\t\t%.2f\n", totalPrice);
        System.out.printf("Shipping\t\t\t%.2f\n", this.shippingService.calculateShipping(cart));
        double amount = totalPrice + this.shippingService.calculateShipping(cart);
        System.out.printf("Amount\t\t\t%.2f\n", amount);
    }
}
