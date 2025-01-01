package com.example.customer_service.services;


import com.example.customer_service.exceptions.CustomerEntityNotFoundException;
import com.example.customer_service.models.CustomerEntity;
import com.example.customer_service.models.dtos.CustomerDto;
import com.example.customer_service.models.dtos.CustomerDtoToCustomerMapper;
import com.example.customer_service.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoToCustomerMapper customerDtoToCustomerMapper;

    public CustomerEntity createCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerDtoToCustomerMapper.mapCustomerDtoToCustomer(customerDto);
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    public CustomerEntity getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerEntityNotFoundException::new);
    }

    public CustomerEntity updateCustomer(Long id, CustomerEntity customerEntity) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerEntityNotFoundException();
        }
        customerEntity.setCustomerId(id);
        return customerRepository.save(customerEntity);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerEntityNotFoundException();
        }
        customerRepository.deleteById(id);
    }

    public Page<CustomerEntity> listCustomers(Integer page, Integer pageLimit) {
        return customerRepository.findAll(PageRequest.of(page, pageLimit));
    }
}
