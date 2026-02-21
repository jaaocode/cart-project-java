package model.services;

import model.entities.Product;

import java.util.List;

public class CartProductsService {

    private DiscountService discountService;

    public CartProductsService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void verifyCart(List<Product> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("Cart shouldn't be empty");
        }

        System.out.println("--- Cart items ---");
        for (Product items : list) {
            System.out.println(items);
        }
        System.out.println("--- Cart items ---");
    }

    public void removeItem(List<Product> list, int id) {
        if (list.isEmpty()) {
            throw new IllegalStateException("Cart shouldn't be empty");
        }

        boolean removed = list.removeIf(product -> product.getId() == id);

        if (!removed) {
            System.out.println("O produto com id: " + id + ", não existe!");
        }

    }

    public void processCart(List<Product> list) {
        double grossTotal = 0.0;
        boolean hasDiscount = false;

        for (Product items : list) {

            grossTotal += items.getPrice() * items.getQuantity();

            if (items.getQuantity() >= 3) {
                hasDiscount = true;
            }

        }

        double total;
        double totalApplied = 0.0;

        if (hasDiscount) {
            total = discountService.apply(grossTotal);
            totalApplied = grossTotal - total;
        }
        else {
            total = grossTotal;
        }
        System.out.printf("Applied discount: R$%.2f\n", totalApplied);
        System.out.printf("Total value: RS$%.2f", total);
    }
}
