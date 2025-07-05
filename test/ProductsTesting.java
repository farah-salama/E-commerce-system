package test;

import src.models.products.TV;
import src.models.products.Yoghurt;
import src.models.products.ConcertTicket;
import src.models.products.MobileScratchCards;
import java.time.LocalDate;

// ...existing code...

public class ProductsTesting {
    public static void main(String[] args) throws Exception {
        // TV
        TV tv = new TV("LG", 25000, 5, 5000);
        System.out.println("TV: " + tv.getName() + ", Price: " + tv.getPrice() + ", Qty: " + tv.getQuantity() + ", Weight: " + tv.getWeight());

        // Edge case: Negative price
        try {
            tv.setPrice(-1000);
        } catch (Exception e) {
            System.out.println("TV negative price error: " + e.getMessage());
        }

        // Edge case: Negative weight
        try {
            tv.setWeight(-10);
        } catch (Exception e) {
            System.out.println("TV negative weight error: " + e.getMessage());
        }

        // Yoghurt
        Yoghurt yoghurt = new Yoghurt("Danone", 15, 10, LocalDate.of(2025, 8, 8), 50);
        System.out.println("Yoghurt: " + yoghurt.getName() + ", Price: " + yoghurt.getPrice() + ", Qty: " + yoghurt.getQuantity() + ", Expiry: " + yoghurt.getExpiryDate() + ", Weight: " + yoghurt.getWeight());
        System.out.println("Yoghurt expired? " + yoghurt.isExpired());

        // Edge case: Expired yoghurt
        Yoghurt expiredYoghurt = new Yoghurt("Old Danone", 10, 5, LocalDate.of(2020, 1, 1), 50);
        System.out.println("Expired Yoghurt expired? " + expiredYoghurt.isExpired());

        // ConcertTicket
        ConcertTicket ticket = new ConcertTicket("Cairokee", 300, 500, LocalDate.of(2025, 9, 26));
        System.out.println("ConcertTicket: " + ticket.getName() + ", Price: " + ticket.getPrice() + ", Qty: " + ticket.getQuantity() + ", Expiry: " + ticket.getExpiryDate());
        System.out.println("Ticket expired? " + ticket.isExpired());

        // Edge case: Expired ticket
        ConcertTicket expiredTicket = new ConcertTicket("Old Concert", 100, 10, LocalDate.of(2020, 1, 1));
        System.out.println("Expired Ticket expired? " + expiredTicket.isExpired());

        // MobileScratchCards
        MobileScratchCards card = new MobileScratchCards("Vodafone 100", 100, 50);
        System.out.println("MobileScratchCards: " + card.getName() + ", Price: " + card.getPrice() + ", Qty: " + card.getQuantity());

        // Edge case: Negative quantity
        try {
            card.setQuantity(-5);
        } catch (Exception e) {
            System.out.println("MobileScratchCards negative quantity error: " + e.getMessage());
        }

        // Test setters
        tv.setPrice(24000);
        tv.setWeight(4800);
        System.out.println("Updated TV price: " + tv.getPrice() + ", weight: " + tv.getWeight());

        yoghurt.setWeight(60);
        System.out.println("Updated Yoghurt weight: " + yoghurt.getWeight());

        // Edge case: Reduce quantity below zero
        try {
            yoghurt.setQuantity(0);
            yoghurt.setQuantity(yoghurt.getQuantity() - 1);
        } catch (Exception e) {
            System.out.println("Yoghurt negative quantity error: " + e.getMessage());
        }
    }
}