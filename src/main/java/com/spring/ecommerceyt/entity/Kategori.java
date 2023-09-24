package com.spring.ecommerceyt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Kategori implements Serializable {
    @Id
    private String id;
    private String name;
}
