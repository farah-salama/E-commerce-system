package src.models.products;
import src.interfaces.Expirable;
import src.models.products.Product;
import java.time.LocalDate;
public abstract class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiryDate;
    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return !LocalDate.now().isBefore(this.expiryDate);
    }
}
