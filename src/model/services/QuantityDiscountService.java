package model.services;

public class QuantityDiscountService implements DiscountService {
    @Override
    public double apply(double total) {
        double discountAmount = 0.15;
        return total - (total * discountAmount);
    }
}
