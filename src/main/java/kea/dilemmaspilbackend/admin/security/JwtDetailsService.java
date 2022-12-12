package kea.dilemmaspilbackend.admin.security;

import kea.dilemmaspilbackend.admin.model.AdminUser;
import kea.dilemmaspilbackend.admin.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtDetailsService implements UserDetailsService {
    @Autowired
    AdminUserRepository adminUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminUser> adminUserDB = adminUserRepository.findAdminUserByUsername(username);
        if (adminUserDB.isEmpty())
            throw new UsernameNotFoundException("Username could not be found");
        return new User(adminUserDB.get().getUsername(), adminUserDB.get().getPassword(), new ArrayList<>());
    }
}