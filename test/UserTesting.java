package test;

import src.models.Customer;
import src.models.products.TV;
import src.models.products.Yoghurt;
import src.models.products.ConcertTicket;
import src.exceptions.EmptyCartException;
import src.exceptions.InsufficientFundsException;
import src.exceptions.InsufficientQuantityException;
import src.exceptions.ProductExpiredException;

import java.time.LocalDate;

public class UserTesting {
    public static void main(String[] args) throws Exception {
        //Test valid customer creation
        Customer user = new Customer("Alice", "alice@gmail.com", 1000.0);
        System.out.println("User: " + user.getName() + ", Email: " + user.getEmail() + ", Balance: " + user.getBalance());

        //Test invalid email
        try{
            Customer invalidEmailUser = new Customer("Bob", "bob#gmail.com");
        } catch (Exception e){
            System.out.println("Invalid Email error: " + e.getMessage());
        }

        //Test balance
        user.increaseBalance(500);
        System.out.println("Increased balance: " + user.getBalance());
        try {
            user.reduceBalance(200);
            System.out.println("Reduced balance: " + user.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds error: " + e.getMessage());
        }

        //Test insufficient funds
        try {
            user.reduceBalance(2000);
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds error: " + e.getMessage());
        }

        //Test cart and checkout
        TV tv = new TV("Samsung", 500, 2, 3000);
        Yoghurt yoghurt = new Yoghurt("Danone", 10, 1, LocalDate.of(2025, 8, 8), 50);
        ConcertTicket ticket = new ConcertTicket("Concert", 100, 1, LocalDate.of(2020, 1, 1)); // expired

        try {
            user.getCart().addItem(tv);
            user.getCart().addItem(yoghurt);
            System.out.println("Cart subtotal: " + user.getCart().getSubTotal());
        } catch (InsufficientQuantityException e) {
            System.out.println("Add item error: " + e.getMessage());
        }

        //Checkout with expired product
        try {
            user.getCart().addItem(ticket);
            user.checkout();
        } catch (ProductExpiredException e) {
            System.out.println("Checkout error (expired): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }

        //Checkout with empty cart
        Customer user2 = new Customer("Eve", "eve@gmail.com", 1000.0);
        try {
            user2.checkout();
        } catch (EmptyCartException e) {
            System.out.println("Checkout error (empty cart): " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Checkout error: " + e.getMessage());
        }
    }
}