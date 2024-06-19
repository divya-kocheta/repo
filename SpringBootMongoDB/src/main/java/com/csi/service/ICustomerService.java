package com.csi.service;

import com.csi.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Customer signUp(Customer c);
    Boolean signIn(String mail, String pass);
    List<Customer> findByName(String name);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    List<Customer> saveAll(List<Customer> custList);
    Customer update(Customer c);
    void deleteById(int id);
    void deleteAll();

}
