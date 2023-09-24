package com.spring.ecommerceyt.repository;

import com.spring.ecommerceyt.entity.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, String> {
}
