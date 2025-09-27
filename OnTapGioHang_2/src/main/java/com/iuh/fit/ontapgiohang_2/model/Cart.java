package com.iuh.fit.ontapgiohang_2.model;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    private List<CartItem> cartItems;

    public void addItem(Product product) {
        for (CartItem cartItem : cartItems) {
            if(cartItem.getProduct().getId() == product.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    public void deleteItem(int productId) {
        cartItems.removeIf(carItem -> carItem.getProduct().getId() == productId);
    }

    public void updateItem(int productId, int quantity) {
        for (CartItem cartItem : cartItems) {
            if(cartItem.getProduct().getId() == productId) {
                if(quantity > 0) cartItem.setQuantity(quantity);
                else cartItems.remove(cartItem);
                return;
            }
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getSubTotal();
        }
        return totalPrice;
    }
}
