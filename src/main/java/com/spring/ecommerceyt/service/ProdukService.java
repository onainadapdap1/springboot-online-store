package com.spring.ecommerceyt.service;

import com.spring.ecommerceyt.entity.Produk;
import com.spring.ecommerceyt.exception.BadRequestException;
import com.spring.ecommerceyt.exception.ResourceNotFoundException;
import com.spring.ecommerceyt.repository.KategoriRepository;
import com.spring.ecommerceyt.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;
    @Autowired
    private KategoriRepository kategoriRepository;

    public List<Produk> findAll() {
        return produkRepository.findAll();
    }

    public Produk findById(String id) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan id " + id + " tidak ditemukan"));
        return produk;
    }

    public Produk create(Produk produk) {
        if (!StringUtils.hasText(produk.getNama())) {
            throw new BadRequestException("Nama produk tidak boleh kosong");
        }

        if (produk.getKategori() == null) {
            throw new BadRequestException("Kategori tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getKategori().getId())) {
            throw new BadRequestException("Kategori ID tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId())
                .orElseThrow(() -> new BadRequestException("Kategori ID " + produk.getKategori().getId() + " tidak ditemukan didatabase"));
        produk.setId(UUID.randomUUID().toString());

        Produk produkCreated = produkRepository.save(produk);
        return produkCreated;
    }

    public Produk edit(Produk produk) {
//        Produk produkDb = produkRepository.findById(produkId)
//                .orElseThrow(() -> new ResourceNotFoundException("Produk dengan id " + produkId + " tidak ditemukan"));
        if (!StringUtils.hasText(produk.getId())) {
            throw new BadRequestException("Produk ID harus diisi");
        }

        if (!StringUtils.hasText(produk.getNama())) {
            throw new BadRequestException("Nama produk tidak boleh kosong");
        }

        if (produk.getKategori() == null) {
            throw new BadRequestException("Kategori tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getKategori().getId())) {
            throw new BadRequestException("Kategori ID tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Kategori ID " + produk.getKategori().getId() + " tidak ditemukan dalam database"));


        return produkRepository.save(produk);
    }

    public Produk ubahGambar(String id, String gambar) {
        Produk produk = findById(id);
        produk.setGambar(gambar);
        return produkRepository.save(produk);
    }

    public void deleteById(String id) {
        produkRepository.deleteById(id);
    }
}
