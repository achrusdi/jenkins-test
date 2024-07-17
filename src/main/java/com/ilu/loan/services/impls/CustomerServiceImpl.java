package com.ilu.loan.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ilu.loan.entities.Customer;
import com.ilu.loan.repositories.CustomerRepository;
import com.ilu.loan.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer request) {
        return customerRepository.save(request);
    }

    @Override
    public boolean delete(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return false;
        }
        customer.setStatus("0");
        customerRepository.save(customer);
        return true;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAllActiveCustomers();
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer request) {
        return customerRepository.save(request);
    }

    
}