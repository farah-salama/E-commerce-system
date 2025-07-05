package test;

import src.models.Cart;
import src.models.Customer;
import src.models.products.TV;
import src.models.products.Yoghurt;
import src.models.products.ConcertTicket;
import src.services.CheckoutService;
import src.services.EmailValidator;
import src.services.ShippingService;
import src.exceptions.EmptyCartException;
import src.exceptions.InsufficientFundsException;
import src.exceptions.InsufficientQuantityException;
import src.exceptions.ProductExpiredException;

import java.time.LocalDate;

public class ServicesTesting {
    public static void main(String[] args) {
        //EmailValidator tests
        System.out.println("EmailValidator Tests:");
        System.out.println("Valid: alice@gmail.com -> " + EmailValidator.isValid("alice@gmail.com"));
        System.out.println("Invalid: bob#gmail.com -> " + EmailValidator.isValid("bob#gmail.com"));
        System.out.println("Invalid: null -> " + EmailValidator.isValid(null));
        System.out.println("Valid: john.doe@company.co.uk -> " + EmailValidator.isValid("john.doe@company.co.uk"));
        System.out.println();

        //ShippingService tests
        System.out.println("ShippingService Tests:");
        Cart cart = new Cart();
        TV tv = new TV("LG", 2000, 2, 5000);
        Yoghurt yoghurt = new Yoghurt("Danone", 10, 3, LocalDate.of(2025, 8, 8), 50);
        ConcertTicket ticket = new ConcertTicket("Concert", 100, 1, LocalDate.of(2025, 9, 26));
        try {
            cart.addItem(tv);
            cart.addItem(yoghurt);
            cart.addItem(ticket);
        } catch (InsufficientQuantityException e) {
            System.out.println("Add item error: " + e.getMessage());
        }
        ShippingService shippingService = new ShippingService();
        System.out.println("Cart is shippable? " + shippingService.isShippable(cart));
        System.out.println("Shipping cost: " + shippingService.calculateShipping(cart));
        shippingService.printShipmentDetails(cart);
        System.out.println();

        //Remove shippable items
        cart.removeItem(tv);
        cart.removeItem(yoghurt);
        System.out.println("Cart is shippable after removing shippable items? " + shippingService.isShippable(cart));
        System.out.println("Shipping cost: " + shippingService.calculateShipping(cart));
        shippingService.printShipmentDetails(cart);
        System.out.println();

        //CheckoutService tests
        System.out.println("CheckoutService Tests:");
        try {
            Customer customer = new Customer("Alice", "alice@gmail.com", 10000);
            Cart checkoutCart = new Cart();
            TV tv2 = new TV("Samsung", 3000, 1, 4000);
            Yoghurt yoghurt2 = new Yoghurt("Activia", 12, 2, LocalDate.of(2025, 8, 8), 60);
            checkoutCart.addItem(tv2);
            checkoutCart.addItem(yoghurt2);

            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(checkoutCart, customer);
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }

        //Empty cart
        try {
            Customer customer2 = new Customer("Bob", "bob@gmail.com", 5000);
            Cart emptyCart = new Cart();
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(emptyCart, customer2);
        } catch (EmptyCartException e) {
            System.out.println("EmptyCartException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }

        //Expired product
        try {
            Customer customer3 = new Customer("Eve", "eve@gmail.com", 5000);
            Cart expiredCart = new Cart();
            ConcertTicket expiredTicket = new ConcertTicket("Old Concert", 100, 1, LocalDate.of(2020, 1, 1));
            expiredCart.addItem(expiredTicket);
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(expiredCart, customer3);
        } catch (ProductExpiredException e) {
            System.out.println("ProductExpiredException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }

        //Insufficient funds
        try {
            Customer customer4 = new Customer("Tom", "tom@gmail.com", 50);
            Cart expensiveCart = new Cart();
            TV expensiveTV = new TV("Expensive TV", 1000, 1, 8000);
            expensiveCart.addItem(expensiveTV);
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(expensiveCart, customer4);
        } catch (InsufficientFundsException e) {
            System.out.println("InsufficientFundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }
    }
}
