package com.example.shoppingcartapplication.service;

import com.example.shoppingcartapplication.model.CartItem;
import com.example.shoppingcartapplication.model.Customer;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findCustomer(long id);
    List<Customer> findAllCustomers();

    double computeCustomerItems(Set<CartItem> cartItems);
}
