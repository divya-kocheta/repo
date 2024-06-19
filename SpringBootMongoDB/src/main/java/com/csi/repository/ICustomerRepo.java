package com.csi.repository;

import com.csi.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepo extends MongoRepository<Customer,Integer>

{

    Customer findByCustEmailIdAndCustPassword(String email, String pass);
    List<Customer> findByCustName(String name);
}
