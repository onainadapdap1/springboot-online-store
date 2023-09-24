package com.spring.ecommerceyt.service;

import com.spring.ecommerceyt.entity.Kategori;
import com.spring.ecommerceyt.exception.ResourceNotFoundException;
import com.spring.ecommerceyt.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KategoriService {
    @Autowired
    private KategoriRepository kategoriRepository;

    public Kategori findById(String id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori dengan id " + id + " tidak ditemukan"));
    }

    public List<Kategori> findAll() {
        return kategoriRepository.findAll();
    }

    public Kategori create(Kategori kategori) {
        kategori.setId(UUID.randomUUID().toString());
        return kategoriRepository.save(kategori);
    }

    public Kategori edit(Kategori kategoriRequest, String kategoriId) {
        Kategori kategori = kategoriRepository.findById(kategoriId)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori dengan id " + kategoriId + " tidak ditemukan"));
        kategori.setName(kategoriRequest.getName());

        Kategori updatedKategori = kategoriRepository.save(kategori);

        return updatedKategori;
    }

    public void deleteById(String id) {
        Kategori kategoriDb = kategoriRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Kategori id : " + id + " tidak ditemukan"));

        kategoriRepository.deleteById(kategoriDb.getId());
    }
}