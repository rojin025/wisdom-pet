package com.rojin.biju.wisdompet.services;

import org.springframework.stereotype.Service;

import com.rojin.biju.wisdompet.data.entities.CustormerEntity;
import com.rojin.biju.wisdompet.data.repositories.CustomerRepository;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    // private CustormerEntity translateWebToDb(Customer customer)
}
