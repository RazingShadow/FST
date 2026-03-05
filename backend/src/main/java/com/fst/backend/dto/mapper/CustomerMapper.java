package com.fst.backend.dto.mapper;

import com.fst.backend.dto.request.CustomerRequest;
import com.fst.backend.dto.response.CustomerResponse;
import com.fst.backend.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerResponse toResponse(CustomerEntity customerEntity) {
        return new CustomerResponse(
                customerEntity.getId(),
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getEmail()
        );
    }

    public CustomerEntity toEntity(CustomerRequest customerRequest) {
        return new CustomerEntity(
                customerRequest.getFirstName(),
                customerRequest.getLastName(),
                customerRequest.getEmail()
        );
    }
}
