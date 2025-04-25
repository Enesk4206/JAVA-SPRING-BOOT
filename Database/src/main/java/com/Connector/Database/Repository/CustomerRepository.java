package com.Connector.Database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Connector.Database.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
