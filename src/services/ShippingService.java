package src.services;

import src.interfaces.Shippable;
import src.models.Cart;
import src.models.CartItem;

public class ShippingService {
    private double shippingCost = 30;
    public double calculateShipping(Cart cart){
        if(isShippable(cart)) return shippingCost;
        return 0;
    }

    public boolean isShippable(Cart cart){
        for (CartItem item: cart.getCartItems()) {
            if(item.getProduct() instanceof Shippable) {
                return true;
            }
        }
        return false;
    }
    public void printShipmentDetails(Cart cart) {
        System.out.println("** Shipment notice ** ");
        double totalWeight = 0;
        for (CartItem item: cart.getCartItems()) {
            if(item.isShippable()) {
                int count = item.getQuantity();
                String name = item.getProduct().getName();
                double weight = ((Shippable)item.getProduct()).getWeight();
                System.out.print(count);
                System.out.print("x " + name + "\t\t\t");
                double subtotalWeight = weight*count;
                if(subtotalWeight>1000) {
                    System.out.print(subtotalWeight/1000);
                    System.out.println("kg");
                } else {
                    System.out.print(subtotalWeight);
                    System.out.println("g");
                }
                totalWeight += subtotalWeight;
            }
        }
        System.out.print("Total package weight ");
        if(totalWeight>1000) {
            System.out.printf("%.2fkg\n\n", totalWeight/1000);
        } else {
            System.out.printf("%.2fg\n\n", totalWeight);
        }
    }
}
