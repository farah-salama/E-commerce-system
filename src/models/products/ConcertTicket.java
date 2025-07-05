package src.models.products;

import java.time.LocalDate;

public class ConcertTicket extends ExpirableProduct{

    public ConcertTicket(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity, expiryDate);
    }
}
