package com.RelationManyToMany.CustomerSystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RelationManyToMany.CustomerSystem.dto.CustomerDTO;
import com.RelationManyToMany.CustomerSystem.entity.Customer;
import com.RelationManyToMany.CustomerSystem.entity.Product;
import com.RelationManyToMany.CustomerSystem.repository.CustomerRepository;
import com.RelationManyToMany.CustomerSystem.repository.ProductRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    // add method
    public CustomerDTO addNewCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setAge(customerDTO.getAge());
        customer.setMarried(customerDTO.getMarried());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setProducts(new HashSet<>());
        

        Customer created = customerRepository.save(customer);

        return new CustomerDTO(
                created.getId(),
                created.getName(),
                created.getSurname(),
                created.getAge(),
                created.getMarried(),
                created.getEmail(), created.getPhoneNumber(),
                created.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));
    }
    //getall
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getAge(),
                customer.getMarried(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getProducts().stream().map(Product::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
    
    //get by id customer
    public CustomerDTO getByIdCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id));

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                customer.getAge(),
                customer.getMarried(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));
    }
    
    //update customer
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id));
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setAge(customerDTO.getAge());
        customer.setMarried(customerDTO.getMarried());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        Customer updated = customerRepository.save(customer);
        return new CustomerDTO(
                updated.getId(),
                updated.getName(),
                updated.getSurname(),
                updated.getAge(),
                updated.getMarried(),
                updated.getEmail(),
                updated.getPhoneNumber(),
                updated.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));

    }

    //delete Customer

    public void deleteCustomer(Long id) {
        Customer existedCustomer = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + id));

        for (Product product : existedCustomer.getProducts()) {
            product.getCustomers().remove(existedCustomer);
        }

        customerRepository.delete(existedCustomer);
    }
    
    // add a product to customer data

    public void addProductToCustomer(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + customerId));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + customerId));

        customer.getProducts().add(product);
        product.getCustomers().add(customer);

        customerRepository.save(customer);
        productRepository.save(product);
    }
    
    public void deleterProductToCustomer(Long customerId, Long productId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(
            () -> new RuntimeException("Customer not found with id: " + customerId));
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Customer not found with id: " + customerId));

        customer.getProducts().remove(product);
        product.getCustomers().remove(customer);

        customerRepository.save(customer);
        productRepository.save(product);
    }
}
