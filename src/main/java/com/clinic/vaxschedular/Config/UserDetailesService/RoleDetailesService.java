package com.clinic.vaxschedular.Config.UserDetailesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.Repository.RoleRepo;
import com.clinic.vaxschedular.Entity.Role;

@Service
public class RoleDetailesService implements UserDetailsService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Role role = roleRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + email));

        return User
                .withUsername(role.getEmail())
                .password(role.getPassword())
                .roles(role.getRole())
                .build();
    }
}
