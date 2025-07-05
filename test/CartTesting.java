package test;

import src.models.Cart;
import src.models.products.TV;
import src.models.products.Yoghurt;
import src.models.products.ConcertTicket;
import src.exceptions.InsufficientQuantityException;

import java.time.LocalDate;

public class CartTesting {
    public static void main(String[] args) {
        try {
            Cart cart = new Cart();

            //Test isEmpty
            System.out.println("Cart is empty? " + cart.isEmpty());

            //Add products
            TV tv = new TV("Sony", 1000, 2, 5000);
            Yoghurt yoghurt = new Yoghurt("Danone", 10, 3, LocalDate.of(2025, 8, 8), 50);
            ConcertTicket ticket = new ConcertTicket("Concert", 200, 5, LocalDate.of(2025, 9, 26));

            cart.addItem(tv);
            cart.addItem(yoghurt);
            cart.addItem(ticket);

            System.out.println("Cart is empty after adding? " + cart.isEmpty());
            System.out.println("Cart subtotal: " + cart.getSubTotal());

            //Add same product again
            cart.addItem(tv);
            System.out.println("Added TV again. Cart subtotal: " + cart.getSubTotal());

            //add more than available quantity
            try {
                cart.addItem(tv);
            } catch (InsufficientQuantityException e) {
                System.out.println("InsufficientQuantityException: " + e.getMessage());
            }

            //Remove item
            boolean removed = cart.removeItem(yoghurt);
            System.out.println("Removed yoghurt? " + removed);

            //Remove non-existent item
            boolean removedAgain = cart.removeItem(yoghurt);
            System.out.println("Removed yoghurt again? " + removedAgain);

            //Increase item quantity (should fail for TV)
            try {
                cart.increaseItem(tv);
            } catch (InsufficientQuantityException e) {
                System.out.println("IncreaseItem InsufficientQuantityException: " + e.getMessage());
            }

            //Reduce item quantity (should remove TV from cart)
            boolean reduced = cart.reduceItem(tv);
            System.out.println("Reduced TV quantity, removed from cart? " + reduced);

            //Reduce item quantity for ticket (should not remove)
            boolean reducedTicket = cart.reduceItem(ticket);
            System.out.println("Reduced ticket quantity, removed from cart? " + reducedTicket);

            //Check shippable
            System.out.println("Cart is shippable? " + cart.isShippable());

            //Final subtotal
            System.out.println("Final cart subtotal: " + cart.getSubTotal());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
