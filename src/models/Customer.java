package src.models;

import src.exceptions.EmptyCartException;
import src.exceptions.InsufficientFundsException;
import src.exceptions.InsufficientQuantityException;
import src.exceptions.ProductExpiredException;
import src.models.Cart;
import src.services.CheckoutService;
import src.services.EmailValidator;
public class Customer {
    private String name;
    private String email;
    private Cart cart;
    private double balance;

    public Customer(String name, String email) throws Exception{
        if(EmailValidator.isValid(email)) {
            this.name = name;
            this.email = email;
            this.cart = new Cart();
            this.balance = 0;
        } else {
            throw new Exception("Invalid User Email");
        }
    }
    public Customer(String name, String email, double balance) throws Exception{
        this(name,email);
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void increaseBalance(double balance){
        this.balance += balance;
    }

    public void reduceBalance(double balance) throws InsufficientFundsException {
        if (balance > this.balance) throw new InsufficientFundsException("Insufficient balance in " + this.name + "'s Account");
        this.balance -= balance;
    }

    public Cart getCart() {
        return cart;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public double getBalance(){
        return this.balance;
    }

    public void checkout() throws InsufficientFundsException, InsufficientQuantityException, ProductExpiredException, EmptyCartException {
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.checkout(cart, this);
        cart = new Cart();
    }
}