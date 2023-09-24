package com.spring.ecommerceyt.repository;

import com.spring.ecommerceyt.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<Pengguna, String> {
    Boolean existsByEmail(String email);
}
