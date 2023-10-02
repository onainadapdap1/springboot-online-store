package com.spring.ecommerceyt.security.service;

import com.spring.ecommerceyt.entity.Pengguna;
import com.spring.ecommerceyt.exception.ResourceNotFoundException;
import com.spring.ecommerceyt.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PenggunaRepository penggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pengguna pengguna = penggunaRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username " + username + " tidak ditemukan"));
        return UserDetailsImpl.build(pengguna);
    }
}
