package com.example.shoppingcartapplication.controller;

import com.example.shoppingcartapplication.model.CartItem;
import com.example.shoppingcartapplication.model.Customer;
import com.example.shoppingcartapplication.service.CartItemService;
import com.example.shoppingcartapplication.service.CustomerService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class CustomerController {

    private CustomerService customerService;
    private CartItemService cartItemService;

    @Autowired
    public CustomerController(CustomerService customerService, CartItemService cartItemService) {
        this.customerService = customerService;
        this.cartItemService = cartItemService;
    }

    //index page upon loading the application
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String inside(ModelMap model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer";
    }

//REST API: POST - add customer
    @PostMapping("/add")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    //WEB Save customer during registration
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String saveCustomer(@Valid Customer customer, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            System.out.println("errors");
            return "index";
        }

        Customer c = customerService.saveCustomer(customer);
        return "redirect:/viewproducts/" + c.getCustomerId();
    }

    //WEB List all available items and customer's basket in one page
    @RequestMapping(value = "/viewproducts/{customerId}")
    public ModelAndView getAllProducts(@PathVariable long customerId){

        Customer c = customerService.findCustomer(customerId);
        ModelAndView modelAndView = new ModelAndView("viewproducts");

        List<CartItem> list = cartItemService.listAllItems();
        modelAndView.addObject("customerId", customerId);
        modelAndView.addObject("list", list);
        modelAndView.addObject("myList", c.getCartItems());
        return modelAndView;
    }

    //WEB
    @RequestMapping(value = "/viewcustomers")
    public ModelAndView getAll(){
        List<Customer> list = customerService.findAllCustomers();
        return new ModelAndView("viewcustomers", "list", list);
    }

    //REST API: GET - list pf items in customer's basket
    @GetMapping("/items/{customerId}")
    public Set<CartItem> getCartItems(@PathVariable long customerId){
        Customer c = customerService.findCustomer(customerId);
        return cartItemService.getCartItems(c);
    }

    @GetMapping("/items/{customerId}/total")
    public double getTotal(@PathVariable long customerId){
        Set<CartItem> items = getCartItems(customerId);
        double total = customerService.computeCustomerItems(items);


    }

}
