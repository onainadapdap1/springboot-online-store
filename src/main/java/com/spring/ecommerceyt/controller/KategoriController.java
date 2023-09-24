package com.spring.ecommerceyt.controller;

import com.spring.ecommerceyt.entity.Kategori;
import com.spring.ecommerceyt.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KategoriController {
    @Autowired
    private KategoriService kategoriService;

    @GetMapping("/kategoris")
    public List<Kategori> findAll() {
        return kategoriService.findAll();
    }

    @GetMapping("/kategoris/{id}")
    public Kategori findById(@PathVariable("id") String id) {
        return kategoriService.findById(id);
    }

    @PostMapping("/kategoris")
    public Kategori create(@RequestBody Kategori kategori) {
        return kategoriService.create(kategori);
    }

    @PutMapping("/kategoris/{id}")
    public Kategori edit(@RequestBody Kategori kategori, @PathVariable("id") String id) {
        return kategoriService.edit(kategori, id);
    }

    @DeleteMapping("/kategoris/{id}")
    public String delete(@PathVariable("id") String id) {
        kategoriService.deleteById(id);

        return "successfully deleted";
    }
}
