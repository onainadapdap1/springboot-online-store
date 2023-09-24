package com.spring.ecommerceyt.repository;

import com.spring.ecommerceyt.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, String> {

}
