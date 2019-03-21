package com.monami.secondroundassignment.dao;

import com.monami.secondroundassignment.modal.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
