package com.example.shoppingcartapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="customer_item", joinColumns = @JoinColumn(name = "cart_item_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private Set<Customer> customers;

    @Column
    private int quantity;

    public CartItem() {

    }

    public CartItem(long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public Set<Customer> getCustomers(){
        return customers;
    }
    public void setCustomers(Set<Customer> customers){
        this.customers = customers;
    }
    private int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}