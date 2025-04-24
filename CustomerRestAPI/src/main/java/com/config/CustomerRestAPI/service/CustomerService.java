package com.config.CustomerRestAPI.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import com.config.CustomerRestAPI.model.Customer; //for Customer
import java.util.*; //for Map
import com.config.CustomerRestAPI.dto.CustomerDto; //for CustomerDTO

@Service
public class CustomerService {

    private final Map<String, Customer> customerMap = new HashMap(); //it is working without database the work method in-memory

    public Customer addCustomer(CustomerDto dto) {
        Customer customer = new Customer(
                dto.getAdi(),
                dto.getSoyadi(),
                dto.getMusteriNo(),
                dto.getYas(),
                dto.getMedeniDurum()
        );

        customerMap.put(customer.getMusteriNo(), customer); //defined for unique value(musteriNo*)
        return customer;
    }
    
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    public Customer getCustomerById(String musteriNo) {
        Customer customer = customerMap.get(musteriNo);
        if (customer == null) {
            throw new Error("Müşteri bulunamadı"); //CustomerNotfound
        }
        return customer;
    }

    public Customer updateCustomer(String musteriNo, CustomerDto dto) {
        Customer existingCustomer = getCustomerById(musteriNo);
        existingCustomer.setAdi(dto.getAdi());
        existingCustomer.setSoyadi(dto.getSoyadi());
        existingCustomer.setYas(dto.getYas());
        existingCustomer.setMedeniDurum(dto.getMedeniDurum());
        return existingCustomer;
    }

    public void deleteCustomer(String musteriNo) {
        if (!customerMap.containsKey(musteriNo)) {
            throw new Error("Silinicek müşteri bulunamadı"); //CustomerNotfound
        }
        customerMap.remove(musteriNo);
    }
}
