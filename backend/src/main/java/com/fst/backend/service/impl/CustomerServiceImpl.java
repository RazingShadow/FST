package com.fst.backend.service.impl;

import com.fst.backend.dto.exception.CustomerNotFoundException;
import com.fst.backend.dto.mapper.CustomerMapper;
import com.fst.backend.dto.request.CustomerRequest;
import com.fst.backend.dto.response.CustomerResponse;
import com.fst.backend.persistence.entity.CustomerEntity;
import com.fst.backend.persistence.repository.CustomerRepository;
import com.fst.backend.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customer = customerMapper.toEntity(customerRequest);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {

        CustomerEntity existing = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        applyUpdates(existing, customerRequest);

        return customerMapper.toResponse(customerRepository.save(existing));
    }

    private void applyUpdates(CustomerEntity customerEntity, CustomerRequest customerRequest) {
        Optional.ofNullable(customerRequest.getFirstName())
                .ifPresent(customerEntity::setFirstName);

        Optional.ofNullable(customerRequest.getLastName())
                .ifPresent(customerEntity::setLastName);

        Optional.ofNullable(customerRequest.getEmail())
                .ifPresent(customerEntity::setEmail);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }
}
