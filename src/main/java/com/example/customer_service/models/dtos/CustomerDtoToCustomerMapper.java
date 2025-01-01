package com.example.customer_service.models.dtos;

import com.example.customer_service.models.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoToCustomerMapper {
    CustomerEntity mapCustomerDtoToCustomer(CustomerDto customerDto);
}
