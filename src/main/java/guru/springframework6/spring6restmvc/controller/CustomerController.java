package guru.springframework6.spring6restmvc.controller;

import guru.springframework6.spring6restmvc.model.Beer;
import guru.springframework6.spring6restmvc.model.Customer;
import guru.springframework6.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteById(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{customerId}")
    public ResponseEntity updateById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handleCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {

        return customerService.listCustomers();

    }

    @RequestMapping("{customerId}")
    public Customer getCustomersByID(@PathVariable("customerId") UUID customerId) {

        log.debug("Get Customer by Id - in controller");

        return customerService.getCustomersByID(customerId);
    }
}
