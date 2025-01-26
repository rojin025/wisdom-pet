package com.rojin.biju.wisdompet.data.repositories;

import com.rojin.biju.wisdompet.data.entities.CustormerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustormerEntity, Long> {

    CustormerEntity findByEmail(String email);
    

}
