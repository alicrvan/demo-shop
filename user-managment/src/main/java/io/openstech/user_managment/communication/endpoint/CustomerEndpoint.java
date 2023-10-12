package io.openstech.user_managment.communication.endpoint;

import io.openstech.user_managment.communication.dto.CustomerDTO;
import io.openstech.user_managment.logic.CustomerService;
import io.openstech.user_managment.persistence.domian.Customer;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerEndpoint {


    private final CustomerService customerService;

    public CustomerEndpoint(@RequestBody CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{username}/account")
    public CustomerDTO getUser(@PathVariable String username) {
        return customerService.getUser(username).orElse(null);
    }


    @PostMapping("/register")
    public CustomerDTO addUser(@RequestBody CustomerDTO customerDTO) {
        return customerService.addUser(customerDTO);
    }





    @PutMapping("/{username}/update-account")
    public void updateUser(@PathVariable String username, @RequestBody CustomerDTO updateCustomerDTO) {
         customerService.updateUser(username,updateCustomerDTO);
    }


    @DeleteMapping("/{username}/delete-account")
    public void deleteUser(@PathVariable String username) {
         customerService.removeUser(username);
    }



}
