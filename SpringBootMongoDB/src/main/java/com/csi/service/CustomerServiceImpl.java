package com.csi.service;

import com.csi.model.Customer;
import com.csi.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService
{

    @Autowired
    private ICustomerRepo custRepo;
    @Override
    public Customer signUp(Customer c) {
        return custRepo.save(c);
    }

    @Override
    public Boolean signIn(String mail, String pass) {
        boolean flag=false;
        Customer c1=custRepo.findByCustEmailIdAndCustPassword(mail,pass);
        if(c1!=null&&c1.getCustEmailId().equals(mail)&& c1.getCustPassword().equals(pass))
        {
            flag=true;
        }
        return flag ;
    }

    @Override
    public List<Customer> findByName(String name) {
        return custRepo.findByCustName(name);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return custRepo.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return custRepo.findAll();
    }

    @Override
    public List<Customer> saveAll(List<Customer> custList) {
        return custRepo.saveAll(custList);
    }

    @Override
    public Customer update(Customer c) {
        return custRepo.save(c);
    }

    @Override
    public void deleteById(int id) {
custRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        custRepo.deleteAll();

    }
}
