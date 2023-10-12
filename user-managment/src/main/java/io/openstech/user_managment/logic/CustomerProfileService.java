package io.openstech.user_managment.logic;

import io.openstech.user_managment.communication.dto.CustomerDTO;
import io.openstech.user_managment.communication.dto.CustomerProfileDTO;
import io.openstech.user_managment.exception.CustomerNotFoundException;
import io.openstech.user_managment.persistence.domian.Customer;
import io.openstech.user_managment.persistence.domian.CustomerProfile;
import io.openstech.user_managment.persistence.repo.CustomerProfileRepository;
import io.openstech.user_managment.persistence.repo.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


@Service
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    public CustomerProfileService(CustomerProfileRepository customerProfileRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerProfileRepository = customerProfileRepository;

        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    public CustomerProfileDTO initializeProfile(String username, CustomerProfileDTO customerProfileDTO) {

        Customer foundCustomer = customerRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));


        modelMapper.map(customerProfileDTO, foundCustomer);

        customerRepository.save(foundCustomer);
        return customerProfileDTO;
    }


    public void modifyProfile(String username, CustomerProfileDTO customerProfileDTO) {
        Customer foundCustomer = customerRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));

        modelMapper.map(customerProfileDTO, foundCustomer);

        customerRepository.save(foundCustomer);
    }


}
