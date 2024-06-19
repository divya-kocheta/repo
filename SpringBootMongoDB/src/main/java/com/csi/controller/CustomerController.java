package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    @Autowired
    ICustomerService custService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer c)
    {
        log.info("###trying to sign up for:"+c.getCustName());
        return new ResponseEntity<>(custService.signUp(c),HttpStatus.CREATED);
    }
    @GetMapping("/signin/{mail}/{pass}")
    public ResponseEntity<Boolean> signIn(@PathVariable String mail,@PathVariable String pass)
    {
        return ResponseEntity.ok(custService.signIn(mail,pass));
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable int id)
    {
        return new ResponseEntity<>(custService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll()
    {
        return ResponseEntity.ok(custService.findAll());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateData(@PathVariable int id,@RequestBody Customer c)
    {
        Customer c1=custService.findById(id).orElseThrow(()->new RecordNotFoundException("Customer id does not exist"));
        c1.setCustName(c.getCustName());
        c1.setCustAddress(c.getCustAddress());
        c1.setCustContactNumber(c.getCustContactNumber());
        return new ResponseEntity<>(custService.update(c1),HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)
    {
        custService.deleteById(id);
        return ResponseEntity.ok("data deleted successfully");
    }
    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll()
    {
        custService.deleteAll();
        return ResponseEntity.ok("All data deleted successfully");
    }

    @GetMapping("/findbyname/{name}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable String name)
    {
        return ResponseEntity.ok(custService.findByName(name));
    }

    @GetMapping("/findbymailid/{mail}")
    public ResponseEntity<Customer> findByEmailId(@PathVariable String mail)
    {
        return ResponseEntity.ok(custService.findAll()
                        .stream()
                        .filter(c->c.getCustEmailId().equals(mail))
                        .toList().get(0));


    }
    @GetMapping("/findbycontactno/{contactno}")
    public ResponseEntity<Customer> findByEmailId(@PathVariable Long contactno)
    {
        return ResponseEntity.ok(custService.findAll()
                .stream()
                .filter(c->c.getCustContactNumber()==(contactno))
                .toList().get(0));


    }
    @GetMapping("/findbydob/{dob}")
    public ResponseEntity<Customer> findByDOB(@PathVariable String dob)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(custService.findAll()
                .stream()
                .filter(c->sdf.format(c.getCustDOB()).equals(dob))
                .toList().get(0));
    }

    @GetMapping("/findbyanyinput/{any}")
    public ResponseEntity<List<Customer>> findByAnyInput(@PathVariable String any)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(custService.findAll()
                .stream()
                .filter(c->c.getCustEmailId().equals(any)
                ||c.getCustName().equals(any)
                        ||c.getCustPassword().equals(any)
                        ||c.getCustAddress().equals(any)
                        ||String.valueOf(c.getCustContactNumber()).equals(any)
                        ||String.valueOf(c.getCustId()).equals(any)
                        ||sdf.format(c.getCustDOB()).equals(any))

                .toList());
    }
    @GetMapping("/sortbyid")
    public ResponseEntity<List<Customer>> sortById()
    {
        return ResponseEntity.ok(custService.findAll()
                .stream()
                .sorted(Comparator.comparing(Customer::getCustId)).toList());
    }
    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByName()
    {
        return ResponseEntity.ok(custService.findAll()
                .stream()
                .sorted(Comparator.comparing(Customer::getCustName)).toList());
    }


    @GetMapping("/sortbydob")
    public ResponseEntity<List<Customer>> sortByDOB()
    {
        return ResponseEntity.ok(custService.findAll()
                .stream()
                .sorted(Comparator.comparing(Customer::getCustDOB)).toList());
    }
   @PostMapping("/saveall")
   public ResponseEntity<List<Customer>> saveAll(@RequestBody List<Customer> cList)
   {
       return ResponseEntity.ok(custService.saveAll(cList));
   }



}
