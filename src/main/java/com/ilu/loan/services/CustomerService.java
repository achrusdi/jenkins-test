package com.ilu.loan.services;

import java.util.List;

import com.ilu.loan.entities.Customer;

public interface CustomerService {
    Customer getById(String id);

    List<Customer> getAll();

    Customer create(Customer request);

    Customer update(Customer request);

    boolean delete(String id);
}