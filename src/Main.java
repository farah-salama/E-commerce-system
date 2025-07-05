package src;

import src.exceptions.EmptyCartException;
import src.exceptions.InsufficientFundsException;
import src.exceptions.InsufficientQuantityException;
import src.exceptions.ProductExpiredException;
import src.models.Customer;
import src.models.products.ConcertTicket;
import src.models.products.TV;
import src.models.products.Yoghurt;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws InsufficientQuantityException, EmptyCartException, InsufficientFundsException, ProductExpiredException {
        TV tv = new TV("LG", 15000,3,5000);
        Yoghurt yoghurt = new Yoghurt("Danone", 15, 2, LocalDate.of(2025,8,8), 50);
        ConcertTicket ticket = new ConcertTicket("Cairokee", 300, 500, LocalDate.of(2025,9,26));
        Customer tommy = new Customer("Tommy", "tommy@gmail.com", 22200.0);

        tommy.getCart().addItem(yoghurt);
        tommy.getCart().addItem(ticket);
        tommy.getCart().increaseItem(ticket);
        tommy.getCart().addItem(tv);
        tommy.checkout();
    }
}
