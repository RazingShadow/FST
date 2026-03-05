package com.fst.backend.service;

import com.fst.backend.dto.request.CustomerRequest;
import com.fst.backend.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);
    CustomerResponse getCustomerById(Long id);
    void deleteCustomer(Long id);
    List<CustomerResponse> getAllCustomers();
}
