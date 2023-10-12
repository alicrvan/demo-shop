package io.openstech.user_managment.logic;

import io.openstech.user_managment.communication.dto.CustomerDTO;
import io.openstech.user_managment.exception.CustomerAlreadyExistException;
import io.openstech.user_managment.exception.CustomerNotFoundException;
import io.openstech.user_managment.persistence.domian.Customer;
import io.openstech.user_managment.persistence.repo.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<CustomerDTO> getUser(String username) {
        Optional<Customer> customer = customerRepository.findByUsername(username);

        if (customer.isEmpty()) {
            return Optional.empty();
        } else {
            CustomerDTO customerDTO = new CustomerDTO();

            modelMapper.map(customer.get(), customerDTO);

            return Optional.of(customerDTO);
        }
    }


    public CustomerDTO addUser(CustomerDTO customerDTO) {

        customerRepository.findByUsername(customerDTO.getUsername()).ifPresent(customer -> {
            throw new CustomerAlreadyExistException();
        });

        Customer customer = new Customer();
        modelMapper.map(customerDTO, customer);
        customerRepository.save(customer);
        return customerDTO;
    }


    public void updateUser(String username, CustomerDTO updatedCustomerDTO) {

        Customer foundCustomer = customerRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));

        customerRepository.findByUsername(updatedCustomerDTO.getUsername()).ifPresent(customer -> {
            throw new CustomerAlreadyExistException();
        });

        modelMapper.map(updatedCustomerDTO, foundCustomer);
        customerRepository.save(foundCustomer);
    }


    @Transactional
    public void removeUser(String username) {

        customerRepository.deleteByUsername(username);

    }

}
