package com.RelationManyToMany.CustomerSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RelationManyToMany.CustomerSystem.dto.CustomerDTO;
import com.RelationManyToMany.CustomerSystem.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    //get all methode
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // get by id methode
    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getByIdCustomer(@PathVariable Long id) {
        try {
            CustomerDTO customer = customerService.getByIdCustomer(id);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    //add methode
    @PostMapping
    public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO dto) {
        try {
            CustomerDTO customerDTO = customerService.addNewCustomer(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    // update methode
    @PutMapping("{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, CustomerDTO dto) {
        try {
            CustomerDTO customerDTO = customerService.updateCustomer(id, dto);
            return ResponseEntity.ok("Customer updated successfully with id: " + customerDTO.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    //delete

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //add product to customer
    @PostMapping("/add-product/{customerId}/{productId}")
    public ResponseEntity<String> addProductToCustomer(@PathVariable Long customerId, @PathVariable Long productId) {
        try {
            customerService.addProductToCustomer(customerId, productId);
            return ResponseEntity.ok("Product added to customer successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //delete product to customer
    @PostMapping("/delete-product/{customerId}/{productId}")
    public ResponseEntity<String> deleteProductToCustomer(@PathVariable Long customerId, @PathVariable Long productId) {
        try {
            customerService.deleterProductToCustomer(customerId, productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted to customer successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
