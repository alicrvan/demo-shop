package io.openstech.user_managment.communication.endpoint;

import io.openstech.user_managment.communication.dto.CustomerDTO;
import io.openstech.user_managment.communication.dto.CustomerProfileDTO;
import io.openstech.user_managment.logic.CustomerProfileService;
import io.openstech.user_managment.persistence.domian.Customer;
import io.openstech.user_managment.persistence.domian.CustomerProfile;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerProfileEndpoint {
private final CustomerProfileService customerProfileService;

    public CustomerProfileEndpoint(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @PostMapping("{username}/create-profile")
    public CustomerProfileDTO createProfile(@PathVariable String username, @RequestBody CustomerProfileDTO customerProfileDTO) {
        return customerProfileService.initializeProfile(username,customerProfileDTO);
    }

    @PutMapping("/{username}/update-profile")
    public void updateProfile(@PathVariable String username, @RequestBody CustomerProfileDTO customerProfileDTO) {
         customerProfileService.modifyProfile(username, customerProfileDTO);
    }
}
