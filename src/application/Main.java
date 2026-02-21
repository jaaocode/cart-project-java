package application;

import model.entities.Product;
import model.services.CartProductsService;
import model.services.QuantityDiscountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> cart = new ArrayList<>();

        System.out.println("--- Market: ---");
        System.out.print("How many products you want to add? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.printf("\nProduct #%d data: \n", i);
            int id = i;
            System.out.print("Product name: ");
            String pName = sc.nextLine();
            System.out.print("Product price: ");
            double pPrice = sc.nextDouble();
            sc.nextLine();
            System.out.print("Product quantity: ");
            int pQuantity = sc.nextInt();
            sc.nextLine();

            Product product = new Product(id, pName, pPrice, pQuantity);
            cart.add(product);
        }

        System.out.println();
        CartProductsService productsService = new CartProductsService(new QuantityDiscountService());
        productsService.verifyCart(cart);

        System.out.println();
        System.out.print("Do you want to remove any item (y/n)? ");
        char option = sc.next().charAt(0);

        System.out.println();

        while (option == 'y') {
            System.out.print("Enter the product id who you want to remove: ");
            int removeId = sc.nextInt();
            sc.nextLine();
            productsService.removeItem(cart, removeId);

            System.out.println();

            productsService.verifyCart(cart);

            System.out.println();

            System.out.print("Do you want to remove any item (y/n)? ");
            option = sc.next().charAt(0);
        }

        productsService.processCart(cart);

        sc.close();
    }
}
