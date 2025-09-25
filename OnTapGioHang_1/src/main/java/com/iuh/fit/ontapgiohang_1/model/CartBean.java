package com.iuh.fit.ontapgiohang_1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartBean {
    List<CartItemBean> cartItemBeanList;

    // Thêm
    public void addProduct(Product product) {
        for (CartItemBean cartItemBean : cartItemBeanList) {
            if (cartItemBean.getProduct().getId() == product.getId()) {
                cartItemBean.setQuantity(cartItemBean.getQuantity() + 1);
                return;
            }
        }
        cartItemBeanList.add(new CartItemBean(product, 1));
    }

    // Xoá
    public void removeProduct(int productId) {
        cartItemBeanList.removeIf(cartItemBean -> cartItemBean.getProduct().getId() == productId);
    }

    // Sửa số luợng
    public void updateProduct(int productId, int quantity) {
        for (CartItemBean cartItemBean : cartItemBeanList) {
            if (cartItemBean.getProduct().getId() == productId) {
                if (quantity > 0) cartItemBean.setQuantity(quantity);
                else removeProduct(productId);
                return;
            }
        }
    }

    // Tổng Tiêền
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItemBean cartItemBean : cartItemBeanList) {
            totalPrice += cartItemBean.getSubtotal();
        }
        return totalPrice;
    }

    // Xoá rỗng
    public void clear() {
        cartItemBeanList.clear();
    }
}
