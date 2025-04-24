package com.config.CustomerRestAPI.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.CustomerRestAPI.dto.CustomerDto;
import com.config.CustomerRestAPI.model.Customer;
import com.config.CustomerRestAPI.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService cs) {
        service = cs;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok(service.addCustomer(dto));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }
    @GetMapping("/{musteriNo}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String musteriNo) {
        return ResponseEntity.ok(service.getCustomerById(musteriNo));
    }
    @PutMapping("/{musteriNo}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String musteriNo,
            @Valid @RequestBody CustomerDto dto) {
        return ResponseEntity.ok(service.updateCustomer(musteriNo, dto));
    }
    @DeleteMapping("/{musteriNo}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String musteriNo){
        service.deleteCustomer(musteriNo);
        return ResponseEntity.noContent().build();
    }
    
}
