package com.rojin.biju.wisdompet.data.repositories;

import com.rojin.biju.wisdompet.data.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
    

}
