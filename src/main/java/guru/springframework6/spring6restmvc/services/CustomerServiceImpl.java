package guru.springframework6.spring6restmvc.services;

import guru.springframework6.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {

        this.customerMap = new HashMap<>();

        Customer customer01 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Lukasz Stusio")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer customer02 = Customer.builder()
                        .id(UUID.randomUUID())
                        .customerName("Teresa Kubiak")
                        .version(1)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();

        Customer customer03 = Customer.builder()
                                .id(UUID.randomUUID())
                                .customerName("Krzysztof Czechowski")
                                .version(1)
                                .createdDate(LocalDateTime.now())
                                .updateDate(LocalDateTime.now())
                                .build();

        customerMap.put(customer01.getId(), customer01);
        customerMap.put(customer02.getId(), customer02);
        customerMap.put(customer03.getId(), customer03);

    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {

        Customer originalCustomer = customerMap.get(customerId);

        originalCustomer.setCustomerName(customer.getCustomerName());
        originalCustomer.setUpdateDate(LocalDateTime.now());

        customerMap.put(originalCustomer.getId(), originalCustomer);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomersByID(UUID id) {

        log.debug("Get Customer by Id - in service. Id: " + id.toString());
        return customerMap.get(id);

    }

    @Override
    public Customer saveNewCustomer(Customer customer) {

        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }
}
