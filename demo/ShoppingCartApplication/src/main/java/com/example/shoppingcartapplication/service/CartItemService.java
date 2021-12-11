package com.example.shoppingcartapplication.service;

import com.example.shoppingcartapplication.model.CartItem;
import com.example.shoppingcartapplication.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface CartItemService {
    Set<CartItem> getCartItems(Customer customer);
    List<CartItem> listAllItems();

    CartItem saveInventoryItem(CartItem item);
    CartItem getCartItem(long CartItemId);
}
