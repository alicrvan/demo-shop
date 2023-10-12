package io.openstech.user_managment.security;

import io.openstech.user_managment.persistence.domian.Customer;
import io.openstech.user_managment.persistence.repo.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider autProvider =new DaoAuthenticationProvider();
        autProvider.setUserDetailsService(userDetailsService);
        autProvider.setPasswordEncoder(passwordEncoder());
        return  autProvider;
    }


    @Bean
    UserDetailsService userDetailsService(CustomerRepository customerRepository){
        return username -> customerRepository.findByUsername(username)
                .map(UserPrinciple::new)
                .orElseThrow(() ->new UsernameNotFoundException(username));
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
