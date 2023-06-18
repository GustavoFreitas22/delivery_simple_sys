package br.com.freitas.dev.delivery_sys.service;

import br.com.freitas.dev.delivery_sys.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService implements UserDetailsService {

    @Autowired
    private UserRepositoryImpl repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserByLogin(username);
    }

    public String criptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordEncoded = encoder.encode(password);

        return passwordEncoded;
    }
}
